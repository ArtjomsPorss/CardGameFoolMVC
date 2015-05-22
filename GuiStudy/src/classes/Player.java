/**
 * holds player-related information as well as performs player actions like move, defend, draw cards etc
 */

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
			if(deck.size() > 0){
				super.addCard(deck.remove(0));
			}
		}
	}
	
	
	//draws all cards from target ArrayList
	public void drawAll(ArrayList<Card> pile){
		while(pile.size() > 0){
			super.getCards().add(pile.remove(0));
		}
	}
}
