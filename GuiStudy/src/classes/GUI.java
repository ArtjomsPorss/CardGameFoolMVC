package classes;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollBar;
import java.awt.ComponentOrientation;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

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
		panel_1.setBounds(10, 303, 586, 133);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		scrollBar = new JScrollBar();
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				for(int i = 0; i < panel_1.getComponentCount(); i++){
					panel_1.getComponent(i).setLocation(e.getValue() + (i * 80), 0);
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
