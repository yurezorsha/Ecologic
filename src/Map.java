import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Map extends JPanel {

	private Field matrix[][];
	private int size_field;
	// higlighted (with mouse) tile 
    private int hx = -1, hy = -1;

    
    
	public Map(int rows, int cols, int size_field) 
	{
		this.setPreferredSize(new Dimension(cols * size_field, rows * size_field));
        this.size_field = size_field;
        matrix = new Field[rows][cols];
        int cnt = 0;
        CreateMap cm = new CreateMap();
        int[]  pixels = cm.createMap(rows, cols);
        for (int i = 0; i < matrix.length; ++i)
        {
            for (int j = 0; j < matrix[i].length; ++j)
            {
                matrix[i][j] = new Field(new Color(pixels[cnt]));
                cnt++;
            }
        }
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (i == hy && j == hx) {
                    g.setColor(matrix[i][j].getColor().brighter());
                } else {
                    g.setColor(matrix[i][j].getColor());
                }
                g.fillRect(j * size_field, i * size_field + 1, size_field - 1, size_field - 1);
            }
        }
    }

    public void highlight(int x, int y) {
        hx = x;
        hy = y;
        repaint();
    }

	

}
