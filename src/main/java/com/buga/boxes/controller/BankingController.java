package com.buga.boxes.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buga.boxes.common.Constants;
import com.buga.boxes.connector.PostgresConnector;
import com.buga.boxes.demo.to.User;

@RestController
public class BankingController {

	private final Logger logger=LoggerFactory.getLogger(getClass());
	private final PostgresConnector connector;
	
	public BankingController() throws ClassNotFoundException, SQLException {
		logger.info("Initializing connection to db...");
		connector=new PostgresConnector(Constants.USER,
				Constants.PASSWORD,
				Constants.DATABASE,
				Constants.URI);
	}
	
	/**
	 * Get all users in db
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("user")
	public ResponseEntity<List<User>> getAllUsers() throws SQLException{
		logger.info("Fetching all users...");
		PreparedStatement preparedStatement=connector.getConnection().prepareStatement("select * from users");
		ResultSet resultSet=preparedStatement.executeQuery();
		List<User> toReturn=new ArrayList<>();
		while (resultSet.next()) {
			User user=new User(UUID.fromString(resultSet.getString("id")),
					resultSet.getString("name"), 
					resultSet.getString("gender"),
					resultSet.getString("email"), 
					resultSet.getDate("date"),
					resultSet.getInt("age"));
			toReturn.add(user);
		}
		return new ResponseEntity<List<User>>(toReturn, HttpStatus.OK);
	}
	
	/**
	 * Get a single user by id
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	@GetMapping("user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") UUID id) throws SQLException{
		logger.info("Get a single user by id");
		PreparedStatement preparedStatement=connector.getConnection().prepareStatement("select * from users where id=?)");
		preparedStatement.setString(1, UUID.randomUUID().toString());
		ResultSet resultSet=preparedStatement.executeQuery();
		List<User> toReturn=new ArrayList<>();
		while (resultSet.next()) {
			User user=new User(UUID.fromString(resultSet.getString("id")),
					resultSet.getString("name"), 
					resultSet.getString("gender"),
					resultSet.getString("email"), 
					resultSet.getDate("date"),
					resultSet.getInt("age"));
			toReturn.add(user);
		}
	
		
		return null;
	}
	
	/**
	 * Post a single user and add it to db
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	@PostMapping("user")
	public ResponseEntity<HttpStatus> postUser(@RequestBody User user) throws SQLException{
		logger.info("Adding {} to database...", user);
		PreparedStatement preparedStatement=connector.getConnection().prepareStatement("insert into users values(?,?,?,?,?,?)");
		preparedStatement.setObject(1, UUID.randomUUID());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(5, user.getGender());
		preparedStatement.setDate(4, user.getDateOfJoining());
		preparedStatement.setString(6, user.getEmail());
		preparedStatement.setLong(3, user.getAge());
		preparedStatement.executeUpdate();
		
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED, HttpStatus.ACCEPTED);
	}
	
	/**
	 * Delete single user by id
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	@DeleteMapping("user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") UUID id) throws SQLException{
		logger.info("Delete single user by id {}",id);
		PreparedStatement preparedStatement=connector.getConnection().prepareStatement("delete from users where id=?");
		preparedStatement.setObject(1, id);
		HttpStatus status=(preparedStatement.executeUpdate()>0)?HttpStatus.OK:HttpStatus.NOT_FOUND;
		// Both cases returns ok. 0 marks.
		return new ResponseEntity<HttpStatus>(status,status);
	}
	
	/**
	 * Delete all users
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	@DeleteMapping("user")
	public ResponseEntity<HttpStatus> deleteUser() throws SQLException{
		logger.info("Delete all from user");
		PreparedStatement preparedStatement=connector.getConnection().prepareStatement("delete from users");
		preparedStatement.executeUpdate();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK, HttpStatus.OK);
	}
	
	/**
	 * Update single user by id
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	@PutMapping("user/{id}")
	public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") UUID id, @RequestBody User user) throws SQLException{
		logger.info("Update single user by id");
		PreparedStatement preparedStatement=connector.getConnection().prepareStatement("Update users Set name=? where id=?");
		preparedStatement.setString(1, user.getName());
		preparedStatement.setObject(2, id);
        HttpStatus status=(preparedStatement.executeUpdate()>0)?HttpStatus.OK:HttpStatus.NOT_FOUND;
		return new ResponseEntity<HttpStatus>(status, status);
	}
}
