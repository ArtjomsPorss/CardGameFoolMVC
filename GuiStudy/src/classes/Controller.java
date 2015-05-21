/**
 * performs game focused actions and acts as event handler
 */

package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Controller implements MouseListener{
	private GUI gui;
	private Model model;
	boolean attacked = false;
	
	/*
	 * Determines state of the game.
	 * 0 - attacker attacks or wins the game if no cards in hand 
	 * 1 - defender defends(move to phase 2) or draws cards if nothing to defend with(moves to)
	 * 2 - attacker ads if have anything to add - goes back to 1, if nothing to add - goes to 3
	 * 3 - 
	 */
	private int phase = 0;	
	
	private boolean actionDone = false;		//determines whether player performed action or not
	
	
	//CONSTRUCTOR============================
	public Controller(GUI gui, Model model){
		this.gui = gui;
		this.model = model;
		
		//add listeners
		model.deck.addListeners(this);
		gui.btnEnd.addMouseListener(this);
		
		model.player1.drawHand(model.deck.getCards());
		model.player2.drawHand(model.deck.getCards());	
		
		gui.infoText.setText(model.assignAttackerDefender());	//assign attacker and defender
		
		gui.showCards(model.player1.getCards(), model.player2.getCards(), model.table.getCards(), model.deck.getCards());
	}
	

	//INSTANCE METHODS=======================

	//returns true if source of event is card in attackers hand
	private boolean isAttacker(MouseEvent e){
		if(model.attacker.getCards().contains(e.getSource())){
			return true;
		}else{
			return false;
		}
	}
	
	
	//check if a button was clicked
	//TODO is this way works fine?
	private boolean isButton(MouseEvent e){
		if(e.getComponent().getClass().isInstance(new JButton())){
			return true;
		}else{
			return false;
		}
	}
	
	//handles the event from cards
	//TODO create state why not to use int state, which changes depending on game state and
	//depending on which appropriate checks and actions are performed on cards, when their events are handled
	@Override
	public void mouseClicked(MouseEvent e) {
		if(phase == 0){						//attacker attacks phase
			if(isButton(e)){					//button is used to end turn ONLY
				if(actionDone){
					++phase;					//pass turn
					actionDone = false;			
					System.out.println("Button clicked phase: " + phase + ", actionDone = " + actionDone);
				}else{
					gui.infoText.setText("Attacker must make move with one of his cards");
				}
			}else if(isAttacker(e) && !actionDone){		//if card clicked in attackers hand(for the first time) move it to table
				int index = model.attacker.getCards().indexOf(e.getSource());
				model.table.getCards().add(model.attacker.getCards().remove(index));
				actionDone = true;
				System.out.println("Button clicked phase: " + phase + ", actionDone = " + actionDone);
				gui.infoText.setText("Press button to pass turn");
			}else{
				gui.infoText.setText("These are not cards from your hand");	
			}
			gui.showCards(model.player1.getCards(), model.player2.getCards(), model.table.getCards(), model.deck.getCards());
		}
	}
	
	

	


	@Override
	public void mouseEntered(MouseEvent arg0) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}


	@Override
	public void mouseReleased(MouseEvent arg0) {}	

}
