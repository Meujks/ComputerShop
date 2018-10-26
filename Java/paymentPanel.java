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
	private JTextField nameField, lastNameField,emailField, holderField, cardField, cvcField;
    private JLabel nameLabel,lastNameLabel,emailLabel, method, holderLabel,cardLabel,cvcLabel;
    private JComboBox optionList;
    private String [] options;
    private JPanel contentPanel;
    private JButton confirmBtn, shopBtn;
    
	public PaymentPanel()
	{

		JPanel contentPanel = new JPanel();
		
		contentPanel.setPreferredSize(new Dimension(800,400));
		contentPanel.setBackground(new Color(0, 204, 153));
	    Border roundedBorder = new LineBorder(new Color(47, 79, 79), 6, true);
	    contentPanel.setLayout(new GridBagLayout());
	    this.setBorder(roundedBorder);
		this.setBackground(new Color(0, 204, 153));

        this.add(contentPanel);
	    // Textfields and labels 
	    
	    nameField = new JTextField(30);
	    nameLabel = new JLabel("Name:");
	    nameLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));

	    lastNameField = new JTextField(30);
	    lastNameLabel = new JLabel("Last Name:");
	    lastNameLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));

	    emailField = new JTextField(30);
	    emailLabel = new JLabel("Email:");
	    emailLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));
	    
	    method = new JLabel("Choose a payment method:");
		method.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		
		// Combobox
	    options = new String[] { "Credit Card", "Cash", "Bank Transfer" };
	    optionList = new JComboBox(options);
	    optionList.setSelectedIndex(0);
		optionList.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		
		// Buttons
		confirmBtn = new JButton("Confirm");
		confirmBtn.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		confirmBtn.setBackground(new Color(255, 255, 255));
		
		shopBtn = new JButton("Continue Shopping");
		shopBtn.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		shopBtn.setBackground(new Color(255, 255, 255));
		
		
		//mkhardwareproject@gmail.com
		
	    // GridContstraints
	    GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);
		
		// Events
      	
        // Create Email Object which sends a email to recipent
        confirmBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    //Check if all fields are filled
				  String choice = (String) optionList.getSelectedItem();

				  switch (choice) {
				      case "Credit Card":
						  if(!creditFieldisEmpty())
						  {
							  // Format card String so entire credit card number isn't sent
							  String cardString = cardField.getText();
							  cardString = cardString.substring(0, Math.min(cardString.length(), 4)); 
							  
							  // Create an emailSend object which creates an email and sends it to the user.
							  EmailSend emailObject = new EmailSend(emailField.getText(), nameField.getText(), lastNameField.getText(), holderField.getText(), cardString, "You forgot to order anything");
							  JOptionPane.showMessageDialog(null,"Success, an email has been sent to: " + emailField.getText() + " Containing order details");
						  }
						  else
						  {
							  JOptionPane.showMessageDialog(null,"Missing input on all fields, please fill in information for every field.");
						  }
						
				      break;
				  case "Cash":
				
				      break;
				  case "Bank Transfer":
				
				      break;
				  default:
				      break;
			  }
		  }

			private boolean creditFieldisEmpty() {
				return (emailField.getText().isEmpty() && nameField.getText().isEmpty() && lastNameField.getText().isEmpty() && holderField.getText().isEmpty() && cardField.getText().isEmpty() && cvcField.getText().isEmpty());
			}
        }
		);
		optionList.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				 
				  String choice = (String) optionList.getSelectedItem();
				  
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
	                		gc.gridy = 4;
	                		contentPanel.add(holderLabel,gc);

	                		gc.gridx = 1;
	                		gc.gridy = 4;
	                		contentPanel.add(holderField,gc);
	                		
	                		// Credit Card
	                		gc.gridx = 0;
	                		gc.gridy = 5;
	                		contentPanel.add(cardLabel,gc);
	                		
	                		gc.gridx = 1;
	                		gc.gridy = 5;
	                		contentPanel.add(cardField,gc);
	                		
	                		// CVC
	                		gc.gridx = 0;
	                		gc.gridy = 6;
	                		contentPanel.add(cvcLabel,gc);
	                		
	                		gc.gridx = 1;
	                		gc.gridy = 6;
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
  		
  		// Method
		gc.gridx = 0;
  		gc.gridy = 3;
  		contentPanel.add(method, gc);
  		
  		// ComboBox
  		gc.gridx = 1;
  		gc.gridy = 3;
  		contentPanel.add(optionList, gc);
		
		gc.gridx = 0;
		gc.gridy = 7;
		contentPanel.add(shopBtn,gc);
		
		gc.gridx = 1;
		gc.gridy = 7;
		contentPanel.add(confirmBtn,gc);

	}


}
