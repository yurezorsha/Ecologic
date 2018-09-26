import java.awt.Color;

public class Square {
    private double h;
    private int x, y;
    private Biome bio;


    public enum Biome{
        PLAIN, FOREST, RIVER, SWAMP, MOUNT, SITY;
    }

    public Square(){
        System.out.println("f u");
        throw new java.lang.Error("try something else");
    }

    public Square(int x, int y, int seed){
        this.x = x;
        this.y = y;
        calcHeight(seed);

        //TODO биомы
        bio = Biome.PLAIN;
    }

    public int getX() {
        return x;
    } public int getY(){
        return y;
    } public double getH(){
        return h;
    } public void setH(double h){
        this.h = h;
    } public Biome getBio(){
        return bio;
    } public void setBio(Biome bio) {
        this.bio = bio;
    }


    public Color getColor(){
        double col = h/2f+0.065f; //from ~0 to ~0.5
        if(col>0.4f){
            return Color.getHSBColor((float)col+.18f, .7f, .8f);
        } else if(col < 0.25f){
            return Color.getHSBColor((float)col+.5f, .03f, .235f);
        }
        return Color.getHSBColor((float)col, .7f, .8f);
    }

    private double scale = .007;
    private double pers = .3;
    public void calcHeight(int seed){
        float res = (float)SimplexNoise.sumOctave(16, x, y, seed, pers, scale);
        h = (res+1)/2;
    }

    static double averange( Square[][] s){
        int numberSq = s.length;
        double av = 0;
        for (int i = 0; i < numberSq; i++) {
            for (int j = 0; j < numberSq; j++) {
                av += s[i][j].getH();
            }
        }
        av /= (numberSq*numberSq);
        return av;
    }


}
