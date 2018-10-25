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
		int request;

		while(true) {
			try {
				request = (int) input.readObject();
				
				switch (request) {
	            case 1: getAllDesktops();
	            break;
	            case 2: getAllGPU();
	            break;
	            case 3: getAllCPU();
	            break;
	            case 4: getAllRAM();
	            break;
	            case 5: getAllChassis();
	            break;
				}	  
			
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
	public void getAllDesktops()
	{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery("SELECT \r\n" + 
					"CONCAT(`Graphics`.`gManufacturer`,' ', `Graphics`.`gName`,' ', `Graphics`.`gMemory`,'Gb - ',`Graphics`.`gCost`,'€')\r\n" + 
					"AS 'GPU',\r\n" + 
					"CONCAT(`Chassis`.`chassName`,' - ',`Chassis`.`chassCost`,'€')\r\n" + 
					"AS 'Chassi', \r\n" + 
					"CONCAT(`Processors`.`cManufacturer`,' ',`Processors`.`cName`,' ',`Processors`.`cCores`,' ',`Processors`.`cSpeed`,'Mhz - ',`Processors`.`cCost`,'€')\r\n" + 
					"AS 'CPU',\r\n" + 
					"CONCAT(`Memories`.`mName`,' ',`Memories`.`mClassification`,' ',`Memories`.`mSize`,'Gb ',`Memories`.`mSpeed`,'Mhz - ',`Memories`.`mCost`,'€')\r\n" + 
					"AS'RAM',\r\n" + 
					"`Products`.`pName`,`Products`.`pType`,\r\n" + 
					"CONCAT(`Memories`.`mCost` + `Processors`.`cCost` + `Graphics`.`gCost`+ `Chassis`.`chassCost`) \r\n" + 
					"AS 'Cost',\r\n" + 
					"`Chassis`.`chassImage`\r\n" + 
					"AS 'path'\r\n" + 
					"FROM `Products`	\r\n" + 
					"INNER JOIN `Desktop`\r\n" + 
					"ON `Desktop`.`pid`=`Products`.`pid`\r\n" + 
					"INNER JOIN `Graphics`\r\n" + 
					"ON `Graphics`.`gid` = `Desktop`.`gid`\r\n" + 
					"INNER JOIN `Processors`\r\n" + 
					"ON `Processors`.`cid` = `Products`.`cid`\r\n" + 
					"INNER JOIN `Chassis`\r\n" + 
					"ON `Chassis`.`chassId` = `Desktop`.`chassId`\r\n" + 
					"INNER JOIN `Memories`\r\n" + 
					"ON `Memories`.`mid` = `Products`.`mid`;");

			String send = "";
			while(result.next())
			{				
				send+= result.getString(5) + "," + result.getString(4)+ "," + result.getString(3) + "," + result.getString(6) + "," + result.getString(2) + "," + result.getString(1) + "," + result.getString(7) +"," + result.getString(8) + "\n";

			}
			output.writeObject(send);
			output.flush();
			System.out.println("Server Sending to Client -> " + send);
		}
		catch(Exception exc) {
			   exc.printStackTrace();

		}
	}
	public void getAllChassis()
	{	
		try{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		// Create a statement for query
		Statement myStmt = conn.createStatement();
		// add result from query to result variable
		ResultSet result = myStmt.executeQuery("SELECT `Chassis`.`chassName`, `Chassis`.`chassCost` FROM `Chassis`;");
		String send = "";
		while(result.next())
		{				
			send+= result.getString(1) + " - " + result.getString(2)+"€" + "\n";

		}
		output.writeObject(send);
		output.flush();
		System.out.println("Server Sending to Client -> " + send);
	} catch(Exception exc) {
		   exc.printStackTrace();
		   }
	}
	public void getAllRAM()
	{	
		try{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		// Create a statement for query
		Statement myStmt = conn.createStatement();
		// add result from query to result variable
		ResultSet result = myStmt.executeQuery("SELECT `Memories`.`mName`, `Memories`.`mClassification`, `Memories`.`mSize`, `Memories`.`mSpeed`, `Memories`.`mCost` FROM `Memories`;");
		String send = "";
		while(result.next())
		{				
			send+= result.getString(1) + " " + result.getString(2)+ " " + result.getString(3) + "Gb " + result.getString(4) + "Mhz - " + result.getString(5) +"€"+ "\n";

		}
		output.writeObject(send);
		output.flush();
		System.out.println("Server Sending to Client -> " + send);
	} catch(Exception exc) {
		   exc.printStackTrace();
		   }
	}
	public void getAllCPU()
	{	
		try{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		// Create a statement for query
		Statement myStmt = conn.createStatement();
		// add result from query to result variable
		ResultSet result = myStmt.executeQuery("SELECT `Processors`.`cManufacturer`, `Processors`.`cName`, `Processors`.`cCores`, `Processors`.`cSpeed`, `Processors`.`cCost` FROM `Processors`;");
		String send = "";
		while(result.next())
		{				
			send+= result.getString(1) + " " + result.getString(2)+ " " + result.getString(3) + " " + result.getString(4) + "Mhz - " + result.getString(5) +"€"+ "\n";

		}
		output.writeObject(send);
		output.flush();
		System.out.println("Server Sending to Client -> " + send);
	} catch(Exception exc) {
		   exc.printStackTrace();
		   }
	}
	public void getAllGPU()
	{
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery("SELECT `Graphics`.`gManufacturer`,`Graphics`.`gName`,`Graphics`.`gMemory`, `Graphics`.`gCost` FROM `Graphics`;");
			String send = "";
			while(result.next())
			{				
				send+= result.getString(1) + " " + result.getString(2)+ " " + result.getString(3) + "Gb - " + result.getString(4) + "€" + "\n";

			}
			output.writeObject(send);
			output.flush();
			System.out.println("Server Sending to Client -> " + send);
		}
		catch(Exception exc) {
			   exc.printStackTrace();

		}
	}
	

}
