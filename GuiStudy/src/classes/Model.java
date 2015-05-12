package classes;

import javax.swing.JComponent;

public class Model {
	//INSTANCE VARIABLES==============================
	GUI gui;
	
	Deck deck;
	
	
	//CONSTRUCTOR=====================================
	public Model(GUI gui){
		this.gui = gui;
		this.deck = new Deck();
	}
	
	
	//METHODS=========================================
	//adds cards to scroll pane
	private void addCardsToScrollPane(Card[] cards, JComponent component){
		for(Card c : cards){
			component.add(c);
		}
	}
}
