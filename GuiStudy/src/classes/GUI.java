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

public class GUI {
	//INSTANCE VARIABLES===========================
	private JFrame frame;

	//upper panel with cards
	private JScrollPane scrollUpper;
	protected JPanel panelUpper;

	//lower panel with cards
	private JScrollPane scrollLower;
	protected JPanel panelLower;
	
	protected JPanel tablePanel;
	
	protected JTextArea infoText; //used to show messages to the user
	protected JButton btnEnd;		//used to end every phase

	//holds coordinates with cards
	private Point[] coords = new Point[14]; //0 - 11 cards on table, 12trump, 13deck
	
	private int cardsOnTable = 0;


	//CONSTRUCTOR====================================
	public GUI(){
		initialiseCoordinates();

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
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setBounds(38, 367, 46, 14);
		frame.add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setForeground(Color.WHITE);
		lblPlayer_1.setBounds(38, 158, 46, 14);
		frame.add(lblPlayer_1);

		//testing version of scrollpane with panel assigned to it
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
		
		JLabel lblTrumps = new JLabel("Trumps");
		lblTrumps.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTrumps.setForeground(Color.WHITE);
		lblTrumps.setBounds(577, 8, 46, 14);
		frame.getContentPane().add(lblTrumps);
		
		JLabel lblDeck = new JLabel("Deck");
		lblDeck.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDeck.setForeground(Color.WHITE);
		lblDeck.setBounds(668, 8, 46, 14);
		frame.getContentPane().add(lblDeck);
		
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


	//INSTANCE METHODS=========================

	
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
		//TODO if both setSize and setPreferredSize for component are used, it always appears on screen, otherwise sometimes it is not shown 
		component.setPreferredSize(new Dimension((cards.size() * 81) + 4, component.getHeight()));	//works fine if array is not breaked
		component.setSize(new Dimension((cards.size() * 81) + 4, component.getHeight()));

		for(Card c : cards){
			//component.setPreferredSize(new Dimension(component.getSize().width + c.getWidth() + 2, component.getHeight()));		//increasing size of the panel for every card shown in it
			component.add(c);
			c.setFlipped(flipped); 				//sets is card flipped or not
			c.setLocation(location, 2);
			//c.addMouseListener(control);
			location += 81;
		}
	}


	//initialise coordinates for card on table, trump, deck
	private void initialiseCoordinates(){
		int x = 50;
		int y = 200;
		for(int i = 0; i < coords.length; ++i){
			if(i == 0){				//first card	
			}else if(i == 13) {		//deck
				x = 649;
				y = 25;
			} else if(i == 12) {	//trump card
				x = 559;
				y = 25;
			} else if(i % 2 == 1){	//odd card
				x += 20;
				y += 20;
			} else {				//even card
				y = 200;
				x += 95;
			}
			coords[i] = new Point(x, y);
		}
	}
	
	
	//shows deck and trumps
	public void showDeckOnTable(ArrayList<Card> deck){
		//TODO if no cards are in deck, think of how to show trumps??
		for(int i = 0; i < deck.size(); ++i){
			tablePanel.add(deck.get(i));
			if(i != deck.size() - 1){	//if its not last card in deck
				deck.get(i).setLocation(coords[13]);	//show in deck position
				deck.get(i).setFlipped(true);
			}else{										//it's last card in deck 
				deck.get(i).setLocation(coords[12]);	//show in trumps position
				deck.get(i).setFlipped(false);				
			}
			
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
			table.get(i).setLocation(coords[i]);
		}
		
		tablePanel.repaint();
	}
}
