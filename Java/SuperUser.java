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
	private JButton notShippedToShippedBtn, shippedToNotShippedBtn, cancelBtn, retrieveBtn;
	private JTable orderTable;
	private JScrollPane orderTablePane;
	private Border roundedBorder;

	public SuperUser() {
		this.contentPanel = new JPanel();
		this.contentPanel.setPreferredSize(new Dimension(800, 300));
		this.contentPanel.setBackground(new Color(178, 254, 247));
		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		this.contentPanel.setLayout(new GridBagLayout());
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));
		this.add(contentPanel);

		// Buttons

		this.setRetrieveBtn(new JButton("Retrieve Table"));
		this.getRetrieveBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.getRetrieveBtn().setBackground(new Color(255, 255, 255));

		this.setCancelBtn(new JButton("Cancel the order"));
		this.getCancelBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.getCancelBtn().setBackground(new Color(255, 255, 255));

		this.setShippedToNotShippedBtn(new JButton("Change to Shipped"));
		this.getShippedToNotShippedBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.getShippedToNotShippedBtn().setBackground(new Color(255, 255, 255));

		this.setNotShippedToShippedBtn(new JButton("Change to Not Shipped"));
		this.getNotShippedToShippedBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.getNotShippedToShippedBtn().setBackground(new Color(255, 255, 255));

		// Table
		this.orderTablePane = new JScrollPane();
		this.orderTablePane.setPreferredSize(new Dimension(300, 50));
		this.setOrderTable(new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		this.orderTablePane.setViewportView(getOrderTable());
		this.getOrderTable().setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.getOrderTable().setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Order ID", "Customer", "Email", "Status" }));

		// GridContstraints
		this.gc = new GridBagConstraints();
		this.gc.fill = GridBagConstraints.HORIZONTAL;
		this.gc.insets = new Insets(10, 10, 10, 10);

		this.gc.gridx = 1;
		this.gc.gridy = 0;
		this.contentPanel.add(orderTablePane, gc);

		this.gc.gridx = 2;
		this.gc.gridy = 0;
		this.contentPanel.add(retrieveBtn, gc);

		this.gc.gridx = 0;
		this.gc.gridy = 4;
		this.contentPanel.add(cancelBtn, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 4;
		this.contentPanel.add(notShippedToShippedBtn, gc);

		this.gc.gridx = 2;
		this.gc.gridy = 4;
		this.contentPanel.add(shippedToNotShippedBtn, gc);

	}

	public JButton getCancelBtn() {
		return this.cancelBtn;
	}

	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public JTable getOrderTable() {
		return this.orderTable;
	}

	public void setOrderTable(JTable orderTable) {
		this.orderTable = orderTable;
	}

	public JButton getShippedToNotShippedBtn() {
		return this.shippedToNotShippedBtn;
	}

	public void setShippedToNotShippedBtn(JButton shippedToNotShippedBtn) {
		this.shippedToNotShippedBtn = shippedToNotShippedBtn;
	}

	public JButton getNotShippedToShippedBtn() {
		return this.notShippedToShippedBtn;
	}

	public void setNotShippedToShippedBtn(JButton notShippedToShippedBtn) {
		this.notShippedToShippedBtn = notShippedToShippedBtn;
	}

	public JButton getRetrieveBtn() {
		return this.retrieveBtn;
	}

	public void setRetrieveBtn(JButton retrieveBtn) {
		this.retrieveBtn = retrieveBtn;
	}
}
