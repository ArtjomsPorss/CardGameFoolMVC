package classes;

import java.util.ArrayList;

public class Deck {
	//INSTANCE VARIABLES==================================
	private Card[] deck = new Card[36];
	private ArrayList<Card> deck1 = new ArrayList<>(36);
	
	
	//CONSTRUCTOR=========================================
	public Deck(){
		createDeck();
		createDeckArrayList();
	}
	
	//INSTANCE METHODS====================================
	
	//creates deck of cards
	private void createDeck(){
		int forLoopCardDeckCounter = 0;		//used to iterate through deck array
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
				deck[forLoopCardDeckCounter] = new Card(rank, suit);
				++forLoopCardDeckCounter;				
			}
		}
	}//END createDeck
	
	
	
	//creates arrayList of cards
	private void createDeckArrayList(){

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
				deck1.add(new Card(rank, suit));
				
			}
		}
	}//END createDeck
	
	//returns deck
	public Card[] getDeck(){
		return deck;
	}
	
	public ArrayList<Card> getDeck1(){
		return deck1;
	}
}
