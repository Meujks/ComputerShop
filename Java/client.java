import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Client extends JFrame {

	private JPanel contentPane, panel, shopPanel, panelContainer;
	private JScrollPane mainScrollPane, tableScrollPane;
	private JLabel icon, lblCostValue, lblShoppingCart, shopCost;
	private GroupLayout gl_contentPane, gl_shopPanel, gl_panel;
	private JButton btnCheckout, btnRemoveItem, btnProducts, btnViewOrders;
	private JPopupMenu categoryMenu;
	private JMenuItem mntmDesktop, mntmLaptop, mntmServer;

	private CustomPanel newPane;
	private PaymentPanel payPanel;
	private ViewPanel viewPanel;
	private ImageIcon newImg;
	private String serverIP;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket connection;
	private int totalCost;
	private JTable shopTable;

	public Client(String host) {
		super("Client - PC Shop");

		this.serverIP = host;
		this.totalCost = 0;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setBounds(100, 100, 1366, 768);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(128, 203, 196));
		this.contentPane.setPreferredSize(new Dimension(400, 300));
		this.contentPane.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		this.setContentPane(contentPane);
		this.setVisible(true);
		this.panel = new JPanel();
		this.panel.setBorder(new LineBorder(new Color(79, 154, 148), 3, true));
		this.panel.setForeground(new Color(79, 154, 148));

		this.panel.setBackground(new Color(79, 154, 148));

		this.icon = new JLabel("");
		this.icon.setIcon(new ImageIcon(getClass().getResource("/Images/MK.png")));

		this.shopPanel = new JPanel();
		this.shopPanel.setBorder(new LineBorder(new Color(79, 154, 148), 3, true));
		this.shopPanel.setBackground(new Color(79, 154, 148));

		this.mainScrollPane = new JScrollPane();
		this.mainScrollPane.setBorder(BorderFactory.createEmptyBorder());

		this.gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(mainScrollPane, GroupLayout.PREFERRED_SIZE, 1328,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
						.addComponent(shopPanel, GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE).addGap(8)
						.addComponent(mainScrollPane, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(shopPanel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)));

		this.lblCostValue = new JLabel("0");
		this.lblCostValue.setFont(new Font("Yu Gothic", Font.BOLD, 16));

		this.panelContainer = new JPanel();
		this.panelContainer.setBorder(null);
		this.mainScrollPane.setViewportView(this.panelContainer);
		this.panelContainer.setBackground(new Color(128, 203, 196));
		this.panelContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.btnCheckout = new JButton("Proceed to checkout");
		this.btnCheckout.setBackground(Color.WHITE);
		this.btnCheckout.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.btnCheckout.setVisible(true);

		this.btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelContainer.removeAll();
				panelContainer.revalidate();
				panelContainer.updateUI();

				// Fetch all items from the last row of the table which holds a tostring of each
				// object
				String itemsFromTable = "";
				String costString = lblCostValue.getText().replace("€", "");
				int theCost = Integer.parseInt(costString);
				updateTotalCost(theCost);
				System.out.println(totalCost);

				DefaultTableModel tableModel = (DefaultTableModel) shopTable.getModel();
				// For each row
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					itemsFromTable += tableModel.getValueAt(i, 3).toString() + "\n";
				}
				// send items to paymentPanel
				payPanel = new PaymentPanel(itemsFromTable);
				panelContainer.add(payPanel);

				// Create Email Object which sends a email to recipent
				payPanel.getConfirmBtn().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Check if all fields are filled
						String choice = payPanel.getPaymentMethod();

						switch (choice) {
						case "Credit Card":

							if (!payPanel.creditFieldisEmpty()) {
								try {
									String values[] = payPanel.getAllFieldsCreditCard();
									for (int i = 0; i < values.length; i++) {
										System.out.println(i + " " + values[i]);
									}
									output.writeObject(values);
									output.flush();
									JOptionPane.showMessageDialog(null,
											"Success, an email has been sent containing order details");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Missing input on all fields, please fill in information for every field.");
							}

							break;
						case "Bank Transfer":

							if (!payPanel.bankTransferisEmpty()) {
								try {
									String values[] = payPanel.getAllFieldsBankTransfer();

									output.writeObject(values);
									output.flush();
									JOptionPane.showMessageDialog(null,
											"Success, an email has been sent containing order details");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Missing input on all fields, please fill in information for every field.");
							}
							break;
						case "":

							break;
						default:
							break;
						}
					}
				});
			}
		});

		this.lblShoppingCart = new JLabel("");

		// update label for image
		this.newImg = new ImageIcon(getClass().getResource("/Images/cart.png"));
		this.lblShoppingCart.setIcon(newImg);
		this.lblShoppingCart.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		this.lblShoppingCart.setVisible(true);

		this.shopCost = new JLabel("Total Cost:");
		this.shopCost.setFont(new Font("Yu Gothic", Font.BOLD, 16));

		this.tableScrollPane = new JScrollPane();

		this.btnRemoveItem = new JButton("Remove Item");
		this.btnRemoveItem.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.btnRemoveItem.setBackground(new Color(255, 255, 255));

		this.gl_shopPanel = new GroupLayout(shopPanel);
		gl_shopPanel.setHorizontalGroup(gl_shopPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_shopPanel.createSequentialGroup().addContainerGap()
						.addComponent(lblShoppingCart, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addGap(33).addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_shopPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_shopPanel.createSequentialGroup().addComponent(shopCost)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblCostValue))
								.addComponent(btnRemoveItem))
						.addGap(341)
						.addComponent(btnCheckout, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addGap(137)));
		gl_shopPanel.setVerticalGroup(gl_shopPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_shopPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_shopPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_shopPanel
						.createSequentialGroup()
						.addGroup(gl_shopPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblShoppingCart, GroupLayout.PREFERRED_SIZE, 75, Short.MAX_VALUE)
								.addGroup(gl_shopPanel.createSequentialGroup()
										.addGroup(gl_shopPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(shopCost).addComponent(lblCostValue))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnRemoveItem)))
						.addGap(6))
						.addGroup(gl_shopPanel.createSequentialGroup()
								.addComponent(btnCheckout, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGap(24))
						.addGroup(
								gl_shopPanel
										.createSequentialGroup().addComponent(tableScrollPane,
												GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))));

		this.shopTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tableScrollPane.setViewportView(this.shopTable);
		this.shopTable.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.shopTable.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Product", "Type", "Cost", "Description" }));

		this.btnRemoveItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				DefaultTableModel tableModel = (DefaultTableModel) shopTable.getModel();
				int costOfItem = Integer.parseInt(tableModel.getValueAt(shopTable.getSelectedRow(), 2).toString());

				String oldCost = lblCostValue.getText();
				oldCost = oldCost.substring(0, oldCost.length() - 1);

				int newCost = (Integer.parseInt(oldCost) - costOfItem);
				totalCost = newCost;
				System.out.println(newCost);
				lblCostValue.setText(String.valueOf(newCost) + "€");
				tableModel.removeRow(shopTable.getSelectedRow());
			}
		});

		this.shopPanel.setLayout(gl_shopPanel);
		this.categoryMenu = new JPopupMenu();
		this.categoryMenu.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.categoryMenu.setBackground(Color.WHITE);
		this.btnProducts = new JButton("Browse Shop");

		this.btnProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

				categoryMenu.show(btnProducts, btnProducts.getWidth() / btnProducts.getWidth(),
						btnProducts.getHeight());
				categoryMenu.setPopupSize(btnProducts.getWidth(), btnProducts.getHeight() * 4);

			}
		});

		this.btnProducts.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.btnProducts.setBackground(new Color(255, 255, 255));
		addPopup(this.btnProducts, this.categoryMenu);

		this.mntmDesktop = new JMenuItem("Desktops");
		this.mntmDesktop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					// Send request for All desktops
					output.writeObject(1);
					output.flush();

					// Store the saved string
					String resultString = ((String) input.readObject());

					// Split each row
					String desktops[] = resultString.split("\\r?\\n");
					// Show label displaying title

					// Dynamically create a JPanel for each product containing the appropriate
					// information

					// Clear panelContainer before adding components
					panelContainer.removeAll();
					panelContainer.revalidate();
					panelContainer.updateUI();
					for (int i = 0; i < desktops.length; i++) {
						// for each iteration assign all variables from each row
						String variables[] = desktops[i].split(",");
						Desktop desktop = new Desktop(variables[0], variables[1], variables[2], variables[3],
								variables[4], variables[5], Integer.valueOf(variables[6]), variables[7]);

						DefaultTableModel tableModel = (DefaultTableModel) shopTable.getModel();
						// Fetch all components from the database, store them into appropriate strings
						output.writeObject(2);
						output.flush();
						String GPU = ((String) input.readObject());

						output.writeObject(3);
						output.flush();
						String CPU = ((String) input.readObject());

						output.writeObject(4);
						output.flush();
						String RAM = ((String) input.readObject());

						output.writeObject(5);
						output.flush();
						String Chassi = ((String) input.readObject());

						// Cut the strings and separate on each row
						String graphics[] = GPU.split("\\r?\\n");
						String processors[] = CPU.split("\\r?\\n");
						String memories[] = RAM.split("\\r?\\n");
						String cases[] = Chassi.split("\\r?\\n");

						newPane = new CustomPanel(desktop, lblCostValue, panelContainer, tableModel, graphics,
								processors, memories, cases);
						panelContainer.add(newPane);
					}
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.categoryMenu.add(mntmDesktop);

		this.mntmLaptop = new JMenuItem("Laptops");
		this.mntmLaptop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					output.writeObject(6);
					output.flush();
					// Store the saved string
					String resultString = ((String) input.readObject());
					// Split each row
					String laptops[] = resultString.split("\\r?\\n");
					// Dynamically create a JPanel for each product containing the appropriate
					// information
					// Clear panelContainer before adding components
					panelContainer.removeAll();
					panelContainer.revalidate();
					panelContainer.updateUI();
					for (int i = 0; i < laptops.length; i++) {
						// for each iteration assign all variables from each row
						String variables[] = laptops[i].split(",");
						Laptop laptop = new Laptop(variables[0], variables[1], variables[2], variables[3], variables[4],
								variables[5], Integer.valueOf(variables[6]), variables[7], variables[8]);
						DefaultTableModel tableModel = (DefaultTableModel) shopTable.getModel();

						// Fetch all components from the database, store them into appropriate strings
						output.writeObject(2);
						output.flush();
						String GPU = ((String) input.readObject());

						output.writeObject(3);
						output.flush();
						String CPU = ((String) input.readObject());

						output.writeObject(4);
						output.flush();
						String RAM = ((String) input.readObject());

						// Cut the strings and separate on each row
						String graphics[] = GPU.split("\\r?\\n");
						String processors[] = CPU.split("\\r?\\n");
						String memories[] = RAM.split("\\r?\\n");
						// retrieve the cost of all items
						// Create the component tabel with appropriate values

						newPane = new CustomPanel(laptop, lblCostValue, panelContainer, tableModel, graphics,
								processors, memories);
						panelContainer.add(newPane);
					}

				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		this.categoryMenu.add(mntmLaptop);

		this.mntmServer = new JMenuItem("Servers");
		mntmServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

				try {
					output.writeObject(7);
					output.flush();
					// Store the saved string
					String resultString = ((String) input.readObject());
					// Split each row
					String servers[] = resultString.split("\\r?\\n");
					// Dynamically create a JPanel for each product containing the appropriate
					// information
					// Clear panelContainer before adding components
					panelContainer.removeAll();
					panelContainer.revalidate();
					panelContainer.updateUI();

					for (int i = 0; i < servers.length; i++) {
						// for each iteration assign all variables from each row
						String variables[] = servers[i].split(",");
						ComponentServer server = new ComponentServer(variables[0], variables[1], variables[2],
								variables[3], variables[4], variables[5], variables[6], Integer.valueOf(variables[7]),
								variables[8]);
						DefaultTableModel tableModel = (DefaultTableModel) shopTable.getModel();

						output.writeObject(3);
						output.flush();
						String CPU = ((String) input.readObject());

						output.writeObject(4);
						output.flush();
						String RAM = ((String) input.readObject());

						// Cut the strings and separate on each row
						String processors[] = CPU.split("\\r?\\n");
						String memories[] = RAM.split("\\r?\\n");

						newPane = new CustomPanel(server, lblCostValue, panelContainer, tableModel, processors,
								memories);
						panelContainer.add(newPane);
					}

				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		this.categoryMenu.add(mntmServer);

		this.btnViewOrders = new JButton("View Orders");
		this.btnViewOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

				panelContainer.removeAll();
				panelContainer.revalidate();
				panelContainer.updateUI();
				viewPanel = new ViewPanel();
				panelContainer.add(viewPanel);

				// Buttons

				viewPanel.getSearchButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!viewPanel.searchFieldIsEmpty()) {
							try {
								String searchString = ("Fetch" + "," + (String) viewPanel.getSearchField());
								output.writeObject(searchString);
								output.flush();
								String result = ((String) input.readObject());

								if (result.length() > 0) {
									String variables[] = result.split(",");
									Object[] row = { variables[0], variables[1], variables[2], variables[3],
											variables[4] };
									DefaultTableModel tableModel = (DefaultTableModel) viewPanel.getOrderTable()
											.getModel();

									while (tableModel.getRowCount() > 0) {
										tableModel.removeRow(0);
									}
									tableModel.addRow(row);

								}

							} catch (IOException | ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}

					}
				});

				viewPanel.getCancelBtn().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						DefaultTableModel tableModel = (DefaultTableModel) viewPanel.getOrderTable().getModel();
						// If the table isnt empty
						if (tableModel.getRowCount() > 0) {
							// Send request for server to remove
							try {
								String searchString = ("Remove" + "," + (String) viewPanel.getSearchField());
								output.writeObject(searchString);
								output.flush();
								String response = ((String) input.readObject());
								System.out.println(response);
								// receive response
								// compare status
								if (response.equals("Success")) {
									tableModel.removeRow(viewPanel.getOrderTable().getSelectedRow());
									JOptionPane.showMessageDialog(null, "Order has been canceled. \n"
											+ "For any inconvenience please message us at mkhardwareproject@gmail.com");
								} else if (response.equals("Fail")) {
									JOptionPane.showMessageDialog(null,
											"Unable to cancel due to the fact that the order has already been sent from warehouse \n"
													+ "For any inconvenience please message us at mkhardwareproject@gmail.com");
								}

							} catch (IOException | ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "No order has been selected.");
						}

					}
				});

			}
		});

		this.btnViewOrders.setBackground(Color.WHITE);
		this.btnViewOrders.setFont(new Font("Yu Gothic", Font.BOLD, 14));

		// Added by request in order to test canceling shipment orders and prove that
		// program is easily extendible
		JButton btnSuperUser = new JButton("Super User");
		btnSuperUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

				panelContainer.removeAll();
				panelContainer.revalidate();
				panelContainer.updateUI();
				SuperUser superPanel = new SuperUser();
				panelContainer.add(superPanel);

				superPanel.getRetrieveBtn().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							output.writeObject(8);
							output.flush();

							DefaultTableModel tableModel = (DefaultTableModel) superPanel.getOrderTable().getModel();

							String result = ((String) input.readObject());
							String rows[] = result.split("\\r?\\n");

							while (tableModel.getRowCount() > 0) {
								tableModel.removeRow(0);
							}

							for (int i = 0; i < rows.length; i++) {

								String variables[] = rows[i].split(",");
								Object[] row = { variables[0], variables[1], variables[2], variables[3] };

								tableModel.addRow(row);

							}

						} catch (IOException | ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				});

				superPanel.getCancelBtn().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						DefaultTableModel tableModel = (DefaultTableModel) superPanel.getOrderTable().getModel();
						// If the table isnt empty
						if (tableModel.getRowCount() > 0 && superPanel.getOrderTable().getSelectedRowCount() > 0) {
							// Send request for server to remove
							try {
								String orderToRemove = (tableModel
										.getValueAt(superPanel.getOrderTable().getSelectedRow(), 0).toString());
								String searchString = ("Remove" + "," + orderToRemove);

								output.writeObject(searchString);
								output.flush();
								String response = ((String) input.readObject());
								System.out.println(response);
								// receive response
								// compare status
								if (response.equals("Success")) {
									tableModel.removeRow(superPanel.getOrderTable().getSelectedRow());
									JOptionPane.showMessageDialog(null, "Order has been canceled. \n"
											+ "For any inconvenience please message us at mkhardwareproject@gmail.com");
								} else if (response.equals("Fail")) {
									JOptionPane.showMessageDialog(null,
											"Unable to cancel due to the fact that the order has already been sent from warehouse \n"
													+ "For any inconvenience please message us at mkhardwareproject@gmail.com");
								}

							} catch (IOException | ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "No order has been selected.");
						}

					}
				});

				superPanel.getShippedToNotShippedBtn().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						DefaultTableModel tableModel = (DefaultTableModel) superPanel.getOrderTable().getModel();
						// If the table isnt empty
						if (tableModel.getRowCount() > 0) {
							try {
								String orderToRemove = (tableModel
										.getValueAt(superPanel.getOrderTable().getSelectedRow(), 0).toString());
								String searchString = ("NotShipped" + "," + orderToRemove);

								output.writeObject(searchString);
								output.flush();
								String response = ((String) input.readObject());
								System.out.println(response);
								// receive response
								// compare status

								output.writeObject(8);
								output.flush();

								String result = ((String) input.readObject());

								String rows[] = result.split("\\r?\\n");

								while (tableModel.getRowCount() > 0) {
									tableModel.removeRow(0);
								}

								for (int i = 0; i < rows.length; i++) {

									String variables[] = rows[i].split(",");
									Object[] row = { variables[0], variables[1], variables[2], variables[3] };

									tableModel.addRow(row);

								}

							} catch (IOException | ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				});

				superPanel.getNotShippedToShippedBtn().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						DefaultTableModel tableModel = (DefaultTableModel) superPanel.getOrderTable().getModel();
						// If the table isnt empty
						if (tableModel.getRowCount() > 0) {
							try {
								String orderToRemove = (tableModel
										.getValueAt(superPanel.getOrderTable().getSelectedRow(), 0).toString());
								String searchString = ("Shipped" + "," + orderToRemove);

								output.writeObject(searchString);
								output.flush();
								String response = ((String) input.readObject());
								System.out.println(response);
								// receive response
								// compare status

								output.writeObject(8);
								output.flush();

								String result = ((String) input.readObject());

								String rows[] = result.split("\\r?\\n");

								while (tableModel.getRowCount() > 0) {
									tableModel.removeRow(0);
								}

								for (int i = 0; i < rows.length; i++) {

									String variables[] = rows[i].split(",");
									Object[] row = { variables[0], variables[1], variables[2], variables[3] };

									tableModel.addRow(row);

								}

							} catch (IOException | ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				});

			}
		});

		this.gl_panel = new GroupLayout(this.panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(10).addComponent(icon).addGap(29)
						.addComponent(btnProducts).addGap(28).addComponent(btnViewOrders)
						.addPreferredGap(ComponentPlacement.RELATED, 882, Short.MAX_VALUE).addComponent(btnSuperUser)
						.addGap(28)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(14).addComponent(icon))
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProducts, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnViewOrders, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSuperUser)));
		panel.setLayout(gl_panel);

		contentPane.setLayout(gl_contentPane);

	}

	public void updateTotalCost(int addedCost) {
		this.totalCost += addedCost;
	}

	public int getTotalCost() {
		return this.totalCost;
	}

	public void startClient() {
		try {
			this.connectToServer();
			this.setupStreams();
			this.whileRunning();

		} catch (EOFException exc) {
			System.out.println("\n Client Terminated Connection");
		} catch (IOException ioexc) {
			ioexc.printStackTrace();
		} finally {
			this.closeClient();
		}
	}

	private void closeClient() {
		System.out.println("Closing Client \n");
		try {
			this.output.close();
			this.input.close();
			this.connection.close();

		} catch (IOException exc) {
			exc.printStackTrace();
		}

	}

	private void whileRunning() throws IOException {
		while (true) {

		}
	}

	private void setupStreams() throws IOException {
		this.output = new ObjectOutputStream(connection.getOutputStream());
		this.output.flush();
		this.input = new ObjectInputStream(connection.getInputStream());
		System.out.println("You are now connected!");
	}

	private void connectToServer() throws IOException {
		System.out.println("Attempting Connection..");
		this.connection = new Socket(InetAddress.getByName(serverIP), 5000);
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
