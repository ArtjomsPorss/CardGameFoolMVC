/**
 * Holds game state
 */

package classes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentListener;

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

}
