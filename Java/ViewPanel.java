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

public class ViewPanel extends JPanel {

	private JTextField searchField;
	private JLabel searchLabel, titleLabel;
	private GridBagConstraints gc;
	private JPanel contentPanel;
	private JButton searchBtn, cancelBtn;
	private JTable orderTable;
	private JScrollPane orderTablePane;
	private Border roundedBorder;

	public ViewPanel() {
		
		this.contentPanel = new JPanel();
		this.contentPanel.setPreferredSize(new Dimension(800, 300));
		this.contentPanel.setBackground(new Color(178, 254, 247));
		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		this.contentPanel.setLayout(new GridBagLayout());
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));
		this.add(contentPanel);

		this.searchField = new JTextField(30);
		this.searchLabel = new JLabel("Input Order ID:");
		this.searchLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));

		this.titleLabel = new JLabel("Search for orders");
		this.titleLabel.setFont(new Font("Yu Gothic", Font.BOLD, 24));
		// Labels

		// Buttons
		this.searchBtn = new JButton("Search");
		this.searchBtn.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.searchBtn.setBackground(new Color(255, 255, 255));

		this.setCancelBtn(new JButton("Cancel the order"));
		this.getCancelBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		this.getCancelBtn().setBackground(new Color(255, 255, 255));

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
		this.getOrderTable().setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Order ID", "Customer", "Email", "Items", "Status" }));

		// GridContstraints
		this.gc = new GridBagConstraints();
		this.gc.fill = GridBagConstraints.HORIZONTAL;
		this.gc.insets = new Insets(10, 10, 10, 10);

		// Search
		this.gc.gridx = 0;
		this.gc.gridy = 1;
		this.contentPanel.add(searchLabel, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 1;
		this.contentPanel.add(searchField, gc);

		this.gc.gridx = 2;
		this.gc.gridy = 1;
		this.contentPanel.add(searchBtn, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 2;
		this.contentPanel.add(orderTablePane, gc);

		this.gc.gridx = 1;
		this.gc.gridy = 3;
		this.contentPanel.add(cancelBtn, gc);

	}

	public boolean searchFieldIsEmpty() {
		return this.searchField.getText().isEmpty();
	}

	public Object getSearchField() {
		return this.searchField.getText();
	}

	public JButton getSearchButton() {
		return this.searchBtn;
	}

	public JTable getOrderTable() {
		return this.orderTable;
	}

	public void setOrderTable(JTable orderTable) {
		this.orderTable = orderTable;
	}

	public JButton getCancelBtn() {
		return this.cancelBtn;
	}

	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}
}
