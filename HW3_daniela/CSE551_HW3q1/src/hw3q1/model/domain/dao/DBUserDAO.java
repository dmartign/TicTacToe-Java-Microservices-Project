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
		//String name, setEmail, userName, password, streetAddress, city, zip;
		User user = new User();
		String stmt1 = "SELECT email, userName, password, streetAddress, state, zip "
				+ "FROM webuser.users WHERE email= ?";
		PreparedStatement s;
		try {
			s = connection.prepareStatement(stmt1);
			s.setString(1, email);
			ResultSet r = s.executeQuery();
			while (r.next()) {
				user.setEmail(r.getString("email"));
				user.setName(r.getString("userName"));
				user.setPassword(r.getString("password"));
				user.setStreetAddress(r.getString("streetAddress"));
				user.setState(r.getString("state"));
				user.setZipcode(r.getString("zip"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void register(User user) {
		//User user2 = new User();
		//String stmt1 = "SELECT email FROM webuser.users";
		//Statement s1;
		PreparedStatement s2;
		//ResultSet r1;
		try {
			//s1 = connection.createStatement();
			//r1 = s1.executeQuery(stmt1);
			//while (r1.next()) {
				// Checking to see if user already exists, if so, give error
			//	if (r1.getString("email") == user.getEmail()){
				if(!findUserByEmail(user.getEmail()).equals(null)) {
					System.out.println("Sorry, user already registered!");
				}
				else {
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
			//else {
			//	System.out.println("Sorry, user already registered with this email. Try again.");
			//}
			//	}
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
