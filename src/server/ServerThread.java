package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import database.JDBC;

public class ServerThread extends Thread {

	String clientAddress;
	Socket socket;
	String msg = "";
	String seperator = ":";
	StringTokenizer tokenizer = new StringTokenizer(msg, seperator);

	public ServerThread(Socket s)
	{
		this.socket = s;
	}

	public void run() 
	{
		try {
			PrintWriter messageOut = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("A client request receievd at "+socket);

			BufferedReader messageIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			msg = messageIn.readLine();

			System.out.println("Message Received from Client: " + msg);

			tokenizer = new StringTokenizer(msg, seperator);


			messageOut.println("Ack");
			System.out.println("sent Ack to client");

			String token = tokenizer.nextToken();

			if (tokenizer.hasMoreTokens() && token.equals("Sent")) {

				System.out.println("found token for sent");

				String username = tokenizer.nextToken();

				String password = tokenizer.nextToken();

				String codeword = tokenizer.nextToken();

				String recipient = tokenizer.nextToken();

				String message = tokenizer.nextToken();

				JDBC database = new JDBC();


				if(database.logIn(username, password)) {

					if(database.writeMessage(username, codeword, recipient, message)) {

						messageOut.println("success");
						System.out.println("I have sent a message");

					}	
					else {
						messageOut.println("I HAVE FAILED TO SEND MESSAGE");
					}
				}

			}
			if (tokenizer.hasMoreTokens() && token.equals("Signup")) {

				System.out.println("found token for signup");

				String username = tokenizer.nextToken();

				String password = tokenizer.nextToken();

				JDBC database = new JDBC();


				if(database.signUp(username, password)) {

					messageOut.println("signup successful");
					System.out.println("I have signed up a user");
				}	
				else {
					messageOut.println("I HAVE FAILED TO SIGNUP");
				}
			}




			else if(tokenizer.hasMoreTokens() && token.equals("Received")) {

				System.out.println("found token for received");

				String username = tokenizer.nextToken();

				String password = tokenizer.nextToken();

				String codeword = tokenizer.nextToken();

				String recipient = tokenizer.nextToken();




				JDBC database = new JDBC();


				if(database.logIn(username, password)) {

//					System.out.println("got to receive login");
					if( !(database.receiveMessage(username, codeword, recipient).equals("No messages found.")) ) {
//						System.out.println("past checking received message in jdbc");
						String messageReceived = database.receiveMessage(username, codeword, recipient);

						messageOut.println(messageReceived);
//						System.out.println("I have received a message and sent from erverthread to client");
					}	
					else {
						messageOut.println("I HAVE FAILED TO RECEIVE MESSAGE \n No message available");
					}
				}
			}



			//socket.close();
		} 
		catch(IOException e)
		{
			System.out.println("Error: ");

			e.printStackTrace();
		}
	}
}