/**
 * Holds game state
 */

package classes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Model {
	//INSTANCE VARIABLES==============================
	private GUI gui;
	protected Deck deck;
	protected Player player1;
	protected Player player2;
	protected Player attacker;
	protected Player defender;
	protected Table table;
	protected Discard discard;
	
	
	//CONSTRUCTOR=====================================
	public Model(){
		this.deck = new Deck();
		this.player1 = new Player();
		this.player2 = new Player();
		this.table = new Table();
		this.discard = new Discard();
	}
	
	
	//CLASS METHODS=========================================

	
	//INSTANCE METHODS=====================================
	
	/**
	 * Checks and assigns who goes first
	 * Player with trump in hand of lowest rank goes first 
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
}
