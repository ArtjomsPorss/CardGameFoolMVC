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

public class GUI {
	JFrame frame;
	JPanel panel;
	JPanel panel_1;
	JScrollPane scrollPane;
	JScrollBar scrollBar;

	public GUI(){
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 775, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 303, 586, 133);
		panel_1.setLayout(null);
		frame.getContentPane().add(panel_1);

		
		scrollBar = new JScrollBar();
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			/*
			public void adjustmentValueChanged(AdjustmentEvent e) {
				Point p = new Point();
				//calculate the multiplier how far each scrolling step should go
				double components = (double)panel_1.getComponentCount();
				double scrollMax = (double)scrollBar.getMaximum();
				double scrollMultiplier = components / scrollMax;
				System.out.println("Components: " + components + "\tMaximum: " + scrollMax + "\tMultiplier" + scrollMultiplier);
				for(int i = 0; i < panel_1.getComponentCount(); i++){
					p.setLocation((double)-1 * (e.getValue() * scrollMultiplier) + (double)(i * 80) , 0D);
					panel_1.getComponent(i).setLocation(p);
				}
			}
			*/
			
			public void adjustmentValueChanged(AdjustmentEvent e) {
				Point p = new Point();
				//calculate the multiplier how far each scrolling step should go
				int componentCount = panel_1.getComponentCount();
				int maxPosition = scrollBar.getMaximum();
				int panelX = panel_1.getWidth();
				int totalCompX = componentCount * 80;				//width of all cards
				int xWithoutPanel = totalCompX - panelX;			//width of all cards excluding width of panel
				//int scrlMultiplier = xWithoutPanel / maxPosition;	//how far each click will scroll cards to show cards beyond panel width
				int scrlMultiplier = totalCompX / maxPosition;
					
				for(int i = 0; i < componentCount; ++i){
					p.setLocation(-1*(e.getValue()* scrlMultiplier) + (80 * i), 0);
					panel_1.getComponent(i).setLocation(p);
				}
			}
		});
		scrollBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(10, 439, 586, 17);
		frame.getContentPane().add(scrollBar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(10, 11, 177, 142);
		frame.getContentPane().add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel = new JPanel();
		panel.setBounds(218, 11, 80, 123);
		frame.getContentPane().add(panel);
		panel.setPreferredSize(new Dimension(80, 123));
		panel.setMinimumSize(new Dimension(80, 123));
	}
}
