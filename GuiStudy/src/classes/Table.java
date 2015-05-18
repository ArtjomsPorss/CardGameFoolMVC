package classes;

import java.util.ArrayList;

public class Table extends Cards{
	
	//CONSTRUCTOR
	public Table(){
		super(new ArrayList<Card>());
	}
	
	
	//INSTANCE METHODS
	//move cards from table to specified ArrayList
	public void moveCards(ArrayList<Card> toPile){
		toPile.addAll(super.getCards());
	}

}
