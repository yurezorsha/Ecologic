import java.util.HashMap;

import java.util.LinkedList;
import java.util.Map;


public class Company {
   private String name;
   private int number;
   private LinkedList<Element> elements;
    
    
    
    		
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
