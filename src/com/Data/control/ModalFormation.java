package com.Data.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ModalFormation {

	private static final String Y = null;
	private static Connection connection = null;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		ModalFormation form = new ModalFormation();
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "root";

			connection = DriverManager.getConnection(url, username, password);	

			System.out.println("ENTER YOUR CHOICE");
			System.out.println("1. insert record ");
			System.out.println("2. fetch data");
			System.out.println("3. delete record");
			System.out.println("4. exit");
			int choice = Integer.parseInt(scanner.nextLine());
			boolean done = true;
			do {
				switch (choice) {
				case 1:
					form.insertRecord();
					break;
				
				case 2:
					form.fetchdata();
					break;
				case 3:
					form.deleteRecord();
					break;
				case 4:
					done = true;
				
			    
				}
			} while (!done);
			

		} catch (Exception e) {
			System.out.println(e + "exception occured");
		}
	}
	
	private void insertRecord() throws SQLException{
		
		String sql = "insert into sampledata(name, email,pasword) values(?,?,?) ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		System.out.println("enter name");
		preparedStatement.setString(1, scanner.next());
		System.out.println("enter email");
		preparedStatement.setString(2, scanner.next());
		System.out.println("enter password");
		preparedStatement.setString(3, scanner.next());
		
		int rows = preparedStatement.executeUpdate();
		
		if(rows>0) {
			System.out.println("recorded added");
		}
		
	}
	
	private void fetchdata() throws SQLException {
		
		System.out.println("enter an id to show data");
		int number = Integer.parseInt(scanner.next());
		
		String sql = "select*from sampledata WHERE id= "+number;
		 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		ResultSet rs = preparedStatement.executeQuery();
		System.out.println("fetched data");
		
		
		
		while (rs.next()) {
			 
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            System.out.println("id" + "\t\t" + "name" + "\t\t" + "email");
			System.out.println(id + "\t\t" + name + "\t\t" + email);
        }		
	}

	
	private void deleteRecord() throws SQLException {
		System.out.println("enter the id to delete");
		int number = Integer.parseInt(scanner.next());
		
		Statement stmt = connection.createStatement();
		String sql = "delete from sampledata where id ="+number;

		stmt.execute(sql);
		connection.close();
		
		System.out.println("record deleted");
		
	}
}
