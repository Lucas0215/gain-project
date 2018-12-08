package gainProject;

import javax.swing.*;
import java.awt.*;

class StartPage extends JPanel {
	public StartPage(CardLayout card, Container container) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(1,4,0,0));
		add(p, BorderLayout.SOUTH);
		
		JButton shopListButton = new JButton("Shop Edit");
		shopListButton.setBounds(50, 100, 150, 40);
		shopListButton.addMouseListener(new PageChanger(card, container));
		p.add(shopListButton);

		JButton randomButton = new JButton("Random Choice");
		randomButton.setBounds(250, 100, 150, 40);
		randomButton.addMouseListener(new PageChanger(card, container));
		p.add(randomButton);

		JButton optionButton = new JButton("Option");
		optionButton.setBounds(450, 100, 150, 40);
		optionButton.addMouseListener(new PageChanger(card, container));
		p.add(optionButton);

		JButton closeButton = new JButton("Close");
		closeButton.setBounds(250, 150, 150, 40);
		closeButton.addMouseListener(new PageChanger(card, container));
		p.add(closeButton);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("Random Chooser", 150, 60);
	}
}