package gainProject;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Vector;

class ShopModifyPage extends JPanel {
	public ShopModifyPage(CreateDepot store, CardLayout card, Container container) {
		setLayout(new BorderLayout());

		CenterPanel centerPanel = new CenterPanel(store);
		EastPanel eastPanel = new EastPanel(store, centerPanel);
		NorthPanel northPanel = new NorthPanel(centerPanel, eastPanel, store, card, container);

		add(centerPanel, BorderLayout.CENTER);
		add(eastPanel, BorderLayout.EAST);
		add(northPanel, BorderLayout.NORTH);
	}
}

class NorthPanel extends JPanel {
	public NorthPanel(CenterPanel centerPanel, EastPanel eastPanel, CreateDepot store, CardLayout card, Container c) {
		setLayout(new FlowLayout());
		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				String source = button.getText();

				eastPanel.setVisible(true);
				eastPanel.getEvent(source);
				centerPanel.warning.setVisible(false);
			}
		});
		add(addButton);

		JButton modifyButton = new JButton("수정");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				String source = button.getText();

				if (centerPanel.shopList.getSelectedRow() != -1) {
					centerPanel.warning.setVisible(false);
					eastPanel.setVisible(true);
					eastPanel.getEvent(source);
				} else
					centerPanel.warning.setVisible(true);
			}
		});
		add(modifyButton);

		JButton deleteButton = new JButton("삭제");
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = centerPanel.shopList.getSelectedRow();
				if (selectedRow != -1) {
					try {
						centerPanel.shopControl.removeRow(selectedRow);
						store.delete(selectedRow);
					} catch (IOException ioe) {
					}
					centerPanel.warning.setVisible(false);
				} else
					centerPanel.warning.setVisible(true);
				centerPanel.shopList.repaint();
			}
		});
		add(deleteButton);

		JButton exitButton = new JButton("나가기");
		exitButton.addMouseListener(new PageChanger(card, c));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eastPanel.setVisible(false);
				centerPanel.warning.setVisible(false);
			}
		});
		add(exitButton);
	}
}

class CenterPanel extends JPanel {
	DefaultTableModel shopControl;
	JTable shopList;
	JLabel warning;

	public CenterPanel(CreateDepot store) {
		setLayout(new FlowLayout());
		String[][] shopInfo = new String[store.storeList.size()][3];
		String[] bogi = { "종류", "이름", "위치" };
		for (int i = 0; i < store.storeList.size(); i++)
			shopInfo[i] = store.storeList.get(i);

		shopControl = new DefaultTableModel(shopInfo, bogi) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		shopList = new JTable(shopControl);
		add(new JScrollPane(shopList));

		warning = new JLabel("수정/삭제할 행을 선택하세요");
		warning.setVisible(false);
		add(warning);
	}
}

class EastPanel extends JPanel {
	GridBagConstraints constraint = new GridBagConstraints();
	GridBagLayout gbl = new GridBagLayout();
	String source;

	public EastPanel(CreateDepot store, CenterPanel centerPanel) {
		TypePanel typePanel=new TypePanel(store);
		LocationPanel locationPanel=new LocationPanel(store);
		
		constraint.weightx = 1;
		constraint.weighty = 1;

		setLayout(gbl);
		setVisible(false);

		JTextField name = new JTextField(20);

		JButton confirmButton = new JButton("확인");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] shopData = new String[3];
				shopData[0] = typePanel.selected;
				shopData[1] = name.getText();
				shopData[2] = locationPanel.selected;
				if (source.equals("추가"))
					try {
						centerPanel.shopControl.addRow(shopData);
						store.add(shopData);
					} catch (IOException ioe) {
					}
				else if (source.equals("수정"))
					try {
						int selected = centerPanel.shopList.getSelectedRow();
						centerPanel.shopControl.removeRow(selected);
						centerPanel.shopControl.insertRow(selected, shopData);
						store.modify(shopData, selected);
					} catch (IOException ioe) {
					}
				name.setText(null);
			}
		});

		JButton cancelButton = new JButton("취소");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText(null);
				setVisible(false);
			}
		});

		layoutSet(new JLabel("종류"), 2, 0, 1, 1);
		layoutSet(typePanel, 0, 1, 5, 1);
		layoutSet(new JLabel("이름"), 2, 2, 1, 1);
		layoutSet(name, 0, 3, 5, 1);
		layoutSet(new JLabel("위치"), 2, 4, 1, 1);
		layoutSet(locationPanel, 0, 5, 5, 1);

		layoutSet(confirmButton, 1, 6, 1, 1);
		layoutSet(cancelButton, 3, 6, 1, 1);
	}

	public void layoutSet(Component c, int x, int y, int width, int height) {
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridwidth = width;
		constraint.gridheight = height;
		gbl.setConstraints(c, constraint);
		this.add(c);
	}

	public void getEvent(String source) {
		this.source = source;
	}
}

class TypePanel extends JPanel {
	String selected;

	public TypePanel(CreateDepot store) {
		Vector<String> typeList = store.typeList;
		ButtonGroup typeGroup = new ButtonGroup();

		setLayout(new FlowLayout());

		for (int i = 0; i < typeList.size(); i++) {
			JRadioButton typeButton = new JRadioButton(typeList.get(i));
			typeButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					JRadioButton changedButton = (JRadioButton) e.getItem();
					if (changedButton.isSelected())
						selected = changedButton.getText();
				}
			});
			typeGroup.add(typeButton);
			add(typeButton);
		}
	}
}

class LocationPanel extends JPanel {
	String selected;

	public LocationPanel(CreateDepot store) {
		Vector<String> locationList = store.locationList;
		ButtonGroup locationGroup = new ButtonGroup();

		setLayout(new FlowLayout());

		for (int i = 0; i < locationList.size(); i++) {
			JRadioButton locationButton = new JRadioButton(locationList.get(i));
			locationButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					JRadioButton changedButton = (JRadioButton) e.getItem();
					if (changedButton.isSelected())
						selected = changedButton.getText();
				}
			});
			locationGroup.add(locationButton);
			add(locationButton);
		}
	}
}