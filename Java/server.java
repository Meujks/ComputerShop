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
	private Connection conn;
	private Statement myStmt;
	private ResultSet result;
	private PreparedStatement pstmt;
	private EmailSend emailObject;

	public Server() {

	}

	public void startServer() {
		try {
			this.server = new ServerSocket(5000, 100);

			while (true) {
				try {
					this.waitForConnection();
					this.setupStreams();
					this.whileRunning();
				} catch (IOException exc) {
					System.out.println("Server ended the connection!");
				} finally {
					this.closeServer();
				}
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}

	}

	private void waitForConnection() throws IOException {
		System.out.println("Server Waiting For Connection..");
		this.connection = server.accept();
		System.out.println("Server Connected to " + connection.getInetAddress().getHostName());

	}

	private void setupStreams() throws IOException {
		this.output = new ObjectOutputStream(connection.getOutputStream());
		this.output.flush();
		this.input = new ObjectInputStream(connection.getInputStream());
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
						this.getAllDesktops();
						break;
					case 2:
						this.getAllGPU();
						break;
					case 3:
						this.getAllCPU();
						break;
					case 4:
						this.getAllRAM();
						break;
					case 5:
						this.getAllChassis();
						break;
					case 6:
						this.getAllLaptops();
						break;
					case 7:
						this.getAllServers();
						break;
					case 8:
						this.getAllOrders();
						break;
					}
				} else if (request instanceof String[]) {
					this.sendOrder((String[]) request);
				} else if (request instanceof String) {
					String result[] = ((String) request).split(",");

					switch (result[0]) {
					case "Fetch":
						this.getOrder(result[1]);
						break;
					case "Remove":
						this.removeOrder(result[1]);
						break;
					case "Shipped":
						this.changeToShipped(result[1]);
						break;
					case "NotShipped":
						this.changeToNotShipped(result[1]);
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
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = this.conn.createStatement();
			// add result from query to result variable
			this.result = myStmt.executeQuery("SELECT * FROM `ordertable`;");
			String send = "";
			while (result.next()) {
				send += result.getString(1) + "," + result.getString(2) + "," + result.getString(3) + ","
						+ result.getString(5) + "\n";
			}

			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void changeToNotShipped(String orderId) {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			// add result from query to result variable
			this.pstmt = conn.prepareStatement("UPDATE `ordertable` SET `oStatus`= \"Shipped\" WHERE `oid` = ?;");
			this.pstmt.setString(1, orderId);
			String send = "Success";

			int result = pstmt.executeUpdate();
			if (result == 0) {
				send = "Fail";
			}

			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = conn.createStatement();
			// add result from query to result variable
			this.result = myStmt
					.executeQuery("SELECT * FROM `ordertable` WHERE `ordertable`.`oid` = '" + orderId + "';");
			String send = "";
			while (this.result.next()) {
				send += this.result.getString(1) + "," + this.result.getString(2) + "," + this.result.getString(3) + ","
						+ this.result.getString(4) + "," + this.result.getString(5) + "\n";

			}
			String variables[] = send.split(",");
			emailObject = new EmailSend(variables[1], variables[2], variables[3], orderId);

			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void changeToShipped(String orderId) {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			// add result from query to result variable
			this.pstmt = this.conn
					.prepareStatement("UPDATE `ordertable` SET `oStatus`= \"Not Shipped\" WHERE `oid` = ?;");
			this.pstmt.setString(1, orderId);
			String send = "Success";

			int result = this.pstmt.executeUpdate();
			if (result == 0) {
				send = "Fail";
			}

			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void getAllServers() {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = this.conn.createStatement();
			// add result from query to result variable
			this.result = this.myStmt.executeQuery("SELECT \r\n" + "`Products`.`pName`, `Products`.`pType`,\r\n"
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
			while (this.result.next()) {
				send += this.result.getString(1) + "," + this.result.getString(2) + "," + this.result.getString(3) + ","
						+ this.result.getString(4) + "," + this.result.getString(5) + "," + this.result.getString(6)
						+ "," + this.result.getString(7) + "," + this.result.getString(8) + ","
						+ this.result.getString(9) + "\n";

			}
			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}

	}

	private void getAllLaptops() {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = this.conn.createStatement();
			// add result from query to result variable
			this.result = this.myStmt.executeQuery("SELECT \r\n" + "`Products`.`pName`,`Products`.`pType`,\r\n"
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
			while (this.result.next()) {
				send += this.result.getString(1) + "," + this.result.getString(2) + "," + this.result.getString(3) + ","
						+ this.result.getString(4) + "," + this.result.getString(5) + "," + this.result.getString(6)
						+ "," + this.result.getString(7) + "," + this.result.getString(8) + ","
						+ this.result.getString(9) + "\n";

			}
			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void removeOrder(String orderId) {

		String variables[] = null;
		String send = "Success";
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = conn.createStatement();
			// add result from query to result variable
			this.result = myStmt
					.executeQuery("SELECT * FROM `ordertable` WHERE `ordertable`.`oid` = '" + orderId + "';");
			String msg = "";
			while (this.result.next()) {
				msg += this.result.getString(1) + "," + this.result.getString(2) + "," + this.result.getString(3) + ","
						+ this.result.getString(4) + "," + this.result.getString(5) + "\n";

			}
			variables = msg.split(",");

			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			// add result from query to result variable
			this.pstmt = conn.prepareStatement(
					"DELETE FROM `Ordertable` WHERE `Ordertable`.`oid` = ? AND `OrderTable`.`oStatus` =\"Not Shipped\"; ");
			this.pstmt.setString(1, orderId);
			int result = pstmt.executeUpdate();
			if (result == 0) {
				send = "Fail";
			}

			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}

		if (send == "Success") {
			emailObject = new EmailSend(variables[1], variables[2], orderId);

		}

	}

	private void getOrder(String orderId) {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = conn.createStatement();
			// add result from query to result variable
			this.result = myStmt
					.executeQuery("SELECT * FROM `ordertable` WHERE `ordertable`.`oid` = '" + orderId + "';");
			String send = "";
			while (this.result.next()) {
				send += this.result.getString(1) + "," + this.result.getString(2) + "," + this.result.getString(3) + ","
						+ this.result.getString(4) + "," + this.result.getString(5) + "\n";

			}
			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private void insertOrder(String orderId, String fName, String lName, String email, String items) {

		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			String oName = fName + " " + lName;
			// add result from query to result variable
			this.pstmt = conn.prepareStatement(
					"INSERT INTO `OrderTable`(oid,oCustomerName,oCustomerEmail,oItems,oStatus) VALUE (?,?,?,?,?);");
			this.pstmt.setString(1, orderId);
			this.pstmt.setString(2, oName);
			this.pstmt.setString(3, email);
			this.pstmt.setString(4, items);
			this.pstmt.setString(5, "Not Shipped");

			this.pstmt.executeUpdate();

			this.conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private void sendOrder(String[] values) {

		// Create an emailSend object which creates an email and sends it to the user.

		if (values.length == 8) {
			String cardString = values[6];

			cardString = cardString.substring(0, Math.min(cardString.length(), 4));
			this.emailObject = new EmailSend(values[0], values[1], values[2], values[3], values[4], values[5],
					cardString, values[7]);
			String orderId = emailObject.getOrderID();
			insertOrder(orderId, values[0], values[1], values[2], values[7]);
		} else if (values.length == 6) {
			this.emailObject = new EmailSend(values[0], values[1], values[2], values[3], values[4], values[5]);
			String orderId = emailObject.getOrderID();
			insertOrder(orderId, values[0], values[1], values[2], values[5]);
		}
	}

	private void closeServer() {
		System.out.println("Closing Server..");

		try {
			this.output.close();
			this.input.close();
			this.connection.close();

		} catch (IOException exc) {
			exc.printStackTrace();
		}

	}

	public void getAllDesktops() {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = this.conn.createStatement();
			// add result from query to result variable
			this.result = this.myStmt.executeQuery("SELECT \r\n"
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
			while (this.result.next()) {
				send += this.result.getString(5) + "," + this.result.getString(4) + "," + this.result.getString(3) + ","
						+ this.result.getString(6) + "," + this.result.getString(2) + "," + this.result.getString(1)
						+ "," + this.result.getString(7) + "," + this.result.getString(8) + "\n";

			}
			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void getAllChassis() {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = this.conn.createStatement();
			// add result from query to result variable
			this.result = this.myStmt
					.executeQuery("SELECT `Chassis`.`chassName`, `Chassis`.`chassCost` FROM `Chassis`;");
			String send = "";
			while (this.result.next()) {
				send += this.result.getString(1) + " - " + this.result.getString(2) + "€" + "\n";

			}
			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void getAllRAM() {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = conn.createStatement();
			// add result from query to result variable
			this.result = myStmt.executeQuery(
					"SELECT `Memories`.`mName`, `Memories`.`mClassification`, `Memories`.`mSize`, `Memories`.`mSpeed`, `Memories`.`mCost` FROM `Memories`;");
			String send = "";
			while (this.result.next()) {
				send += this.result.getString(1) + " " + this.result.getString(2) + " " + this.result.getString(3)
						+ "Gb " + this.result.getString(4) + "Mhz - " + this.result.getString(5) + "€" + "\n";

			}
			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void getAllCPU() {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = this.conn.createStatement();
			// add result from query to result variable
			this.result = this.myStmt.executeQuery(
					"SELECT `Processors`.`cManufacturer`, `Processors`.`cName`, `Processors`.`cCores`, `Processors`.`cSpeed`, `Processors`.`cCost` FROM `Processors`;");
			String send = "";
			while (this.result.next()) {
				send += this.result.getString(1) + " " + this.result.getString(2) + " " + this.result.getString(3) + " "
						+ this.result.getString(4) + "Mhz - " + this.result.getString(5) + "€" + "\n";

			}
			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void getAllGPU() {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ShopDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// Create a statement for query
			this.myStmt = conn.createStatement();
			// add result from query to result variable
			this.result = myStmt.executeQuery(
					"SELECT `Graphics`.`gManufacturer`,`Graphics`.`gName`,`Graphics`.`gMemory`, `Graphics`.`gCost` FROM `Graphics`;");
			String send = "";
			while (this.result.next()) {
				send += this.result.getString(1) + " " + this.result.getString(2) + " " + this.result.getString(3)
						+ "Gb - " + this.result.getString(4) + "€" + "\n";

			}
			this.output.writeObject(send);
			this.output.flush();
			this.conn.close();

		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

}
