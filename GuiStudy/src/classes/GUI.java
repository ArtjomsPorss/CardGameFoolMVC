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

public class GUI {
	JFrame frame;
	JPanel panel_1;
	JScrollPane scrollPane;
	JScrollBar scrollBar;
	JPanel test;

	public GUI(){
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 775, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panel_1 = new JPanel();
		panel_1.setAutoscrolls(true);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 303, 505, 127);
		panel_1.setLayout(null);
		frame.getContentPane().add(panel_1);
		
		test = new JPanel();
		test.setLayout(null);
		test.setPreferredSize(new Dimension(0,127));
		JScrollPane scrollFrame = new JScrollPane(test);
		test.setAutoscrolls(true);
		scrollFrame.setSize(new Dimension(500,100));
		frame.getContentPane().add(scrollFrame);
		

		
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

			/*
			public void adjustmentValueChanged(AdjustmentEvent e) {
				Point p = new Point();
				//calculate the multiplier how far each scrolling step should go
				int componentCount = panel_1.getComponentCount();
				int maxPosition = scrollBar.getMaximum();
				int panelX = panel_1.getWidth();
				int totalCompX = componentCount * 80;				//width of all cards
				int xWithoutPanel = totalCompX - panelX;			//width of all cards excluding width of panel
				//int scrlMultiplier = xWithoutPanel / maxPosition;	//how far each click will scroll cards to show cards beyond panel width
				int scrlMultiplier = componentCount / maxPosition;
					
				for(int i = 0; i < scrlMultiplier; ++i){
					p.setLocation(-1*((e.getValue()* 27)) + (81 * i + 2), 2);
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
				int totalCompX = 0;				//width of all components in panel_1
				
				//count space taken by all components
				for(int i = 0; i < componentCount; ++i){
					if(i == componentCount - 1){
						totalCompX = panel_1.getComponent(i).getLocation().x;	//set position of last component
						totalCompX += panel_1.getComponent(i).getWidth();		//add last components width
					}
				}
				
				
				int xWithoutPanel = totalCompX - panelX;			//width of all cards excluding width of panel
				//int scrlMultiplier = xWithoutPanel / maxPosition;	//how far each click will scroll cards to show cards beyond panel width
				//double scrlMultiplier = (double)componentCount / (double)maxPosition;		//too less
				//double scrlMultiplier =  (double)maxPosition / (double)componentCount;	//too far
				//double scrlMultiplier = (double)xWithoutPanel / (double)maxPosition; 		//too less
				//double scrlMultiplier = ((double)totalCompX / (double)maxPosition) / 3.200000D;	//better, but still not that precise
				double scrlMultiplier = ((double)xWithoutPanel / (double)maxPosition);
				
				//adjust position of every card in panel when scroll bar is moved
				for(int i = 0; i < componentCount; ++i){
					p.setLocation(-1D * (((double)e.getValue()* scrlMultiplier)) + (double)(81 * i + 2), 2D);
					panel_1.getComponent(i).setLocation(p);
				}
			}
			
			
		});
		scrollBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(10, 434, 500, 17);
		frame.getContentPane().add(scrollBar);
	}
}
