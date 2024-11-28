package com.user.model;

public class farmer {
	
	 private Long farmerId;

	    private String name;
	    private String contactInfo;
	    
		public farmer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public farmer(Long farmerId, String name, String contactInfo) {
			super();
			this.farmerId = farmerId;
			this.name = name;
			this.contactInfo = contactInfo;
		}

		public Long getFarmerId() {
			return farmerId;
		}

		public void setFarmerId(Long farmerId) {
			this.farmerId = farmerId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContactInfo() {
			return contactInfo;
		}

		public void setContactInfo(String contactInfo) {
			this.contactInfo = contactInfo;
		}

		@Override
		public String toString() {
			return "farmer [farmerId=" + farmerId + ", name=" + name + ", contactInfo=" + contactInfo + "]";
		}
	    
	    

}
