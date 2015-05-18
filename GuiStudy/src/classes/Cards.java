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
	
	//METHODS
	//returns cards
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	
	//adds card to cards 
	protected void addCard(Card c){
		cards.add(c);
	}
	
}
