package gainProject;

import javax.swing.*;
import java.awt.*;

class StartPage extends JPanel {
	public StartPage(CardLayout card, Container container) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JButton shopListButton = new JButton("Shop Edit");
		shopListButton.setBounds(15, 250, 250, 100);
		shopListButton.addMouseListener(new PageChanger(card, container));
		add(shopListButton);

		JButton randomButton = new JButton("Random Choice");
		randomButton.setBounds(315, 250, 250, 100);
		randomButton.addMouseListener(new PageChanger(card, container));
		add(randomButton);

		JButton optionButton = new JButton("Option");
		optionButton.setBounds(615, 250, 250, 100);
		optionButton.addMouseListener(new PageChanger(card, container));
		add(optionButton);

		JButton closeButton = new JButton("Close");
		closeButton.setBounds(315, 450, 250, 100);
		closeButton.addMouseListener(new PageChanger(card, container));
		add(closeButton);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.BOLD, 60));
		g.drawString("Random Chooser", 200, 100);
	}
}