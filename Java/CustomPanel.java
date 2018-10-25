import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CustomPanel extends JPanel {
		private JLabel lblNameOfProduct;
		private JLabel lblCPUOfProduct;
		private JLabel lblMemoryOfProduct;
		private JLabel lblGpuOfProduct;
		private JLabel lblChassiOfProduct;
		private JLabel lblCostOfProduct;
		private JPanel btnPanel;

		private String nameOfProduct;
		private String gpuOfProduct;
		private String cpuOfProduct;
		private String ramOfProduct;
		private String chassiOfProduct;
		private String typeOfProduct;
		private int costOfProduct;
		private Desktop desktop;
		BufferedImage myPicture;

		
		public CustomPanel(Desktop computer)
		{
			//String name, String memory, String cpu, String type,String chassi, String gpu
			// Styling
			desktop = computer; 
		    Border roundedBorder = new LineBorder(new Color(47, 79, 79), 3, true);

		    this.setPreferredSize(new Dimension(300,350));
			this.setBackground(new Color(245, 245, 245));
			this.setForeground(new Color(51, 102, 102));
			this.setBorder(roundedBorder);

			this.setAlignmentX(Component.LEFT_ALIGNMENT);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			this.setBackground(new Color(0, 204, 153));
	
			try {
				myPicture = ImageIO.read(new File("C://Users/Max/Documents/Skola/Utlandsstudier/Kurser/Software Engineering/Project/ComputerShop/Images/obsidian.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				add(picLabel);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    // Labels
		    this.nameOfProduct = desktop.getName();
		    this.gpuOfProduct = desktop.getGpu();
		    this.cpuOfProduct = desktop.getCpu();
		    this.ramOfProduct = desktop.getRam();
		    this.chassiOfProduct = desktop.getChassi();
		    this.typeOfProduct = desktop.getType();
		    this.costOfProduct = desktop.getCost();
		    
			lblCPUOfProduct = new JLabel("- CPU: " + this.cpuOfProduct);
			lblMemoryOfProduct = new JLabel("- RAM: " + this.ramOfProduct);
			lblGpuOfProduct = new JLabel("- GPU: " + this.gpuOfProduct);
			lblNameOfProduct = new JLabel(" " + this.nameOfProduct + " " + this.typeOfProduct);
			lblChassiOfProduct = new JLabel("- Chassi: " + this.chassiOfProduct );
			lblCostOfProduct = new JLabel("- Cost: " + this.costOfProduct + "€");

			lblNameOfProduct.setFont(new Font("Yu Gothic", Font.BOLD, 18));

			this.add(lblNameOfProduct);
			this.add(lblCPUOfProduct);
			this.add(lblGpuOfProduct);
			this.add(lblMemoryOfProduct);
			this.add(lblChassiOfProduct);
			this.add(lblCostOfProduct);
			
		}
		public String addToCart()
		{
			return this.desktop.getName() + " " + this.desktop.getType();
		}
		public JPanel getBtnPanel() {
			return this.btnPanel;
		}
		public void updateLabel()
		{
			this.desktop.setType("Custom");
			this.lblNameOfProduct.setText(this.desktop.getName() + " " + this.desktop.getType());
		}
		public void updateEvent(int itemIndex,String itemValue) {
			
			String costOfItem = formatString(itemValue);
			
			
			
			switch(itemIndex) {
			case 0: 
				String costOfOldCpu = formatString(this.desktop.getCpu());
				calculateCost(costOfItem,costOfOldCpu);
				this.desktop.setCpu(itemValue);
				this.lblCPUOfProduct.setText("- CPU: " + this.desktop.getCpu());
				break;
			case 1: 
				String costOfOldGpu = formatString(this.desktop.getGpu());
				calculateCost(costOfItem,costOfOldGpu);
				this.desktop.setGpu(itemValue);
				this.lblGpuOfProduct.setText("- GPU: " + this.desktop.getGpu());
				break;
			case 2:
				String costOfOldRam = formatString(this.desktop.getRam());
				calculateCost(costOfItem,costOfOldRam);
				this.desktop.setRam(itemValue);
				this.lblMemoryOfProduct.setText("- RAM: " + this.desktop.getRam());
				break;
			case 3: 
				String costOfOldChassi = formatString(this.desktop.getChassi());
				calculateCost(costOfItem,costOfOldChassi);
				this.desktop.setChassi(itemValue);
				this.lblChassiOfProduct.setText("- Chassi: " + this.desktop.getChassi());
				break;
			}import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CustomPanel extends JPanel {
		private JLabel lblNameOfProduct;
		private JLabel lblCPUOfProduct;
		private JLabel lblMemoryOfProduct;
		private JLabel lblGpuOfProduct;
		private JLabel lblChassiOfProduct;
		private JLabel lblCostOfProduct;
		private JPanel btnPanel;
		private JLabel picLabel;
		
		private String nameOfProduct;
		private String gpuOfProduct;
		private String cpuOfProduct;
		private String ramOfProduct;
		private String chassiOfProduct;
		private String typeOfProduct;
		private int costOfProduct;
		private String pathOfImage;
		private Desktop desktop;
		BufferedImage myPicture;

		
		public CustomPanel(Desktop computer)
		{
			//String name, String memory, String cpu, String type,String chassi, String gpu
			// Styling
			desktop = computer; 
		    Border roundedBorder = new LineBorder(new Color(47, 79, 79), 3, true);

		    this.setPreferredSize(new Dimension(300,350));
			this.setBackground(new Color(245, 245, 245));
			this.setForeground(new Color(51, 102, 102));
			this.setBorder(roundedBorder);

			this.setAlignmentX(Component.LEFT_ALIGNMENT);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			this.setBackground(new Color(0, 204, 153));
				
				

		
			
		    // Labels
			
		    this.nameOfProduct = desktop.getName();
		    this.gpuOfProduct = desktop.getGpu();
		    this.cpuOfProduct = desktop.getCpu();
		    this.ramOfProduct = desktop.getRam();
		    this.chassiOfProduct = desktop.getChassi();
		    this.typeOfProduct = desktop.getType();
		    this.costOfProduct = desktop.getCost();
		    this.pathOfImage = desktop.getPathOfImage();
		    
		    
		    ImageIcon img = new ImageIcon(getClass().getResource(this.pathOfImage));	
		    picLabel = new JLabel(img);
			lblCPUOfProduct = new JLabel("- CPU: " + this.cpuOfProduct);
			lblMemoryOfProduct = new JLabel("- RAM: " + this.ramOfProduct);
			lblGpuOfProduct = new JLabel("- GPU: " + this.gpuOfProduct);
			lblNameOfProduct = new JLabel(" " + this.nameOfProduct + " " + this.typeOfProduct);
			lblChassiOfProduct = new JLabel("- Chassi: " + this.chassiOfProduct );
			lblCostOfProduct = new JLabel("- Cost: " + this.costOfProduct + "€");

			lblNameOfProduct.setFont(new Font("Yu Gothic", Font.BOLD, 18));

			this.add(picLabel);
			this.add(lblNameOfProduct);
			this.add(lblCPUOfProduct);
			this.add(lblGpuOfProduct);
			this.add(lblMemoryOfProduct);
			this.add(lblChassiOfProduct);
			this.add(lblCostOfProduct);
			
		}
		public String addToCart()
		{
			return this.desktop.getName() + " " + this.desktop.getType();
		}
		public JPanel getBtnPanel() {
			return this.btnPanel;
		}
		public void updateLabel()
		{
			this.desktop.setType("Custom");
			this.lblNameOfProduct.setText(this.desktop.getName() + " " + this.desktop.getType());
		}
		public void updateEvent(int itemIndex,String itemValue) {
			
			String costOfItem = formatString(itemValue);
			// Change desktop image path
			this.desktop.setPathOfImage("/Images/custom.png");
			// update label for image
		    ImageIcon newImg = new ImageIcon(getClass().getResource("/Images/custom.png"));	
			this.picLabel.setIcon(newImg);
			
			
			switch(itemIndex) {
			case 0: 
				String costOfOldCpu = formatString(this.desktop.getCpu());
				calculateCost(costOfItem,costOfOldCpu);
				this.desktop.setCpu(itemValue);
				this.lblCPUOfProduct.setText("- CPU: " + this.desktop.getCpu());
				break;
			case 1: 
				String costOfOldGpu = formatString(this.desktop.getGpu());
				calculateCost(costOfItem,costOfOldGpu);
				this.desktop.setGpu(itemValue);
				this.lblGpuOfProduct.setText("- GPU: " + this.desktop.getGpu());
				break;
			case 2:
				String costOfOldRam = formatString(this.desktop.getRam());
				calculateCost(costOfItem,costOfOldRam);
				this.desktop.setRam(itemValue);
				this.lblMemoryOfProduct.setText("- RAM: " + this.desktop.getRam());
				break;
			case 3: 
				String costOfOldChassi = formatString(this.desktop.getChassi());
				calculateCost(costOfItem,costOfOldChassi);
				this.desktop.setChassi(itemValue);
				this.lblChassiOfProduct.setText("- Chassi: " + this.desktop.getChassi());
				break;
			}
		}
		public String formatString(String str) {
			String formatString = str.substring(str.indexOf("-") + 1, str.indexOf("€"));
			formatString = formatString.replaceAll("\\s+","");
			return formatString;
		}
		public void calculateCost(String costOfNewItem, String costOfOldItem)
		{
			this.desktop.setCost(this.desktop.getCost() + (Integer.parseInt(costOfNewItem) - Integer.parseInt(costOfOldItem)));
			this.lblCostOfProduct.setText("- Cost: " + this.desktop.getCost() + "€");	
		}
}	 


		}
		public String formatString(String str) {
			String formatString = str.substring(str.indexOf("-") + 1, str.indexOf("€"));
			formatString = formatString.replaceAll("\\s+","");
			return formatString;
		}
		public void calculateCost(String costOfNewItem, String costOfOldItem)
		{
			this.desktop.setCost(this.desktop.getCost() + (Integer.parseInt(costOfNewItem) - Integer.parseInt(costOfOldItem)));
			this.lblCostOfProduct.setText("- Cost: " + this.desktop.getCost() + "€");	
		}
}	 

