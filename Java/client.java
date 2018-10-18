import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {

	private JPanel contentPane;
	private String serverIP;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private JTextField userText;
	private Socket connection;
			
	public Client(String host) {
		super("Client");
		
		serverIP = host;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	  public void startClient()
	  {
		  try {
			  connectToServer();
			  setupStreams();
			  whileRunning();
			  
		  }catch(EOFException exc)
		  {
			  System.out.println("\n Client Terminated Connection");
		  }catch(IOException ioexc)
		  {
			  ioexc.printStackTrace();
		  }finally {
			  closeClient();
		  }
		  
		  
	  }

	private void closeClient() {
		System.out.println("Closing Client \n");
		try {
			output.close();
			input.close();
			connection.close();
			
		}catch(IOException exc)
		{
			exc.printStackTrace();
		}
		
	}

	private void whileRunning() throws IOException {
		
		while(true)
		{
			
		}
		
	}

	private void setupStreams()throws IOException{
		
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("You are now connected!");
	}

	private void connectToServer() throws IOException {
		System.out.println("Attempting Connection.. \n");
		connection = new Socket(InetAddress.getByName(serverIP),5000);
		
		
	}

}
