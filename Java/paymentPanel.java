import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class PaymentPanel extends JPanel {
	

	public PaymentPanel()
	{
		this.setPreferredSize(new Dimension(800,400));
		this.setBackground(new Color(0, 204, 153));
		this.setAlignmentX(Component.LEFT_ALIGNMENT );
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new LineBorder(new Color(0, 102, 102), 3, true));
		
		
		JLabel method = new JLabel("Choose a payment method");
		method.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		JLabel name = new JLabel("Name:");
		name.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		JLabel lastName = new JLabel("Lastname:");
		lastName.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		JLabel email = new JLabel("Email:");
		email.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		JLabel creditCard = new JLabel("Credit Card Number:");
		creditCard.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		JLabel cvc = new JLabel("CVC:");
		cvc.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		
		JTextField nameField = new JTextField();
	    nameField.setBounds(50,100, 200,30);  
		JTextField lastNameField = new JTextField();
		lastNameField.setBounds(50,100, 200,30);  
		JTextField emailField = new JTextField();
		emailField.setBounds(50,100, 200,30);  
		JTextField creditCardField = new JTextField();
		creditCardField.setBounds(50,100, 200,30);  
		JTextField cvcField = new JTextField();
		cvcField.setBounds(50,100, 200,30);  

		JButton purchaseBtn = new JButton("Purhcase");
		purchaseBtn.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		purchaseBtn.setBackground(new Color(255, 255, 255));
		
		this.add(method);
		this.add(name);
		this.add(nameField);
		this.add(lastName);
		this.add(lastNameField);
		this.add(email);
		this.add(emailField);
		this.add(creditCard);
		this.add(creditCardField);
		this.add(cvc);
		this.add(cvcField);
		this.add(purchaseBtn);


	}


}
