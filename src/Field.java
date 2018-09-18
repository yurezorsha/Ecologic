import java.awt.Color;

public class Field {

	public Color color;
	public double height;
	public int x, y;
	public TypeField type;
	private int R, G, B;

	
	
	public Field() {
		RepositoryColors r = new RepositoryColors();
		R = r.getColorR().get(0);
		G = r.getColorG().get(0);
		B = r.getColorB().get(0);
		System.out.println("R: " + R + " G: " + G + " B: " + B);
	}

	public Field(Color color, double height, int x, int y, TypeField type) {
		this.color = color;
		this.height = height;
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public Color getColor() {
		color = new Color(R, G, B);

		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public TypeField getType() {
		return type;
	}

	public void setType(TypeField type) {
		this.type = type;
	}

}
