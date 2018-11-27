package gainProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame {
	public UserInterface(JPanel page) {
		setTitle("결정장애 ㅎㅇ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = getContentPane();

		container.add(page);
		
		setSize(500, 500);
		setVisible(true);
	}

}

class StartPage extends JPanel {
	public StartPage() {
	}
}

class RandomChoosePage extends JPanel {
	public RandomChoosePage() {
	}
}

class ShopModifyPage extends JPanel {
	public ShopModifyPage() {
	}
}

class ExtraPage extends JPanel{
	public ExtraPage() {
	}
}

class PageChanger extends MouseAdapter {
	int pageNum;

	public PageChanger(int pageNum) {
		this.pageNum = pageNum;
	}

	public void mouseClicked(MouseEvent e) {
		switch (pageNum) {
		case 0:
			new UserInterface(new StartPage());
			break;
		case 1:
			new UserInterface(new RandomChoosePage());
			break;
		case 2:
			new UserInterface(new ShopModifyPage());
			break;
		case 3:
			new UserInterface(new ExtraPage());
			break;
		}
	}
}