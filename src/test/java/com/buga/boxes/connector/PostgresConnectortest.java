package com.buga.boxes.connector;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class PostgresConnectortest {
	private static final String USER = "buga";
	private static final String PASSWORD = "SSGrocks@123";
	private static final String DATABASE = "buga-banking";
	private static final String URI = "jdbc:postgresql://localhost:5432";

	@Test
	public void testConnection() throws ClassNotFoundException, SQLException {
		new PostgresConnector(USER, PASSWORD, DATABASE, URI);
		assertTrue(true);
	}

	@Test
	public void testGet() throws ClassNotFoundException, SQLException {
		final Connection connection = new PostgresConnector(USER, PASSWORD, DATABASE, URI).getConnection();
		ResultSet resultset = connection.prepareStatement("select * from users").executeQuery();
		while (resultset.next()) {
			for (int i = 1; i < 4; i++) {
				System.out.println(resultset.getString(i));
			}
		}
	}

	@Test
	public void testInsert() throws ClassNotFoundException, SQLException {
		final Connection connection = new PostgresConnector(USER, PASSWORD, DATABASE, URI).getConnection(); // establih
																											// connection
		final UUID id = UUID.randomUUID();
		final String name = "FromTestCase";
		final int age = 34;
		final Date date = new Date();
		final String gender = "chakka";
		final String email = "swd543";

		final PreparedStatement statement = connection.prepareStatement("insert into users values(?,?,?,?,?,?)");
		statement.setObject(1, id);
		statement.setString(2, name);
		statement.setInt(3, age);
		statement.setObject(4, new Timestamp(date.getTime()));
		statement.setString(5, gender);
		statement.setString(6, email);

		System.out.println(statement.executeUpdate());
	}

	@Test
	public void testDelete() throws ClassNotFoundException, SQLException {
		final Connection connection = new PostgresConnector(USER, PASSWORD, DATABASE, URI).getConnection();
		final PreparedStatement statement = connection.prepareStatement("delete from users where name=?");
		final String name = "Babuli hiiiii";
		statement.setString(1, name);
		statement.executeUpdate();
	}

	@Test
	public void testUpdate() throws ClassNotFoundException, SQLException {
		final Connection connection = new PostgresConnector(USER, PASSWORD, DATABASE, URI).getConnection();
		final PreparedStatement statement = connection.prepareStatement("Update users Set name=? where email=?");
		final String name = "sakshi";
		statement.setString(1, name);
		final String email = "s.gajbhiye08@gmail.com";
		statement.setString(2, email);
		statement.executeUpdate();
	}

	@Test
	public void testInsert2() throws ClassNotFoundException, SQLException {
		final Connection connection = new PostgresConnector(USER, PASSWORD, DATABASE, URI).getConnection(); // establih
																											// connection
		final UUID id = UUID.randomUUID();
		final String name = "Babuli hiiiii";
		final int age = 5;
		final Date date = new Date();
		final String gender = "chakka";
		final String email = "s.gajbhiye08@gmail.com";
		final PreparedStatement statement = connection.prepareStatement("insert into users values(?,?,?,?,?,?)");
		statement.setObject(1, id);
		statement.setString(2, name);
		statement.setInt(3, age);
		statement.setObject(4, new Timestamp(date.getTime()));
		statement.setString(5, gender);
		statement.setString(6, email);
		System.out.println(statement.executeUpdate());
	}
}
