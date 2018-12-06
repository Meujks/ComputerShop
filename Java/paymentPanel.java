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

	private JTextField nameField, lastNameField, emailField, addressField, postalField, holderField, cardField,
			cvcField;
	private JLabel nameLabel, lastNameLabel, emailLabel, addressLabel, postalLabel, method, holderLabel, cardLabel,
			cvcLabel;

	private JPanel contentPanel;
	private String items, paymentMethod;
	private GridBagConstraints gc;
	private JButton confirmBtn, continueBtn;
	private JRadioButton methodCard, methodTransfer;
	private ButtonGroup buttonGrp;
	private Border roundedBorder;
	private Border radioBorder;

	public PaymentPanel(String itemsFromCart) {

		this.contentPanel = new JPanel();
		this.contentPanel.setPreferredSize(new Dimension(700, 500));
		this.contentPanel.setBackground(new Color(178, 254, 247));
		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 6, true);
		this.contentPanel.setLayout(new GridBagLayout());
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));

		this.add(contentPanel);

		// Radio Buttons
		this.methodCard = new JRadioButton("Credit Card");
		this.methodTransfer = new JRadioButton("Bank Transfer");
		this.buttonGrp = new ButtonGroup();
		this.radioBorder = new LineBorder(new Color(47, 79, 79), 2, true);
		this.buttonGrp.add(methodCard);
		this.buttonGrp.add(methodTransfer);
		this.methodCard.setSelected(true);

		// Textfields and labels
		this.items = itemsFromCart;
		this.setPaymentMethod("None");

		this.nameField = new JTextField(30);
		this.nameLabel = new JLabel("Name:");
		this.nameLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));

		this.lastNameField = new JTextField(30);
		this.lastNameLabel = new JLabel("Last Name:");
		this.lastNameLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));

		this.emailField = new JTextField(30);
		this.emailLabel = new JLabel("Email:");
		this.emailLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));

		this.addressField = new JTextField(30);
		this.addressLabel = new JLabel("Billing Address:");
		this.addressLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));

		this.postalField = new JTextField(30);
		this.postalLabel = new JLabel("Postal Code:");
		this.postalLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));

		// Credit Card Fields and Labels
		this.holderField = new JTextField(30);
		this.holderLabel = new JLabel("Account Holder:");

		this.cardField = new JTextField(30);
		this.cardLabel = new JLabel("Card Number:");

		this.cvcField = new JTextField(10);
		this.cvcLabel = new JLabel("Security Code:");

		this.method = new JLabel("Choose a payment method:");
		this.method.setFont(new Font("Yu Gothic", Font.BOLD, 14));

		// Buttons
		this.setConfirmBtn(new JButton("Submit"));
		this.getConfirmBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.getConfirmBtn().setBackground(new Color(255, 255, 255));

		this.setContinueBtn(new JButton("Continue"));
		this.getContinueBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.getContinueBtn().setBackground(new Color(255, 255, 255));

		// Button Listeners
		this.continueBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Enumeration<AbstractButton> allRadioButton = buttonGrp.getElements();
				while (allRadioButton.hasMoreElements()) {
					JRadioButton temp = (JRadioButton) allRadioButton.nextElement();
					if (temp.isSelected() && temp.getText() == "Credit Card") {
						JOptionPane.showMessageDialog(null, "Paying with: " + temp.getText());
						contentPanel.removeAll();
						contentPanel.revalidate();
						contentPanel.updateUI();

						setPaymentMethod("Credit Card");
						// Name
						gc.gridx = 0;
						gc.gridy = 1;
						contentPanel.add(nameLabel, gc);

						gc.gridx = 1;
						gc.gridy = 1;
						contentPanel.add(nameField, gc);

						// Last Name
						gc.gridx = 0;
						gc.gridy = 2;
						contentPanel.add(lastNameLabel, gc);

						gc.gridx = 1;
						gc.gridy = 2;
						contentPanel.add(lastNameField, gc);

						// Email
						gc.gridx = 0;
						gc.gridy = 3;
						contentPanel.add(emailLabel, gc);

						gc.gridx = 1;
						gc.gridy = 3;
						contentPanel.add(emailField, gc);

						// Address
						gc.gridx = 0;
						gc.gridy = 4;
						contentPanel.add(addressLabel, gc);

						gc.gridx = 1;
						gc.gridy = 4;
						contentPanel.add(addressField, gc);

						// Postal Code
						gc.gridx = 0;
						gc.gridy = 5;
						contentPanel.add(postalLabel, gc);

						gc.gridx = 1;
						gc.gridy = 5;
						contentPanel.add(postalField, gc);

						// Holder
						gc.gridx = 0;
						gc.gridy = 6;
						contentPanel.add(holderLabel, gc);

						gc.gridx = 1;
						gc.gridy = 6;
						contentPanel.add(holderField, gc);

						// Credit Card
						gc.gridx = 0;
						gc.gridy = 7;
						contentPanel.add(cardLabel, gc);

						gc.gridx = 1;
						gc.gridy = 7;
						contentPanel.add(cardField, gc);

						// CVC
						gc.gridx = 0;
						gc.gridy = 8;
						contentPanel.add(cvcLabel, gc);

						gc.gridx = 1;
						gc.gridy = 8;
						contentPanel.add(cvcField, gc);

						// Confirm
						gc.gridx = 1;
						gc.gridy = 9;
						contentPanel.add(getConfirmBtn(), gc);
					} else if (temp.isSelected() && temp.getText() == "Bank Transfer") {

						JOptionPane.showMessageDialog(null, "Paying with: " + temp.getText());
						contentPanel.removeAll();
						contentPanel.revalidate();
						contentPanel.updateUI();
						setPaymentMethod("Bank Transfer");

						// Name
						gc.gridx = 0;
						gc.gridy = 1;
						contentPanel.add(nameLabel, gc);

						gc.gridx = 1;
						gc.gridy = 1;
						contentPanel.add(nameField, gc);

						// Last Name
						gc.gridx = 0;
						gc.gridy = 2;
						contentPanel.add(lastNameLabel, gc);

						gc.gridx = 1;
						gc.gridy = 2;
						contentPanel.add(lastNameField, gc);

						// Email
						gc.gridx = 0;
						gc.gridy = 3;
						contentPanel.add(emailLabel, gc);

						gc.gridx = 1;
						gc.gridy = 3;
						contentPanel.add(emailField, gc);

						// Address
						gc.gridx = 0;
						gc.gridy = 4;
						contentPanel.add(addressLabel, gc);

						gc.gridx = 1;
						gc.gridy = 4;
						contentPanel.add(addressField, gc);

						// Postal Code
						gc.gridx = 0;
						gc.gridy = 5;
						contentPanel.add(postalLabel, gc);

						gc.gridx = 1;
						gc.gridy = 5;
						contentPanel.add(postalField, gc);

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
		this.gc.gridx = 0;
		this.gc.gridy = 1;
		this.contentPanel.add(method, gc);

		// Radio Buttons
		this.gc.gridx = 0;
		this.gc.gridy = 2;
		this.contentPanel.add(methodCard, gc);

		this.gc.gridx = 0;
		this.gc.gridy = 3;
		this.contentPanel.add(methodTransfer, gc);
		// Continue
		this.gc.gridx = 0;
		this.gc.gridy = 4;
		this.contentPanel.add(continueBtn, gc);
	}

	public void addCreditCardFields() {
		// Name
		this.gc.gridx = 0;
		this.gc.gridy = 1;
		this.contentPanel.add(nameLabel, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 1;
		this.contentPanel.add(nameField, gc);

		// Last Name
		this.gc.gridx = 0;
		this.gc.gridy = 2;
		this.contentPanel.add(lastNameLabel, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 2;
		this.contentPanel.add(lastNameField, gc);

		// Email
		this.gc.gridx = 0;
		this.gc.gridy = 3;
		this.contentPanel.add(emailLabel, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 3;
		this.contentPanel.add(emailField, gc);

		// Address
		this.gc.gridx = 0;
		this.gc.gridy = 4;
		this.contentPanel.add(addressLabel, gc);

		gc.gridx = 1;
		gc.gridy = 4;
		contentPanel.add(addressField, gc);

		// Postal Code
		this.gc.gridx = 0;
		this.gc.gridy = 5;
		this.contentPanel.add(postalLabel, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 5;
		this.contentPanel.add(postalField, gc);

		// Holder
		this.gc.gridx = 0;
		this.gc.gridy = 6;
		this.contentPanel.add(holderLabel, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 6;
		this.contentPanel.add(holderField, gc);

		// Credit Card
		this.gc.gridx = 0;
		this.gc.gridy = 7;
		this.contentPanel.add(cardLabel, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 7;
		this.contentPanel.add(cardField, gc);

		// CVC
		this.gc.gridx = 0;
		this.gc.gridy = 8;
		this.contentPanel.add(cvcLabel, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 8;
		this.contentPanel.add(cvcField, gc);

		// Confirm
		this.gc.gridx = 1;
		this.gc.gridy = 9;
		this.contentPanel.add(getConfirmBtn(), gc);
	}

	public boolean creditFieldisEmpty() {
		return (this.addressField.getText().isEmpty() && this.postalField.getText().isEmpty()
				&& this.emailField.getText().isEmpty() && this.nameField.getText().isEmpty()
				&& this.lastNameField.getText().isEmpty() && this.holderField.getText().isEmpty()
				&& this.cardField.getText().isEmpty() && this.cvcField.getText().isEmpty());
	}

	public boolean bankTransferisEmpty() {
		return (this.addressField.getText().isEmpty() && this.postalField.getText().isEmpty()
				&& this.emailField.getText().isEmpty() && this.nameField.getText().isEmpty()
				&& this.lastNameField.getText().isEmpty());
	}

	public String[] getAllFieldsCreditCard() {
		String[] stringToReturn = { this.nameField.getText(), this.lastNameField.getText(), this.emailField.getText(),
				this.addressField.getText(), this.postalField.getText(), this.holderField.getText(),
				this.cardField.getText(), this.items };

		return stringToReturn;
	}

	public String[] getAllFieldsBankTransfer() {
		String[] stringToReturn = { this.nameField.getText(), this.lastNameField.getText(), this.emailField.getText(),
				this.addressField.getText(), this.postalField.getText(), this.items };

		return stringToReturn;
	}

	public JButton getConfirmBtn() {
		return this.confirmBtn;
	}

	public void setConfirmBtn(JButton confirmBtn) {
		this.confirmBtn = confirmBtn;
	}

	public JButton getContinueBtn() {
		return this.continueBtn;
	}

	public void setContinueBtn(JButton continueBtn) {
		this.continueBtn = continueBtn;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
