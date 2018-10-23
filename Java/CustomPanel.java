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
		private JLabel lblGfxOfProduct;
		private JLabel lblChassiOfProduct;
		private JLabel lblCostOfProduct;

		private String nameOfProduct;
		private String gpuOfProduct;
		private String cpuOfProduct;
		private String ramOfProduct;
		private String chassiOfProduct;
		private String typeOfProduct;
		private String costOfProduct;

		public CustomPanel(String name, String memory, String cpu, String type,String chassi, String gpu)
		{
			// Styling
		    LineBorder line = new LineBorder(Color.BLACK, 1, true);
			this.setPreferredSize(new Dimension(300,200));
			this.setBackground(new Color(245, 245, 245));
			this.setAlignmentX(Component.LEFT_ALIGNMENT );
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			this.setBackground(Color.WHITE);
		    this.setBorder(line);
		    
		    // Labels
		    this.nameOfProduct = name;
		    this.gpuOfProduct = gpu;
		    this.cpuOfProduct = cpu;
		    this.ramOfProduct = memory;
		    this.chassiOfProduct = chassi;
		    this.typeOfProduct = type;
		    
			lblCPUOfProduct = new JLabel("- CPU: " + cpu);
			lblMemoryOfProduct = new JLabel("- RAM: " + memory);
			lblGfxOfProduct = new JLabel("- GFX: " + gpu);
			lblNameOfProduct = new JLabel("- " + name + " " +type);
			lblChassiOfProduct = new JLabel("- Chassi: " + chassi);
			lblCostOfProduct = new JLabel("- Cost: " + this.costOfProduct);

			lblNameOfProduct.setFont(new Font("Yu Gothic", Font.BOLD, 18));
			
			this.add(lblNameOfProduct);
			this.add(lblCPUOfProduct);
			this.add(lblGfxOfProduct);
			this.add(lblMemoryOfProduct);
			this.add(lblChassiOfProduct);
			this.add(lblCostOfProduct);
			
		}
		public String addToCart()
		{
			return this.nameOfProduct;
		}
		public void updateLabel()
		{
			this.lblNameOfProduct.setText("- Desktop Custom");
		}
		public void updateEvent(int itemIndex,String itemValue) {
			System.out.println("--" + itemIndex + itemValue);
			switch(itemIndex) {
			case 0: 
				this.cpuOfProduct = itemValue;
				this.lblCPUOfProduct.setText("- CPU: " + this.cpuOfProduct);
				break;
			case 1: 
				this.gpuOfProduct = itemValue;
				this.lblGfxOfProduct.setText("- GFX: " + this.gpuOfProduct);
				break;
			case 2:
				this.ramOfProduct = itemValue;
				this.lblMemoryOfProduct.setText("- RAM: " + this.ramOfProduct);
				break;
			case 3: 
				this.chassiOfProduct = itemValue;
				this.lblChassiOfProduct.setText("- Chassi: " + this.chassiOfProduct);
				break;
			}
		}
		
		public Desktop getDesktop()
		{
			return new Desktop(this.nameOfProduct,this.ramOfProduct,this.cpuOfProduct,this.typeOfProduct,this.chassiOfProduct,this.gpuOfProduct);
		}
		public int getCostOfDesktop()
		{
			int totalCost = 800;
			return totalCost;
		}
}	 

