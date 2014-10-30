package DBaccess;

import java.util.List;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Queries {
	public static void insertUser(String firsName, String lastName){
		
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
			
			stmt = conn.createStatement();
			String sql = "insert into students1 (first_name, last_name) values ('"+firsName+"', '"
			                                   +lastName+"'); ";
			
		   stmt.execute(sql);
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	
	} 

	public static ArrayList<User> findAll(){
		ArrayList<User> allUsers = new ArrayList<>();
		int id;
		String firstName;
		String lastName ;
		
		
		 Connection conn = null;
		   Statement stmt = null;
		   try{
		      Class.forName("com.mysql.jdbc.Driver");

		      conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);

		      //STEP 4: Execute a query
		      stmt = conn.createStatement();
		      
		      String sql = "Select STUDENT_ID, first_name, last_name from students1;";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  User user = new User();
		    	  id = rs.getInt("student_id");
		    	  firstName = rs.getString("first_name");
		    	  lastName = rs.getString("last_name");
		    	  user.setId(id);
		    	  user.setFirstName(firstName);
		    	  user.setLastName(lastName);
		    	  allUsers.add(user);  
		      }
		  	
		     
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
		return allUsers;
	}

	public static void updateUser(String id, String firstName, String lastName){
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
			
			stmt = conn.createStatement();
			String sql = "update students1 set first_name = '"+ firstName+"', last_name = '"+lastName+"' where student_id = '"
			                                  +id+"'";
			
		   stmt.execute(sql);
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
		
		}
	
	public static void deleteteUser(String id){
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
			
			stmt = conn.createStatement();
			String sql = "delete from students1  where student_id = '"+id+"'";
			
		   stmt.execute(sql);
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
		
		}
	
	public static User findByID(String id){
		User user = new User();
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
			
			stmt = conn.createStatement();
			String sql = "select * from students1  where student_id = '"+id+"'";
			
		   ResultSet rs = stmt.executeQuery(sql);
		  
		   if( rs.next()){
		   int id1 = rs.getInt("student_id");
		   String firstName = rs.getString("first_name");
		   String lastName = rs.getString("last_name");
	
		    	  user.setId(id1);
		    	  user.setFirstName(firstName);
		    	  user.setLastName(lastName);
		   }
		   else user = null;
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
		
		  return user;
		}
	public static Vector<User> findByName(String name, String column){
		Vector<User> users = new Vector<>();
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Main.DB_URL, Main.USER, Main.PASS);
			
			stmt = conn.createStatement();
			String sql = "select * from students1  where "+column+" = UPPER('"+name+"')";
			
		   ResultSet rs = stmt.executeQuery(sql);
		   while( rs.next()){
		   int id1 = rs.getInt("student_id");
		   String firstName = rs.getString("first_name");
		   String lastName = rs.getString("last_name");
		   User user = new User();
		    	  user.setId(id1);
		    	  user.setFirstName(firstName);
		    	  user.setLastName(lastName);
		    	  users.add(user);
		   }
		  
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
		
		  return users;
		}
	
}
