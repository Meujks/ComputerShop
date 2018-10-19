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
		System.out.println("Server Waiting For Connection..");
		connection = server.accept();
		System.out.println("Server Connected to "+ connection.getInetAddress().getHostName());

	}
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("Streams Successfully Setup");
		
	}
	private void whileRunning() throws IOException {
		System.out.println("Client can begin shopping");
		
		while(true) {
			try {
				System.out.println(input.readObject());
				
				getAllProducts();
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
	public void getAllProducts()
	{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery("select * from Products");
			// print out the name from table
			String allProducts = "";
			
			while(result.next())
			{				
				allProducts += result.getString("pName") + "\n";
			}
			output.writeObject(allProducts);
			output.flush();
			System.out.println("All products have been sent");
		}
		catch(Exception exc) {
			   exc.printStackTrace();

		}
	}

}
