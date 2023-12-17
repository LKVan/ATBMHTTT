package model;

import java.sql.Timestamp;

public class Confim {
	private int id;
	private String userName;
	private String code;
	private Timestamp dateCreate;
	private Timestamp dateEnd;

	


	public Confim(int id, String userName, String code, Timestamp dateCreate, Timestamp dateEnd) {
		super();
		this.id = id;
		this.userName = userName;
		this.code = code;
		this.dateCreate = dateCreate;
		this.dateEnd = dateEnd;
	}

	
	
	public String getCode() {
		return code;
	}



	public String getUserName() {
		return userName;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public Timestamp getDateEnd() {
		return dateEnd;
	}

}