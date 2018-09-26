

import java.util.Random;


public class CreateMap {
    public static final Random random = new Random();

    public int w;
    public int h;
    public double[] map;

    public CreateMap() {}
    
    public CreateMap(int w, int h, int featureSize) {
        this.w = w;
        this.h = h;
        this.map = new double[w * h];
        this.generateNoise(featureSize);
    }

    public void generateNoise(int featureSize) {

        for (int y = 0; y < h; y += featureSize) {
            for (int x = 0; x < w; x += featureSize) {
                setSample(x, y, random.nextDouble() * 2 - 1);
            }
        }
        int stepSize = featureSize;
        double scale = 1.0 / w;
        double scaleMod = 1;
        do {
            int halfStep = stepSize / 2;
            for (int y = 0; y < h; y += stepSize) {
                for (int x = 0; x < w; x += stepSize) {
                    double a = getSample(x, y);
                    double b = getSample(x + stepSize, y);
                    double c = getSample(x, y + stepSize);
                    double d = getSample(x + stepSize, y + stepSize);

                    double e = (a + b + c + d) / 4.0 + (random.nextFloat() * 2 - 1) * stepSize * scale;
                    setSample(x + halfStep, y + halfStep, e);
                }
            }
            for (int y = 0; y < h; y += stepSize) {
                for (int x = 0; x < w; x += stepSize) {
                    double a = getSample(x, y);
                    double b = getSample(x + stepSize, y);
                    double c = getSample(x, y + stepSize);
                    double d = getSample(x + halfStep, y + halfStep);
                    double e = getSample(x + halfStep, y - halfStep);
                    double f = getSample(x - halfStep, y + halfStep);

                    double h = (a + b + d + e) / 4.0 + (random.nextFloat() * 2 - 1) * stepSize * scale * 0.5;
                    double g = (a + c + d + f) / 4.0 + (random.nextFloat() * 2 - 1) * stepSize * scale * 0.5;
                    setSample(x + halfStep, y, h);
                    setSample(x, y + halfStep, g);
                }
            }

            stepSize /= 2;
            scale *= (scaleMod * 0.8);
            scaleMod *= 0.3;
        } while (stepSize > 1);
    }

    public static final int NIZINA = 0xb9d6a0;      //0
    public static final int RAVNINA = 0xd0e1ab;     //1
    public static final int RAVNINAPLUS = 0xeac183; //2
    public static final int TREE = 0xde936c;        //3
    public static final int ROCK = 0xe06955;        //4

    public static int[] createMap(int w, int h) {
        CreateMap mnoise1 = new CreateMap(w, h, 16);
        CreateMap mnoise2 = new CreateMap(w, h, 16);
        CreateMap mnoise3 = new CreateMap(w, h, 16);

        CreateMap noise1 = new CreateMap(w, h, 32);
        CreateMap noise2 = new CreateMap(w, h, 32);

        int[] map = new int[w * h];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = x + y * w;

                double val = Math.abs(noise1.map[i] - noise2.map[i]) * 3 - 2;
                double mval = Math.abs(mnoise1.map[i] - mnoise2.map[i]);
                mval = Math.abs(mval - mnoise3.map[i]) * 3 - 2;

                double xd = x / (w - 1.0) * 2 - 1;
                double yd = y / (h - 1.0) * 2 - 1;
                if (xd < 0) xd = -xd;
                if (yd < 0) yd = -yd;
                double dist = xd >= yd ? xd : yd;
                dist = dist * dist * dist * dist;
                dist = dist * dist * dist * dist;
                val = val + 1 - dist ;

                if (val < -0.5) {
                    map[i] = NIZINA;
                } else {
                    map[i] = RAVNINA;
                }
            }
        }
        
      //ƒальше хаотично находим место с травой и засел€ем его плотненько песочком
        for (int i = 0; i < w * h / 2000; i++) {
            int xs = random.nextInt(w);
            int ys = random.nextInt(h);
            for (int k = 0; k < 10; k++) {
                int x = xs + random.nextInt(21) - 10;
                int y = ys + random.nextInt(21) - 10;
                for (int j = 0; j < 100; j++) {
                    int xo = x + random.nextInt(30) - random.nextInt(10);
                    int yo = y + random.nextInt(30) - random.nextInt(10);
                    for (int yy = yo - 1; yy <= yo + 1; yy++)
                        for (int xx = xo - 1; xx <= xo + 1; xx++)
                            if (xx >= 0 && yy >= 0 && xx < w && yy < h) {
                                if (map[xx + yy * w] == RAVNINA) {
                                    map[xx + yy * w] = RAVNINAPLUS;
                                }
                            }
                }
            }
        }
        
      //так же находим траву и засел€ем ее в радиусе random.nextInt(10) - random.nextInt(10) деревь€м
        for (int i = 0; i < w * h / 200; i++) {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            for (int j = 0; j < 200; j++) {
                int xx = x + random.nextInt(8) - random.nextInt(8);
                int yy = y + random.nextInt(8) - random.nextInt(8);
                if (xx >= 0 && yy >= 0 && xx < w && yy < h) {
                    if (map[xx + yy * w] == RAVNINAPLUS) {
                        map[xx + yy * w] = TREE;
                    }
                }
            }
        }
        
        for (int i = 0; i < w * h / 500; i++) {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            for (int j = 0; j < 200; j++) {
                int xx = x + random.nextInt(8) - random.nextInt(8);
                int yy = y + random.nextInt(8) - random.nextInt(8);
                if (xx >= 0 && yy >= 0 && xx < w && yy < h) {
                    if (map[xx + yy * w] == TREE) {
                        map[xx + yy * w] = ROCK;
                    }
                }
            }
        }

        return map;
    }

    public double getSample(int x, int y) {
        return this.map[(x & (w - 1)) + (y & (h - 1)) * w];
    }

    public void setSample(int x, int y, double value) {
        this.map[(x & (w - 1)) + (y & (h - 1)) * w] = value;
    }

}
