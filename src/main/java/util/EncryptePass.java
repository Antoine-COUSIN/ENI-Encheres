package util;

import java.security.NoSuchAlgorithmException;

public class EncryptePass {

	public static void main(String[] args) {
		
		
		String pass = "password";
		
		try {
			pass = PasswordUtil.hashPassword(pass);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(pass);

	}

}
