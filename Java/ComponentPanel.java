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

	// DESKTOP
	public ComponentPanel(String GPU[], String CPU[], String RAM[], String Chassi[], String currentName,
			String currentMemory, String currentCpu, String currentType, String currentChassi, String currentGpu) {
		Border roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);

		this.setPreferredSize(new Dimension(500, 200));
		this.setForeground(new Color(178, 254, 247));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));

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

		populateList(CPU, itemModel, itemList, currentCpu);

		// Events
		compList.addListSelectionListener(new ListSelectionListener() {
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

		itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				setSelectedElement(itemList.getSelectedValue());
			}
		});

		compList.setSelectedIndex(0);

		this.add(compList);
		this.add(itemList);

	}

	// LAPTOP
	public ComponentPanel(String GPU[], String CPU[], String RAM[], String currentName, String currentMemory,
			String currentCpu, String currentType, String currentGpu) {
		Border roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);
		this.setPreferredSize(new Dimension(500, 200));
		this.setForeground(new Color(51, 102, 102));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));

		// Objects

		String currentCPU = currentCpu;
		String currentGPU = currentGpu;
		String currentRAM = currentMemory;

		DefaultListModel<String> compModel = new DefaultListModel<String>();
		JList<String> compList = new JList<String>(compModel);
		compList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		compList.setBackground(new Color(240, 248, 255));
		compList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		compModel.addElement("Processor");
		compModel.addElement("Graphics Card");
		compModel.addElement("Memory");

		DefaultListModel<String> itemModel = new DefaultListModel<String>();
		JList<String> itemList = new JList<String>(itemModel);
		itemList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		itemList.setBackground(new Color(240, 248, 255));
		itemList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		itemList.setPreferredSize(new Dimension(200, 200));

		populateList(CPU, itemModel, itemList, currentCpu);

		// Events
		compList.addListSelectionListener(new ListSelectionListener() {
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

		itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				setSelectedElement(itemList.getSelectedValue());
			}
		});

		compList.setSelectedIndex(0);

		this.add(compList);
		this.add(itemList);

	}

	// Server
	public ComponentPanel(String CPU[], String RAM[], String currentName, String currentMemory, String currentCpu) {
		Border roundedBorder = new LineBorder(new Color(178, 254, 247), 3, true);

		this.setPreferredSize(new Dimension(500, 200));
		this.setForeground(new Color(51, 102, 102));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(roundedBorder);
		this.setBackground(new Color(178, 254, 247));

		// Objects

		String currentCPU = currentCpu;
		String currentRAM = currentMemory;

		DefaultListModel<String> compModel = new DefaultListModel<String>();
		JList<String> compList = new JList<String>(compModel);
		compList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		compList.setBackground(new Color(240, 248, 255));
		compList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		compModel.addElement("Processor");
		compModel.addElement("Memory");

		DefaultListModel<String> itemModel = new DefaultListModel<String>();
		JList<String> itemList = new JList<String>(itemModel);
		itemList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		itemList.setBackground(new Color(240, 248, 255));
		itemList.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		itemList.setPreferredSize(new Dimension(200, 200));

		populateList(CPU, itemModel, itemList, currentCpu);

		// Events
		compList.addListSelectionListener(new ListSelectionListener() {
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

		itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				setSelectedElement(itemList.getSelectedValue());
			}
		});

		compList.setSelectedIndex(0);

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

		return selectedElement;
	}

	public void setSelectedElement(String selectedElement) {
		this.selectedElement = selectedElement;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}
}
