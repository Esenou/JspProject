package com.demo.kg.dao;
import com.demo.kg.model.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;

public class UserDao {
	
	public int registerEmployee(User employee) throws ClassNotFoundException {
		
		
		
        String INSERT_USERS_SQL = "INSERT INTO Persons" +
            "  (PersonId, LastName, FirstName, Address, City) VALUES " +
            " (?, ?, ?, ?, ?)";
       
        int result = 0;
        String cal = "{ ? = call Esen(?) }";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Locale.setDefault(Locale.ENGLISH);
        try (Connection connection = DriverManager
            .getConnection("jdbc:oracle:thin:@localhost:1521:xe","test","test");

        		
        		CallableStatement cstmt = connection.prepareCall(cal)){
        	    cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
        	    cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
        	   
        		
        		
        		
        		cstmt.executeUpdate();
            // Step 2:Create a statement using connection object
           

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
