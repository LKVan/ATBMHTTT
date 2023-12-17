package model;

public class User {
	private int id;
	private String fullName;
	private String numberPhone;
	private String address;
	private String userName;
	private String password;
	private int rolId;
	private String email;
	private int status;
	private String publicKey;
	private String signatureData;

	public User(String fullName, String numberPhone, String address, String userName, String password, int rolId) {
		super();
		this.fullName = fullName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.rolId = rolId;
	}



	public User( String fullName, String numberPhone, String address, String userName, String password,
			int rolId, String email) {
		super();
		this.fullName = fullName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.rolId = rolId;
		this.email = email;
	}



	public User(int id, String fullName, String numberPhone, String address, String userName, String password,
			int rolId, String email) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.rolId = rolId;
		this.email = email;
	}



	public User(int id, String fullName, String numberPhone, String address, String userName, String password,
			int rolId) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.rolId = rolId;
	}
	
	

	public User( String fullName, String numberPhone, String address, String userName, String password,
			int rolId, String email, int status) {
		super();
		this.fullName = fullName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.rolId = rolId;
		this.email = email;
		this.status = status;
	}
	
		



	public User(String fullName, String numberPhone, String address, String userName, String password,
			int rolId, String email, int status, String publicKey, String signatureData) {
		this.fullName = fullName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.rolId = rolId;
		this.email = email;
		this.status = status;
		this.publicKey = publicKey;
		this.signatureData = signatureData;
	}
	public User(int id,String fullName, String numberPhone, String address, String userName, String password,
			int rolId, String email, String publicKey, String signatureData) {
		this.id = id;
		this.fullName = fullName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.rolId = rolId;
		this.email = email;
		this.publicKey = publicKey;
		this.signatureData = signatureData;
	}

	public String getPublicKey() {
		return publicKey;
	}



	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}



	public String getSignatureData() {
		return signatureData;
	}



	public void setSignatureData(String signatureData) {
		this.signatureData = signatureData;
	}



	public User(int id, String fullName, String numberPhone, String address, String userName, String password,
			int rolId, String email, int status) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.numberPhone = numberPhone;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.rolId = rolId;
		this.email = email;
		this.status = status;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return " fullName=" + fullName + ", numberPhone=" + numberPhone + ", address=" + address
				+ ", userName=" + userName + ", password=" + password + ", rolId=" + rolId + " ,status:" + status;
	}

}
