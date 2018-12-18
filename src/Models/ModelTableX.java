package Models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import Calculations.Company;

public class ModelTableX extends AbstractTableModel{
	Company c;
	private Vector<String> columnNames;

	public ModelTableX(Company c) {
		this.c = c;
		columnNames = new Vector<String>();
	    columnNames.add("Горячие выбросы [м]" );
	    columnNames.add("Холодные выбросы [м]" );
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
		return 2;
	}

	@Override
	public Object getValueAt(int i, int j) {
		String value = "";
		if(j==0){
			if(i==0)
				value = "Xм = " + String.valueOf(c.getXmhot()); 
			else if(i==1)
				value = "Xми = " + String.valueOf(c.getXmihot()); 
			
		}
		if(j==1){
			if(i==0)
				value = "Xм = " + String.valueOf(c.getXmcold());
				
			else if(i==1)
				value = "Xми = " + String.valueOf(c.getXmicold()); 
		}
		return value;
	}

}
