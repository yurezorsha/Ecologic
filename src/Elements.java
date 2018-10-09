import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class Elements extends JFrame {
	private Company c;
    private CatalogOfElements catalog = CatalogOfElements.getInstance();
    JPanel p1;
    JPanel p2;
	Elements(Company c){
		
		this.c=c;
		setTitle("Перечень элементов "+c.getName()+ " ");
		initComponents();	
	    
	}
	
	 private void initComponents() {
            LinkedList<Element> elements=c.getElements();
            Iterator iterator = elements.iterator();
            setSize(1050, 650);
	        JLabel l;
	        JTextField t;
	        
	        setLayout(null);
	        p1=new JPanel();
	        p1.setBounds(10, 10, 400, 600);
	        p1.setLayout(new GridLayout(0,2));
	        
	        while(iterator.hasNext()){
	           Element el=(Element) iterator.next();
	           String name="";
	            //System.out.println("Element :" + el.getCode()+" Concentration " +el.getMass());
	            l= new JLabel();
	            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	            l.setBorder(border);
	            name=el.getName();
	            if(el.isHot()){
	            	name=name + " *";
	            }
	            l.setText(name);
	            p1.add(l);
	            t= new JTextField();
	            t.setBorder(border);
	            t.setText(el.getMass().toString());
	            p1.add(t);
	            
	           
	        }
	        
	        p2 = panel2();
	        //System.out.println(getListofElements(p1,elements).toString());
	        getContentPane().add(p1);
	        getContentPane().add(p2);
	        
	        
	       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	       
	 }
	 
	 private LinkedList<Element> getListofElements(JPanel p, LinkedList<Element> elements){
		 
		 LinkedList<Double> mas=new LinkedList<Double>(); 
		 Component[] c=p.getComponents();
		 int size=c.length;
		 for(int i = 0; i<size;i++){
			 if(c[i] instanceof JTextField){
				 JTextField f=(JTextField) c[i];
				 mas.add( Double.parseDouble(f.getText()));	 
			 }
			  
			 
		 }
		 Iterator iterator = elements.iterator();
		 int i=0;
		 while(iterator.hasNext() ){
			 Element el=(Element) iterator.next();
			 el.setMass(mas.get(i));			 
			 elements.set(i, el);
			 i++;

		 }
		 
		 return elements;
		 
		 
	 }
	 
	 
	 private JPanel panel2(){
		 JPanel p =new JPanel();
		 p.setBounds(415, 10, 600, 200);
		 p.setLayout(new GridLayout(0,2));
		// p.setSize(new Dimension(100, 200));
		 JLabel lh =new JLabel("Высота источника выброса H: ");
		 JTextField h =new JTextField();
		 p.add(lh); p.add(h);
		 JLabel ld =new JLabel("Диаметр источника выброса D: ");
		 JTextField d =new JTextField();
		 p.add(ld); p.add(d);
		 JLabel lv1 =new JLabel("Объемный расход выбрасываемой смеси V1: ");
		 JTextField v1 =new JTextField();
		 p.add(lv1); p.add(v1);
		 JLabel ltg =new JLabel("Температура газовоздушной смеси Tг: ");
		 JTextField tg =new JTextField();
		 p.add(ltg); p.add(tg);
		 JLabel ln3 =new JLabel("n3: ");
		 JTextField n3 =new JTextField();
		 p.add(ln3); p.add(n3);
		 JLabel larea =new JLabel("Область : ");
		 String[] areas = { "Брестская", "Витебская", "Гродненская", "Гомельская", "Минская", "Могилевская" };
         p.add(larea); 
	
		 JComboBox areasList = new JComboBox(areas);
		 areasList.setSelectedIndex(0);
		 p.add(areasList);
		 JLabel lTv =new JLabel("Температура воздуха Тв: ");
		 JTextField tv =new JTextField();
		 tv.setEditable(false);
		 p.add(lTv); p.add(tv);
		 JLabel lUm =new JLabel("Максимальная скорость ветра Uм: ");
		 JTextField um =new JTextField();
		 um.setEditable(false);
		 p.add(lUm); p.add(um);
		 JLabel lU =new JLabel("Среднегодовая скорость ветра U: ");
		 JTextField u =new JTextField();
		 u.setEditable(false);
		 p.add(lU); p.add(u);
		 
		 areasList.addItemListener(new ItemListener() {
		       
				@Override
				public void itemStateChanged(ItemEvent arg0) {
				  int area=areasList.getSelectedIndex();
				  
				  switch (area) {
				case 0:
					tv.setText("24");
					um.setText("2.5");
					u.setText("22");
					
					break;
                case 1:
                	tv.setText("23");
					um.setText("2");
					u.setText("23");
					
					break;
                case 2:
                	tv.setText("24");
					um.setText("2.4");
					u.setText("24");
					
					break;
                case 3:
                	tv.setText("25");
					um.setText("1.9");
					u.setText("17");
					
					break;
                case 4:
                	tv.setText("23");
					um.setText("2.3");
					u.setText("24");
					
					break;
                case 5:
                	tv.setText("24");
					um.setText("2.6");
					u.setText("24");
					
					break;


					

				default:
					
					break;
				} 
					
				}
		    });
		 
		JButton set =new JButton("Рассчитать");
		set.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Click");
				c.setElements(getListofElements(p1, c.getElements()));
				getParameters(p);
				calculateConcentration();
        

				Report fr  = new Report(c); 
		        SwingUtilities.invokeLater(new Runnable() {
		           public void run() {
		              fr.setVisible(true);
		           }
		        });	
				
				
			}
		});
		 
		p.add(set);
		
         return p;
		 	 
	 }
	 
	 private void getParameters(JPanel p){
			 LinkedList<Double> mas=new LinkedList<Double>(); 
			 Component[] c=p.getComponents();
			 int size=c.length;
			 for(int i = 0; i<size;i++){
				 if(c[i] instanceof JTextField){
					 JTextField f=(JTextField) c[i];
					 mas.add( Double.parseDouble(f.getText()));	 
				 }			 
			 }
			 this.c.setH(mas.get(0));
			 this.c.setD(mas.get(1));
			 this.c.setV1(mas.get(2));
			 this.c.setTg(mas.get(3));
			 this.c.setN3(mas.get(4)); 	
			 this.c.setTv(mas.get(5));
			 this.c.setUm(mas.get(6));
			 this.c.setU(mas.get(7));
		 
	 }
	 
	 private void calculateConcentration(){
		 c.setW0();
			c.setDelt();
			c.setF();
			c.setVm();
			c.setVm1();
			c.setFe();
			c.setM();
			c.setkF();
			System.out.println("W0: " + c.getW0()); 
			System.out.println("delT: " +c.getDelt());
			System.out.println(" Vm: " +c.getVm());
			System.out.println(" fe: " + c.getFe());
			System.out.println(" m: " + c.getM());
			
			LinkedList<Element> elements=c.getElements();
         Iterator iterator = elements.iterator();
         int i=0;
         while(iterator.hasNext()){
			 Element el=(Element) iterator.next();
			 double n = c.getN(el.isHot());
			 el.setCm(n, c.getM(), c.getkF(), c.getH(), c.getDelt(), c.getV1(), c.getD());
			 el.setUz();
			 elements.set(i, el);
		     i++;
         }
         
		 
	 }
	 
	 

}
