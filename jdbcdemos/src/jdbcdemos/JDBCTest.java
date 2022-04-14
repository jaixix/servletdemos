package jdbcdemos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	public static void main(String[] args) {
		System.out.println("Let's Gooooo!");
		try (
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select* from account");
		){
			//CRUD - C (Create)
//			int result = statement.executeUpdate("insert into account values(107,'Singh', 'Chandransh', 1200)");
//			System.out.println("Number of rows affected : " + result);
			
			//CRUD - U (Update)
//			int result = statement.executeUpdate("update account set balance=1230 where accno=106");
//			System.out.println("Number of rows affected : " + result);
			
			//CRUD - D (Delete)
//			int result = statement.executeUpdate("delete from account where accno=106");
//			System.out.println("Number of rows affected : " + result);
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+". "+rs.getString(2)+","+rs.getString(3)+" - Balance = "+rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
