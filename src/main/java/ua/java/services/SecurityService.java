package ua.java.services;

import ua.java.models.User;

public interface SecurityService {
	String findLoggedInUsername();

	void autologin(String username, String password);

	String getName();
	
	void logout();
	
	User getUser();
}
