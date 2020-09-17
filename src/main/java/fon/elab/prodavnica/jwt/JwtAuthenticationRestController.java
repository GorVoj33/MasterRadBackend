package fon.elab.prodavnica.jwt;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fon.elab.prodavnica.dao.KorpaService;
import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.domain.StavkaKorpe;
import fon.elab.prodavnica.rep.KorpaRepository;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class JwtAuthenticationRestController {

  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService jwtUserDetailsService;
  
  @Autowired
  KorpaService korpaService;

  @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
      throws AuthenticationException {
	//String pass = new BCryptPasswordEncoder().encode(rawPassword) 
	  System.out.println("SAD IDE AUTENT: "+authenticationRequest.getUsername() +" ----- "+ authenticationRequest.getPassword());
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    final JwtUserDetails userDetails = (JwtUserDetails) jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    System.out.println("UserDetails: "+userDetails);
    
    String role = userDetails.getAuthorities().toString();
//    if(role.contains("admin") || role.contains("ADMIN")) role = "admin";
//    else role="user";
    System.out.println("ULOGA KORISNIKA JE : "+role);
    
    final String token = jwtTokenUtil.generateToken(userDetails);
    int brojStavki = 0;
    Korpa k = null;
	try {
		if(!role.equals("[admin]")) {
			k = korpaService.vratiPoID(userDetails.getCartId());
			brojStavki = k.getBrojStavki();	
			Integer[] selektovaniArtikli = new Integer[brojStavki];
			for(int i=0; i<brojStavki; i++) {
				selektovaniArtikli[i] = k.getStavke().get(i).getArtikal().getId();
			}
			JwtTokenResponse responseToken = new JwtTokenResponse(token,role,userDetails.getId(), userDetails.getCartId(), brojStavki, selektovaniArtikli);
			return ResponseEntity.ok(responseToken);
			
		}else {
			JwtTokenResponse responseToken = new JwtTokenResponse(token,role,userDetails.getId(), null, null, null);
			return ResponseEntity.ok(responseToken);
		}
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//System.out.println("Response token: "+responseToken);
    return ResponseEntity.badRequest().body(null);
  }

  @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUserDetails user = (JwtUserDetails) jwtUserDetailsService.loadUserByUsername(username);
    String role = user.getAuthorities().toString();
//    if(role.contains("admin") || role.contains("ADMIN")) role = "admin";
//    else role="user";
    System.out.println("ULOGA KORISNIKA JE : "+role);
    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      int brojStavki = 0;
      Korpa k;
      Integer[] selektovaniArtikli = new Integer[50];
  	try {
  		k = korpaService.vratiPoID(user.getCartId());
  		brojStavki = k.getBrojStavki();
  		
		for(int i=0; i<brojStavki; i++) {
			selektovaniArtikli[i] = k.getStavke().get(i).getArtikal().getId();
		}
		JwtTokenResponse responseToken = new JwtTokenResponse(token,role,user.getId(), user.getCartId(), brojStavki, selektovaniArtikli);
		return ResponseEntity.ok(responseToken);
  	} catch (Exception e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken,role, user.getId(), user.getCartId(), brojStavki, selektovaniArtikli));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
  }
}

