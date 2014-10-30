package DBaccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Vector;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/test";

	   //  Database credentials
	   static final String USER = "username";
	   static final String PASS = "";
	   
	   public static void main(String[] args) throws IOException {
	 
	   
	   HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
	     
	     server.createContext("/", new Handler());
	        
	     server.setExecutor(null); // creates a default executor
	     server.start(); 
	}//end main
	      




public static int convertToInt(String s){
  char a[];
  int result = 0;
  int h=1;
  a = s.toCharArray();
  int n = s.length();
  for(int i=n-1; i>=0; i--){
	   result += (a[i]-48)*h;
	   h*=10;
  }
  return result;
}
	
	
	
static class Handler implements HttpHandler {
   public void handle(HttpExchange t) throws IOException {
   	
   	
   	
   	URI uri = t.getRequestURI();
   	String path = uri.toString();
       String response = new String();
       
       String templ = ReadFunction.readFromFile("templ.html");
       String view = new String();
      
   
       System.out.println(100);
       
       
       if(path.equals("/updating")){
    	   Vector<String> data = ReadFunction.readUserInput(t);
    	   response = templ.replace("%content%",Transform.viewForUpdating(data.get(1), data.get(3), data.get(5)));
      }
       else if(path.equals("/update")){
    	   String cont = ReadFunction.readFromFile("list.html");
    	   Vector<String> data = ReadFunction.readUserInput(t);
    	   Queries.updateUser(data.get(1), data.get(3) , data.get(5));
    	   response = templ.replace("%content%", cont);
      }
       
       else if(path.equals("/deleting")){
    	   Vector<String> data = ReadFunction.readUserInput(t);
    	   response = templ.replace("%content%",Transform.viewForDeleting(data.get(1)));
      }
       else if(path.equals("/delete")){
    	   String cont = ReadFunction.readFromFile("list.html");
    	   Vector<String> data = ReadFunction.readUserInput(t);
    	   Queries.deleteteUser(data.get(1));
    	   response = templ.replace("%content%", cont);
      }
       
       else if(path.equals("/list.html")){
       		response = templ.replace("%content%", Transform.listOfStudents());
       		
      }
       else if(path.equals("/registration")){
       	   String newContent = ReadFunction.readFromFile("registration.html");
    	   response = templ.replace("%content%", newContent);
    	   Vector<String> data = ReadFunction.readUserInput(t);
    	   Queries.insertUser(data.get(1), data.get(3));
    	   
      }
       else if(path.equals("/find-by-id")){
       	   String id = ReadFunction.readUserInput(t).get(1);
       	   User user = Queries.findByID(id);
       	   if(user!=null)
    	   response = templ.replace("%content%", Transform.viewStudents(user));
       	   else response = templ.replace("%content%", "No user with such id");
       	   
      }
       else if(path.equals("/find-by-lname")){
       	   String lname = ReadFunction.readUserInput(t).get(1);
       	   String newContent ="";
       	   Vector<User> users = Queries.findByName(lname , "last_name");
       	
       		   for(int i=0; i<users.size(); i++ )
       			   newContent +=  Transform.viewStudents(users.get(i));
       	   if(users.size()!=0) response =  templ.replace("%content%", newContent);
       	   else response = templ.replace("%content%", "No user with such last name");
      }
       else if(path.equals("/find-by-fname")){
    	   String newContent = "";
       	   String fname = ReadFunction.readUserInput(t).get(1);
       	   Vector<User> users = Queries.findByName(fname, "first_name");
       	for(int i=0; i<users.size(); i++ )
			   newContent +=  Transform.viewStudents(users.get(i));
	   if(users.size()!=0) response =  templ.replace("%content%", newContent);
       	   else response = templ.replace("%content%", "No user with such first name");
      }
       
       else if(!path.equals("/")){
      
   		path=path.substring(1);
   		String sub = ReadFunction.readFromFile(path);
   		response=templ.replace("%content%", sub);
   		
       }
       
         else if(path.equals("/")){
        	 response = templ.replace("%content%", "");
       	
       }
  
         
  
       
		
		Headers header = t.getResponseHeaders();	//adding header
		header.add("Content-Type", "text/html; charset=UTF-8");
		System.out.println("Sheeeeeeeest'");
		path = "";
       t.sendResponseHeaders(200, response.length());
       OutputStream os = t.getResponseBody();
       os.write(response.getBytes());
       os.close();
   
}


}


}

