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
		private String nameOfProduct;
		private JLabel lblChassiOfProduct;
		
		
		public CustomPanel(String name, String memory, String cpu, String type,String chassi, String gfx)
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
		    
			lblCPUOfProduct = new JLabel("- CPU: " + cpu);
			lblMemoryOfProduct = new JLabel("- RAM: " + memory);
			lblGfxOfProduct = new JLabel("- GFX: " + gfx);
			lblNameOfProduct = new JLabel("- " + name + " " +type);
			lblChassiOfProduct = new JLabel("- Chassi: " + chassi);
			
			lblNameOfProduct.setFont(new Font("Yu Gothic", Font.BOLD, 14));
			
			this.add(lblNameOfProduct);
			this.add(lblCPUOfProduct);
			this.add(lblGfxOfProduct);
			this.add(lblMemoryOfProduct);
			this.add(lblChassiOfProduct);
			
		}
		public String addToCart()
		{
			return this.nameOfProduct;
		}
		
}
