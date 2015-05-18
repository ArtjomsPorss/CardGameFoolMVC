package classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollBar;

import java.awt.ComponentOrientation;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Rectangle;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI {
	//INSTANCE VARIABLES===========================
	private JFrame frame;
	
	//upper panel with cards
	private JScrollPane scrollUpper;
	protected JPanel panelUpper;
	
	//lower panel with cards
	private JScrollPane scrollLower;
	protected JPanel panelLower;
	
	//holds coordinates with cards
	private Point[] coords = new Point[15]; //12 cards on table, 13deck

	
	//CONSTRUCTOR====================================
	public GUI(){
		
		//Main GUI frame
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 775, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		//testing version of scrollpane with panel assigned to it
		panelUpper = new JPanel();
		panelUpper.setBackground(Color.LIGHT_GRAY);
		panelUpper.setLayout(null);
		//test.setPreferredSize(new Dimension(500,127));
		scrollUpper = new JScrollPane(panelUpper);
		scrollUpper.setLocation(15, 10);
		scrollUpper.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollUpper.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panelUpper.setAutoscrolls(true);
		scrollUpper.setSize(new Dimension(500,146));	//if there are cards to be scrolled
		frame.getContentPane().add(scrollUpper);
		
		//Lower panel and scroll pane for cards
		panelLower = new JPanel();
		panelLower.setBackground(Color.LIGHT_GRAY);
		panelLower.setLayout(null);
		//test.setPreferredSize(new Dimension(500,127));
		scrollLower = new JScrollPane(panelLower);
		scrollLower.setLocation(15, 385);
		scrollLower.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollLower.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panelLower.setAutoscrolls(true);
		scrollLower.setSize(new Dimension(500,146));	//if there are cards to be scrolled
		frame.getContentPane().add(scrollLower);
	}
	
	
	//INSTANCE METHODS=========================
	
	//TODO create arrayList deck, remove first element in arraylist, display first element in hand

	//adds cards to panel with scroll pane
	public void showCardsInPanel(ArrayList<Card> cards, JPanel component){
		System.out.println("entered showCardsInPanel()");
		
		int location = 2;
		//TODO if both setSize and setPreferredSize for component are used, it always appears on screen, otherwise sometimes it is not shown 
		component.setPreferredSize(new Dimension((cards.size() * 81) + 4, component.getHeight()));	//works fine if array is not breaked
		component.setSize(new Dimension((cards.size() * 81) + 4, component.getHeight()));
		System.out.println("Component sizes are set");
		
		for(Card c : cards){
			//component.setPreferredSize(new Dimension(component.getSize().width + c.getWidth() + 2, component.getHeight()));		//increasing size of the panel for every card shown in it
			component.add(c);
			c.setLocation(location, 2);
			//c.addMouseListener(control);
			location += 81;
		}
		
		
		//testing printouts
		System.out.println("Cards are added");
		//System.out.println("JScrollPane sizes: " + gui.scrollPane.getPreferredSize().width + " " + gui.scrollPane.getPreferredSize().height);
		System.out.println("Component sizes: " + component.getPreferredSize().width + " " + component.getPreferredSize().height);
	}
}
