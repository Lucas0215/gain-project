package gainProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame {
	CardLayout card = new CardLayout();
	Container container = getContentPane();

	public UserInterface(CreateDepot store) {
		setTitle("메뉴를 못정하겠다고?");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(card);
		add("frame1", new StartPage(card, container));
		add("frame2", new ShopModifyPage(store, card, container));
		add("frame3", new RandomChoosePage(store, card, container));
		add("frame4", new OptionPage(store, card, container));

		setSize(900, 700);
		setVisible(true);
	}
}

class PageChanger extends MouseAdapter {
	CardLayout card;
	Container container;

	public PageChanger(CardLayout card, Container container) {
		this.card = card;
		this.container = container;
	}

	public void mouseClicked(MouseEvent e) {
		JButton pressedButton = (JButton) e.getSource();
		String buttonName = pressedButton.getText();
		if (buttonName.equals("Shop Edit"))
			card.show(container, "frame2");
		else if (buttonName.equals("나가기"))
			card.show(container, "frame1");
		else if (buttonName.equals("Random Choice"))
			card.show(container, "frame3");
		else if (buttonName.equals("Option"))
			card.show(container, "frame4");
		else if (buttonName.equals("Close"))
			System.exit(0);
	}
}
