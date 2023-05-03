package dal;

import bo.User;

public interface UserDAO {
	
	public User login(String login, String password);
	public void createUser(User user);
}
