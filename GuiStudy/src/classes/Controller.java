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
	
	//CONSTRUCTOR============================
	public Controller(GUI gui, Model model){
		this.gui = gui;
		this.model = model;
		model.player.drawHand(model.deck.getCards());
		
		//TESTING
		gui.showCardsInPanel(model.deck.getCards(), gui.panelUpper, false);
		//gui.showCardsInPanel(model.deck.getCards(), gui.panelLower, false);
		gui.showCardsInPanel(model.player.getCards(), gui.panelLower, false);
	}
	

	//INSTANCE METHODS=======================

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String cardName = e.getComponent().toString();
		System.out.println(cardName + " has been pressed");
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
