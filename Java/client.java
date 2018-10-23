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
import javax.swing.BoxLayout;
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
import javax.swing.MenuElement;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;

public class Client extends JFrame {

	private JPanel contentPane;
	private String serverIP;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket connection;
	private int totalCost;
			
	public Client(String host) {
		super("Client - PC Shop");
		
		serverIP = host;
		totalCost = 0;
		boolean showingDesktop = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 421);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		setContentPane(contentPane);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		this.setVisible(true);

		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 102, 153), 3, true));
		panel.setForeground(new Color(128, 128, 128));
		panel.setBackground(new Color(0, 102, 153));
		
	
		DefaultListModel shopModel = new DefaultListModel();
		
		JPanel panelContainer = new JPanel();
		panelContainer.setBackground(new Color(0, 102, 102));
		
		JPanel shopPanel = new JPanel();
		shopPanel.setBackground(new Color(0, 204, 153));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelContainer, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(shopPanel, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelContainer, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(shopPanel, GroupLayout.PREFERRED_SIZE, 65, Short.MAX_VALUE))
		);
		JList shoppingList = new JList(shopModel);
		shoppingList.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		shoppingList.setBackground(new Color(240, 248, 255));
		shoppingList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		shoppingList.setVisible(true);
		
		JButton btnCheckout = new JButton("Proceed to checkout");
		btnCheckout.setBackground(Color.WHITE);
		btnCheckout.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnCheckout.setVisible(true);
		
		JLabel lblShoppingCart = new JLabel("Shopping Cart");
		lblShoppingCart.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblShoppingCart.setVisible(true);
		
		JLabel shopCost = new JLabel("Total Cost:");
		shopCost.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		
		JLabel lblCostValue = new JLabel("0");
		lblCostValue.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		GroupLayout gl_shopPanel = new GroupLayout(shopPanel);
		gl_shopPanel.setHorizontalGroup(
			gl_shopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_shopPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_shopPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_shopPanel.createSequentialGroup()
							.addComponent(shoppingList, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(shopCost)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCostValue)
							.addGap(208)
							.addComponent(btnCheckout))
						.addComponent(lblShoppingCart, Alignment.LEADING))
					.addContainerGap())
		);
		gl_shopPanel.setVerticalGroup(
			gl_shopPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_shopPanel.createSequentialGroup()
					.addComponent(lblShoppingCart)
					.addGap(6)
					.addGroup(gl_shopPanel.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_shopPanel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_shopPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(shoppingList, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
								.addComponent(shopCost)
								.addComponent(lblCostValue)))
						.addComponent(btnCheckout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(3))
		);
		shopPanel.setLayout(gl_shopPanel);
		panelContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		panelContainer.add(scrollPane);
		
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
		btnShoppingCart.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		
		JPopupMenu categoryMenu = new JPopupMenu();
		categoryMenu.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		categoryMenu.setBackground(Color.WHITE);

		JButton btnProducts = new JButton("Browse Shop");
		
		btnProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
		
                    categoryMenu.show(btnProducts, btnProducts.getWidth()/btnProducts.getWidth(), btnProducts.getHeight());
            		categoryMenu.setPopupSize(btnProducts.getWidth(),btnProducts.getHeight() * 4);

			}
		});

		btnProducts.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		btnProducts.setBackground(new Color(255, 255, 255));
		
		addPopup(btnProducts, categoryMenu);
		
		JMenuItem mntmDesktop = new JMenuItem("Desktops");
		mntmDesktop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					// Send request for All desktops
					output.writeObject(1);
					output.flush();
					
					// Store the saved string 
					String resultString = ((String)input.readObject());
					
					// Split each row
					String desktops[] = resultString.split("\\r?\\n");
					// Show label displaying title
					
					// Dynamically create a JPanel for each product containing the appropriate information

					// Clear panelContainer before adding components
					panelContainer.removeAll();
					panelContainer.revalidate();
					for(int i = 0;i<desktops.length;i++)
					{
						// for each iteration assign all variables from each row
						String variables[] = desktops[i].split(",");
						
						CustomPanel newPane = new CustomPanel(variables[0],variables[1],variables[2],variables[3],variables[4],variables[5]);	
						panelContainer.add(newPane);
						
						JButton customizeBtn = new JButton("Customize");
						customizeBtn.setBackground(Color.WHITE);
						customizeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
						newPane.add(customizeBtn);
						
						JButton cartBtn = new JButton("Add To Cart");
						cartBtn.setBackground(Color.WHITE);
						cartBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));	
						newPane.add(cartBtn);
						cartBtn.addActionListener(new ActionListener() { 
							  public void actionPerformed(ActionEvent e) { 
								  Desktop desktop = newPane.getDesktop();
								  shopModel.addElement(desktop);
								  totalCost += newPane.getCostOfDesktop();
								  lblCostValue.setText(String.valueOf(totalCost)+"€");
							  }

						  }
						);

						customizeBtn.addActionListener(new ActionListener() { 
							  public void actionPerformed(ActionEvent e) { 
								  
								  // Remove all desktops apart from the selected from the panel
								  for (Component cp : panelContainer.getComponents() ){
									  if(cp != customizeBtn.getParent())
								        cp.setVisible(false);
								 }
								  
								  // hide the customize button
								  customizeBtn.setVisible(false);
									try {
										// Fetch all components from the database, store them into appropriate strings
										output.writeObject(2);
										output.flush();
										String GPU = ((String)input.readObject());

										output.writeObject(3);
										output.flush();
										String CPU = ((String)input.readObject());
										
										output.writeObject(4);
										output.flush();
										String RAM = ((String)input.readObject());
										
										output.writeObject(5);
										output.flush();
										String Chassi = ((String)input.readObject());	
										
										// Cut the strings and separate on each row
										String graphics[] = GPU.split("\\r?\\n");
										String processors[] = CPU.split("\\r?\\n");
										String memories[] = RAM.split("\\r?\\n");
										String cases[] = Chassi.split("\\r?\\n");
										
										// Create the component tabel with appropriate values
										ComponentPanel compPanel = new ComponentPanel(graphics,processors,memories,cases,variables[0],variables[1],variables[2],variables[3],variables[4],variables[5]);	
										panelContainer.add(compPanel);
										
										//Create a button for changing values between compPanel and CustomPanel
										JButton changeBtn = new JButton("Change");
										changeBtn.setBackground(Color.WHITE);
										changeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
										compPanel.add(changeBtn);

										// Get the selected value from compPanel and add it to the current configuration on the custom panel
										changeBtn.addActionListener(new ActionListener() { 
											  public void actionPerformed(ActionEvent e) { 
												 // Update the name of component to Custom
												 newPane.updateLabel();
												 // Store index and name of component being changed
												 int itemIndex = compPanel.getSelectedIndex();
												 String itemValue = compPanel.getSelectedElement();
												 // Update the configuration by passing the necessary values
												 newPane.updateEvent(itemIndex, itemValue);
												 
											  }
										  }
										);
									} catch (IOException | ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							  } 
							} );
					}
					
					
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		categoryMenu.add(mntmDesktop);
		
		JMenuItem mntmLaptop = new JMenuItem("Laptops");
		categoryMenu.add(mntmLaptop);
		
		JMenuItem mntmServer = new JMenuItem("Servers");
		categoryMenu.add(mntmServer);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon("C:\\Users\\Max\\Documents\\Skola\\Utlandsstudier\\Kurser\\Software Engineering\\Project\\ComputerShop\\Images\\MK.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(icon)
					.addGap(18)
					.addComponent(btnProducts)
					.addGap(30)
					.addComponent(btnShoppingCart)
					.addContainerGap(287, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(icon)
					.addContainerGap())
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnShoppingCart, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnProducts, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
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
