package gainProject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Vector;

class OptionPage extends JPanel {

	public OptionPage(CreateDepot store, CardLayout card, Container container) {
		setLayout(new BorderLayout());
		add(new JLabel("������ ����� ��ġ ������ �غ����Դϴ�.."), BorderLayout.NORTH);
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);

		JButton typeAdd = new JButton("���� �߰�");
		typeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reminder = 0;
				String type = JOptionPane.showInputDialog("�߰��� ������ �Է��ϼ���");
				if (type != null) {
					for (int i = 0; i < store.typeList.size(); i++)
						if (type.equals(store.typeList.get(i))) {
							JOptionPane.showMessageDialog(null, "�̹� ���� ������ �ֽ��ϴ�", "!", JOptionPane.ERROR_MESSAGE);
							reminder = 1;
						}
					if (reminder == 0) {
						store.typeList.add(type);
						try {
							new SaveTypeList(store.typeList);
							JOptionPane.showMessageDialog(null, "����Ϸ�", "����!", JOptionPane.INFORMATION_MESSAGE);
							new UserInterface(store);
						} catch (IOException ioe) {
						}
					}
				}

			}
		});

		JButton typeRemove = new JButton("���� ����");
		typeRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reminder = -1;
				String type = JOptionPane.showInputDialog("������ ������ �Է��ϼ���");
				if (type != null) {
					for (int i = 0; i < store.typeList.size(); i++)
						if (type.equals(store.typeList.get(i)))
							reminder = i;
					if (reminder != -1) {
						store.typeList.remove(reminder);
						try {
							new SaveTypeList(store.typeList);
							JOptionPane.showMessageDialog(null, "����Ϸ�", "����!", JOptionPane.INFORMATION_MESSAGE);
							new UserInterface(store);
						} catch (IOException ioe) {
						}
					} else
						JOptionPane.showMessageDialog(null, "�Է��� ������ �����ϴ�", "!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JButton locationAdd = new JButton("��ġ �߰�");
		locationAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reminder = 0;
				String location = JOptionPane.showInputDialog("�߰��� ��Ҹ� �Է��ϼ���");
				if (location != null) {
					for (int i = 0; i < store.locationList.size(); i++)
						if (location.equals(store.locationList.get(i))) {
							JOptionPane.showMessageDialog(null, "�̹� ���� ��Ұ� �ֽ��ϴ�", "!", JOptionPane.ERROR_MESSAGE);
							reminder = 1;
						}
					if (reminder == 0) {
						store.locationList.add(location);
						try {
							new SaveLocationList(store.locationList);
							JOptionPane.showMessageDialog(null, "����Ϸ�", "����!", JOptionPane.INFORMATION_MESSAGE);
							new UserInterface(store);
						} catch (IOException ioe) {
						}
					}
				}
			}
		});

		JButton locationRemove = new JButton("��ġ ����");
		locationRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reminder = -1;
				String location = JOptionPane.showInputDialog("������ ��ġ�� �Է��ϼ���");
				if (location != null) {
					for (int i = 0; i < store.locationList.size(); i++)
						if (location.equals(store.locationList.get(i)))
							reminder = i;
					if (reminder != -1) {
						store.locationList.remove(reminder);
						try {
							new SaveLocationList(store.locationList);
							JOptionPane.showMessageDialog(null, "����Ϸ�", "����!", JOptionPane.INFORMATION_MESSAGE);
							new UserInterface(store);
						} catch (IOException ioe) {
						}
					} else
						JOptionPane.showMessageDialog(null, "�Է��� ��ġ�� �����ϴ�", "!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JButton sortButton = new JButton("����Ʈ ����");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> sortedList1 = DepotUtil.sort(store.storeInfoList);
				Vector<String> sortedList2 = DepotUtil.sort(store.typeList);
				Vector<String> sortedList3 = DepotUtil.sort(store.locationList);
				try {
					JOptionPane.showMessageDialog(null, "����Ϸ�", "����!", JOptionPane.INFORMATION_MESSAGE);
					new SaveRestaurantList(sortedList1);
					new SaveTypeList(sortedList2);
					new SaveLocationList(sortedList3);
					new UserInterface(new CreateDepot(sortedList1, sortedList2, sortedList3));
				} catch (IOException ioe) {
				}
			}
		});

		JButton exitButton = new JButton("������");
		exitButton.addMouseListener(new PageChanger(card, container));

		center.add(typeAdd);
		center.add(typeRemove);
		center.add(locationAdd);
		center.add(locationRemove);
		center.add(sortButton);
		center.add(exitButton);
	}
}
