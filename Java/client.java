import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
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
import javax.swing.table.TableModel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Client extends JFrame {

	private JPanel contentPane;
	private String serverIP;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket connection;
	private int totalCost;
	private JTable shopTable;
			
	public Client(String host) {
		super("Client - PC Shop");
		
		serverIP = host;
		totalCost = 0;
		boolean showingDesktop = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setPreferredSize(new Dimension(400, 300));

		contentPane.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));

		setContentPane(contentPane);

		this.setVisible(true);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(47, 79, 79), 3, true));
		panel.setForeground(new Color(51, 102, 102));
		panel.setBackground(new Color(51, 102, 102));
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon("C:\\Users\\Max\\Documents\\Skola\\Utlandsstudier\\Kurser\\Software Engineering\\Project\\ComputerShop\\Images\\MK.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		
		DefaultListModel shopModel = new DefaultListModel();
		
		JPanel shopPanel = new JPanel();
		shopPanel.setBorder(new LineBorder(new Color(0, 102, 102), 3, true));
		shopPanel.setBackground(new Color(0, 204, 153));
		
		JScrollPane mainScrollPane = new JScrollPane();
		mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1458, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(mainScrollPane, GroupLayout.PREFERRED_SIZE, 1328, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(shopPanel, GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(mainScrollPane, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(shopPanel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panelContainer = new JPanel();
		panelContainer.setBorder(null);
		mainScrollPane.setViewportView(panelContainer);
		panelContainer.setBackground(new Color(0, 102, 102));
		panelContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCheckout = new JButton("Proceed to checkout");
		btnCheckout.setBackground(Color.WHITE);
		btnCheckout.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnCheckout.setVisible(true);

		
		btnCheckout.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  panelContainer.removeAll();
				  panelContainer.revalidate();
				  
				  PaymentPanel payPanel = new PaymentPanel();
				  panelContainer.add(payPanel);
			
				  
			  }
		  }
		);
		
		JLabel lblShoppingCart = new JLabel("");
		lblShoppingCart.setIcon(new ImageIcon("C:\\Users\\Max\\Documents\\Skola\\Utlandsstudier\\Kurser\\Software Engineering\\Project\\ComputerShop\\Images\\cart.png"));
		lblShoppingCart.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblShoppingCart.setVisible(true);
		
		JLabel shopCost = new JLabel("Total Cost:");
		shopCost.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		
		JLabel lblCostValue = new JLabel("0");
		lblCostValue.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		
		JScrollPane tableScrollPane = new JScrollPane();
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnRemoveItem.setBackground(new Color(255, 255, 255));
			
		
		GroupLayout gl_shopPanel = new GroupLayout(shopPanel);
		gl_shopPanel.setHorizontalGroup(
			gl_shopPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_shopPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblShoppingCart, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_shopPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_shopPanel.createSequentialGroup()
							.addComponent(shopCost)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblCostValue))
						.addComponent(btnRemoveItem))
					.addGap(341)
					.addComponent(btnCheckout, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addGap(137))
		);
		gl_shopPanel.setVerticalGroup(
			gl_shopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_shopPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_shopPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_shopPanel.createSequentialGroup()
							.addGroup(gl_shopPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblShoppingCart, GroupLayout.PREFERRED_SIZE, 75, Short.MAX_VALUE)
								.addGroup(gl_shopPanel.createSequentialGroup()
									.addGroup(gl_shopPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(shopCost)
										.addComponent(lblCostValue))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveItem)))
							.addGap(6))
						.addGroup(gl_shopPanel.createSequentialGroup()
							.addComponent(btnCheckout, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(24))
						.addGroup(gl_shopPanel.createSequentialGroup()
							.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		shopTable = new JTable(){
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		tableScrollPane.setViewportView(shopTable);
		shopTable.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		shopTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product", "Type", "Cost", "Description"
			}
		));
		
		btnRemoveItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				  DefaultTableModel tableModel = (DefaultTableModel) shopTable.getModel();
				  int costOfItem = Integer.parseInt(tableModel.getValueAt(shopTable.getSelectedRow(), 2).toString());
				  

				  String oldCost = lblCostValue.getText();
				  oldCost = oldCost.substring(0, oldCost.length() - 1);
				  
				  int newCost = (Integer.parseInt(oldCost) - costOfItem); 
				  totalCost = newCost;
				  System.out.println(newCost);
				  lblCostValue.setText(String.valueOf(newCost)+"€");
				  
				  tableModel.removeRow(shopTable.getSelectedRow());

			}
		});
		
		shopPanel.setLayout(gl_shopPanel);
		
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
						Desktop desktop = new Desktop(variables[0],variables[1],variables[2],variables[3],variables[4],variables[5],Integer.valueOf(variables[6]));
						CustomPanel newPane = new CustomPanel(desktop);	
						panelContainer.add(newPane);
						
						// Button Panel
						JPanel btnPanel = new JPanel();
						btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
						btnPanel.setBackground(new Color(0, 204, 153));
						LayoutManager layout = new FlowLayout();
						btnPanel.setLayout(layout);
						
						newPane.add(btnPanel);
						
						JButton cartBtn = new JButton("Add To Cart");
						cartBtn.setBackground(Color.WHITE);
						cartBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));	
						btnPanel.add(cartBtn);
						cartBtn.addActionListener(new ActionListener() { 
							  public void actionPerformed(ActionEvent e) { 
								  
								  
								  Object[] row = { desktop.getName(), desktop.getType(), desktop.getCost(), desktop.toString() };

								  DefaultTableModel tableModel = (DefaultTableModel) shopTable.getModel();
								  
								  tableModel.addRow(row);

								  totalCost += desktop.getCost();
								  lblCostValue.setText(String.valueOf(totalCost)+"€");
							  }

						  }
						);
						
						JButton customizeBtn = new JButton("Customize");
						customizeBtn.setBackground(Color.WHITE);
						customizeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
						btnPanel.add(customizeBtn);

						customizeBtn.addActionListener(new ActionListener() { 
							  public void actionPerformed(ActionEvent e) { 
								  
								  // Remove all desktops apart from the selected from the panel
								  for (Component cp : panelContainer.getComponents() ){
									  if(cp != customizeBtn.getParent().getParent())
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
										// retrieve the cost of all items
										
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
		
		
		
		
		// Layout
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(icon)
					.addGap(18)
					.addComponent(btnProducts)
					.addGap(30)
					.addContainerGap(287, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(icon)
					.addContainerGap())
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
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
