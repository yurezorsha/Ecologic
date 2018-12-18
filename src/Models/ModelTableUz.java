package Models;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ModelTableUz extends AbstractTableModel{
	ArrayList<String> lst;
	private Vector<String> columnNames;

	public ModelTableUz(ArrayList<String> lst) {
		this.lst = lst;
		columnNames = new Vector<String>();
	    columnNames.add("Вещества обладающие эффектом суммации:" );
	    columnNames.add("Вывод:" );
	}

	@Override
	public int getColumnCount() {
		
		return 2;
	}
	
	@Override
	public String getColumnName(int column)
    {
          return columnNames.get(column);
    }

	@Override
	public int getRowCount() {
		return lst.size();
	}

	@Override
	public Object getValueAt(int i, int j) {
		if(j==0){
		return lst.get(i);
		}
		else{
			if(lst.get(i).contains(">")){
			        return "Сумма элементов превышает норму!";
			}else{
				    return "Показатели в норме";
			}
		}
	}

}
