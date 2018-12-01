import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {

	private Socket connection;
	private ServerSocket server;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	public Server() {

	}

	public void startServer() {
		try {
			server = new ServerSocket(5000, 100);

			while (true) {
				try {
					waitForConnection();
					setupStreams();
					whileRunning();
				} catch (IOException exc) {
					System.out.println("Server ended the connection!");
				} finally {
					closeServer();
				}
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}

	}

	private void waitForConnection() throws IOException {
		System.out.println("Server Waiting For Connection..");
		connection = server.accept();
		System.out.println("Server Connected to " + connection.getInetAddress().getHostName());

	}

	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("Streams Successfully Setup");

	}

	private void whileRunning() throws IOException {

		System.out.println("Client can begin shopping");
		Object request;

		while (true) {
			try {
				request = input.readObject();
				if (request instanceof Integer) {

					switch ((int) request) {
					case 1:
						getAllDesktops();
						break;
					case 2:
						getAllGPU();
						break;
					case 3:
						getAllCPU();
						break;
					case 4:
						getAllRAM();
						break;
					case 5:
						getAllChassis();
						break;
					case 6:
						getAllLaptops();
						break;
					case 7:
						getAllServers();
						break;
					case 8:
						getAllOrders();
						break;
					}
				} else if (request instanceof String[]) {
					sendOrder((String[]) request);
				} else if (request instanceof String) {
					String result[] = ((String) request).split(",");

					switch (result[0]) {
					case "Fetch":
						getOrder(result[1]);
						break;
					case "Remove":
						removeOrder(result[1]);
						break;
					case "Shipped":
						changeToShipped(result[1]);
						break;
					case "NotShipped":
						changeToNotShipped(result[1]);
						break;
					}
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	private void getAllOrders() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery("SELECT * FROM `ordertable`;");
			String send = "";
			while (result.next()) {
				send += result.getString(1) + "," + result.getString(2) + "," + result.getString(3) + "," + result.getString(5) + "\n";
			}
			
			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void changeToNotShipped(String orderId) {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			// add result from query to result variable
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE `ordertable` SET `oStatus`= \"Shipped\" WHERE `oid` = ?;");
			pstmt.setString(1, orderId);
			String send = "Success";

			int result = pstmt.executeUpdate();
			if (result == 0) {
				send = "Fail";
			}

			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void changeToShipped(String orderId) {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			// add result from query to result variable
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE `ordertable` SET `oStatus`= \"Not Shipped\" WHERE `oid` = ?;");
			pstmt.setString(1, orderId);
			String send = "Success";

			int result = pstmt.executeUpdate();
			if (result == 0) {
				send = "Fail";
			}

			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void getAllServers() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery("SELECT \r\n" + "`Products`.`pName`, `Products`.`pType`,\r\n"
					+ "`Server`.`sFormFactor`,`Server`.`sScalability`,\r\n"
					+ "CONCAT(`serverChassis`.`sChassName`,' - ',`serverChassis`.`sChassCost`,'€')\r\n"
					+ "AS 'Chassi', \r\n"
					+ "CONCAT(`Processors`.`cManufacturer`,' ',`Processors`.`cName`,' ',`Processors`.`cCores`,' ',`Processors`.`cSpeed`,'Mhz - ',`Processors`.`cCost`,'€')\r\n"
					+ "AS 'CPU',\r\n"
					+ "CONCAT(`Memories`.`mName`,' ',`Memories`.`mClassification`,' ',`Memories`.`mSize`,'Gb ',`Memories`.`mSpeed`,'Mhz - ',`Memories`.`mCost`,'€')\r\n"
					+ "AS'RAM',\r\n"
					+ "CONCAT(`Memories`.`mCost` + `Processors`.`cCost` + `serverChassis`.`sChassCost`) \r\n"
					+ "AS 'Cost',\r\n" + "`serverChassis`.`sChassImage`\r\n" + "AS 'path'\r\n"
					+ "FROM `Products`	\r\n" + "INNER JOIN `Server`\r\n" + "ON `Server`.`pid`=`Products`.`pid`\r\n"
					+ "INNER JOIN `Processors`\r\n" + "ON `Processors`.`cid` = `Products`.`cid`\r\n"
					+ "INNER JOIN `serverChassis`\r\n" + "ON `serverChassis`.`sChassId` = `Server`.`sChassId`\r\n"
					+ "INNER JOIN `Memories`\r\n" + "ON `Memories`.`mid` = `Products`.`mid`;");

			String send = "";
			while (result.next()) {
				send += result.getString(1) + "," + result.getString(2) + "," + result.getString(3) + ","
						+ result.getString(4) + "," + result.getString(5) + "," + result.getString(6) + ","
						+ result.getString(7) + "," + result.getString(8) + "," + result.getString(9) + "\n";

			}
			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}

	}

	private void getAllLaptops() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery("SELECT \r\n" + "`Products`.`pName`,`Products`.`pType`,\r\n"
					+ "CONCAT(`laptopChassis`.`lChassName`,' - ',`laptopChassis`.`lChassCost`,'€')\r\n"
					+ "AS 'Chassi', \r\n"
					+ "CONCAT(`Graphics`.`gManufacturer`,' ', `Graphics`.`gName`,' ', `Graphics`.`gMemory`,'Gb - ',`Graphics`.`gCost`,'€')\r\n"
					+ "AS 'GPU',\r\n"
					+ "CONCAT(`Processors`.`cManufacturer`,' ',`Processors`.`cName`,' ',`Processors`.`cCores`,' ',`Processors`.`cSpeed`,'Mhz - ',`Processors`.`cCost`,'€')\r\n"
					+ "AS 'CPU',\r\n"
					+ "CONCAT(`Memories`.`mName`,' ',`Memories`.`mClassification`,' ',`Memories`.`mSize`,'Gb ',`Memories`.`mSpeed`,'Mhz - ',`Memories`.`mCost`,'€')\r\n"
					+ "AS'RAM',\r\n"
					+ "CONCAT(`Memories`.`mCost` + `Processors`.`cCost` + `Graphics`.`gCost`+ `laptopChassis`.`lChassCost`) \r\n"
					+ "AS 'Cost',\r\n" + "`laptopChassis`.`lChassImage`\r\n" + "AS 'path',\r\n"
					+ "`Laptop`.`inches`\r\n" + "FROM `Products`	\r\n" + "INNER JOIN `Laptop`\r\n"
					+ "ON `Laptop`.`pid`=`Products`.`pid`\r\n" + "INNER JOIN `Graphics`\r\n"
					+ "ON `Graphics`.`gid` = `Laptop`.`gid`\r\n" + "INNER JOIN `Processors`\r\n"
					+ "ON `Processors`.`cid` = `Products`.`cid`\r\n" + "INNER JOIN `laptopChassis`\r\n"
					+ "ON `laptopChassis`.`lChassId` = `Laptop`.`lChassId`\r\n" + "INNER JOIN `Memories`\r\n"
					+ "ON `Memories`.`mid` = `Products`.`mid`;");

			String send = "";
			while (result.next()) {
				send += result.getString(1) + "," + result.getString(2) + "," + result.getString(3) + ","
						+ result.getString(4) + "," + result.getString(5) + "," + result.getString(6) + ","
						+ result.getString(7) + "," + result.getString(8) + "," + result.getString(9) + "\n";

			}
			output.writeObject(send);
			output.flush();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();

		}

	}

	private void removeOrder(String orderId) {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			// add result from query to result variable
			PreparedStatement pstmt = conn.prepareStatement(
					"DELETE FROM `Ordertable` WHERE `Ordertable`.`oid` = ? AND `OrderTable`.`oStatus` =\"Not Shipped\"; ");
			pstmt.setString(1, orderId);
			String send = "Success";

			int result = pstmt.executeUpdate();
			if (result == 0) {
				send = "Fail";
			}

			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void getOrder(String orderId) {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt
					.executeQuery("SELECT * FROM `ordertable` WHERE `ordertable`.`oid` = '" + orderId + "';");
			String send = "";
			while (result.next()) {
				send += result.getString(1) + "," + result.getString(2) + "," + result.getString(3) + ","
						+ result.getString(4) + "," + result.getString(5) + "\n";

			}
			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private void insertOrder(String orderId, String fName, String lName, String email, String items) {

		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			String oName = fName + " " + lName;
			// add result from query to result variable
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO `OrderTable`(oid,oCustomerName,oCustomerEmail,oItems,oStatus) VALUE (?,?,?,?,?);");
			pstmt.setString(1, orderId);
			pstmt.setString(2, oName);
			pstmt.setString(3, email);
			pstmt.setString(4, items);
			pstmt.setString(5, "Not Shipped");

			pstmt.executeUpdate();

			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private void sendOrder(String[] values) {

		// Create an emailSend object which creates an email and sends it to the user.

		if (values.length == 8) {
			String cardString = values[6];

			cardString = cardString.substring(0, Math.min(cardString.length(), 4));
			EmailSend emailObject = new EmailSend(values[0], values[1], values[2], values[3], values[4], values[5],
					cardString, values[7]);
			String orderId = emailObject.getOrderID();
			insertOrder(orderId, values[0], values[1], values[2], values[7]);
		} else if (values.length == 6) {
			EmailSend emailObject = new EmailSend(values[0], values[1], values[2], values[3], values[4], values[5]);
			String orderId = emailObject.getOrderID();
			insertOrder(orderId, values[0], values[1], values[2], values[5]);
		}
	}

	private void closeServer() {
		System.out.println("Closing Server..");

		try {
			output.close();
			input.close();
			connection.close();

		} catch (IOException exc) {
			exc.printStackTrace();
		}

	}

	public void getAllDesktops() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery("SELECT \r\n"
					+ "CONCAT(`Graphics`.`gManufacturer`,' ', `Graphics`.`gName`,' ', `Graphics`.`gMemory`,'Gb - ',`Graphics`.`gCost`,'€')\r\n"
					+ "AS 'GPU',\r\n" + "CONCAT(`Chassis`.`chassName`,' - ',`Chassis`.`chassCost`,'€')\r\n"
					+ "AS 'Chassi', \r\n"
					+ "CONCAT(`Processors`.`cManufacturer`,' ',`Processors`.`cName`,' ',`Processors`.`cCores`,' ',`Processors`.`cSpeed`,'Mhz - ',`Processors`.`cCost`,'€')\r\n"
					+ "AS 'CPU',\r\n"
					+ "CONCAT(`Memories`.`mName`,' ',`Memories`.`mClassification`,' ',`Memories`.`mSize`,'Gb ',`Memories`.`mSpeed`,'Mhz - ',`Memories`.`mCost`,'€')\r\n"
					+ "AS'RAM',\r\n" + "`Products`.`pName`,`Products`.`pType`,\r\n"
					+ "CONCAT(`Memories`.`mCost` + `Processors`.`cCost` + `Graphics`.`gCost`+ `Chassis`.`chassCost`) \r\n"
					+ "AS 'Cost',\r\n" + "`Chassis`.`chassImage`\r\n" + "AS 'path'\r\n" + "FROM `Products`	\r\n"
					+ "INNER JOIN `Desktop`\r\n" + "ON `Desktop`.`pid`=`Products`.`pid`\r\n"
					+ "INNER JOIN `Graphics`\r\n" + "ON `Graphics`.`gid` = `Desktop`.`gid`\r\n"
					+ "INNER JOIN `Processors`\r\n" + "ON `Processors`.`cid` = `Products`.`cid`\r\n"
					+ "INNER JOIN `Chassis`\r\n" + "ON `Chassis`.`chassId` = `Desktop`.`chassId`\r\n"
					+ "INNER JOIN `Memories`\r\n" + "ON `Memories`.`mid` = `Products`.`mid`;");

			String send = "";
			while (result.next()) {
				send += result.getString(5) + "," + result.getString(4) + "," + result.getString(3) + ","
						+ result.getString(6) + "," + result.getString(2) + "," + result.getString(1) + ","
						+ result.getString(7) + "," + result.getString(8) + "\n";

			}
			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void getAllChassis() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt
					.executeQuery("SELECT `Chassis`.`chassName`, `Chassis`.`chassCost` FROM `Chassis`;");
			String send = "";
			while (result.next()) {
				send += result.getString(1) + " - " + result.getString(2) + "€" + "\n";

			}
			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void getAllRAM() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery(
					"SELECT `Memories`.`mName`, `Memories`.`mClassification`, `Memories`.`mSize`, `Memories`.`mSpeed`, `Memories`.`mCost` FROM `Memories`;");
			String send = "";
			while (result.next()) {
				send += result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + "Gb "
						+ result.getString(4) + "Mhz - " + result.getString(5) + "€" + "\n";

			}
			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void getAllCPU() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery(
					"SELECT `Processors`.`cManufacturer`, `Processors`.`cName`, `Processors`.`cCores`, `Processors`.`cSpeed`, `Processors`.`cCost` FROM `Processors`;");
			String send = "";
			while (result.next()) {
				send += result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + " "
						+ result.getString(4) + "Mhz - " + result.getString(5) + "€" + "\n";

			}
			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void getAllGPU() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			Statement myStmt = conn.createStatement();
			// add result from query to result variable
			ResultSet result = myStmt.executeQuery(
					"SELECT `Graphics`.`gManufacturer`,`Graphics`.`gName`,`Graphics`.`gMemory`, `Graphics`.`gCost` FROM `Graphics`;");
			String send = "";
			while (result.next()) {
				send += result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + "Gb - "
						+ result.getString(4) + "€" + "\n";

			}
			output.writeObject(send);
			output.flush();
			conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

}
