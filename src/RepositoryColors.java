import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RepositoryColors {

	private static final String FILENAME = "dataColors.txt";
	private String[] color;
	private ArrayList<Integer> colorR = new ArrayList<Integer>();
	private ArrayList<Integer> colorG = new ArrayList<Integer>(); 
	private ArrayList<Integer> colorB = new ArrayList<Integer>(); 

	public void LoadFromFile() {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				color = sCurrentLine.split(",");
				colorR.add(Integer.parseInt(color[0]));
			    colorG.add(Integer.parseInt(color[1]));
			    colorB.add(Integer.parseInt(color[2]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//System.out.println(colorR + " " +  colorG + " " + colorB);
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}
	}

	public ArrayList<Integer> getColorR() {
		return colorR;
	}

	public ArrayList<Integer> getColorG() {
		return colorG;
	}

	public ArrayList<Integer> getColorB() {
		return colorB;
	}

}
