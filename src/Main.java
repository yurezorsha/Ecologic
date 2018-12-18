import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Calculations.Company;
import Calculations.XY;
import DataFromExcel.ExcelParser;
import Forms.FormElements;
import Generation.Terrain;

public class Main {

public static void main(String[] args) {

	int gridSize = 50;
	
	Terrain terrain = new Terrain(800, 400, gridSize);
	JFrame frame = new JFrame();
	frame.setResizable(false);
	frame.setTitle("Карта местности");
	
	
	JLabel label = new JLabel();
	label.setForeground(Color.red);
	frame.getContentPane().add(terrain);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	terrain.setLayout(new FlowLayout());
	terrain.add(label);
    

	terrain.addMouseMotionListener(new MouseAdapter() {
		@Override
			public void mouseMoved(MouseEvent e) {
                int x = e.getX();
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
			
			if(input!=null){
			Company c = null;
			try {
				c = parser.getData(input);
			}   catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			FormElements fr  = new FormElements(c,new XY(e.getX(),e.getY())); 
					        SwingUtilities.invokeLater(new Runnable() {
					           @Override
							public void run() {
					        	   
					        		fr.setLocationRelativeTo(null);
					        		fr.setVisible(true);
					           }
					        });
				
			}		
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
		/*	if(e.getKeyChar() == 'v')
				terrain.zoom(3);
			if(e.getKeyChar() == 'c'){
                terrain.mode++;
                terrain.repaint();
            }*/
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	});
	}



}
