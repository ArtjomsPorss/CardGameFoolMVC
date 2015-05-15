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
		showCardsInPanel(deck.getDeck(), gui.test);
		//addCardsToScrollPane(deck.getDeck(), gui.panel_1);
	}
	
	
	//METHODS=========================================
	//adds cards to scroll pane
	private void addCardsToScrollPane(Card[] cards, JScrollPane component){
		for(Card c : cards){
			component.setViewportView(c);
		}
	}
	
	private void showCardsInPanel(Card[] cards, JPanel component){
		System.out.println("entered showCardsInPanel()");
		
		int location = 2;
		//component.setPreferredSize(new Dimension(component.getSize().width + location + 1, component.getHeight()));
		//TODO if both setSize and setPreferredSize for component are used, it always appears on screen, otherwise sometimes it is not shown 
		component.setPreferredSize(new Dimension((cards.length * 81) + 4, component.getHeight()));	//works fine if array is not breaked
		component.setSize(new Dimension((cards.length * 81) + 4, component.getHeight()));
		System.out.println("Component sizes are set");
		for(Card c : cards){
			//component.setPreferredSize(new Dimension(component.getSize().width + c.getWidth() + 2, component.getHeight()));		//increasing size of the panel for every card shown in it
			component.add(c);
			c.setLocation(location, 2);
			c.addMouseListener(control);
			location += 81;
		}
		
		
		//testing printouts
		System.out.println("Cards are added");
		//System.out.println("JScrollPane sizes: " + gui.scrollPane.getPreferredSize().width + " " + gui.scrollPane.getPreferredSize().height);
		System.out.println("Component sizes: " + component.getPreferredSize().width + " " + component.getPreferredSize().height);
	}
	
}
