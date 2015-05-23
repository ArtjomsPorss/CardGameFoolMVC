/**
 * holds player-related information as well as performs player actions like move, defend, draw cards etc
 */

package classes;

import java.util.ArrayList;

public class Player extends Cards{
	//INSTANCE VARIABLES===========================
	String name = "";

	//CONSTRUCTOR==================================
	public Player(String name){
		super(new ArrayList<Card>());
		this.name = name;
	}

	//METHODS======================================

	//draws a card
	public void drawCard(ArrayList<Card> pile){
		super.addCard(pile.remove(0));
	}


	//draws hand of 6 cards
	public void drawHand(ArrayList<Card> deck){
		while(super.getCards().size() < 6){
			if(deck.size() > 0){					//if deck has more than zero cards in it
				super.addCard(deck.remove(0));			//draw
			}else{									//otherwise
				break;									//break loop
			}
		}
	}


	//draws all cards from target ArrayList
	public void drawAll(ArrayList<Card> pile){
		while(pile.size() > 0){
			super.getCards().add(pile.remove(0));
		}
	}
	
	
	//returns player name
	public String getName(){
		return this.name;
	}
}
