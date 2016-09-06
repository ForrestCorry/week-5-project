package com.ravens;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.ravens.Players;

public class DAO {
	
	public static final ArrayList<Players> ourPlayers = new ArrayList<>();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root$autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	static Scanner sc = new Scanner(System.in);

	public static void connToDB() {

		try {
			
			Class.forName(JDBC_DRIVER);
			
			
			//System.out.println("Trying to connect to the Database...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			//System.out.println("Connected to the Database");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Failed to connect to the Database");
			e.printStackTrace();
		}

	}

	public static void readFromDB() {

		connToDB();
		

		try {
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM players.ravens;");

			while (RES_SET.next()) {
				Players playersInDB = new Players();

				playersInDB.setPlayerID(RES_SET.getInt("Player ID"));
				playersInDB.setName(RES_SET.getString("Name"));
				playersInDB.setPosition(RES_SET.getString("Position"));
				playersInDB.setNumber(RES_SET.getString("Number"));
				

				ourPlayers.add(playersInDB);

			}

//			for (Players player : ourPlayers) {
//				//System.out.println(animal.toString());
//			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public static void writeToDB(Players players){
		Players playerToAdd = new Players();
		
		playerToAdd = players;
		
		connToDB();
		
		try{
			PREP_STMT =CONN.prepareStatement(insertToDB);
			
			PREP_STMT.setString(1, playerToAdd.getName());
			PREP_STMT.setString(2, playerToAdd.getPosition());
			PREP_STMT.setString(3, playerToAdd.getNumber());
						
			//System.out.println(PREP_STMT);
			
			PREP_STMT.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Players updateDB(Players players){
		Players playerToUpdate = new Players();
		
		playerToUpdate = players;
		connToDB();
		try{
			PREP_STMT = CONN.prepareStatement(updateToDB);
			
			PREP_STMT.setString(1, playerToUpdate.getName());
			PREP_STMT.setString(2, playerToUpdate.getPosition());
			PREP_STMT.setString(3, playerToUpdate.getNumber());
			
			//System.out.println(PREP_STMT.toString());
			PREP_STMT.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return playerToUpdate;
	}
	
	public static void deleteFromDB(int id){
		
		connToDB();
		try{
			PREP_STMT = CONN.prepareStatement(deleteDB);
					
			PREP_STMT.setInt(1, id);
			
			PREP_STMT.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
//		return playerToDelete;
	}
	
	//public static Players deleteThePlayer(){
		//Players playerToDelete = new Players();
		
		//System.out.println("What is the name of the animal you wish to delete?");
		//playerToDelete.setName(sc.nextLine());
		
		
		//return playerToDelete;
	
	
	private static String insertToDB = "INSERT INTO players.ravens"
			+ "(Name, Position, Number)"
			+ "VALUES"
			+ "(?, ?, ?)";
	
	private static String updateToDB = "UPDATE players.ravens"
			+ " SET Name=?, Position=?, Number=? WHERE Player ID = ?";
				
	
	private static String deleteDB = "DELETE FROM players.ravens WHERE `Player ID` = ?";
	
//public static Animals aboutTheAnimal(){
//		
//		Animals animalToAdd = new Animals();
//		
//		System.out.println("What is the Species of the Animal?");
//		animalToAdd.setSpecies(sc.nextLine());
//		
//		System.out.println("What type of Habitat does the " + animalToAdd.getSpecies() + " live in?");
//		animalToAdd.setTypeOfCage(sc.nextLine());
//		
//		System.out.println("What food does your "+ animalToAdd.getSpecies() + " eat");
//		animalToAdd.setFood(sc.nextLine());
//		
//		System.out.println("What is your " + animalToAdd.getSpecies() + " name?");
//		animalToAdd.setName(sc.nextLine());
//		
//		System.out.println("What does your " + animalToAdd.getSpecies() + " weigh?");
//		animalToAdd.setWeight(sc.nextLine());
//		
//		return animalToAdd;
//		
//		
//	}
	
	
	
	
}
