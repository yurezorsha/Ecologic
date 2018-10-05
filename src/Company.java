import java.util.HashMap;

import java.util.LinkedList;
import java.util.Map;


public class Company {
   private String name;
   private int number;
   private LinkedList<Element> elements;
   private double h;
   private double d;
   private double v1;
   private double tg;
   private double n3;
   private double tv;
   private double um;
   private double u;
    
    
    
    		
	public Company(String name, int number) {
		super();
		
		
		this.name = name;
		this.number = number;
	}
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public LinkedList<Element> getElements() {
		return elements;
	}


	public void setElements(LinkedList<Element> elements) {
		this.elements = elements;
	}
	
	
    
	public double getH() {
		return h;
	}


	public void setH(double h) {
		this.h = h;
	}


	public double getD() {
		return d;
	}


	public void setD(double d) {
		this.d = d;
	}


	public double getV1() {
		return v1;
	}


	public void setV1(double v1) {
		this.v1 = v1;
	}


	public double getTg() {
		return tg;
	}


	public void setTg(double tg) {
		this.tg = tg;
	}


	public double getN3() {
		return n3;
	}


	public void setN3(double n3) {
		this.n3 = n3;
	}


	public double getTv() {
		return tv;
	}


	public void setTv(double tv) {
		this.tv = tv;
	}


	public double getUm() {
		return um;
	}


	public void setUm(double um) {
		this.um = um;
	}


	public double getU() {
		return u;
	}


	public void setU(double u) {
		this.u = u;
	}


	public Element getElementByCode(String Code){
		Element tmpel=null;
		for (Element e:elements) {
			if(e.getName().equals(Code)){
				tmpel=e;
				break;
			}
		}
		return tmpel;
		
	}

	@Override
	public String toString() {
				return name;
	}
    
	
}
