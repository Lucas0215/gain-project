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
		SouthPanel southPanel = new SouthPanel(store, centerPanel);

		add(centerPanel, BorderLayout.CENTER);
		add(eastPanel, BorderLayout.EAST);
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
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
				eastPanel.mode1.setVisible(true);
				eastPanel.mode2.setVisible(false);
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
					eastPanel.mode2.setVisible(true);
					eastPanel.mode1.setVisible(false);
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
	ColorChanger r;
	DefaultTableModel shopControl;
	JTable shopList;
	JLabel warning;

	public CenterPanel(CreateDepot store) {
		setLayout(new FlowLayout());
		r = new ColorChanger(store.storeInfoList);
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
		shopList.setColumnSelectionAllowed(false);
		shopList.setOpaque(true);
		shopList.setDefaultRenderer(Object.class, r);

		add(new JScrollPane(shopList));
		warning = new JLabel("수정/삭제할 행을 선택하세요");
		warning.setVisible(false);
		add(warning);
	}
}

class ColorChanger implements TableCellRenderer {
	DefaultTableCellRenderer dr = new DefaultTableCellRenderer();
	Vector<Integer> colorMap = new Vector<>();

	public ColorChanger(Vector<String> shopList) {
		for (int i = 0; i < shopList.size(); i++)
			colorMap.add(-1);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = dr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (colorMap.get(row) != -1)
			c.setForeground(Color.RED);
		else
			c.setForeground(Color.BLACK);

		return c;
	}

	public void getColorMap(Vector<Integer> colorMap) {
		this.colorMap = colorMap;
	}
}

class EastPanel extends JPanel {
	GridBagConstraints constraint = new GridBagConstraints();
	GridBagLayout gbl = new GridBagLayout();
	String source;
	JLabel mode1, mode2;

	public EastPanel(CreateDepot store, CenterPanel centerPanel) {
		TypePanel typePanel = new TypePanel(store);
		LocationPanel locationPanel = new LocationPanel(store);

		mode1 = new JLabel("추가하기");
		mode1.setVisible(false);
		mode2 = new JLabel("수정하기");
		mode2.setVisible(false);

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
				if (typePanel.selected == null || locationPanel.selected == null)
					JOptionPane.showMessageDialog(null, "체크를 다 해주세요", "위험!", JOptionPane.ERROR_MESSAGE);
				else {
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
			}
		});

		JButton cancelButton = new JButton("취소");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText(null);
				setVisible(false);
			}
		});

		layoutSet(mode1, 0, 0, 5, 1);
		layoutSet(mode2, 0, 0, 5, 1);
		layoutSet(new JLabel("종류"), 2, 1, 1, 1);
		layoutSet(typePanel, 0, 2, 5, 1);
		layoutSet(new JLabel("이름"), 2, 3, 1, 1);
		layoutSet(name, 0, 4, 5, 1);
		layoutSet(new JLabel("위치"), 2, 5, 1, 1);
		layoutSet(locationPanel, 0, 5, 6, 1);

		layoutSet(confirmButton, 1, 7, 1, 1);
		layoutSet(cancelButton, 3, 7, 1, 1);
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

class SouthPanel extends JPanel {
	Vector<Integer> searchResult;

	public SouthPanel(CreateDepot store, CenterPanel centerPanel) {
		String[] searchType = { "종류로 찾기", "이름으로 찾기", "위치로 찾기" };
		setLayout(new FlowLayout());
		JComboBox<String> typeSearch = new JComboBox<>(searchType);
		add(typeSearch);

		JTextField searchArea = new JTextField(55);
		searchArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField searchData = (JTextField) e.getSource();
				int type = typeSearch.getSelectedIndex();
				String input = searchData.getText();
				if (!input.equals("")) {
					searchResult = DepotUtil.search(input, store.storeList, type);
					centerPanel.r.getColorMap(searchResult);
					centerPanel.repaint();
				}
			}
		});
		add(searchArea);
	}
}