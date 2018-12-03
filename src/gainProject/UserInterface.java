package gainProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame {
	CardLayout card = new CardLayout();
	Container container = getContentPane();

	public UserInterface(CreateDepot store) {
		setTitle("������� ����");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(card);
		add("frame1", new StartPage(card, container));
		add("frame2", new ShopModifyPage(store, card, container));
		add("frame2-1", new ShopAddPage(card, container));
		add("frame3", new RandomChoosePage(card, container));
		add("frame4", new ExtraPage(card, container));

		setSize(650, 400);
		setVisible(true);
	}
}

class StartPage extends JPanel {
	public StartPage(CardLayout card, Container container) {
		setBackground(Color.WHITE);
		setLayout(null);
		JButton shopListButton = new JButton("Shop Edit");
		shopListButton.setLocation(50, 100);
		shopListButton.setSize(150, 40);
		shopListButton.addMouseListener(new PageChanger(card, container));
		add(shopListButton);

		JButton randomButton = new JButton("Random Choice");
		randomButton.setLocation(250, 100);
		randomButton.setSize(150, 40);
		randomButton.addMouseListener(new PageChanger(card, container));
		add(randomButton);

		JButton moreButton = new JButton("More Option");
		moreButton.setLocation(450, 100);
		moreButton.setSize(150, 40);
		moreButton.addMouseListener(new PageChanger(card, container));
		add(moreButton);

		JButton closeButton = new JButton("Close");
		closeButton.setLocation(250, 150);
		closeButton.setSize(150, 40);
		closeButton.addMouseListener(new PageChanger(card, container));
		add(closeButton);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("Random Chooser", 80, 60);
	}
}

class RandomChoosePage extends JPanel {
	public RandomChoosePage(CardLayout card, Container container) {
		setLayout(new FlowLayout());
		add(new JLabel("����  : "));
		add(new JTextField(55));
		add(new JLabel("�̸� : "));
		add(new JTextField(55));
		add(new JLabel("��ġ : "));
		add(new JTextField(55));

		JButton optionButton = new JButton("�ɼ�");
		add(optionButton);

		JButton doRandom = new JButton("��������");
		add(doRandom);

		JButton exitButton = new JButton("������");
		exitButton.addMouseListener(new PageChanger(card, container));
		add(exitButton);
	}
}

class ShopModifyPage extends JPanel {
	public ShopModifyPage(CreateDepot store, CardLayout card, Container container) {
		setLayout(new FlowLayout());
		JTextArea shopList=new JTextArea("____���� ____��_________________�̸�__________________��__________��ġ__________",10, 50);
		for(int i=0;i<store.storeInfoList.size();i++)
			shopList.append(store.storeInfoList.get(i)+"\n");
		
		
		JButton addButton = new JButton("�߰�");
		add(addButton);

		JButton modifyButton = new JButton("����");
		add(modifyButton);

		JButton deleteButton = new JButton("����");
		add(deleteButton);

		JButton exitButton = new JButton("������");
		exitButton.addMouseListener(new PageChanger(card, container));
		add(exitButton);
		
		add(new JScrollPane(shopList));
	}
}

class ShopAddPage extends JPanel{
	public ShopAddPage(CardLayout card, Container container) {
		
	}
}

class ExtraPage extends JPanel {
	public ExtraPage(CardLayout card, Container container) {
		setLayout(new FlowLayout());
		add(new JLabel("��� �߰���.."));
		JButton exitButton = new JButton("������");
		exitButton.addMouseListener(new PageChanger(card, container));
		add(exitButton);
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
		else if (buttonName.equals("������"))
			card.show(container, "frame1");
		else if (buttonName.equals("Random Choice"))
			card.show(container, "frame3");
		else if (buttonName.equals("More Option"))
			card.show(container, "frame4");
		else if (buttonName.equals("Close"))
			System.exit(0);
	}
}
