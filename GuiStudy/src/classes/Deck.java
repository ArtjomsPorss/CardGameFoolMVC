/**
 * holds deck of cards and performs deck related actions e.g. create deck, shuffle deck
 */

package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck extends Cards{
	//INSTANCE VARIABLES==================================
	private static char trumps;
	
	
	//CONSTRUCTOR=========================================
	public Deck(){
		super(new ArrayList<Card>());
		createDeck();
		shuffleDeck();
	}
	
	//INSTANCE METHODS====================================
	
	//creates arrayList of cards
	private void createDeck(){

		char suit = ' ';
		String rank = " ";
		for(int s = 0; s < 4; ++s){			//sets card suits
			switch(s){
				case 0 : suit = 'H'; break;
				case 1 : suit = 'D'; break;
				case 2 : suit = 'C'; break;
				case 3 : suit = 'S'; break;
			}
			
			for(int r = 6; r < 15; ++r){	//sets card ranks
				if(r > 10) {
					switch(r){
						// if rank is greater than 10, change it to J, Q, K or A respectively
						case 11 : rank = "J"; break;
						case 12 : rank = "Q"; break;
						case 13 : rank = "K"; break;
						case 14 : rank = "A"; break;
					}
				} else {
					rank = Integer.toString(r);		// if rank is below 11, set it
				}

				//sets suits and rank for current card
				super.addCard(new Card(rank, suit));	
			}
		}
	}//END createDeck
	
	
	//add listeners to cards
	public void addListeners(Controller listener){
		for(Card c : super.getCards()){
			c.addMouseListener(listener);
		}
	}
	
	
	// Shuffle deck algorithm
	public void shuffleDeck(){	//bottom cards suit in shuffled deck becomes trumps
		do{
			Collections.shuffle(super.getCards());
		}while(super.getCards().get(super.getCards().size()-1).getRank().equals("A"));	//bottom card cannot be Ace
		
		trumps = super.getCards().get(super.getCards().size()-1).getSuit();		//set trumps
	}//shuffleDeck()
	
	
	public static char getTrump(){
		return trumps;
	}
}
