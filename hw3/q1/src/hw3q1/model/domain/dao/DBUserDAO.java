package hw3q1.model.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hw3q1.model.domain.User;

public class DBUserDAO implements UserDAO{

	private Connection connection;
	
	public DBUserDAO(Connection connection){
		this.connection = connection;
	}
	
	@Override
	public User findUserByEmail(String email) {
		User user = null;
		String stmt1 = "SELECT email, userName, password, streetAddress, state, zip "
				+ "FROM webuser.users WHERE email= ?";
		PreparedStatement s;
		try {
			s = connection.prepareStatement(stmt1);
			s.setString(1, email);
			ResultSet r = s.executeQuery();
			while (r.next()) {
				user = new User();
				user.setEmail(r.getString("email"));
				user.setName(r.getString("userName"));
				user.setPassword(r.getString("password"));
				user.setStreetAddress(r.getString("streetAddress"));
				user.setState(r.getString("state"));
				user.setZipcode(r.getString("zip"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void register(User user) {
		PreparedStatement s2;
		try {
			// Checking to see if user already exists, if so, gives error, cannot register a user twice
			if(findUserByEmail(user.getEmail()) != null) {
				System.out.println("Sorry, user already registered!");
			}
			else { 	// new user to be inserted into the db
				String stmt2 = "INSERT INTO webuser.users(email,userName,password,streetAddress,state,zip)" 
						+ "VALUES(?,?,?,?,?,?)";
				s2 = connection.prepareStatement(stmt2);
				s2.setString(1, user.getEmail());
				s2.setString(2, user.getName());
				s2.setString(3, user.getPassword());
				s2.setString(4, user.getStreetAddress());
				s2.setString(5, user.getState());
				s2.setString(6, user.getZipcode());
				s2.executeUpdate();
				System.out.println("Update successful. New record: " + user.getEmail() + ", " 
						+ user.getName() + ", " + user.getPassword() + ", " + user.getStreetAddress() + ", " 
						+ user.getState() + ", " + user.getZipcode());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
