package classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollBar;

import java.awt.ComponentOrientation;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Rectangle;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GUI {
	protected JFrame frame;

	JLabel lblTrumps;

	//upper panel with cards
	private JScrollPane scrollUpper;
	protected JPanel panelUpper;

	//lower panel with cards
	private JScrollPane scrollLower;
	protected JPanel panelLower;

	protected JPanel tablePanel;

	protected JTextArea infoText; 	//used to show messages to the user
	protected JButton btnEnd;	//used to end every phase

	//holds coordinates with cards
	private Point[] tableCoords = new Point[12];
	private Point[] deckCoords = new Point[36];

	private int cardsOnTable = 0;

	//hold player names
	private JLabel lblPlayerUpper;
	private JLabel lblPlayerLower;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;


	public GUI(){

		initTableCoordinates();
		initDeckCoordinates();

		//Main GUI frame
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 775, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		tablePanel = new JPanel();
		tablePanel.setSize(new Dimension(775,570));
		tablePanel.setBackground(Color.DARK_GRAY);
		tablePanel.setOpaque(false);
		tablePanel.setLayout(null);
		frame.getContentPane().add(tablePanel);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(621, 11, 80, 123);
		tablePanel.add(panel);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(644, 34, 80, 123);
		tablePanel.add(panel_1);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(560, 34, 80, 123);
		tablePanel.add(panel_2);

		JLabel lblDeck = new JLabel("Deck");
		lblDeck.setBounds(663, 158, 46, 14);
		frame.add(lblDeck);
		lblDeck.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeck.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDeck.setForeground(Color.WHITE);

		lblTrumps = new JLabel("Trumps");
		lblTrumps.setBounds(537, 158, 133, 14);
		frame.add(lblTrumps);
		lblTrumps.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrumps.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTrumps.setForeground(Color.WHITE);

		lblPlayerLower = new JLabel("Player 1");
		lblPlayerLower.setForeground(Color.WHITE);
		lblPlayerLower.setBounds(38, 367, 46, 14);
		frame.getContentPane().add(lblPlayerLower);

		lblPlayerUpper = new JLabel("Player 2");
		lblPlayerUpper.setForeground(Color.WHITE);
		lblPlayerUpper.setBounds(38, 158, 46, 14);
		frame.getContentPane().add(lblPlayerUpper);

		
		panelUpper = new JPanel();
		panelUpper.setName("upper");
		panelUpper.setBackground(Color.LIGHT_GRAY);
		panelUpper.setLayout(null);
		scrollUpper = new JScrollPane(panelUpper);
		scrollUpper.setLocation(15, 10);
		scrollUpper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollUpper.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panelUpper.setAutoscrolls(true);
		scrollUpper.setSize(new Dimension(500,146));	//if there are cards to be scrolled
		frame.getContentPane().add(scrollUpper);

		//Lower panel and scroll pane for cards
		panelLower = new JPanel();
		panelLower.setName("lower");	//to control to resize and adjust scroll bar for component
		panelLower.setBackground(Color.LIGHT_GRAY);
		panelLower.setLayout(null);
		//test.setPreferredSize(new Dimension(500,127));
		scrollLower = new JScrollPane(panelLower);
		scrollLower.setLocation(15, 385);
		scrollLower.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLower.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panelLower.setAutoscrolls(true);
		scrollLower.setSize(new Dimension(500,146));	//if there are cards to be scrolled
		frame.getContentPane().add(scrollLower);

		infoText = new JTextArea();
		infoText.setWrapStyleWord(true);
		infoText.setLineWrap(true);
		infoText.setEditable(false);
		infoText.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		infoText.setBackground(Color.GRAY);
		infoText.setForeground(Color.WHITE);
		infoText.setFont(new Font("Monospaced", Font.PLAIN, 11));
		infoText.setBounds(525, 404, 234, 127);
		frame.getContentPane().add(infoText);

		btnEnd = new JButton("Pass turn");
		btnEnd.setBorder(new CompoundBorder(UIManager.getBorder("Button.border"), null));
		btnEnd.setBackground(Color.WHITE);
		btnEnd.setMargin(new Insets(2, 4, 2, 4));
		btnEnd.setBounds(525, 385, 234, 18);
		frame.getContentPane().add(btnEnd);
	}



	//shows all cards in game
	public void showCards(ArrayList<Card> p1, ArrayList<Card> p2, ArrayList<Card> table, ArrayList<Card> deck){
		showCardsInPanel(p1, panelLower, false);
		showCardsInPanel(p2, panelUpper, false);
		tablePanel.removeAll();
		showDeckOnTable(deck);
		showTable(table);
	}

	//adds cards to panel with scroll pane
	public void showCardsInPanel(ArrayList<Card> cards, JPanel component, boolean flipped){

		int location = 2;
		//if both setSize and setPreferredSize for component are used, it always appears on screen, otherwise sometimes it is not shown 
		component.setPreferredSize(new Dimension((cards.size() * 81) + 4, component.getHeight()));  //works fine if array is not breaked
		component.setSize(new Dimension((cards.size() * 81) + 4, component.getHeight()));

		for(Card c : cards){
			component.add(c);
			c.setFlipped(flipped); 				//sets is card flipped or not
			c.setLocation(location, 2);
			location += 81;
		}
	}


	//initialise coordinates for card on table, trump, deck
	private void initTableCoordinates(){
		int x = 50;
		int y = 200;
		for(int i = 0; i < tableCoords.length; ++i){
			if(i == 0){				//first card	
			} else if(i % 2 == 1){	//odd card
				x += 20;
				y += 20;
			} else {				//even card
				y = 200;
				x += 95;
			}
			tableCoords[i] = new Point(x, y);
		}
	}


	//initialise deck coordinates
	private void initDeckCoordinates(){
		int x = 609;
		int y = -1;
		for(int i = 0; i < 35; ++i){
			deckCoords[i] = new Point(x, y);
			++x;
			++y;
		}
		deckCoords[35] = new Point(560, 34);
	}


	//shows deck and trump card
	public void showDeckOnTable(ArrayList<Card> deck){
		int cardCounter = 0;
		if(deck.size() > 0){	//show deck on table if it contains more than 0 cards
			for(int i = deckCoords.length - deck.size(); i < 36; ++i){
				tablePanel.add(deck.get(cardCounter));
				deck.get(cardCounter).setLocation(deckCoords[i]);
				deck.get(cardCounter).setFlipped(true);
				++cardCounter;
			}
			deck.get(deck.size()-1).setFlipped(false);	//last card trump can be seen
		}

		//if no cards are in deck, show trumps in trumps label
		if(deck.size() == 0){
			String trumps = "";
			switch(Deck.getTrump()){
			case 'S' : trumps = "Spades"; break;
			case 'C' : trumps = "Clubs"; break;
			case 'D' : trumps = "Diamonds"; break;
			case 'H' : trumps = "Hearts"; break;
			}

			lblTrumps.setText("Trumps are " + trumps);
		}
	}



	/*
	 * show cards on table
	 * cards are added in reversed order because otherwise first card
	 * will be shown on top of the next one
	 */
	public void showTable(ArrayList<Card> table){
		//cards to be shown last on top, should be added in reverse order
		for(int i = table.size()-1; i >= 0; --i){
			tablePanel.add(table.get(i));
			table.get(i).setLocation(tableCoords[i]);
		}

		tablePanel.repaint();
	}
}
