import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PaymentPanel extends JPanel {
	private JTextField nameField, lastNameField,emailField, addressField, postalField, holderField, cardField, cvcField;
    private JLabel nameLabel,lastNameLabel,emailLabel,addressLabel,postalLabel, method, holderLabel,cardLabel,cvcLabel;

    private JPanel contentPanel;
    private String items, paymentMethod;
    GridBagConstraints gc;
    private JButton confirmBtn,continueBtn;

    
	public PaymentPanel(String itemsFromCart)
	{		
		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(700,500));
		contentPanel.setBackground(new Color(178,254,247));
	    Border roundedBorder = new LineBorder(new Color(178,254,247), 6, true);
	    contentPanel.setLayout(new GridBagLayout());
	    this.setBorder(roundedBorder);
		this.setBackground(new Color(178,254,247));
		
		
        this.add(contentPanel);
	    
        // Radio Buttons
        JRadioButton methodCard = new JRadioButton("Credit Card");
        JRadioButton methodTransfer = new JRadioButton("Bank Transfer");
        ButtonGroup buttonGrp = new ButtonGroup();
	    Border radioBorder = new LineBorder(new Color(47, 79, 79), 2, true);
        buttonGrp.add(methodCard);
        buttonGrp.add(methodTransfer);
        methodCard.setSelected(true);
        
	    // Textfields and labels        
        this.items = itemsFromCart;
	    this.setPaymentMethod("None");
	    
	    nameField = new JTextField(30);
	    nameLabel = new JLabel("Name:");
	    nameLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));

	    lastNameField = new JTextField(30);
	    lastNameLabel = new JLabel("Last Name:");
	    lastNameLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));

	    emailField = new JTextField(30);
	    emailLabel = new JLabel("Email:");
	    emailLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));
	    
	    addressField = new JTextField(30);
	    addressLabel = new JLabel("Billing Address:");
	    addressLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));
	    
	    postalField = new JTextField(30);
	    postalLabel = new JLabel("Postal Code:");
	    postalLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));
	    
 	   	// Credit Card Fields and Labels
  	   	holderField = new JTextField(30);
		holderLabel = new JLabel("Account Holder:");

		cardField = new JTextField(30);
		cardLabel = new JLabel("Card Number:");
		
		cvcField = new JTextField(10);
		cvcLabel = new JLabel("Security Code:");
	    
	    method = new JLabel("Choose a payment method:");
		method.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		
		// Buttons
		setConfirmBtn(new JButton("Submit"));
		getConfirmBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getConfirmBtn().setBackground(new Color(255, 255, 255));
		
		setContinueBtn(new JButton("Continue"));
		getContinueBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getContinueBtn().setBackground(new Color(255, 255, 255));

		// Button Listeners
		continueBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				 Enumeration<AbstractButton> allRadioButton=buttonGrp.getElements();  
			        while(allRadioButton.hasMoreElements())  
			        {  
			           JRadioButton temp=(JRadioButton)allRadioButton.nextElement(); 
			           if(temp.isSelected() && temp.getText() == "Credit Card")  
			           {  
			            JOptionPane.showMessageDialog(null,"Paying with: "+temp.getText());  
			            contentPanel.removeAll(); 
			            contentPanel.revalidate();
			            contentPanel.updateUI();
			           
			            setPaymentMethod("Credit Card");
			            // Name
			            gc.gridx = 0;
			            gc.gridy = 1;
			            contentPanel.add(nameLabel,gc);
			      		
			      		gc.gridx = 1;
			      		gc.gridy = 1;
			      		contentPanel.add(nameField,gc);
			      		
			            // Last Name
			      		gc.gridx = 0;
			      		gc.gridy = 2;
			      		contentPanel.add(lastNameLabel,gc);
			      		
			      		gc.gridx = 1;
			      		gc.gridy = 2;
			      		contentPanel.add(lastNameField,gc);
			      		
			            // Email
			     		gc.gridx = 0;
			      		gc.gridy = 3;
			      		contentPanel.add(emailLabel,gc);
			      		
			      		gc.gridx = 1;
			      		gc.gridy = 3;
			      		contentPanel.add(emailField,gc);
			      		
			      		// Address
			      		gc.gridx = 0;
			      		gc.gridy = 4;
			      		contentPanel.add(addressLabel,gc);
			      		
			      		gc.gridx = 1;
			      		gc.gridy = 4;
			      		contentPanel.add(addressField,gc);
			      		
			      		// Postal Code
			      		gc.gridx = 0;
			      		gc.gridy = 5;
			      		contentPanel.add(postalLabel,gc);
			      		
			      		gc.gridx = 1;
			      		gc.gridy = 5;
			      		contentPanel.add(postalField,gc);
			    		
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
			      		
			      		// Confirm
			      		gc.gridx = 1;
			      		gc.gridy = 9;
			      		contentPanel.add(getConfirmBtn(), gc);
			           } 
			           else if(temp.isSelected() && temp.getText() == "Bank Transfer")
			           {
			               JOptionPane.showMessageDialog(null,"Paying with: "+temp.getText());  
				            contentPanel.removeAll(); 
				            contentPanel.revalidate();
				            contentPanel.updateUI();
				            setPaymentMethod("Bank Transfer");

			        	    // Name
				            gc.gridx = 0;
				            gc.gridy = 1;
				            contentPanel.add(nameLabel,gc);
				      		
				      		gc.gridx = 1;
				      		gc.gridy = 1;
				      		contentPanel.add(nameField,gc);
				      		
				            // Last Name
				      		gc.gridx = 0;
				      		gc.gridy = 2;
				      		contentPanel.add(lastNameLabel,gc);
				      		
				      		gc.gridx = 1;
				      		gc.gridy = 2;
				      		contentPanel.add(lastNameField,gc);
				      		
				            // Email
				     		gc.gridx = 0;
				      		gc.gridy = 3;
				      		contentPanel.add(emailLabel,gc);
				      		
				      		gc.gridx = 1;
				      		gc.gridy = 3;
				      		contentPanel.add(emailField,gc);
				      		
				      		// Address
				      		gc.gridx = 0;
				      		gc.gridy = 4;
				      		contentPanel.add(addressLabel,gc);
				      		
				      		gc.gridx = 1;
				      		gc.gridy = 4;
				      		contentPanel.add(addressField,gc);
				      		
				      		// Postal Code
				      		gc.gridx = 0;
				      		gc.gridy = 5;
				      		contentPanel.add(postalLabel,gc);
				      		
				      		gc.gridx = 1;
				      		gc.gridy = 5;
				      		contentPanel.add(postalField,gc);
				    		
				     		// Confirm
				      		gc.gridx = 1;
				      		gc.gridy = 9;
				      		contentPanel.add(getConfirmBtn(), gc);
			           }
			        }     
			}
		});
		
	    // GridContstraints
	    this.gc = new GridBagConstraints();
        this.gc.fill = GridBagConstraints.HORIZONTAL;
        this.gc.insets = new Insets(10, 10, 10, 10);
   		
        // Method
 		gc.gridx = 0;
   		gc.gridy = 1;
   		contentPanel.add(method, gc);
   		
   		// Radio Buttons
   		gc.gridx = 0;
   		gc.gridy = 2;
   		contentPanel.add(methodCard, gc);
   		gc.gridx = 0;
   		gc.gridy = 3;
   		contentPanel.add(methodTransfer, gc);
   		// Continue
   		gc.gridx = 0;
   		gc.gridy = 4;
   		contentPanel.add(continueBtn,gc);
	}
	
	public void addCreditCardFields()
	{
	    // Name
        gc.gridx = 0;
        gc.gridy = 1;
        contentPanel.add(nameLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 1;
  		contentPanel.add(nameField,gc);
  		
        // Last Name
  		gc.gridx = 0;
  		gc.gridy = 2;
  		contentPanel.add(lastNameLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 2;
  		contentPanel.add(lastNameField,gc);
  		
        // Email
 		gc.gridx = 0;
  		gc.gridy = 3;
  		contentPanel.add(emailLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 3;
  		contentPanel.add(emailField,gc);
  		
  		// Address
  		gc.gridx = 0;
  		gc.gridy = 4;
  		contentPanel.add(addressLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 4;
  		contentPanel.add(addressField,gc);
  		
  		// Postal Code
  		gc.gridx = 0;
  		gc.gridy = 5;
  		contentPanel.add(postalLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 5;
  		contentPanel.add(postalField,gc);
		
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
	public boolean bankTransferisEmpty() {
		return (addressField.getText().isEmpty() 
				&& postalField.getText().isEmpty() 
				&& emailField.getText().isEmpty() 
				&& nameField.getText().isEmpty() 
				&& lastNameField.getText().isEmpty());
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
	
	public String[] getAllFieldsBankTransfer()
	{
		String[] stringToReturn = {
				this.nameField.getText(), 
				this.lastNameField.getText(),
				this.emailField.getText(), 
				this.addressField.getText(), 
				this.postalField.getText(),
				this.items};
		
		return stringToReturn;
	}

	public JButton getConfirmBtn() {
		return confirmBtn;
	}
	public void setConfirmBtn(JButton confirmBtn) {
		this.confirmBtn = confirmBtn;
	}

	public JButton getContinueBtn() {
		return continueBtn;
	}

	public void setContinueBtn(JButton continueBtn) {
		this.continueBtn = continueBtn;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


}
