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
		add(new JLabel("지도를 사용한 위치 지정은 준비중입니다.."), BorderLayout.NORTH);
		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);

		JButton typeAdd = new JButton("종류 추가");
		typeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reminder = 0;
				type = JOptionPane.showInputDialog("추가할 종류를 입력하세요");
				if (type != null)
					for (int i = 0; i < store.typeList.size(); i++)
						if (type.equals(store.typeList.get(i))) {
							JOptionPane.showMessageDialog(null, "이미 같은 종류가 있습니다", "!", JOptionPane.ERROR_MESSAGE);
							reminder = 1;
						}
				if (reminder == 0) {
					store.typeList.add(type);
					try {
						new SaveTypeList(store.typeList);
						JOptionPane.showMessageDialog(null, "적용완료", "성공!", JOptionPane.INFORMATION_MESSAGE);
						new UserInterface(store);
					} catch (IOException ioe) {
					}
				}

			}
		});

		JButton locationAdd = new JButton("위치 추가");
		locationAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reminder = 0;
				location = JOptionPane.showInputDialog("추가할 장소를 입력하세요");
				if (location != null)
					for (int i = 0; i < store.locationList.size(); i++)
						if (location.equals(store.locationList.get(i))) {
							JOptionPane.showMessageDialog(null, "이미 같은 장소가 있습니다", "!", JOptionPane.ERROR_MESSAGE);
							reminder = 1;
						}
				if (reminder == 0) {
					store.locationList.add(location);
					try {
						new SaveLocationList(store.locationList);
						JOptionPane.showMessageDialog(null, "적용완료", "성공!", JOptionPane.INFORMATION_MESSAGE);
						new UserInterface(store);
					} catch (IOException ioe) {
					}
				}

			}
		});

		JButton sortButton = new JButton("리스트 정렬");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> sortedList = DepotUtil.sort(store.storeInfoList);
				try {
					JOptionPane.showMessageDialog(null, "적용완료", "성공!", JOptionPane.INFORMATION_MESSAGE);
					new SaveRestaurantList(sortedList);
					new UserInterface(store);
				} catch (IOException ioe) {
				}
			}
		});

		JButton exitButton = new JButton("나가기");
		exitButton.addMouseListener(new PageChanger(card, container));

		center.add(typeAdd);
		center.add(locationAdd);
		center.add(sortButton);
		center.add(exitButton);
	}
}
