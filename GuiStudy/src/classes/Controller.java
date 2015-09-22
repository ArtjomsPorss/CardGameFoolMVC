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
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller implements MouseListener{
	private GUI gui;
	private Model model;
	
	/*
	 * Determines state of the game.
	 * 0 - attacker attacks or adds or wins the game if no cards in hand 
	 * 1 - defender defends(move to phase 2) or draws cards if nothing to defend with(moves to)
	 */
	private int phase = 0;	
	private boolean firstAttackOnTurn = true;
	
	private boolean actionDone = false;		//determines whether player performed action or not
	
	

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
	

	//returns true if source of event is card in attackers hand
	private boolean isAttackersCard(MouseEvent e){
		if(model.attacker.getCards().contains(e.getSource())){
			return true;
		}else{
			return false;
		}
	}
	
	
	//returns true if event was raised by the button
	private boolean isButton(MouseEvent e){
		if(e.getComponent().getClass().isInstance(new JButton())){
			return true;
		}else{
			return false;
		}
	}
	
	
	//handles the event from cards
	//depending on which appropriate checks and actions are performed on cards, when their events are handled
	@Override
	public void mouseClicked(MouseEvent e) {
		if(phase == 0){						//attacker attacks phase
			phaseZero(e);
		}else if(phase == 1){
			phaseOne(e);
		}
		
		gui.showCards(model.player1.getCards(), model.player2.getCards(), model.table.getCards(), model.deck.getCards());
		model.winCheck(gui.frame);
	}
	
	
	//TODO - no swiss army knives - split this method in several single-purpose methods
	/**
	 * Handles events passed from buttons or cards. Method is used for several purposes.
	 * It allows attacker to make first move by attacking defending player with one of his cards.
	 * It allows attacker to add card to table after defender beats first attack card.
	 * It passes turn on button press in case if attacking player decides not to add cards to table.
	 * 
	 * @param e MouseEvent passed from button or cards pressed
	 */
	private void phaseZero(MouseEvent e){
		if(isButton(e)){					//button is used to end turn ONLY
			if(!firstAttackOnTurn){		//player decided not to add more cards for defending player to defend 
				model.table.moveCardsTo(model.discard.getCards());//move cards to discard pile
				
				model.switchAttackerDefender();					//attacker and defender are switched
				
				model.attacker.drawHand(model.deck.getCards());		//attacker draws hand of cards
				model.defender.drawHand(model.deck.getCards());		//defender draws hand of cards
				firstAttackOnTurn = true;						//new attacker MUST perform his first move

			}else{
				gui.infoText.setText("Attacking player must make move with one of his cards");
			}
		}else if(isAttackersCard(e) && firstAttackOnTurn){		//if card clicked in attackers hand(for the first time) move it to table
			//move card to table
			int index = model.attacker.getCards().indexOf(e.getSource());
			model.table.getCards().add(model.attacker.getCards().remove(index));
		
			firstAttackOnTurn = false;	//first attack is done
			
			gui.infoText.setText("Defending player choose card to defend or press button to draw cards from table and pass turn." + 
						"In order to defend pick card of same suit and higher rank or trump card.");
			++phase;					//pass turn
			return;
		}else if(isAttackersCard(e) && !firstAttackOnTurn){			//if attacking player adds cards to table
			if(model.canAdd(e)){				//if card chosen is eligible, add it to table
				int index = model.attacker.getCards().indexOf(e.getSource());
				model.table.getCards().add(model.attacker.getCards().remove(index));
				
				gui.infoText.setText("Defending player choose card to defend or press button to draw cards from table and pass turn." + 
						"In order to defend pick card of same suit and higher rank or trump card.");
				++phase;					//pass turn
				return;
			}else{
				gui.infoText.setText("Only cards of rank same that is present on table can be added. Or press button to pass turn.");	
			}
		}else if(isAttackersCard(e) && actionDone){
			gui.infoText.setText("Press button to pass turn to defending player");
		}else{
			gui.infoText.setText("These cards are not from your hand");	
		}
	}
	
	
	//TODO - same no swiss army knives - split this method in several single-purpose methods, KISS
	/**
	 * Handles defending player phase.
	 * Defending player can beat card that attacker placed on table.
	 * Defending player can choose to take cards instead of beating them if he/she doesn't have card to beat with,
	 * or finds that taking card is better in this situation.
	 * 
	 * @param e MouseEvent passed from cards or button
	 */
	private void phaseOne(MouseEvent e){
		if(isButton(e)){
				model.defender.drawAll(model.table.getCards());//defender takes all cards
				model.attacker.drawHand(model.deck.getCards());		//attacker draws cards
				--phase;		//attacker goes again
				firstAttackOnTurn = true;	//attacker will attack for first time on new turn

		}else if(!isAttackersCard(e)){	//if it's a card in defending players hand and haven't performed any actions yet
			if(model.checkDefend(e)){	//if card pressed can beat card on table
				//move that card to table
				int index = model.defender.getCards().indexOf(e.getSource());
				model.table.getCards().add(model.defender.getCards().remove(index));
				
				--phase;			//player defended successfully, swithc back to attacker
				gui.infoText.setText("Attacking player can add card to table of same rank that is present on table. Or press button to pass turn.");	
			}else{						//if chosen card cannot beat card on table
				gui.infoText.setText("Choose a card of same suit and higher rank or trump to defend");
			}
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
