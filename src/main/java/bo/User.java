package bo;

public class User {

	private int no_user;
	private String pseudo;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private String streetAddress;
	private int postalCodeAddress;
	private String cityAddress;
	private String password;
	private int credit;
	private boolean admin;
	
	public User() {
		
	}
	
	public User(int no_user, String pseudo, String lastName, String firstName, String email, String phoneNumber,
			String streetAddress, int postalCodeAddress, String cityAddress, String password, int credit,
			boolean admin) {
		super();
		this.no_user = no_user;
		this.pseudo = pseudo;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.postalCodeAddress = postalCodeAddress;
		this.cityAddress = cityAddress;
		this.password = password;
		this.credit = credit;
		this.admin = admin;
	}

	public int getNo_user() {
		return no_user;
	}

	public void setNo_user(int no_user) {
		this.no_user = no_user;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public int getPostalCodeAddress() {
		return postalCodeAddress;
	}

	public void setPostalCodeAddress(int postalCodeAddress) {
		this.postalCodeAddress = postalCodeAddress;
	}

	public String getCityAddress() {
		return cityAddress;
	}

	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	
}
