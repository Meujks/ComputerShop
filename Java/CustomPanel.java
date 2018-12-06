import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CustomPanel extends JPanel {

	private JLabel lblNameOfProduct;
	private JLabel lblCPUOfProduct;
	private JLabel lblMemoryOfProduct;
	private JLabel lblGpuOfProduct;
	private JLabel lblChassiOfProduct;
	private JLabel lblCostOfProduct;
	private JPanel btnPanel;
	private JLabel picLabel;

	private JButton cartBtn;
	private String nameOfProduct;
	private String gpuOfProduct;
	private String cpuOfProduct;
	private String ramOfProduct;
	private String chassiOfProduct;
	private String typeOfProduct;
	private int costOfProduct;
	private String pathOfImage;

	private Border roundedBorder;
	private LayoutManager layout;
	private JButton customizeBtn;
	private ComponentPanel compPanel;
	private JButton changeBtn;
	// Products
	private Desktop desktop;
	private Laptop laptop;
	private ComponentServer server;

	private ImageIcon img;
	private BufferedImage myPicture;

	// Laptop

	private JLabel lblmGpuOfLaptop;
	private JLabel lblInchesOfLaptop;
	private JLabel lblChassiOfLaptop;

	private String inchesOfLaptop;
	private String mGpuOfLaptop;
	private String lChassiOfLaptop;

	// Server
	private JLabel lblScalabilityOfServer;
	private JLabel lblFormFactorOfServer;
	private JLabel lblChassiOfServer;

	private String formFactorOfServer;
	private String scalabilityOfServer;
	private String chassiOfServer;

	public CustomPanel(ComponentServer server, JLabel lblCostValue, JPanel panelContainer, TableModel tableModel,
			String[] processors, String[] memories) {

		this.server = server;
		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		this.setPreferredSize(new Dimension(300, 350));
		this.setBackground(new Color(178, 254, 247));
		this.setForeground(new Color(51, 102, 102));
		this.setBorder(roundedBorder);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Labels
		this.nameOfProduct = server.getName();
		this.cpuOfProduct = server.getCpu();
		this.ramOfProduct = server.getRam();
		this.formFactorOfServer = server.getFormFactor();
		this.scalabilityOfServer = server.getScalability();
		this.typeOfProduct = server.getType();
		this.costOfProduct = server.getCost();
		this.pathOfImage = server.getPathOfImage();
		this.chassiOfServer = server.getChassi();

		this.img = new ImageIcon(getClass().getResource(this.pathOfImage));
		this.picLabel = new JLabel(img);
		this.lblCPUOfProduct = new JLabel("- CPU: " + this.cpuOfProduct);
		this.lblMemoryOfProduct = new JLabel("- RAM: " + this.ramOfProduct);
		this.lblFormFactorOfServer = new JLabel("- Form Factor: " + this.formFactorOfServer);
		this.lblNameOfProduct = new JLabel(" " + this.nameOfProduct + " " + this.typeOfProduct);
		this.lblScalabilityOfServer = new JLabel("- Scalability: " + this.scalabilityOfServer);
		this.lblChassiOfServer = new JLabel("- Case: " + this.chassiOfServer);
		this.lblCostOfProduct = new JLabel("- Cost: " + this.costOfProduct + "€");

		this.lblNameOfProduct.setFont(new Font("Yu Gothic", Font.BOLD, 18));

		this.add(picLabel);
		this.add(lblNameOfProduct);
		this.add(lblCPUOfProduct);
		this.add(lblFormFactorOfServer);
		this.add(lblMemoryOfProduct);
		this.add(lblScalabilityOfServer);
		this.add(lblChassiOfServer);
		this.add(lblCostOfProduct);

		// Button Panel
		this.btnPanel = new JPanel();
		this.btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.btnPanel.setBackground(new Color(178, 254, 247));
		this.layout = new FlowLayout();
		this.btnPanel.setLayout(layout);

		this.add(btnPanel);

		this.cartBtn = new JButton("Add To Cart");
		this.cartBtn.setBackground(Color.WHITE);
		this.cartBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.btnPanel.add(cartBtn);

		this.cartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object[] row = { server.getName(), server.getType(), server.getCost(), server.toString() };

				((DefaultTableModel) tableModel).addRow(row);

				int amount = 0;
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					amount += (Integer) tableModel.getValueAt(i, 2);
				}
				lblCostValue.setText(String.valueOf(amount) + "€");
			}
		});

		this.customizeBtn = new JButton("Customize");
		this.customizeBtn.setBackground(Color.WHITE);
		this.customizeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.btnPanel.add(customizeBtn);

		customizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Remove all desktops apart from the selected from the panel
				for (Component cp : panelContainer.getComponents()) {
					if (cp != customizeBtn.getParent().getParent())
						cp.setVisible(false);
				}

				// hide the customize button
				customizeBtn.setVisible(false);
				compPanel = new ComponentPanel(processors, memories, server.getName(), server.getRam(),
						server.getCpu());
				panelContainer.add(compPanel);

				// Create a button for changing values between compPanel and CustomPanel
				changeBtn = new JButton("Change");
				changeBtn.setBackground(Color.WHITE);
				changeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
				compPanel.add(changeBtn);

				// Get the selected value from compPanel and add it to the current configuration
				// on the custom panel
				changeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Update the name of component to Custom
						// Store index and name of component being changed
						int itemIndex = compPanel.getSelectedIndex();
						String itemValue = compPanel.getSelectedElement();

						// Update the configuration by passing the necessary values
						updateEventServer(itemIndex, itemValue);
					}
				});
			}
		});
	}

	public CustomPanel(Laptop laptop, JLabel lblCostValue, JPanel panelContainer, TableModel tableModel,
			String[] graphics, String[] processors, String[] memories) {
		this.laptop = laptop;
		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);

		this.setPreferredSize(new Dimension(300, 350));
		this.setForeground(new Color(51, 102, 102));
		this.setBorder(roundedBorder);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(178, 254, 247));

		// Labels
		this.nameOfProduct = laptop.getName();
		this.cpuOfProduct = laptop.getCpu();
		this.ramOfProduct = laptop.getRam();
		this.inchesOfLaptop = laptop.getInches();
		this.mGpuOfLaptop = laptop.getMgpu();
		this.typeOfProduct = laptop.getType();
		this.costOfProduct = laptop.getCost();
		this.pathOfImage = laptop.getPathOfImage();
		this.lChassiOfLaptop = laptop.getlChassi();

		this.img = new ImageIcon(getClass().getResource(this.pathOfImage));
		this.picLabel = new JLabel(img);
		this.lblCPUOfProduct = new JLabel("- CPU: " + this.cpuOfProduct);
		this.lblMemoryOfProduct = new JLabel("- RAM: " + this.ramOfProduct);
		this.lblmGpuOfLaptop = new JLabel("- mGPU: " + this.mGpuOfLaptop);
		this.lblNameOfProduct = new JLabel(" " + this.nameOfProduct + " " + this.typeOfProduct);
		this.lblInchesOfLaptop = new JLabel("- Inches: " + this.inchesOfLaptop);
		this.lblChassiOfLaptop = new JLabel("- Case: " + this.lChassiOfLaptop);
		this.lblCostOfProduct = new JLabel("- Cost: " + this.costOfProduct + "€");

		this.lblNameOfProduct.setFont(new Font("Yu Gothic", Font.BOLD, 18));

		this.add(picLabel);
		this.add(lblNameOfProduct);
		this.add(lblCPUOfProduct);
		this.add(lblmGpuOfLaptop);
		this.add(lblMemoryOfProduct);
		this.add(lblInchesOfLaptop);
		this.add(lblChassiOfLaptop);
		this.add(lblCostOfProduct);

		// Button Panel
		this.btnPanel = new JPanel();
		this.btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.btnPanel.setBackground(new Color(178, 254, 247));
		this.layout = new FlowLayout();
		this.btnPanel.setLayout(layout);

		this.add(btnPanel);

		this.cartBtn = new JButton("Add To Cart");
		this.cartBtn.setBackground(Color.WHITE);
		this.cartBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.btnPanel.add(cartBtn);
		this.cartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] row = { laptop.getName(), laptop.getType(), laptop.getCost(), laptop.toString() };
				((DefaultTableModel) tableModel).addRow(row);

				int amount = 0;
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					amount += (Integer) tableModel.getValueAt(i, 2);
				}
				lblCostValue.setText(String.valueOf(amount) + "€");
			}
		});

		this.customizeBtn = new JButton("Customize");
		this.customizeBtn.setBackground(Color.WHITE);
		this.customizeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.btnPanel.add(customizeBtn);

		this.customizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Remove all desktops apart from the selected from the panel
				for (Component cp : panelContainer.getComponents()) {
					if (cp != customizeBtn.getParent().getParent())
						cp.setVisible(false);
				}

				// hide the customize button
				customizeBtn.setVisible(false);
				compPanel = new ComponentPanel(graphics, processors, memories, laptop.getName(), laptop.getRam(),
						laptop.getCpu(), laptop.getType(), laptop.getMgpu());
				panelContainer.add(compPanel);

				// Create a button for changing values between compPanel and CustomPanel
				changeBtn = new JButton("Change");
				changeBtn.setBackground(Color.WHITE);
				changeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
				compPanel.add(changeBtn);

				// Get the selected value from compPanel and add it to the current configuration
				// on the custom panel
				changeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Update the name of component to Custom
						// Store index and name of component being changed
						int itemIndex = compPanel.getSelectedIndex();
						String itemValue = compPanel.getSelectedElement();
						// Update the configuration by passing the necessary values
						updateEventLaptop(itemIndex, itemValue);
					}
				});
			}
		});

	}

	public CustomPanel(Desktop computer, JLabel lblCostValue, JPanel panelContainer, TableModel tableModel,
			String[] graphics, String[] processors, String[] memories, String[] cases) {

		this.desktop = computer;
		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		this.setPreferredSize(new Dimension(300, 350));
		this.setForeground(new Color(51, 102, 102));
		this.setBorder(roundedBorder);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(178, 254, 247));

		// Labels
		this.nameOfProduct = desktop.getName();
		this.gpuOfProduct = desktop.getGpu();
		this.cpuOfProduct = desktop.getCpu();
		this.ramOfProduct = desktop.getRam();
		this.chassiOfProduct = desktop.getChassi();
		this.typeOfProduct = desktop.getType();
		this.costOfProduct = desktop.getCost();
		this.pathOfImage = desktop.getPathOfImage();

		this.img = new ImageIcon(getClass().getResource(this.pathOfImage));
		this.picLabel = new JLabel(img);
		this.lblCPUOfProduct = new JLabel("- CPU: " + this.cpuOfProduct);
		this.lblMemoryOfProduct = new JLabel("- RAM: " + this.ramOfProduct);
		this.lblGpuOfProduct = new JLabel("- GPU: " + this.gpuOfProduct);
		this.lblNameOfProduct = new JLabel(" " + this.nameOfProduct + " " + this.typeOfProduct);
		this.lblChassiOfProduct = new JLabel("- Chassi: " + this.chassiOfProduct);
		this.lblCostOfProduct = new JLabel("- Cost: " + this.costOfProduct + "€");

		this.lblNameOfProduct.setFont(new Font("Yu Gothic", Font.BOLD, 18));

		this.add(picLabel);
		this.add(lblNameOfProduct);
		this.add(lblCPUOfProduct);
		this.add(lblGpuOfProduct);
		this.add(lblMemoryOfProduct);
		this.add(lblChassiOfProduct);
		this.add(lblCostOfProduct);

		// Button Panel
		this.btnPanel = new JPanel();
		this.btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.btnPanel.setBackground(new Color(178, 254, 247));
		this.layout = new FlowLayout();
		this.btnPanel.setLayout(layout);

		this.add(btnPanel);

		this.cartBtn = new JButton("Add To Cart");
		this.cartBtn.setBackground(Color.WHITE);
		this.cartBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.btnPanel.add(cartBtn);
		this.cartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] row = { desktop.getName(), desktop.getType(), desktop.getCost(), desktop.toString() };
				((DefaultTableModel) tableModel).addRow(row);
				int amount = 0;
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					amount += (Integer) tableModel.getValueAt(i, 2);
				}
				lblCostValue.setText(String.valueOf(amount) + "€");
			}
		});
		this.customizeBtn = new JButton("Customize");
		this.customizeBtn.setBackground(Color.WHITE);
		this.customizeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.btnPanel.add(customizeBtn);

		this.customizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Remove all desktops apart from the selected from the panel
				for (Component cp : panelContainer.getComponents()) {
					if (cp != customizeBtn.getParent().getParent())
						cp.setVisible(false);
				}

				// hide the customize button
				customizeBtn.setVisible(false);
				// Create the component table with appropriate values
				compPanel = new ComponentPanel(graphics, processors, memories, cases, computer.getName(),
						computer.getRam(), computer.getCpu(), computer.getType(), computer.getChassi(),
						computer.getGpu());
				panelContainer.add(compPanel);

				// Create a button for changing values between compPanel and CustomPanel
				changeBtn = new JButton("Change");
				changeBtn.setBackground(Color.WHITE);
				changeBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
				compPanel.add(changeBtn);

				// Get the selected value from compPanel and add it to the current configuration
				// on the custom panel
				changeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Update the name of component to Custom
						updateLabelDesktop();
						// Store index and name of component being changed
						int itemIndex = compPanel.getSelectedIndex();
						String itemValue = compPanel.getSelectedElement();
						// Update the configuration by passing the necessary values
						updateEventDesktop(itemIndex, itemValue);
					}
				});
			}
		});
	}

	public String addToCartDesktop() {
		return this.desktop.getName() + " " + this.desktop.getType();
	}

	public String addToCartLaptop() {
		return this.laptop.getName() + " " + this.laptop.getType();
	}

	public String addToCartServer() {
		return this.server.getName() + " " + this.server.getType();
	}

	public JPanel getBtnPanel() {
		return this.btnPanel;
	}

	public void updateLabelDesktop() {
		this.desktop.setType("Custom");
		this.lblNameOfProduct.setText(this.desktop.getName() + " " + this.desktop.getType());
	}

	public void updateEventDesktop(int itemIndex, String itemValue) {

		String costOfItem = formatString(itemValue);
		// Change desktop image path
		this.desktop.setPathOfImage("/Images/custom.png");
		// update label for image
		ImageIcon newImg = new ImageIcon(getClass().getResource("/Images/custom.png"));
		this.picLabel.setIcon(newImg);

		switch (itemIndex) {
		case 0:
			String costOfOldCpu = formatString(this.desktop.getCpu());
			calculateCostDesktop(costOfItem, costOfOldCpu);
			this.desktop.setCpu(itemValue);
			this.lblCPUOfProduct.setText("- CPU: " + this.desktop.getCpu());
			break;
		case 1:
			String costOfOldGpu = formatString(this.desktop.getGpu());
			calculateCostDesktop(costOfItem, costOfOldGpu);
			this.desktop.setGpu(itemValue);
			this.lblGpuOfProduct.setText("- GPU: " + this.desktop.getGpu());
			break;
		case 2:
			String costOfOldRam = formatString(this.desktop.getRam());
			calculateCostDesktop(costOfItem, costOfOldRam);
			this.desktop.setRam(itemValue);
			this.lblMemoryOfProduct.setText("- RAM: " + this.desktop.getRam());
			break;
		case 3:
			String costOfOldChassi = formatString(this.desktop.getChassi());
			calculateCostDesktop(costOfItem, costOfOldChassi);
			this.desktop.setChassi(itemValue);
			this.lblChassiOfProduct.setText("- Chassi: " + this.desktop.getChassi());
			break;
		}
	}

	public void updateEventLaptop(int itemIndex, String itemValue) {

		String costOfItem = formatString(itemValue);

		switch (itemIndex) {
		case 0:
			String costOfOldCpu = formatString(this.laptop.getCpu());
			this.calculateCostLaptop(costOfItem, costOfOldCpu);
			this.laptop.setCpu(itemValue);
			this.lblCPUOfProduct.setText("- CPU: " + this.laptop.getCpu());
			this.laptop.setType("Custom");
			break;
		case 1:
			String costOfOldGpu = formatString(this.laptop.getMgpu());
			this.calculateCostLaptop(costOfItem, costOfOldGpu);
			this.laptop.setMgpu(itemValue);
			this.lblmGpuOfLaptop.setText("- GPU: " + this.laptop.getMgpu());
			this.laptop.setType("Custom");

			break;
		case 2:
			String costOfOldRam = formatString(this.laptop.getRam());
			this.calculateCostLaptop(costOfItem, costOfOldRam);
			this.laptop.setRam(itemValue);
			this.lblMemoryOfProduct.setText("- RAM: " + this.laptop.getRam());
			this.laptop.setType("Custom");
			break;
		}
	}

	public void updateEventServer(int itemIndex, String itemValue) {

		String costOfItem = formatString(itemValue);

		switch (itemIndex) {
		case 0:
			String costOfOldCpu = formatString(this.server.getCpu());
			this.calculateCostServer(costOfItem, costOfOldCpu);
			this.server.setCpu(itemValue);
			this.lblCPUOfProduct.setText("- CPU: " + this.server.getCpu());
			this.server.setType("Custom");
			break;
		case 1:
			String costOfOldRam = formatString(this.server.getRam());
			this.calculateCostServer(costOfItem, costOfOldRam);
			this.server.setRam(itemValue);
			this.lblMemoryOfProduct.setText("- RAM: " + this.server.getRam());
			this.server.setType("Custom");
			break;
		}
	}

	public String formatString(String str) {
		String formatString = str.substring(str.indexOf("-") + 1, str.indexOf("€"));
		formatString = formatString.replaceAll("\\s+", "");
		return formatString;
	}

	public void calculateCostDesktop(String costOfNewItem, String costOfOldItem) {
		this.desktop
				.setCost(this.desktop.getCost() + (Integer.parseInt(costOfNewItem) - Integer.parseInt(costOfOldItem)));
		this.lblCostOfProduct.setText("- Cost: " + this.desktop.getCost() + "€");
	}

	public void calculateCostLaptop(String costOfNewItem, String costOfOldItem) {
		this.laptop
				.setCost(this.laptop.getCost() + (Integer.parseInt(costOfNewItem) - Integer.parseInt(costOfOldItem)));
		this.lblCostOfProduct.setText("- Cost: " + this.laptop.getCost() + "€");
	}

	public void calculateCostServer(String costOfNewItem, String costOfOldItem) {
		this.server
				.setCost(this.server.getCost() + (Integer.parseInt(costOfNewItem) - Integer.parseInt(costOfOldItem)));
		this.lblCostOfProduct.setText("- Cost: " + this.server.getCost() + "€");
	}
}
