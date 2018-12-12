package gainProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

class RandomChoosePage extends JPanel {
	String[] randomShop = new String[3];
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints constraint = new GridBagConstraints();

	public RandomChoosePage(CreateDepot store, CardLayout card, Container container) {
		BanPanel banPanel = new BanPanel(store);

		JTextField type = new JTextField(55);
		JTextField name = new JTextField(55);
		JTextField location = new JTextField(55);
		type.setEditable(false);
		name.setEditable(false);
		location.setEditable(false);

		constraint.weightx = 1;
		constraint.weighty = 1;

		setLayout(gbl);
		layoutSet(new JLabel("종류 : "), 0, 0, 3, 1);
		layoutSet(type, 3, 0, 3, 1);
		layoutSet(new JLabel("이름 : "), 0, 1, 3, 1);
		layoutSet(name, 3, 1, 3, 1);
		layoutSet(new JLabel("위치 : "), 0, 2, 3, 1);
		layoutSet(location, 3, 2, 3, 1);
		layoutSet(banPanel, 0, 3, 6, 1);

		JButton doRandom = new JButton("결정해줌");
		doRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String[]> storeList = store.storeList;
				DepotUtil.ban(storeList, banPanel.getTypeBanList(), 0);
				DepotUtil.ban(storeList, banPanel.getLocationBanList(), 2);

				System.out.println(banPanel.getTypeBanList().size() + "\n" + banPanel.getLocationBanList().size());
				System.out.println(store.storeList.size() + " " + storeList.size());

				randomShop = DepotUtil.randomRecommend(storeList);

				type.setText(randomShop[0]);
				name.setText(randomShop[1]);
				location.setText(randomShop[2]);
			}
		});
		layoutSet(new JLabel(""), 0, 4, 1, 1);
		layoutSet(doRandom, 1, 4, 3, 1);

		JButton exitButton = new JButton("나가기");
		exitButton.addMouseListener(new PageChanger(card, container));
		layoutSet(exitButton, 3, 4, 3, 1);
	}

	public void layoutSet(Component c, int x, int y, int width, int height) {
		constraint.gridx = x;
		constraint.gridy = y;
		constraint.gridwidth = width;
		constraint.gridheight = height;
		gbl.setConstraints(c, constraint);
		this.add(c);
	}
}

class BanPanel extends JPanel {
	Vector<String> typeList, locationList;
	JCheckBox[] types, locations;

	public BanPanel(CreateDepot store) {
		setLayout(new FlowLayout());
		this.typeList = store.typeList;
		this.locationList = store.locationList;

		JPanel typePanel = new JPanel();
		JPanel locationPanel = new JPanel();
		JPanel northPanel = new JPanel();
		typePanel.setLayout(new FlowLayout());
		locationPanel.setLayout(new FlowLayout());
		northPanel.setLayout(new FlowLayout());

		types = new JCheckBox[typeList.size()];
		locations = new JCheckBox[locationList.size()];

		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(new JLabel("|"), BorderLayout.CENTER);
		add(typePanel, BorderLayout.WEST);
		add(locationPanel, BorderLayout.EAST);

		northPanel.add(new JLabel(""));
		northPanel.add(new JLabel("싫은 것 체크"));
		northPanel.add(new JLabel(""));

		for (int i = 0; i < typeList.size(); i++) {
			types[i] = new JCheckBox(typeList.get(i));
			typePanel.add(types[i]);
		}

		for (int i = 0; i < locationList.size(); i++) {
			locations[i] = new JCheckBox(locationList.get(i));
			locationPanel.add(locations[i]);
		}

	}

	public Vector<String> getTypeBanList() {
		Vector<String> banList = new Vector<>();

		for (int i = 0; i < typeList.size(); i++)
			if (types[i].isSelected())
				banList.add(types[i].getText());
		return banList;
	}

	public Vector<String> getLocationBanList() {
		Vector<String> banList = new Vector<>();

		for (int i = 0; i < locationList.size(); i++)
			if (locations[i].isSelected())
				banList.add(locations[i].getText());
		return banList;
	}
}