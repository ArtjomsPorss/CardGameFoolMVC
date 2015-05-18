package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck extends Cards{
	//INSTANCE VARIABLES==================================
	private ArrayList<Card> deck = new ArrayList<>(36);	//ArrayList does reindexing if element was removed
	private char trumps;
	
	
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
	
	
	// Shuffle deck algorithm
	public void shuffleDeck(){	//shuffle while trump card is ace
		do{
			Collections.shuffle(super.getCards());
		}while(super.getCards().get(super.getCards().size()-1).getRank().equals("A"));
		
		trumps = super.getCards().get(super.getCards().size()-1).getSuit();		//set trumps
		System.out.println("" + trumps);
	}//shuffleDeck()
}
