package Models;

import java.util.LinkedList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import Calculations.Element;

public class ModelResultConcentrationTable extends AbstractTableModel{
	private LinkedList<Element> elements;
	private Vector<String> columnNames;
	

	public ModelResultConcentrationTable(LinkedList<Element> elements) {
 		this.elements = elements;
 		 columnNames = new Vector<String>();
		    columnNames.add("Код" );
			columnNames.add("Элемент" );
			columnNames.add("Концентрация [г/м\u00B3]");
		    columnNames.add("Сми [г/м\u00B3]");
		    columnNames.add("ПДК [г/м\u00B3]");
		    columnNames.add("Уровень загрязнения ");
		    columnNames.add("Вывод");
 		
	}

	@Override
	public int getColumnCount() {	
		return 7;
	}
	
	@Override
	public String getColumnName(int column)
    {
          return columnNames.get(column);
    }

	@Override
	public int getRowCount() {
		return elements.size();
	}

	@Override
	public Object getValueAt(int i, int j) {
		String value;
		if(j == 0)
			value = elements.get(i).getCode();
		else if (j==1) {
				    value = elements.get(i).getName();
			} else if(j==2){
				    value = String.valueOf(elements.get(i).getCm());
			} else if(j==3){
				    value = String.valueOf(elements.get(i).getCmi());
			} else if(j==4){
				    value = String.valueOf(elements.get(i).getMPC()); 
			}
			  else if(j==5){
				    value = String.valueOf(elements.get(i).getUz()); 
			}
			  else{
				    if(elements.get(i).getUz()>1){
				    	value="УЗ > 1";
				    }else{
				    	value="УЗ в норме";
			        }
			}
		
	
		return value;
	}
	

}
