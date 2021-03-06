/**
 * Holds game state
 */
package classes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Model {
	
	protected Deck deck;
	protected Player player1;
	protected Player player2;
	protected Player attacker;
	protected Player defender;
	protected Table table;
	protected Discard discard;


	public Model(){
		this.deck = new Deck();
		this.player1 = new Player("First player");
		this.player2 = new Player("Second player");
		this.table = new Table();
		this.discard = new Discard();
	}


	/**
	 * Checks and assigns who goes first
	 * Player with trump in hand of lowest rank goes first
	 * returns String with description
	 */
	protected String assignAttackerDefender(){
		int rank1 = 15;
		int rank2 = 15;
		for(int i = 0; i < 6; ++i){
			if(player1.getCards().get(i).getSuit() == deck.getTrump()){		//if this card is trump
				if(rank1 > Card.rankToInt(player1.getCards().get(i).getRank())){		//if it's lower than current trump rank assigned
					rank1 = Card.rankToInt(player1.getCards().get(i).getRank());		//assign it as new rank
				}
			}
			if(player2.getCards().get(i).getSuit() == deck.getTrump()){		//if this card is trump
				if(rank2 > Card.rankToInt(player2.getCards().get(i).getRank())){		//if it's lower than current trump rank assigned
					rank2 = Card.rankToInt(player2.getCards().get(i).getRank());		//assign it as new rank
				}
			}
		}
		if(rank1 < rank2){	//check who's trump rank is lower, this one goes first
			this.attacker = player1;
			this.defender = player2;
			return "Player 1 has the lowest trump, goes first..";
		}else if (rank1 > rank2){
			this.attacker = player2;
			this.defender = player1;	
			return "Player 2 has the lowest trump, goes first..";
		}else{				//if no one has trumps in hand, random chooses
			Random r = new Random();
			if(r.nextInt(2) == 0){
				this.attacker = player1;
				this.defender = player2;
				return "None has trumps, randomly player 1 goes first";
			}else{
				this.attacker = player2;
				this.defender = player1;
				return "None has trumps, randomly player 2 goes first";
			}
		}
	}	



	// checks if defending player has cards of same suit higher rank or trumps to be able to defend
	protected boolean checkDefend(MouseEvent e){
		Card players = (Card) e.getComponent();	//set card that player clicked
		Card onTable = table.getCards().get(table.getCards().size()-1);	//get last card on table
		if(players.getSuit() == onTable.getSuit()){						//if cards are of same suit
			if(Card.rankToInt(players.getRank()) > Card.rankToInt(onTable.getRank())){	//and players card is of higher rank
				return true;								//players card beats card on table -> return true
			}else{										//if rank of players card is not higher
				return false;								//players card doesn't beat card on table -> return false
			}
		}else if(players.getSuit() == deck.getTrump() && onTable.getSuit() != deck.getTrump()){	//else if players card is trump and on table is not a trump
			return true;										//players card beats card on table
		}else{						//else cards are of different suits and players card is not trump
			return false;				// player cannot beat card on table -> return false
		}

	}


	//checks if card is eligible to add to table
	protected boolean canAdd(MouseEvent e){
		Card players = (Card) e.getComponent();	//set card that player clicked
		//only card of same rank can be added to table
		for(int t = 0; t < table.getCards().size(); ++t){
			if(table.getCards().get(t).getRank().equals(players.getRank())){
				return true;
			}
		}
		return false;
	}


	//switches attacker and defender
	protected void switchAttackerDefender() {
		Player temp = attacker;
		attacker = defender;
		defender = temp;
		temp = null;	
	}


	//check win state
	protected void winCheck(JFrame frame){
		JLabel message = new JLabel();
		message.setFont(new Font("Consolas", Font.BOLD, 20));
		
		if(deck.getCards().size() == 0){
			if(attacker.getCards().size() == 0 && defender.getCards().size() > 0){
				message.setText(attacker.getName() + "player won! Player with no cards in hand wins");
				JOptionPane.showMessageDialog(frame, message);
			}else if(attacker.getCards().size() > 0 && defender.getCards().size() == 0){
				message.setText(defender.getName() + "player won! Player with no cards in hand wins");
				JOptionPane.showMessageDialog(frame, message);
			}else if(attacker.getCards().size() == 0 && defender.getCards().size() == 0){
				message.setText("Draw! Player with no cards in hand wins");
				JOptionPane.showMessageDialog(frame, message);
			}
		}

	}

}
