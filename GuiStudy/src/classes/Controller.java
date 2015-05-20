/**
 * performs game focused actions and acts as event handler
 */

package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Controller implements MouseListener{
	private GUI gui;
	private Model model;
	boolean attacked = false;
	
	//CONSTRUCTOR============================
	public Controller(GUI gui, Model model){
		this.gui = gui;
		this.model = model;
		model.deck.addListeners(this);
		model.player1.drawHand(model.deck.getCards());
		model.player2.drawHand(model.deck.getCards());	
		
		gui.infoText.setText(model.assignAttackerDefender());	//assign attacker and defender
		
		gui.showCards(model.player1.getCards(), model.player2.getCards(), model.table.getCards(), model.deck.getCards());
	}
	

	//INSTANCE METHODS=======================
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getClass().isInstance(new Card())){		// TODO how to compare class of object that caused event to Card class without creating actual card? 
			String cardName = e.getComponent().toString();
			System.out.println(cardName + " has been pressed");
			model.handler(e);
			
			gui.showCards(model.player1.getCards(), model.player2.getCards(), model.table.getCards(), model.deck.getCards());
		} else {
			System.out.println("Something else has been pressed");				
		}
	}
	
	
	private void attack(){
		do{
			//System.out.println("");
		}while(attacked == false);
		System.out.println("HE PRESSSED DA BUTTON!");
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
