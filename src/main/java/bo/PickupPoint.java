package bo;

public class PickupPoint {
	
	private int no_item;
	private String streetAddress;
	private String postalCode;
	private String cityAddress;
	
	
	public PickupPoint() {
		
	}
	
	public PickupPoint(int no_item, String streetAddress, String postalCode, String cityAddress) {
		super();
		this.no_item = no_item;
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.cityAddress = cityAddress;
	}
	
	public int getNo_item() {
		return no_item;
	}
	public void setNo_item(int no_item) {
		this.no_item = no_item;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCityAddress() {
		return cityAddress;
	}
	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}
	
}	
