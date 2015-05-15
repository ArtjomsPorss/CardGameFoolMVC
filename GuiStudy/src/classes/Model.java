package classes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentListener;

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
		addCardsToScrollPane(deck.getDeck(), gui.test);
		//addCardsToScrollPane(deck.getDeck(), gui.panel_1);
	}
	
	
	//METHODS=========================================
	//adds cards to scroll pane
	private void addCardsToScrollPane(Card[] cards, JScrollPane component){
		for(Card c : cards){
			component.setViewportView(c);
		}
	}
	
	private void addCardsToScrollPane(Card[] cards, JPanel component){
		int location = 2;
		//TODO here
		//component.setPreferredSize(new Dimension((cards.length * 81) + 4, component.getHeight()));	//works fine if array is not breaked
		for(Card c : cards){
			component.setPreferredSize(new Dimension(component.getSize().width + c.getWidth() + 1, component.getHeight()));
			component.add(c);
			c.setLocation(location, 2);
			c.addMouseListener(control);
			location += 81;
			

		}
	}
	
}
