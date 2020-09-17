package fon.elab.prodavnica.jwt;

import java.io.Serializable;

import org.springframework.security.core.userdetails.UserDetails;
// also changed
public class JwtTokenResponse implements Serializable {

  private static final long serialVersionUID = 8317676219297719109L;
  private final String role;
  private final String token;
  private final Integer userId;
  private final Integer cartId;
  private final Integer cartItems;
  private final Integer[] selectedArticles;
  //private final Long id;
  //private final UserDetails userDetails;
   public JwtTokenResponse(String token, String role,Integer userId, Integer cartId, Integer cartItems, Integer[] selectedArticles) {
  
        this.token = token;
        this.role=role;
        this.userId = userId;
        this.cartId = cartId;
        this.cartItems = cartItems;
        this.selectedArticles = selectedArticles;
        //this.id=id;
        //this.userDetails = ud;
    }

    public String getToken() {
        return this.token;
    }
    
    public String getRole() {
		return role;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public Integer getCartItems() {
		return cartItems;
	}

	public Integer[] getSelectedArticles() {
		return selectedArticles;
	}

	@Override
	public String toString() {
		return "JwtTokenResponse [role=" + role + ", token=" + token + ", userId=" + userId + ", cartId=" + cartId
				+ ", cartItems=" + cartItems + "]";
	}
    
}