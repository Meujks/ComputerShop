import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CustomPanel extends JPanel {
		private JLabel lblNameOfProduct;
		private JLabel lblCPUOfProduct;
		private JLabel lblMemoryOfProduct;
		private JLabel lblGpuOfProduct;
		private JLabel lblChassiOfProduct;
		private JLabel lblCostOfProduct;

		private String nameOfProduct;
		private String gpuOfProduct;
		private String cpuOfProduct;
		private String ramOfProduct;
		private String chassiOfProduct;
		private String typeOfProduct;
		private int costOfProduct;
		private Desktop desktop;

		public CustomPanel(Desktop computer)
		{
			//String name, String memory, String cpu, String type,String chassi, String gpu
			// Styling
			desktop = computer; 
		    LineBorder line = new LineBorder(Color.BLACK, 1, true);
			this.setPreferredSize(new Dimension(300,200));
			this.setBackground(new Color(245, 245, 245));
			this.setAlignmentX(Component.LEFT_ALIGNMENT );
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			this.setBackground(Color.WHITE);
		    this.setBorder(line);
		    
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
			lblNameOfProduct = new JLabel("- " + this.nameOfProduct + " " + this.typeOfProduct);
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

