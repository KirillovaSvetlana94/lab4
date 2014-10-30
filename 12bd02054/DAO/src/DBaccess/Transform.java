package DBaccess;

import java.io.IOException;
import java.util.ArrayList;


public class Transform {

	public static String viewStudents(User user) throws IOException{
		String response = null;
		response = ReadFunction.readFromFile("form.html");
		response = response.replace("user.getId()", user.getId()+"");
		response = response.replace("user.getFirstName()", user.getFirstName());
		response = response.replace("user.getLastName()", user.getLastName());
	
		
		return response;
		
	}
	public static String viewForUpdating(String id, String firstName, String lastName) throws IOException{
		String response = null;
		response = ReadFunction.readFromFile("updating.html");
		response = response.replace("user.getId()", id);
		response = response.replace("user.getFirstName()", firstName);
		response = response.replace("user.getLastName()", lastName);
	
		
		return response;
		
	}
	public static String viewForDeleting(String id) throws IOException{
		String response = null;
		response = ReadFunction.readFromFile("deleting.html");
		response = response.replace("user.getId()", id);
		
		return response;
		
	}	
	public static String listOfStudents() throws IOException{
		int count = Queries.findAll().size();
		//System.out.println(Queries.findAll().get(0).toString());
		ArrayList<User> users = Queries.findAll();
		String response = "";
		
		for(int i=0; i<count; ++i){
			//System.out.println(users.get(i).getFirstName()+"==="+users.get(i).getId());
			response += viewStudents(users.get(i));
			
			//System.out.println(users.get(i).getFirstName()+"==="+i);
		}
		
		return response;
	}
}
