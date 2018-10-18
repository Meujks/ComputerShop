import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server {

	public static void main(String[] args)
	{
		try
		{
			// Create connection to the associated Database located at localhost 
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
