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
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.ImageIcon;

public class Client extends JFrame {

	private JPanel contentPane;
	private String serverIP;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket connection;
			
	public Client(String host) {
		super("Client - PC Shop");
		
		serverIP = host;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 421);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(128, 128, 128));
		panel.setBackground(new Color(70, 130, 180));
		
		JLabel lblProductList = new JLabel("Pick a Product");
		lblProductList.setVisible(false);
		lblProductList.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		
	
		DefaultListModel shopModel = new DefaultListModel();
		JList shoppingList = new JList(shopModel);
		shoppingList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		shoppingList.setVisible(false);
		
		JLabel lblShoppingCart = new JLabel("Shopping Cart");
		lblShoppingCart.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblShoppingCart.setVisible(false);
		
		JButton btnCheckout = new JButton("Proceed to checkout");
		btnCheckout.setBackground(Color.WHITE);
		btnCheckout.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnCheckout.setVisible(false);
		
		JPanel panelContainer = new JPanel();
		panelContainer.setBackground(new Color(102, 205, 170));

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(43)
									.addComponent(lblProductList))
								.addComponent(panelContainer, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblShoppingCart)
									.addGap(43))
								.addComponent(shoppingList, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnCheckout)
									.addGap(26)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblShoppingCart)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(shoppingList, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnCheckout)
							.addGap(82))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProductList)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panelContainer, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addGap(33))))
		);
		
		JButton btnShoppingCart = new JButton("Shopping Cart");
		btnShoppingCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if(shoppingList.isVisible())
				{
					
					shoppingList.setVisible(false);
					btnCheckout.setVisible(false);
					lblShoppingCart.setVisible(false);

				}
				else
				{
					lblShoppingCart.setVisible(true);
					shoppingList.setVisible(true);
					btnCheckout.setVisible(true);
				}
		
				
			}
		});
		btnShoppingCart.setBackground(Color.WHITE);
		btnShoppingCart.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		
		JPopupMenu categoryMenu = new JPopupMenu();
		categoryMenu.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		categoryMenu.setBackground(Color.WHITE);
		
		JButton btnProducts = new JButton("Browse Shop");
		btnProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
		
                    categoryMenu.show(btnProducts, btnProducts.getWidth()/btnProducts.getWidth(), btnProducts.getHeight());
			}
		});
		btnProducts.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnProducts.setBackground(Color.WHITE);
		
		addPopup(btnProducts, categoryMenu);
		
		JMenuItem mntmDesktop = new JMenuItem("Desktop");
		mntmDesktop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					
					// Send request for All desktops
					output.writeObject(1);
					output.flush();
					
					
					// Store the saved string
					String resultString = ((String)input.readObject());
					String desktops[] = resultString.split("\\r?\\n");
					lblProductList.setVisible(true);
					
					// Dynamically create a Jpanel for each product containing the appropriate information

					for(int i = 0;i<desktops.length;i++)
					{
						CustomPanel newPane = new CustomPanel(desktops[i]);	
						panelContainer.add(newPane);
						JButton addButton = new JButton("Add To Cart");
						newPane.add(addButton);
						
						addButton.addActionListener(new ActionListener() { 
							  public void actionPerformed(ActionEvent e) { 
								  shopModel.addElement(newPane.addToCart());
							  } 
							} );
					}
					
					lblProductList.setText("All available Desktops");
					
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		categoryMenu.add(mntmDesktop);
		
		JMenuItem mntmLaptop = new JMenuItem("Laptop");
		categoryMenu.add(mntmLaptop);
		
		JMenuItem mntmServer = new JMenuItem("Server");
		categoryMenu.add(mntmServer);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon("C:\\Users\\Max\\Documents\\Skola\\Utlandsstudier\\Kurser\\Software Engineering\\Project\\ComputerShop\\Images\\MK.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(icon)
					.addGap(128)
					.addComponent(btnProducts)
					.addGap(33)
					.addComponent(btnShoppingCart)
					.addContainerGap(174, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(icon)
						.addComponent(btnShoppingCart)
						.addComponent(btnProducts))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		contentPane.setLayout(gl_contentPane);
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
		System.out.println("Happy Shopping!");
		while(true) {
	
		}
	}

	private void setupStreams()throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("You are now connected!");
		}

	private void connectToServer() throws IOException {
		System.out.println("Attempting Connection..");
		connection = new Socket(InetAddress.getByName(serverIP),5000);	
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
