package ua.java.services;

import ua.java.models.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

	User findByEmail(String email);

	void notVerificateUserClean();

	void sendVerifiEmail(User user);

	void sendPasswordToUser(String user);
}
