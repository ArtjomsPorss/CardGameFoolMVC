package classes;

import java.util.ArrayList;

public class Player extends Cards{
	//INSTANCE VARIABLES===========================

	
	//CONSTRUCTOR==================================
	public Player(){
		super(new ArrayList<Card>());
	}
	
	//METHODS======================================
	
	//draws a card
	public void drawCard(ArrayList<Card> pile){
		super.addCard(pile.remove(0));
	}
	
	//draws hand of 6 cards
	public void drawHand(ArrayList<Card> deck){
		while(super.getCards().size() < 6){
			super.addCard(deck.remove(0));
		}
	}
}
