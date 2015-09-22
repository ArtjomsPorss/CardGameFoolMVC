/**
 * base class for classes that have ArrayLists of cards
 */
package classes;

import java.util.ArrayList;

public class Cards {

	private ArrayList<Card> cards;
	

	public Cards(){}
	
	public Cards(ArrayList<Card> cards){
		this.cards = cards;
	}
	

	//returns cards
	protected ArrayList<Card> getCards(){
		return cards;
	}
	
	
	protected void addCard(Card c){
		cards.add(c);
	}
	
	
	//TESTING
//	public static void moveFromTo(ArrayList<Card> from, ArrayList<Card> to){
//		for(Card c : from){
//			to.add(from.remove(0));
//		}
//	}
}
