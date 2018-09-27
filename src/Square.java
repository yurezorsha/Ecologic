import java.awt.Color;
import java.util.Random;

public class Square {
    private double height;
    private double moist;
    private int x, y;
    private Biome bio;




    public Square(){
        System.out.println("f u");
        throw new java.lang.Error("try something else");
    }

    public Square(int x, int y, int seed){
        this.x = x;
        this.y = y;
        calcHeight(seed);
        calcMoinst(seed);
        setBio();
    }



    public int getX() {
        return x;
    } public int getY(){
        return y;
    } public double getHeight (){
        return height;
    } public void setHeight (double height){
        this.height = height;
    } public Biome getBio(){
        return bio;
    } public void setBio(Biome bio) {
        this.bio = bio;
    } public double getMoist () {
        return moist;
    } public void setMoist(double moist){
        this.moist = moist;
    }

    public Color getColor(){
            switch (bio) {
                case RIVER:
                    return Color.decode("#41C4EB");
                case DESERT:
                    return Color.decode("#EDC9AF");
                case PLAIN:
                    return Color.decode("#49b484");
                case FOREST:
                    return Color.decode("#228B22");
                case BRUSH:
                    return Color.decode("#7d8e74");
                case TAIGA:
                    return Color.decode("#808000");
                case MOUNT:
                    return Color.decode("#977c53");
                case SNOW:
                    return Color.WHITE;
                case SITY:

                default:
                    return Color.getHSBColor((float) height, .7f, .535f);
            }
    }


    private void calcHeight(int seed){
        double scale = .007;
        double pers = .3;
        height = SimplexNoise.sumOctave(16, x, y, seed, pers, scale);
        height = (height+1)/2;
    }

    private void calcMoinst (int seed) {
        double scale = .004;
        double pers = .3;
        seed = seed;
        moist = SimplexNoise.sumOctave(16, x, y, seed, pers, scale);
        moist = (moist+1)/2;
    }

    public void setBio(){
        //TODO биомы нормально
        if(height < .3f){
                bio = Biome.RIVER;
        }
        else if(height < .5f){
            if(moist < .3f)
                bio = Biome.DESERT;
            else
                bio = Biome.PLAIN;
        } else if(height < .7f){
            if(moist < .5f)
                bio = Biome.BRUSH;
            else
                bio = Biome.FOREST;
        } else if(height < .9f){
            if(moist < .5f)
                bio = Biome.TAIGA;
            else
                bio = Biome.MOUNT;
        } else
            bio = Biome.SNOW;

    }



    static double averangeHeight (Square[][] s){
        int numberSq = s.length;
        double av = 0;
        for (int i = 0; i < numberSq; i++) {
            for (int j = 0; j < numberSq; j++) {
                av += s[i][j].getHeight();
            }
        }
        av /= (numberSq*numberSq);
        return av;
    }

    static Biome averangeBiome(Square[][] s){
        int len = s.length;
        Biome[] bio = new Biome[len * len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                bio[i*len+j] = s[i][j].getBio();
            }
        }
        return Biome.maxBiome(bio);
    }


}
