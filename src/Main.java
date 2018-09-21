import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Main {

	private static final int size = 10;
	
public static void main(String[] args) {
		
	RepositoryColors r = new RepositoryColors();
	r.LoadFromFile();
	int w = 128; 
	int h = 128;
	
	
	
		final Map map = new Map(w, h, size);
		JFrame frame = new JFrame();
		frame.getContentPane().add(map);
        frame.pack();
		frame.setVisible(true);
		
		map.addMouseMotionListener(new MouseAdapter() {

            @Override
			public void mouseMoved(MouseEvent e) {
                int x = e.getX() / size;
                int y = e.getY() / size;
                map.highlight(x, y);
            }
		});
		
	}
	
}
