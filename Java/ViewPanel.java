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
	private JLabel searchLabel,titleLabel;
    private GridBagConstraints gc;
    private JPanel contentPanel;
    private JButton searchBtn, cancelBtn;
    private JTable orderTable;
    private JScrollPane orderTablePane;
    
	public ViewPanel()
	{
		contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(800,300));
		contentPanel.setBackground(new Color(178,254,247));
	    Border roundedBorder = new LineBorder(new Color(178,254,247), 3, true);
	    contentPanel.setLayout(new GridBagLayout());
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178,254,247));
        this.add(contentPanel);
        
        searchField = new JTextField(30);
	    searchLabel = new JLabel("Input Order ID:");
	    searchLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));
	    
	    titleLabel = new JLabel("Search for orders");
	    titleLabel.setFont(new Font("Yu Gothic", Font.BOLD, 24));
        // Labels
	    
	    // Buttons
		searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		searchBtn.setBackground(new Color(255, 255, 255));
		
		setCancelBtn(new JButton("Cancel the order"));
		getCancelBtn().setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getCancelBtn().setBackground(new Color(255, 255, 255));
		
		// Table 
		orderTablePane = new JScrollPane();
		orderTablePane.setPreferredSize(new Dimension(300,50));
		setOrderTable(new JTable(){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		});
		orderTablePane.setViewportView(getOrderTable());
		getOrderTable().setFont(new Font("Yu Gothic", Font.BOLD, 12));
		getOrderTable().setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order ID", "Customer", "Email", "Items","Status"
			}
		));
        
	    // GridContstraints
	    this.gc = new GridBagConstraints();
        this.gc.fill = GridBagConstraints.HORIZONTAL;
        this.gc.insets = new Insets(10, 10, 10, 10);
        
        // Search
        gc.gridx = 0;
        gc.gridy = 1;
        contentPanel.add(searchLabel,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 1;
  		contentPanel.add(searchField,gc);
  		
  		gc.gridx = 2;
  		gc.gridy = 1;
  		contentPanel.add(searchBtn,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 2;
  		contentPanel.add(orderTablePane,gc);
  		
  		gc.gridx = 1;
  		gc.gridy = 3;
  		contentPanel.add(cancelBtn,gc);
  		
	}
	public boolean searchFieldIsEmpty()
	{
		return this.searchField.getText().isEmpty();
	}
	public Object getSearchField() {
		return this.searchField.getText();
	}
	public JButton getSearchButton()
	{
		return this.searchBtn;
	}
	public JTable getOrderTable() {
		return orderTable;
	}
	public void setOrderTable(JTable orderTable) {
		this.orderTable = orderTable;
	}
	public JButton getCancelBtn() {
		return cancelBtn;
	}
	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}
}
