package classes;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Model {
	//INSTANCE VARIABLES==============================
	private GUI gui;
	private Controller control;
	private Deck deck;
	
	
	//CONSTRUCTOR=====================================
	public Model(GUI gui, Controller control){
		this.gui = gui;
		this.control = control;
		this.deck = new Deck();
		//addCardsToScrollPane(deck.getDeck(), gui.scrollPane);
		addCardsToScrollPane(deck.getDeck(), gui.panel_1);
	}
	
	
	//METHODS=========================================
	//adds cards to scroll pane
	private void addCardsToScrollPane(Card[] cards, JScrollPane component){
		for(Card c : cards){
			component.setViewportView(c);
		}
	}
	
	private void addCardsToScrollPane(Card[] cards, JPanel component){
		int location = 0;
		for(Card c : cards){
			component.add(c);
			c.setLocation(location, 0);
			location += 80;
		}
	}
	
}
