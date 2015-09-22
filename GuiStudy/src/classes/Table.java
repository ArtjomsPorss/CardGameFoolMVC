/*
* Holds ArrayList with cards on table
*/
package classes;

import java.util.ArrayList;

public class Table extends Cards{
	
	public Table(){
		super(new ArrayList<Card>());
	}
	
	
	//used to move cards from table to specified ArrayList
	public void moveCardsTo(ArrayList<Card> toPile){
		while(super.getCards().size() > 0){
			toPile.add(super.getCards().remove(0));
		}
	}

}
