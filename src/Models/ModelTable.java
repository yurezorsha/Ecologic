package Models;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import Calculations.Element;
import Calculations.XY;
import DataFromExcel.CatalogOfElements;

public class ModelTable extends AbstractTableModel{

	 private LinkedList<Element> elem;
	 private XY xy;
	

	private Vector<String> columnNames;

	public ModelTable(LinkedList<Element> elem, XY xy) {
		
		this.elem=elem;
		this.xy=xy;
		    columnNames = new Vector<String>();
		    columnNames.add("Код" );
		    columnNames.add("Название элемента " );
			columnNames.add("C ("+ xy +") [г/с\u00B3]" );
			columnNames.add("Cp ("+ xy +") [г/с\u00B3]");
		    columnNames.add("ПДКcc [г/с\u00B3]");
		    columnNames.add("Вывод ");
		
			}

	@Override
	public int getColumnCount() {
		return 6;
	}
		
	public LinkedList<Element> getElem() {
		return elem;
	}

	public void setElem(LinkedList<Element> elem) {
		this.elem = elem;
	}

	public XY getXy() {
		return xy;
	}

	public void setXy(XY xy) {
		this.xy = xy;
	}

	@Override
	public String getColumnName(int column)
    {
          return columnNames.get(column);
    }

	@Override
	public int getRowCount() {
		return elem.size();
	}

	@Override
	public Object getValueAt(int i, int j) {
		String value;
		if(j == 0){
			value = elem.get(i).getCode();
		}
		else if (j==1) {
			value = CatalogOfElements.getInstance().getElementByCode(elem.get(i).getCode()).getName();
		}
		
		else if (j==2) {
				value = String.valueOf(elem.get(i).getC());
			} else if(j==3){
				    value = String.valueOf(elem.get(i).getCp());
			} else if(j==4){
				 value = String.valueOf(elem.get(i).getCcc());
			} else{
				if(elem.get(i).getCp()<=elem.get(i).getCcc()){
				  value = "Концентрация в норме";
				}else{
					value ="Cp > ПДКсс";
				}
			}
		
	
		return value;
	}

}
