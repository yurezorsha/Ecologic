import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;


public class CatalogOfElements {
	private LinkedList<Element> elements;
	private static String PATH ="parametry_vybrosov_ZV.xls";
	public static ArrayList<SumElem> sumelem;
	
	private static CatalogOfElements single_instance = null; 
	private String code=null;
    private  Double MPC=null;
	private String name=null;
     
	private CatalogOfElements() {
		
		try {
			loadFromExcel(PATH);
			loadsum(PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void init() {
		// TODO Auto-generated method stub

	}
	
	public static CatalogOfElements getInstance() 
    { 
        if (single_instance == null){ 
            single_instance = new CatalogOfElements(); 
        }
  
        return single_instance; 
    } 

	public LinkedList<Element> getElements() {
		return elements;
	}

	public void setElements(LinkedList<Element> elements) {
		this.elements = elements;
	}
	
	private void loadFromExcel(String path) throws IOException{
		    FileInputStream inputStream=new FileInputStream(new File(path));
		    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		    HSSFSheet sheet = workbook.getSheetAt(workbook.getNumberOfSheets()-2);
		    Iterator<Row> rowIterator = sheet.iterator();	    
		    this.elements=new LinkedList();
		    
		    while (rowIterator.hasNext()) {  	
		        Row row = rowIterator.next();
		        Iterator<Cell> cellIterator = row.cellIterator();
		        
		        while (cellIterator.hasNext()) {
		            Cell cell = cellIterator.next();		
		            CellType cellType =cell.getCellType() ;
		            
		            addElementToList();
		            
		            switch (cellType) {
		            
		            case _NONE:
		                
		                break;
		            case BOOLEAN:
		                		                break;
		            case BLANK:
		                
		                break;
		            case FORMULA:
		                // Formula
		               // System.out.print(cell.getCellFormula());
		               // System.out.print("\t");
		                 
		               // FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		                // Print out value evaluated by formula
		                //System.out.print(evaluator.evaluate(cell).getNumberValue());
		                break;
		            case NUMERIC:
		            	
		                //System.out.print(cell.getNumericCellValue());
		                MPC=cell.getNumericCellValue();
		               // System.out.print("\t");
		                break;
		            case STRING:
		                //System.out.print(cell.getStringCellValue());
		                if(code==null){
		                    code=cell.getStringCellValue();
		                }else{
		                	name=cell.getStringCellValue();
		                }
		                //System.out.print("\t");
		                break;
		            case ERROR:
		               // System.out.print("!");
		                //System.out.print("\t");
		                break;
		            }

		        }
		        //System.out.println("");
		    }
		    
		    
	
}
	
	public void addElementToList(){
		if(code!=null && name!=null && MPC!=null){
        	Element elem =new Element();
        	elem.setCode(code);
        	elem.setName(name);
        	elem.setMPC(MPC/1000);
          	this.elements.add(elem);
        	code=null;
        	MPC=null;
        	name=null;
        }
	}
	
	public Element getElementByCode(String code){
		Element tmpel=null;
		
		for (Element e:elements) {
		if(e.getCode().equals(code)){
			//System.out.println(e.getCode() + " : "+ code);
			tmpel=e;
			break;
		}
		
	
			
		}
		
		if(tmpel==null){
			return new Element("empty");
			
		}else{
		    return tmpel;
		}
	}
	
	
	
	private void loadsum(String path) throws IOException{
		sumelem=new ArrayList<SumElem>();
	    FileInputStream inputStream=new FileInputStream(new File(path));
	    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
	    HSSFSheet sheet = workbook.getSheetAt(workbook.getNumberOfSheets()-1);
	    Iterator<Row> rowIterator = sheet.iterator();	    
	    	    
	    while (rowIterator.hasNext()) {  	
	        Row row = rowIterator.next();
	        SumElem elem=new SumElem();
	        ArrayList<String> lst=new ArrayList<String>();
	        Iterator<Cell> cellIterator = row.cellIterator();
	        
	        while (cellIterator.hasNext()) {
	            Cell cell = cellIterator.next();		
	            CellType cellType =cell.getCellType() ;
	            
	            
	            switch (cellType) {
	           
	            case STRING:
	                lst.add(cell.getStringCellValue());	                
	                break;
	            case ERROR:
	               // System.out.print("!");
	                //System.out.print("\t");
	                break;
	            }

	        }
	        elem.setSumelem(lst);
	        sumelem.add(elem);
	        //System.out.println("");
	    }
	    
	    

}
	
	

}
