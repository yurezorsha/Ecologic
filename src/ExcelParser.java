import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;


public class ExcelParser {
	private String path;
	private int sheetNumber;
	private CatalogOfElements catalog=CatalogOfElements.getInstance();

	public ExcelParser(String path,int sheetNumber) throws FileNotFoundException {
					this.path=path;
		            this.sheetNumber=sheetNumber;
	}
	
	public ExcelParser(String path) throws FileNotFoundException {
		this.path=path;
        
    }
	
	public int getSheetNumber() {
		return sheetNumber;
	}
	
	public int getNumberOfSheet(String name) throws IOException{
		FileInputStream inputStream=new FileInputStream(new File(path));
	    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
	    return workbook.getSheetIndex(name);
		
	}
	
	


	public void setSheetNumber(int sheetNumber) {
		this.sheetNumber = sheetNumber;
	}
	
	public String[] getSheetList() throws IOException {
		FileInputStream inputStream=new FileInputStream(new File(path));
	    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        String[] s=new String[workbook.getNumberOfSheets()-1];
		for (int i = 0; i < workbook.getNumberOfSheets()-1; i++) {
			 s[i]=workbook.getSheetName(i);
		}
		return s;
		
	}
    
	
	public Company getData(String name) throws IOException {
	    this.sheetNumber=getNumberOfSheet(name);
        Company c=new Company(name,sheetNumber);
    FileInputStream inputStream=new FileInputStream(new File(path));
    // Get the workbook instance for XLS file
    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

    // Get first sheet from the workbook
    HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
    // Get iterator to all the rows in current sheet
    Iterator<Row> rowIterator = sheet.iterator();
    Double n=null;
	String s=null;
    LinkedList<Element> elements=new LinkedList();
    //System.out.println("Names: "+ workbook.getSheetName(0));
    while (rowIterator.hasNext()) {
    	
    	
        Row row = rowIterator.next();
        // Get iterator to all cells of current row
        Iterator<Cell> cellIterator = row.cellIterator();
   
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

            // Change to getCellType() if using POI 4.x
            CellType cellType =cell.getCellType() ;
            if(n!=null && s!=null){
            	Element elem =new Element();
            	elem.setCode(s);
            	elem.setMass(n);
 
            	elem.setName(catalog.getElementByCode(s).getName());	
            	elements.add(elem);
            	n=null;
            	s=null;
            	
            }
            
            switch (cellType) {
            case _NONE:
                System.out.print("");
                System.out.print("\t");
                break;
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                System.out.print("\t");
                break;
            case BLANK:
                System.out.print("");
                System.out.print("\t");
                break;
            case FORMULA:
                // Formula
                System.out.print(cell.getCellFormula());
                System.out.print("\t");
                 
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                // Print out value evaluated by formula
                System.out.print(evaluator.evaluate(cell).getNumberValue());
                break;
            case NUMERIC:
            	
                System.out.print(cell.getNumericCellValue());
                n=cell.getNumericCellValue();
                System.out.print("\t");
                break;
            case STRING:
                System.out.print(cell.getStringCellValue());
                s=cell.getStringCellValue();
                System.out.print("\t");
                break;
            case ERROR:
                System.out.print("!");
                System.out.print("\t");
                break;
            }

        }
        System.out.println("");
    }
    c.setElements(elements);
    return c;
}
	
	
	

}
