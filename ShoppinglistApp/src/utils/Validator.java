package utils;

public class Validator {
	
	public Validator() {}
	
	public boolean validateUsername(String username) {
		return username != null && username.matches("^[a-zA-ZæøåÆØÅ |_-]{3,20}$");
	}
	
	public boolean validatePassword(String password) {
		if(password == null || !password.matches(".{8,64}")) {
			return false;
		}
		else {
			for(int i = 0; i < password.length() - 2; i++) {
				int i2 = i + 1;
				int i3 = i + 2;
				if(password.charAt(i) == password.charAt(i2) && password.charAt(i) == password.charAt(i3)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean validatePasswordRepeat(String passwordRepeat, String password) {
		return passwordRepeat == null ? password == null : passwordRepeat.equals(password);
	}

}