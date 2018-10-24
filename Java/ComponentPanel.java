import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ComponentPanel extends JPanel {
		
		private String selectedElement;
		private int selectedIndex;
	
		public ComponentPanel(String GPU[],String CPU[],String RAM[],String Chassi[], String currentName, String currentMemory, String currentCpu, String currentType,String currentChassi, String currentGpu)
		{
			LineBorder line = new LineBorder(Color.BLACK, 1, true);
			this.setPreferredSize(new Dimension(500,200));
			this.setBackground(new Color(245, 245, 245));
			this.setAlignmentX(Component.LEFT_ALIGNMENT );
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			this.setBackground(Color.WHITE);
			this.setBorder(line);
			
			
			// Objects	
			
			String currentCPU = currentCpu;
			String currentGPU = currentGpu;
			String currentRAM = currentMemory;
			String currentCase = currentChassi;
			
			DefaultListModel<String> compModel = new DefaultListModel<String>();
			JList<String> compList = new JList<String>(compModel);
			compList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			compList.setBackground(new Color(240, 248, 255));
			compList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
			compModel.addElement("Processor");
			compModel.addElement("Graphics Card");
			compModel.addElement("Memory");
			compModel.addElement("Chassi");
			
			DefaultListModel<String> itemModel = new DefaultListModel<String>();
			JList<String> itemList = new JList<String>(itemModel);
			itemList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			itemList.setBackground(new Color(240, 248, 255));
			itemList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
			itemList.setPreferredSize(new Dimension(200, 200));
			
			populateList(CPU,itemModel,itemList,currentCpu);
			
			// Events 
			compList.addListSelectionListener(new ListSelectionListener() {
			    public void valueChanged(ListSelectionEvent event) {
			    	int choice = compList.getSelectedIndex();
			    	selectedIndex = compList.getSelectedIndex();
			    	
					switch (choice) {
		            case 0: populateList(CPU,itemModel,itemList,currentCPU);
		            break;
		            case 1: populateList(GPU,itemModel,itemList,currentGPU);
		            break;
		            case 2: populateList(RAM,itemModel,itemList,currentRAM);
		            break;
		            case 3: populateList(Chassi,itemModel,itemList,currentCase);
		            break;
					}				    
				}
			});
			
			itemList.addListSelectionListener(new ListSelectionListener() {
			    public void valueChanged(ListSelectionEvent event) {
			    	setSelectedElement(itemList.getSelectedValue());
				}
			});
			
			compList.setSelectedIndex(0);

			this.add(compList);
			this.add(itemList);			
			

		}
		public void populateList(String[] arr, DefaultListModel<String> model, JList<String> list, String currentItem)
		{
			model.removeAllElements();
			for(int i =0;i<arr.length;i++)
			{
				model.addElement(arr[i]);
			
				if(arr[i].equals(currentItem))
				{
					list.setSelectedIndex(i);
				}
			}
		}
		
		public String getSelectedElement() {
			
			return selectedElement;
		}
		public void setSelectedElement(String selectedElement) {
			this.selectedElement = selectedElement;
		}
		public int getSelectedIndex()
		{
			return selectedIndex;
		}
}
