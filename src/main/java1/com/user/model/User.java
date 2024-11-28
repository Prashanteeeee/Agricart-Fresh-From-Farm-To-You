package com.user.model;

public class User {
	
	 private Long userId;

	    private String name;
	    private String email;
	    private String password;
	    private String address;
	    private String phoneNo;
	    
		public User(int id, String name2, String email2, String address2, String phone, String password2) {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public User(Long userId, String name, String email, String password, String address, String phoneNo) {
			super();
			this.userId = userId;
			this.name = name;
			this.email = email;
			this.password = password;
			this.address = address;
			this.phoneNo = phoneNo;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", address=" + address + ", phoneNo=" + phoneNo + "]";
		}
	    
	    

}
