package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
	private AppWindow gui = new AppWindow();
	
	
	public Controller(AppWindow gui){
		this.gui = gui;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
