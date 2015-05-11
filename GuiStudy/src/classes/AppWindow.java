package classes;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AppWindow {

	private JFrame frame;
	Card card;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//testing cards position to figure out the coordinates for cards to be shown in the in the game GUI
		//TODO cards are rendered in backwards position
		//cards added first are at front, later cards added - are rendered behind one that's added first
		//top row
		Card HA = new Card('H', "A");
		frame.add(HA);
		HA.setLocation(185, 15);
		
		Card CA = new Card('C', "A");
		frame.add(CA);
		CA.setLocation(100, 15);
		
		Card SA = new Card('S', "A");
		frame.add(SA);
		SA.setLocation(15, 15);
		
		//middle row
		Card SK = new Card('S', "K");
		frame.add(SK);
		SK.setLocation(90, 220);
		
		Card S10 = new Card('S', "10");
		frame.add(S10);
		S10.setLocation(70, 200);
		
		
		//bottom row		
		Card H10 = new Card('H', "10");
		frame.add(H10);
		H10.setLocation(15, 385);	
		
	}
}
