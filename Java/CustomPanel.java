import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {
		private JLabel lblNameOfProduct;
		private JButton addButton;
		private String nameOfProduct;
		
		public CustomPanel(String name)
		{
		    this.setPreferredSize(new Dimension(380,100));
			this.setBackground(new Color(245, 245, 245));
		    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			this.setBackground(Color.WHITE);
			
			this.nameOfProduct = name;
			lblNameOfProduct = new JLabel(name);
			this.add(lblNameOfProduct);
			
		}
		public String addToCart()
		{
			return this.nameOfProduct;
		}
		
}
