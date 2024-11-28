package com.user.model;

public class Cartitem {

	private Long cartItemId;

	private Integer quantity;

	public Cartitem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cartitem(Long cartItemId, Integer quantity) {
		super();
		this.cartItemId = cartItemId;
		this.quantity = quantity;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cartitem [cartItemId=" + cartItemId + ", quantity=" + quantity + "]";
	}

}
