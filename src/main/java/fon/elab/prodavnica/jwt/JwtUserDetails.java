package fon.elab.prodavnica.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUserDetails implements UserDetails {

  private static final long serialVersionUID = 5155720064139820502L;

  private final Integer id;
  private final String username;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;
  private final Integer cartId;
  private final String name;
  public JwtUserDetails(Integer id, String username, String password, String role, Integer cartId, String name) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.cartId = cartId;
    this.name = name;
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(role));

    this.authorities = authorities;
  }


  public Integer getId() {
    return id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
  public String getName() {
	return name;
  }

  public Integer getCartId() {
	return cartId;
  }


@Override
public String toString() {
	return "JwtUserDetails [id=" + id + ", username=" + username + ", password=" + password + ", authorities="
			+ authorities + ", cartId=" + cartId + ", name=" + name + "]";
}

  
}


