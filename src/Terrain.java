import java.awt.*;
import java.util.Random;
import javax.swing.*;


public class Terrain extends JPanel {
    private Square[][] squares;
    private int gridSize;
    private int squareSize = 1;
    private int height, width;
    private int seed;


    public Terrain(int rows, int cols, int gridSize) {
        this.setPreferredSize(new Dimension(rows, cols));
        this.gridSize = gridSize;
        this.width = rows;
        this.height = cols;
        squares = new Square[rows][cols];
        seed = new Random().nextInt();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                squares[i][j] = new Square(i,j,seed);
            }
        }
    }

    private void addRivers(int count){

    }

    public int mode = 3;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(mode%3 == 2)
            showHei(g);
        else if(mode%3 == 1)
            showMoi(g);
        else
            draw(g);
    }

    public void draw(Graphics g){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(i% gridSize == 0 || j% gridSize == 0)
                    g.setColor(Color.black);
                else if (getTSS(i) == hX && getTSS(j) == hY && hightlight == true)
                    g.setColor(squares[i][j].getColor().brighter());
                else
                    g.setColor(squares[i][j].getColor());
                g.fillRect(i,j,1,1);
            }
        }
    }

    public void showMoi (Graphics g) {
        System.out.println("Moist");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor(Color.getHSBColor((float)squares[i][j].getMoist(), .5f, .5f));
                g.fillRect(i,j,1,1);
            }
        }
    }

    public void showHei (Graphics g) {
        System.out.println("Height");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor(Color.getHSBColor((float)squares[i][j].getHeight(), .5f, .5f));
                g.fillRect(i,j,1,1);
            }
        }
    }

    private int getTSS(int value){
        return Math.round(value/ gridSize)* gridSize;
    }
    private int getToSquareSize(int value){
        return Math.round(value/ gridSize)* gridSize;
    }

    private int hX, hY;
    boolean hightlight = false;
    public void pickFiled(int x, int y, JLabel label) {
        hX = getToSquareSize(x);
        hY = getToSquareSize(y);
        label.setText(String.format("%s", squares[x][y].getBio()));
        hightlight = true;
        repaint();
    }
    public void stopHighlight(){
        hightlight = false;
    }

    public void zoom(int scale){
        squareSize = scale;
            for (int i = 0; i <= width-scale; i+=scale) {
                for (int j = 0; j <= height-scale; j+=scale) {
                    Square[][] someSq = new Square[scale][scale];
                    for (int l = 0; l < scale; l++) {
                        for (int k = 0; k < scale; k++) {
                            someSq[l][k] = squares[i+l][j+k];
                        }
                    }
                    //TODO функции считают только height и biome
                    double sqH =  Square.averangeHeight(someSq);
                    Biome sqB = Square.averangeBiome(someSq);
                    for (int l = 0; l < scale; l++) {
                        for (int k = 0; k < scale; k++) {
                            squares[i+l][j+k].setHeight(sqH);
                            squares[i+l][j+k].setBio(sqB);
                        }
                    }
                    System.out.println(String.format("Done for i = %d, j = %d", i, j));
                }
            }
        repaint();
    }



}
