package validity;

public class Validator {
	
	public Validator() {}
	
	public boolean validateUsername(String username) {
		return username != null && username.matches(".{3,20}");
	}

}