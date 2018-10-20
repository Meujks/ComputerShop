import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		private String nameOfProduct;
		
	
		public CustomPanel(String name)
		{
			// Styling
		    LineBorder line = new LineBorder(Color.BLACK, 2, true);
			this.setPreferredSize(new Dimension(380,100));
			this.setBackground(new Color(245, 245, 245));
		    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			this.setBackground(Color.WHITE);
		    this.setBorder(line);
		    
		    // Objects
			this.nameOfProduct = name;
			lblNameOfProduct = new JLabel(name);
			lblNameOfProduct.setFont(new Font("Yu Gothic", Font.BOLD, 16));
			this.add(lblNameOfProduct);
			
		}
		public String addToCart()
		{
			return this.nameOfProduct;
		}
		
}
