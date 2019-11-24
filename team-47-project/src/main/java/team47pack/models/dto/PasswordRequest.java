package team47pack.models.dto;

public class PasswordRequest {

	private String passwordNew;
	private String passwordConf;
	private String passwordCurr;
	private String email;

	public PasswordRequest() {

	}

	public PasswordRequest(String passwordNew, String passwordConf, String passwordCurr, String email) {
		super();
		this.passwordNew = passwordNew;
		this.passwordConf = passwordConf;
		this.passwordCurr = passwordCurr;
		this.email = email;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public String getPasswordCurr() {
		return passwordCurr;
	}

	public void setPasswordCurr(String passwordCurr) {
		this.passwordCurr = passwordCurr;
	}

	public String getPasswordConf() {
		return passwordConf;
	}

	public void setPasswordConf(String passwordNew2) {
		this.passwordConf = passwordNew2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
