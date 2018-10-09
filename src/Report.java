import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Report extends JFrame{
  private Company c;

  public Report(Company c) throws HeadlessException {
	super();
	this.c = c;
	initFrame();
  }
  
  private void initFrame(){
	  LinkedList<Element> elements=c.getElements();
      Iterator iterator = elements.iterator();
      setSize(1050, 650);
      JLabel l;
      JLabel t;
      JLabel uz;
      JLabel mpc;
      //setLayout();
      JPanel p1 = new JPanel();
      //p1.setBounds(10, 10, 400, 600);
      p1.setLayout(new GridLayout(0,4));
      p1.add(new JLabel("Элемент "));
      p1.add(new JLabel("Концентрация "));
      p1.add(new JLabel("ПДК "));
      p1.add(new JLabel("Уровень загрязнения "));
      while(iterator.hasNext()){
         Element el=(Element) iterator.next();
         String name="";
          System.out.println("Element :" + el.getCode()+" Concentration " +el.getMass());
          l= new JLabel();
          Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
          l.setBorder(border);
          name=el.getName();
          if(el.isHot()){
          	name=name + " *";
          }
          l.setText(name);
          p1.add(l);
          t= new JLabel();
          t.setBorder(border);
          t.setText(Double.toString(el.getCm()));
          p1.add(t);
          mpc = new JLabel();
          mpc.setBorder(border);
          mpc.setText(Double.toString(el.getMPC()));
          p1.add(mpc);
          uz= new JLabel();
          uz.setBorder(border);
          String uzresult = Double.toString(el.getUz());
          uz.setOpaque(true);
          if (el.getUz()>1){
        	  uzresult=uzresult +" >1";
        	  uz.setBackground(Color.RED);
        	  uz.setText(uzresult);
          } else{
        	  uz.setText(uzresult);
        	  uz.setBackground(null);
          }
          
          p1.add(uz);
          
         
      }
      
      
      getContentPane().add(p1);
      
  }
  

}
