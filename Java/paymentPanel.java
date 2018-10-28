import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PaymentPanel extends JPanel {
	private JTextField nameField, lastNameField,emailField, addressField, postalField, holderField, cardField, cvcField;
    private JLabel nameLabel,lastNameLabel,emailLabel,addressLabel,postalLabel, method, holderLabel,cardLabel,cvcLabel;
    private JComboBox optionList;
    private String [] options;
    private JPanel contentPanel;
    private String items;
    GridBagConstraints gc;
    private JButton confirmBtn;
    
	public PaymentPanel(String itemsFromCart)
	{

		
		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(700,500));
		contentPanel.setBackground(new Color(0, 204, 153));
	    Border roundedBorder = new LineBorder(new Color(47, 79, 79), 6, true);
	    contentPanel.setLayout(new GridBagLayout());
	    this.setBorder(roundedBorder);
		this.setBackground(new Color(0, 204, 153));
		
        this.add(contentPanel);
	    // Textfields and labels
        
        this.items = itemsFromCart;
	    
	    nameField = new JTextField(30);
	    nameLabel = new JLabel("Name:");
	    nameLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));

	    lastNameField = new JTextField(30);
	    lastNameLabel = new JLabel("Last Name:");
	    lastNameLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));

	    emailField = new JTextField(30);
	    emailLabel = new JLabel("Email:");
	    emailLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));
	    
	    addressField = new JTextField(30);
	    addressLabel = new JLabel("Address:");
	    addressLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));
	    
	    postalField = new JTextField(30);
	    postalLabel = new JLabel("Postal Code:");
	    postalLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));
	    
	    method = new JLabel("Choose a payment method:");
		method.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		
		// Combobox
	    options = new String[] { "Credit Card", "Cash", "Bank Transfer" };
	    setOptionList(new JComboBox(options));
	    getOptionList().setSelectedIndex(0);
		getOptionList().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		
		
		// Buttons
		setConfirmBtn(new JButton("Confirm"));
		getConfirmBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getConfirmBtn().setBackground(new Color(255, 255, 255));
		
		
		//mkhardwareproject@gmail.com
		
	    // GridContstraints
	    this.gc = new GridBagConstraints();
        this.gc.fill = GridBagConstraints.HORIZONTAL;
        this.gc.insets = new Insets(10, 10, 10, 10);
		
		// Events
		getOptionList().addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				 
				  String choice = (String) getOptionList().getSelectedItem();
				  
	                switch (choice) {
	                    case "Credit Card":
	                	    emailLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));

	                    	// Credit Card Fields and Labels
	                  	   	holderField = new JTextField(30);
	                		holderLabel = new JLabel("Account Holder:");

	                		cardField = new JTextField(30);
	                		cardLabel = new JLabel("Credit Card Number:");
	                		
	                		cvcField = new JTextField(3);
	                		cvcLabel = new JLabel("cvc:");
	                		
	                    	//Holder
	                		gc.gridx = 0;
	                		gc.gridy = 6;
	                		contentPanel.add(holderLabel,gc);

	                		gc.gridx = 1;
	                		gc.gridy = 6;
	                		contentPanel.add(holderField,gc);
	                		
	                		// Credit Card
	                		gc.gridx = 0;
	                		gc.gridy = 7;
	                		contentPanel.add(cardLabel,gc);
	                		
	                		gc.gridx = 1;
	                		gc.gridy = 7;
	                		contentPanel.add(cardField,gc);
	                		
	                		// CVC
	                		gc.gridx = 0;
	                		gc.gridy = 8;
	                		contentPanel.add(cvcLabel,gc);
	                		
	                		gc.gridx = 1;
	                		gc.gridy = 8;
	                		contentPanel.add(cvcField,gc);	                  
	                									
	                        break;
	                    case "Cash":

	                        break;
	                    case "Bank Transfer":

	                        break;
	                    default:
	                        break;
	                }
			
			  }
		  }
		);
	    
        // Name
        gc.gridx = 0;
        gc.gridy = 0;
        contentPanel.add(nameLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 0;
  		contentPanel.add(nameField,gc);
  		
        // Last Name
  		gc.gridx = 0;
  		gc.gridy = 1;
  		contentPanel.add(lastNameLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 1;
  		contentPanel.add(lastNameField,gc);
  		
        // Email
 		gc.gridx = 0;
  		gc.gridy = 2;
  		contentPanel.add(emailLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 2;
  		contentPanel.add(emailField,gc);
  		
  		// Address
  		gc.gridx = 0;
  		gc.gridy = 3;
  		contentPanel.add(addressLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 3;
  		contentPanel.add(addressField,gc);
  		
  		// Postal Code
  		gc.gridx = 0;
  		gc.gridy = 4;
  		contentPanel.add(postalLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 4;
  		contentPanel.add(postalField,gc);
  		
  		// Method
		gc.gridx = 0;
  		gc.gridy = 5;
  		contentPanel.add(method, gc);
  		
  		// ComboBox
  		gc.gridx = 1;
  		gc.gridy = 5;
  		contentPanel.add(getOptionList(), gc);
  		
  		// Confirm
  		gc.gridx = 1;
  		gc.gridy = 9;
  		contentPanel.add(getConfirmBtn(), gc);

	}
	public boolean creditFieldisEmpty() {
		return (addressField.getText().isEmpty() 
				&& postalField.getText().isEmpty() 
				&& emailField.getText().isEmpty() 
				&& nameField.getText().isEmpty() 
				&& lastNameField.getText().isEmpty() 
				&& holderField.getText().isEmpty() 
				&& cardField.getText().isEmpty() 
				&& cvcField.getText().isEmpty());
	}
	public String[] getAllFieldsCreditCard()
	{
		String[] stringToReturn = {
				this.nameField.getText(), 
				this.lastNameField.getText(),
				this.emailField.getText(), 
				this.addressField.getText(), 
				this.postalField.getText(),
				this.holderField.getText(),
				this.cardField.getText(),
				this.items};
		
		return stringToReturn;
	}
	public JComboBox getOptionList() {
		return optionList;
	}
	public void setOptionList(JComboBox optionList) {
		this.optionList = optionList;
	}
	public GridBagConstraints getGc(){
		return this.gc;
	}
	public JButton getConfirmBtn() {
		return confirmBtn;
	}
	public void setConfirmBtn(JButton confirmBtn) {
		this.confirmBtn = confirmBtn;
	}


}