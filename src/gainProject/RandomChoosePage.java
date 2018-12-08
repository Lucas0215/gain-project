package gainProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

class RandomChoosePage extends JPanel {
	String[] randomShop = new String[3];
	Vector<String[]> storeList;

	public RandomChoosePage(CreateDepot store, CardLayout card, Container container) {
		this.storeList=store.storeList;
		
		JTextField type = new JTextField(55);
		JTextField name = new JTextField(55);
		JTextField location = new JTextField(55);

		setLayout(new GridLayout(4, 2));
		add(new JLabel("종류  : "));
		add(type);
		add(new JLabel("이름 : "));
		add(name);
		add(new JLabel("위치 : "));
		add(location);
		
		JButton banButton=new JButton("옵션");

		JButton doRandom = new JButton("결정해줌");
		doRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int randomChooser = (int) (Math.random() * storeList.size());
				randomShop = store.storeList.get(randomChooser);
				
				type.setText(randomShop[0]);
				name.setText(randomShop[1]);
				location.setText(randomShop[2]);
			}
		});
		add(doRandom);

		JButton exitButton = new JButton("나가기");
		exitButton.addMouseListener(new PageChanger(card, container));
		add(exitButton);
	}
}
