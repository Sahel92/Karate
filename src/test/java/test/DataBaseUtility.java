package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataBaseUtility {

	private static final String db_url = "jdbc:mysql://tek-database-server.mysql.database.azure.com:3306/tek_insurance_app?useSSL=true&requireSSL=false";
	private static final String db_userName = "tek_student_user";
	private static final String db_password = "MAY_2022";

	// step 1 establish connection with database

	private static Connection getConnection() {
		try {
			System.out.println("Getting Connection to DataBase");
			return DriverManager.getConnection(db_url, db_userName, db_password);
		} catch (SQLException e) {

			throw new RuntimeException("Error Connecting to DataBase");
		}

	}

// get connection statement
	private static Statement getConnectionStatement() {
		try {
			return getConnection().createStatement();
		} catch (SQLException e) {
			throw new RuntimeException("Error Creating Statement");

		}

	}

	// execute the query
	public static ResultSet executeQuery(String query) {
		Statement statement;
		statement = getConnectionStatement();
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			throw new RuntimeException("Error executing query");

		}
	}

	// after we get the results we need to store, the results as key value pairs and
	// we use LinkedList of List<Map<String, String>> to store a hashmap of column
	// and row value (key :value)

	public static List<Map<String, Object>> queryResult(String query) {
		Statement statement = null;
		try {
			List<Map<String, Object>> list = new LinkedList<>();
			ResultSet resultSet = executeQuery(query);
			statement = resultSet.getStatement();
			ResultSetMetaData metadata = resultSet.getMetaData();
			int columns = metadata.getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<>();
				for (int i = 1; i <= columns; i++) {
					map.put(metadata.getColumnName(i), resultSet.getObject(i));
				}
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException("Error getting query result");
		} finally {
			if (statement != null) {
				try {
					statement.getConnection().close();
					statement.close();
				} catch (SQLException e) {
					System.out.println("No open connection to DB to close");
				}
			}
		}
	}

	/**
	 * 
	 * @param query
	 * @param columnName
	 * @return The value at the associated columnName. columnName = key, value =
	 *         ojbect in that column at row 0 (first row) since index based start at
	 *         0. returns query result of a particular columnName.
	 * 
	 *         def dataBaseUtility = Java.type("test.api.DataBaseUtility") def
	 *         databaseEmail = dataBaseUtility.result("select email from
	 *         primary_person where id = '"+id+"'","email") The below email will be
	 *         returned from the above query. |email| |manogul@hotmail.com|
	 */
	public static String result(String query, String columnName) {
		List<Map<String, Object>> result = queryResult(query);
		return result.get(0).get(columnName).toString();

	}

	public static void main(String[] args) {

		// System.out.println(queryResult("select email from primary_person\r\n" +
		// "where id = '5644';"));

		List<Map<String, Object>> list = queryResult("select * \r\n" + "from primary_person\r\n" + "inner join\r\n"
				+ "primary_person_phone\r\n" + "on primary_person.id = primary_person_phone.primary_person_id\r\n"
				+ "where primary_person_id = 5644;");

		// System.out.println(list);

		for (Map<String, Object> string : list) {
			if (string.containsKey("email")) {
				System.out.println(string.get("email"));
			}

		}
	}

}
