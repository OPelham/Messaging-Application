package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
	public boolean logIn(String userName, String password)
	{
		boolean res = true;

		try
		{
			String databaseUser = "pelhamoliv";

			String databaseUserPass = "pass123";  
			
			System.out.println("in login before login");

			Class.forName("org.postgresql.Driver");
			
			System.out.println("logged in at login method");

			Connection connection = null;            

			String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";

			connection = DriverManager.getConnection(url,databaseUser, databaseUserPass);
			
//			System.out.println("logged in at login method");

			Statement s = connection.createStatement();

			ResultSet rs = s.executeQuery("select * from users where username='"+userName+"' and password='"+password+"'");

			if(! rs.next())

				res = false;            

			rs.close();

			connection.close();
			System.out.println("closed nconnedction in login");
		}
		catch(Exception e)
		{
			System.out.println("LogIn Error: "+e.toString());

			res = false;
		}
		return res;
	}

	//    /**
	//     * @param args the command line arguments
	//     */
	//    public static void main(String[] args) {
	//        // TODO code application logic here
	//        PostgresJDBC psgres = new PostgresJDBC();
	//        if(psgres.signUp("test1", "test"))
	//            System.out.println("Login successful");
	//        else System.out.println("Login unsuccessful");
	//                
	//    }
	public boolean writeMessage(String username, String codeword, String recipient, String message) {

		boolean res = true;

		try 
		{
			String databaseUser = "pelhamoliv";

			String databaseUserPass = "pass123";   

			Class.forName("org.postgresql.Driver");

			Connection connection = null;           

			String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";

			connection = DriverManager.getConnection(url,databaseUser, databaseUserPass);

			Statement s = connection.createStatement();

			//add user
			int newUser = s.executeUpdate("Insert into messages(username, codeword, recipient, message) values ('"+username+"', '"+codeword+"', '"+recipient+"', '"+message+"' ) ");
//			newUser.close();
			
			connection.close();
			System.out.println("closed connection in write message");
		}
		catch(Exception e)
		{
			System.out.println("LogIn Error: "+e.toString());

			res = false;
		}
		return res;
	}
	
	public String receiveMessage(String username, String codeword, String recipient) {
			
		String res = "No messages found.";

		try 
		{
			String databaseUser = "pelhamoliv";

			String databaseUserPass = "pass123";   

			Class.forName("org.postgresql.Driver");

			Connection connection = null;           

			String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";

			connection = DriverManager.getConnection(url,databaseUser, databaseUserPass);

			Statement s = connection.createStatement();

			//add user
			ResultSet newUser = s.executeQuery("select * from messages where username = '"+recipient+"' and codeword = '"+codeword+"' and recipient = '"+username+"' ");
			
			newUser.next();
			String senderName = newUser.getString("recipient");
			System.out.println(senderName);
			String message = newUser.getString("message");
			System.out.println(message);
			
			
			
			newUser.close();
			
			res = senderName + "\n" + message;
			
			System.out.println("concat message from db" + res);		
			
			connection.close();
			System.out.println("closed connection in write message");


		}
		catch(Exception e)
		{
			System.out.println("LogIn Error: "+e.toString());

		}
		return res;
	}





	public boolean signUp(String userName, String password) {

		boolean res = true;

		try 
		{
			String databaseUser = "pelhamoliv";

			String databaseUserPass = "pass123";   

			Class.forName("org.postgresql.Driver");

			Connection connection = null;           

			String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";

			connection = DriverManager.getConnection(url,databaseUser, databaseUserPass);

			Statement s = connection.createStatement();

			//add user
			ResultSet newUser = s.executeQuery("Insert into users(username, password) values ('"+userName+"', '"+password+"' ) ");
			
			
			newUser.close();
			
			connection.close();
			System.out.println("closed connection in signup");
			//TODO put check in place
			//check user was added
			//             ResultSet rs = s.executeQuery("select * from users where username='"+userName+"' and password='"+password+"'");
			//             if(! rs.next())
			//                 res = false;            
			//             rs.close();
			//TODO make this fire on sign out
			//            connection.close();
		}
		catch(Exception e)
		{
			System.out.println("LogIn Error: "+e.toString());

			res = false;
		}
		return res;
		
		
	}
}