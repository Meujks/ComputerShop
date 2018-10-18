import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {
	
	  private Socket connection;
	  private ServerSocket server;
	  private ObjectInputStream input;
	  private ObjectOutputStream output;
	  
	
	  public Server()
	  {
		  
	  }
	  public void startServer()
	  {
		  try {
			  server = new ServerSocket(5000,100);
			  
			  while(true)
			  {
				  try{
					  waitForConnection();
					  setupStreams();
					  whileRunning();
				  }catch(IOException exc){
					 System.out.println("Server ended the connection!");
				  }finally {
					  closeServer();
				  }
			  }
		  }catch(IOException exc) {
			  exc.printStackTrace();
		  }
		  
	  }
	private void waitForConnection() throws IOException{
		System.out.println("Waiting For Connection..");
		connection = server.accept();
		System.out.println("Connected to "+ connection.getInetAddress().getHostName());

	}
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("Streams Successfully Setup");
		
	}
	private void whileRunning() throws IOException {
		System.out.println("Client can begin shopping");
		
	}
	private void closeServer() {
		System.out.println("Closing Server..");
		
		try {
			output.close();
			input.close();
			connection.close();
			
		}catch(IOException exc) {
			exc.printStackTrace();
		}
		
	}
	public static void getAllProducts()
	{
		try{
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery("select * from Products");
			// print out the name from table
			while(result.next())
			{
				System.out.println(result.getString("pName"));
			}
		}
		catch(Exception exc) {
			   exc.printStackTrace();

		}
	}

}
