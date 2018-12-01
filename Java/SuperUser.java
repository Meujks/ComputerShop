import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class SuperUser extends JPanel {

	private JLabel titleLabel;
	private GridBagConstraints gc;
	private JPanel contentPanel;
	private JButton notShippedToShippedBtn,shippedToNotShippedBtn, cancelBtn, retrieveBtn;
	private JTable orderTable;
	private JScrollPane orderTablePane;

	public SuperUser() {
		contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(800, 300));
		contentPanel.setBackground(new Color(178, 254, 247));
		Border roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		contentPanel.setLayout(new GridBagLayout());
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));
		this.add(contentPanel);

		
		// Buttons
		
		setRetrieveBtn(new JButton("Retrieve Table"));
		getRetrieveBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getRetrieveBtn().setBackground(new Color(255, 255, 255));
		
		setCancelBtn(new JButton("Cancel the order"));
		getCancelBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getCancelBtn().setBackground(new Color(255, 255, 255));
		
		setShippedToNotShippedBtn(new JButton("Change to Shipped"));
		getShippedToNotShippedBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getShippedToNotShippedBtn().setBackground(new Color(255, 255, 255));
		
		setNotShippedToShippedBtn(new JButton("Change to Not Shipped"));
		getNotShippedToShippedBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getNotShippedToShippedBtn().setBackground(new Color(255, 255, 255));

		// Table
		orderTablePane = new JScrollPane();
		orderTablePane.setPreferredSize(new Dimension(300, 50));
		setOrderTable(new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		orderTablePane.setViewportView(getOrderTable());
		getOrderTable().setFont(new Font("Yu Gothic", Font.BOLD, 12));
		getOrderTable().setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Order ID", "Customer", "Email", "Status" }));

		// GridContstraints
		this.gc = new GridBagConstraints();
		this.gc.fill = GridBagConstraints.HORIZONTAL;
		this.gc.insets = new Insets(10, 10, 10, 10);

		gc.gridx = 1;
		gc.gridy = 0;
		contentPanel.add(orderTablePane, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		contentPanel.add(retrieveBtn, gc);

		gc.gridx = 0;
		gc.gridy = 4;
		contentPanel.add(cancelBtn, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		contentPanel.add(notShippedToShippedBtn, gc);
		
		gc.gridx = 2;
		gc.gridy = 4;
		contentPanel.add(shippedToNotShippedBtn, gc);


	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public JTable getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(JTable orderTable) {
		this.orderTable = orderTable;
	}

	public JButton getShippedToNotShippedBtn() {
		return shippedToNotShippedBtn;
	}

	public void setShippedToNotShippedBtn(JButton shippedToNotShippedBtn) {
		this.shippedToNotShippedBtn = shippedToNotShippedBtn;
	}

	public JButton getNotShippedToShippedBtn() {
		return notShippedToShippedBtn;
	}

	public void setNotShippedToShippedBtn(JButton notShippedToShippedBtn) {
		this.notShippedToShippedBtn = notShippedToShippedBtn;
	}

	public JButton getRetrieveBtn() {
		return retrieveBtn;
	}

	public void setRetrieveBtn(JButton retrieveBtn) {
		this.retrieveBtn = retrieveBtn;
	}
}
