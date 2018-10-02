import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.*;

public class Main {

public static void main(String[] args) {

	int gridSize = 50;
	
	Terrain terrain = new Terrain(800, 400, gridSize);
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	label.setForeground(Color.red);
	frame.getContentPane().add(terrain);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
	frame.setVisible(true);
	terrain.setLayout(new FlowLayout());
	terrain.add(label);
    

	terrain.addMouseMotionListener(new MouseAdapter() {
		@Override
			public void mouseMoved(MouseEvent e) {
                int x = e.getX() ;
                int y = e.getY();
                terrain.pickFiled(x, y, label);
  	          }
		});
	// :(
	terrain.addMouseListener(new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			String[] companies=null;
			ExcelParser parser=null;
			try {
				parser = new ExcelParser("parametry_vybrosov_ZV.xls");
			    try {
					companies=parser.getSheetList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			String input = (String) JOptionPane.showInputDialog(
			null, 
			"Выберите предприятие",
			"Выбор предприятия для постройки", 
			JOptionPane.QUESTION_MESSAGE, 
			null,                                                                                                    // icon
			companies, 
			companies[0]);
			
			Company c = null;
			try {
				c = parser.getData(input);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Elements fr  = new Elements(c); 
					        SwingUtilities.invokeLater(new Runnable() {
					           public void run() {
					              fr.setVisible(true);
					           }
					        });
				
					
							}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
			terrain.stopHighlight();
		}
	});

	frame.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == 'v')
				terrain.zoom(3);
			if(e.getKeyChar() == 'c'){
                terrain.mode++;
                terrain.repaint();
            }
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	});
	}



}
