package dal;

import bo.User;

public interface UserDAO {
	
	public User login(String login, String password);
	public boolean createUser(User user);
	public boolean checkEmail(String email);
	public boolean checkPseudo(String pseudo);
	public void updateUser(User user);
	public void deleteUser(int id);
}
