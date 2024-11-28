package com.user.model;

public class Order {

	private Long orderId;
	private Double totalPrice;
	private int orderDate;
	private String deliveryStatus;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long orderId, Double totalPrice, int orderDate, String deliveryStatus) {
		super();
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.deliveryStatus = deliveryStatus;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(int orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate
				+ ", deliveryStatus=" + deliveryStatus + "]";
	}

}
