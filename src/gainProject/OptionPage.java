package gainProject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Vector;

class OptionPage extends JPanel {
	String type, location;

	public OptionPage(CreateDepot store, CardLayout card, Container container) {
		setLayout(new BorderLayout());
		add(new JLabel("������ ����� ��ġ ������ �غ����Դϴ�.."), BorderLayout.NORTH);
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);

		JButton typeAdd = new JButton("���� �߰�");
		typeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reminder = 0;
				type = JOptionPane.showInputDialog("�߰��� ������ �Է��ϼ���");
				if (type != null)
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
		});

		JButton locationAdd = new JButton("��ġ �߰�");
		locationAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reminder = 0;
				location = JOptionPane.showInputDialog("�߰��� ��Ҹ� �Է��ϼ���");
				if (location != null)
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
		});

		JButton sortButton = new JButton("����Ʈ ����");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> sortedList = DepotUtil.sort(store.storeInfoList);
				try {
					JOptionPane.showMessageDialog(null, "����Ϸ�", "����!", JOptionPane.INFORMATION_MESSAGE);
					new SaveRestaurantList(sortedList);
					new UserInterface(store);
				} catch (IOException ioe) {
				}
			}
		});

		JButton exitButton = new JButton("������");
		exitButton.addMouseListener(new PageChanger(card, container));

		center.add(typeAdd);
		center.add(locationAdd);
		center.add(sortButton);
		center.add(exitButton);
	}
}
