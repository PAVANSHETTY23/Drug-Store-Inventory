package Model;
 
	public enum expirationStatus {
		INSTANCE;
		private String value;
		private expirationStatus(String value) {
			this.value = value;
		}
		expirationStatus() {}
		public String getValue() {
	        return value;
	    }
	  
	    public void setValue(String value) {
	        this.value = value;
	    }
	}
