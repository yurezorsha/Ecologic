import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Main {

public static void main(String[] args) {
		
	RepositoryColors r = new RepositoryColors();
	r.LoadFromFile();
	
		final Map map = new Map(10, 15, 80);
		JFrame frame = new JFrame();
		frame.getContentPane().add(map);
        frame.pack();
		frame.setVisible(true);
		
		map.addMouseMotionListener(new MouseAdapter() {

            @Override
			public void mouseMoved(MouseEvent e) {
                int x = e.getX() / 80;
                int y = e.getY() / 80;
                map.highlight(x, y);
            }
		});
		
	}
	
}
