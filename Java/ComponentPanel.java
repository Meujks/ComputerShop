import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ComponentPanel extends JPanel {

	private String selectedElement;
	private int selectedIndex;

	private String currentCPU;
	private String currentGPU;
	private String currentRAM;
	private String currentCase;

	private JList<String> itemList;
	private JList<String> compList;
	private DefaultListModel<String> itemModel;
	private DefaultListModel<String> compModel;
	private Border roundedBorder;

	// DESKTOP
	public ComponentPanel(String GPU[], String CPU[], String RAM[], String Chassi[], String currentName,
			String currentMemory, String currentCpu, String currentType, String currentChassi, String currentGpu) {

		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		this.setPreferredSize(new Dimension(500, 200));
		this.setForeground(new Color(178, 254, 247));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));

		// Objects
		this.currentCPU = currentCpu;
		this.currentGPU = currentGpu;
		this.currentRAM = currentMemory;
		this.currentCase = currentChassi;
		this.compModel = new DefaultListModel<String>();
		this.compList = new JList<String>(compModel);
		this.compList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.compList.setBackground(new Color(240, 248, 255));
		this.compList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.compModel.addElement("Processor");
		this.compModel.addElement("Graphics Card");
		this.compModel.addElement("Memory");
		this.compModel.addElement("Chassi");
		this.itemModel = new DefaultListModel<String>();
		this.itemList = new JList<String>(itemModel);
		this.itemList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.itemList.setBackground(new Color(240, 248, 255));
		this.itemList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.itemList.setPreferredSize(new Dimension(200, 200));
		this.populateList(CPU, itemModel, itemList, currentCpu);

		// Events
		this.compList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int choice = compList.getSelectedIndex();
				selectedIndex = compList.getSelectedIndex();

				switch (choice) {
				case 0:
					populateList(CPU, itemModel, itemList, currentCPU);
					break;
				case 1:
					populateList(GPU, itemModel, itemList, currentGPU);
					break;
				case 2:
					populateList(RAM, itemModel, itemList, currentRAM);
					break;
				case 3:
					populateList(Chassi, itemModel, itemList, currentCase);
					break;
				}
			}
		});

		this.itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				setSelectedElement(itemList.getSelectedValue());
			}
		});
		
		this.compList.setSelectedIndex(0);
		this.add(compList);
		this.add(itemList);
	}

	// LAPTOP
	public ComponentPanel(String GPU[], String CPU[], String RAM[], String currentName, String currentMemory,
			String currentCpu, String currentType, String currentGpu) {
		
		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		this.setPreferredSize(new Dimension(500, 200));
		this.setForeground(new Color(51, 102, 102));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));
		
		// Objects
		this.currentCPU = currentCpu;
		this.currentGPU = currentGpu;
		this.currentRAM = currentMemory;
		this.compModel = new DefaultListModel<String>();
		this.compList = new JList<String>(compModel);
		this.compList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.compList.setBackground(new Color(240, 248, 255));
		this.compList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.compModel.addElement("Processor");
		this.compModel.addElement("Graphics Card");
		this.compModel.addElement("Memory");
		this.itemModel = new DefaultListModel<String>();
		this.itemList = new JList<String>(itemModel);
		this.itemList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.itemList.setBackground(new Color(240, 248, 255));
		this.itemList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.itemList.setPreferredSize(new Dimension(200, 200));
		this.populateList(CPU, itemModel, itemList, currentCpu);

		// Events
		this.compList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int choice = compList.getSelectedIndex();
				selectedIndex = compList.getSelectedIndex();

				switch (choice) {
				case 0:
					populateList(CPU, itemModel, itemList, currentCPU);
					break;
				case 1:
					populateList(GPU, itemModel, itemList, currentGPU);
					break;
				case 2:
					populateList(RAM, itemModel, itemList, currentRAM);
					break;
				}
			}
		});

		this.itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				setSelectedElement(itemList.getSelectedValue());
			}
		});

		this.compList.setSelectedIndex(0);
		this.add(compList);
		this.add(itemList);

	}

	// Server
	public ComponentPanel(String CPU[], String RAM[], String currentName, String currentMemory, String currentCpu) {

		this.roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		this.setPreferredSize(new Dimension(500, 200));
		this.setForeground(new Color(51, 102, 102));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));

		// Objects
		this.currentCPU = currentCpu;
		this.currentRAM = currentMemory;
		this.compModel = new DefaultListModel<String>();
		this.compList = new JList<String>(compModel);
		this.compList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.compList.setBackground(new Color(240, 248, 255));
		this.compList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.compModel.addElement("Processor");
		this.compModel.addElement("Memory");
		this.itemModel = new DefaultListModel<String>();
		this.itemList = new JList<String>(itemModel);
		this.itemList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.itemList.setBackground(new Color(240, 248, 255));
		this.itemList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		this.itemList.setPreferredSize(new Dimension(200, 200));
		this.populateList(CPU, itemModel, itemList, currentCpu);

		// Events
		this.compList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int choice = compList.getSelectedIndex();
				selectedIndex = compList.getSelectedIndex();
				switch (choice) {
				case 0:
					populateList(CPU, itemModel, itemList, currentCPU);
					break;
				case 1:
					populateList(RAM, itemModel, itemList, currentRAM);
					break;
				}
			}
		});
		this.itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				setSelectedElement(itemList.getSelectedValue());
			}
		});

		this.compList.setSelectedIndex(0);
		this.add(compList);
		this.add(itemList);
	}

	public void populateList(String[] arr, DefaultListModel<String> model, JList<String> list, String currentItem) {
		
		model.removeAllElements();
		for (int i = 0; i < arr.length; i++) {
			model.addElement(arr[i]);

			if (arr[i].equals(currentItem)) {
				list.setSelectedIndex(i);
			}
		}
	}

	public String getSelectedElement() {
		return this.selectedElement;
	}

	public void setSelectedElement(String selectedElement) {
		this.selectedElement = selectedElement;
	}

	public int getSelectedIndex() {
		return this.selectedIndex;
	}
}
