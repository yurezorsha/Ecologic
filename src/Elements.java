import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Elements extends JFrame {
	private Company c;
    private CatalogOfElements catalog = CatalogOfElements.getInstance();
	
	Elements(Company c){
		
		this.c=c;
		setTitle("Перечень элементов "+c.getName()+ " ");
		initComponents();	
	    
	}
	
	 private void initComponents() {
            LinkedList<Element> elements=c.getElements();
            Iterator iterator = elements.iterator();
            setSize(300, 500);
	        JLabel l;
	        JTextField t;
	        setLayout(new GridLayout(0,2));
	        while(iterator.hasNext()){
	           Element el=(Element) iterator.next();
	            //System.out.println("Element :" + el.getCode()+" Concentration " +el.getMass());
	            l= new JLabel();
	            Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
	            l.setBorder(border);
	            l.setText((String) el.getName());
	            getContentPane().add(l);
	            t= new JTextField();
	            t.setBorder(border);
	            t.setText(el.getMass().toString());
	            getContentPane().add(t);
	            
	        
	        }
	        
	       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	       
	 }

}
