package com.user.model;

public class Cart {

	private Long cartId;

	private Double totalPrice;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Long cartId, Double totalPrice) {
		super();
		this.cartId = cartId;
		this.totalPrice = totalPrice;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", totalPrice=" + totalPrice + "]";
	}

}
