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

public class GUI {
	//INSTANCE VARIABLES===========================
	private JFrame frame;

	//upper panel with cards
	private JScrollPane scrollUpper;
	protected JPanel panelUpper;

	//lower panel with cards
	private JScrollPane scrollLower;
	protected JPanel panelLower;

	//holds coordinates with cards
	private Point[] coords = new Point[14]; //0 - 11 cards on table, 12trump, 13deck


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

		//testing version of scrollpane with panel assigned to it
		panelUpper = new JPanel();
		panelUpper.setBackground(Color.LIGHT_GRAY);
		panelUpper.setLayout(null);
		//test.setPreferredSize(new Dimension(500,127));
		scrollUpper = new JScrollPane(panelUpper);
		scrollUpper.setLocation(15, 10);
		scrollUpper.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollUpper.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panelUpper.setAutoscrolls(true);
		scrollUpper.setSize(new Dimension(500,146));	//if there are cards to be scrolled
		frame.getContentPane().add(scrollUpper);

		//Lower panel and scroll pane for cards
		panelLower = new JPanel();
		panelLower.setBackground(Color.LIGHT_GRAY);
		panelLower.setLayout(null);
		//test.setPreferredSize(new Dimension(500,127));
		scrollLower = new JScrollPane(panelLower);
		scrollLower.setLocation(15, 385);
		scrollLower.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
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
	}


	//INSTANCE METHODS=========================


	//adds cards to panel with scroll pane
	public void showCardsInPanel(ArrayList<Card> cards, JPanel component, boolean flipped){
		System.out.println("entered showCardsInPanel()");

		int location = 2;
		//TODO if both setSize and setPreferredSize for component are used, it always appears on screen, otherwise sometimes it is not shown 
		component.setPreferredSize(new Dimension((cards.size() * 81) + 4, component.getHeight()));	//works fine if array is not breaked
		component.setSize(new Dimension((cards.size() * 81) + 4, component.getHeight()));
		System.out.println("Component sizes are set");

		for(Card c : cards){
			//component.setPreferredSize(new Dimension(component.getSize().width + c.getWidth() + 2, component.getHeight()));		//increasing size of the panel for every card shown in it
			component.add(c);
			c.setFlipped(flipped); 				//sets is card flipped or not
			c.setLocation(location, 2);
			//c.addMouseListener(control);
			location += 81;
		}

		//testing printouts
		System.out.println("Cards are added");
		//System.out.println("JScrollPane sizes: " + gui.scrollPane.getPreferredSize().width + " " + gui.scrollPane.getPreferredSize().height);
		System.out.println("Component sizes: " + component.getPreferredSize().width + " " + component.getPreferredSize().height);
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
	
	
	//shows card on table
	public void showCardOnTable(ArrayList<Card> cards){
		/*
		 * cards are added in reversed order because otherwise first card
		 * will be shown on top of the next one
		 */
		for(int i = coords.length - 3; i > -1; --i){	
			frame.getContentPane().add(cards.get(i));
			cards.get(i).setLocation(coords[i]);
			cards.get(i).setFlipped(false);
		}
	}
	
	
	//shows deck and trumps
	public void showDeckOnTable(ArrayList<Card> deck){
		for(int i = 0; i < deck.size(); ++i){
			frame.getContentPane().add(deck.get(i));
			if(i != deck.size() - 1){	//if its not last card in deck
				deck.get(i).setLocation(coords[13]);	//show in deck position
				deck.get(i).setFlipped(true);
			}else{										//it's last card in deck 
				deck.get(i).setLocation(coords[12]);	//show in trumps position
				deck.get(i).setFlipped(false);				
			}
			
		}
	}
}
