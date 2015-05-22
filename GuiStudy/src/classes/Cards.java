/**
 * base class for classes that have ArrayLists of cards
 */
package classes;

import java.util.ArrayList;

public class Cards {
	//VARIABLES
	private ArrayList<Card> cards;
	
	//CONSTRUCTOR
	public Cards(){}
	
	public Cards(ArrayList<Card> cards){
		this.cards = cards;
	}
	
	//INSTANCE METHODS
	//returns cards
	protected ArrayList<Card> getCards(){
		return cards;
	}
	
	
	//adds card to cards 
	protected void addCard(Card c){
		cards.add(c);
	}
	
	
	//CLASS METHODS
//	public static void moveFromTo(ArrayList<Card> from, ArrayList<Card> to){
//		for(Card c : from){
//			to.add(from.remove(0));
//		}
//	}
}
