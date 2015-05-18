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
	protected Player player;
	
	
	//CONSTRUCTOR=====================================
	public Model(){
		this.deck = new Deck();
		this.player = new Player();

	}
	
	
	//CLASS METHODS=========================================

	
	//INSTANCE METHODS=====================================
	
}
