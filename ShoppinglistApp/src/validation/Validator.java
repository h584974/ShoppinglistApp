package validation;

public class Validator {
	
	public Validator() {}
	
	public boolean validateUsername(String username) {
		return username != null && username.matches(".{3,20}");
	}
	
	public boolean validatePassword(String password) {
		return password != null && password.matches(".{8,64}");
	}
	
	public boolean validatePasswordRepeat(String passwordRepeat, String password) {
		return passwordRepeat == null ? password == null : passwordRepeat.equals(password);
	}

}