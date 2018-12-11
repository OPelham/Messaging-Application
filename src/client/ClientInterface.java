package client;

import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ClientInterface extends JFrame{
	
	String serverAddress = "127.0.0.1"; //make this the public ip to allow others to connect
	int port = 9095;
	
	//declaring components
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textField_3;
	private JTextArea textField_4;
	private JTextField textField_5;
//	Socket clientSocket = new Socket(serverAddress, port);
	
	public ClientInterface() {
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblSpytime = new JLabel("SPYTIME 95");
		lblSpytime.setBounds(191, 0, 137, 36);
		lblSpytime.setFont(new Font("Luminari", Font.PLAIN, 22));
		getContentPane().add(lblSpytime);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(45, 41, 171, 36);
		getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(141, 46, 130, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(45, 96, 141, 16);
		getContentPane().add(lblPassword);
		
		JButton btnSend = new JButton("SEND");
		btnSend.setBounds(327, 87, 117, 29);
		btnSend.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					// connect to server
//					String serverAddress = "127.0.0.1";
//					int port = 9095;
					Socket clientSocket = new Socket(serverAddress, port);
					
					PrintWriter messageOut = new PrintWriter(clientSocket.getOutputStream(), true);
					String username = textField.getText();
					String password = textField_1.getText();
					String codeword = textField_2.getText();
					String recipient = textField_5.getText();
					String emailOut = textField_3.getText();
					
					messageOut.println("Sent:" + username + ":" + password + ":" + codeword + ":" + recipient + ":" + emailOut);
					System.out.println("Sent:" + username + ":" + password + ":" + codeword + ":" + recipient + ":" + emailOut);
					
					BufferedReader messageIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String messageReceived = messageIn.readLine();
					System.out.println(messageReceived);
					
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					textField_5.setText(null);
					textField_3.setText("Message sent!");
					textField_4.setText(null);
					

//					String messageReceived = messageIn.readLine();
//					System.out.println(messageIn.readLine());
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 91, 130, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		getContentPane().add(btnSend);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				String serverAddress = "127.0.0.1";
//				int port = 9095;
				Socket clientSocket;
				try {
					clientSocket = new Socket(serverAddress, port);
				
				
				PrintWriter messageOut = new PrintWriter(clientSocket.getOutputStream(), true);
				
				String username = textField.getText();
				String password = textField_1.getText();
				
				messageOut.println("Signup:"+username+":"+password);
				
				
				textField.setText(null);
				textField_1.setText(null);
				
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSignUp.setBounds(327, 46, 117, 29);
		getContentPane().add(btnSignUp);
		
		JLabel lblCodeword = new JLabel("CODEWORD");
		lblCodeword.setBounds(45, 138, 91, 16);
		getContentPane().add(lblCodeword);
		
		textField_2 = new JTextField();
		textField_2.setBounds(141, 129, 130, 26);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnReceive = new JButton("RECEIVE");
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// connect to server
//					String serverAddress = "127.0.0.1"; //make this the public ip to allow others to connect
//					int port = 9095;
					Socket clientSocket = new Socket(serverAddress, port);
					
					PrintWriter messageOut = new PrintWriter(clientSocket.getOutputStream(), true);
					String username = textField.getText();
					String password = textField_1.getText();
					String codeword = textField_2.getText();
					String recipient = textField_5.getText();
					
					messageOut.println("Received:" + username + ":" + password + ":" + codeword + ":" + recipient);
					System.out.println("sent -- Received:" + username + ":" + password + ":" + codeword + ":" + recipient);
					
					BufferedReader messageIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String messageReceived = messageIn.readLine();
					System.out.println("message received here by client = " + messageReceived);
					System.out.println(messageReceived + " received back by client");
					
					String displayMessage = "Message from " + messageIn.readLine() + ": \n" + messageIn.readLine();
					System.out.println(displayMessage);
					textField_4.setText(displayMessage);
					
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					textField_3.setText(null);
					textField_5.setText(null);
//					String messageReceived = messageIn.readLine();
//					System.out.println(messageIn.readLine());
					
					
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
			
			}
		});
		btnReceive.setBounds(327, 125, 117, 29);
		getContentPane().add(btnReceive);
		
		textField_3 = new JTextArea();
		textField_3.setBounds(45, 237, 193, 231);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextArea();
		textField_4.setBounds(267, 237, 199, 231);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblReceiveMessage = new JLabel("RECEIVE MESSAGE");
		lblReceiveMessage.setBounds(309, 219, 117, 16);
		getContentPane().add(lblReceiveMessage);
		
		JLabel lblSendMessage = new JLabel("SEND MESSAGE");
		lblSendMessage.setBounds(87, 219, 99, 16);
		getContentPane().add(lblSendMessage);
		
		JLabel lblRecipient = new JLabel("THIRD PARTY");
		lblRecipient.setBounds(45, 166, 79, 16);
		getContentPane().add(lblRecipient);
		
		textField_5 = new JTextField();
		textField_5.setBounds(141, 161, 130, 26);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
	}
	
	
//	String serverAddress = "127.0.0.1";
//	int port = 9095;
	
	
	//sSocket clientSocket= new Socket(serverAddress, port);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientInterface gui = new ClientInterface();
		
		gui.setVisible(true);

	}
}
