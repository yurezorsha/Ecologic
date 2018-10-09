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
   private double w0;
   private double delt; 
   
   private double f;
   private double fe;
   private double vm;
   private double vm1;
   private double m;
   private double n;
   private double kF; 		
	



	public Company(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}
	
	public double getW0() {		
		return w0;
	}


	public void setW0() {
		this.w0 = 4*v1/(Math.PI*Math.pow(d, 2));
	}
	
	public double getDelt() {
		return delt;
	}


	public void setDelt() {
		this.delt = tg-tv;
	}
	
	
	public double getF() {
		return f;
	}

	public void setF() {
		this.f = (1000*Math.pow(w0, 2)*d)/(Math.pow(h, 2)*delt);
	}

	public double getFe() {
		return fe;
	}

	public void setFe() {
		this.fe = 800*Math.pow(vm1, 3);
	}

	public double getVm() {
		return vm;
	}

	public void setVm() {
		this.vm = 0.65*Math.pow(v1*delt/h, 1/3);
	}

	public double getVm1() {
		return vm1;
	}

	public void setVm1() {
		this.vm1 = 1.3*w0*d/h;
	}
	
	

	public double getM() {
		return m;
	}

	public void setM() {
		this.m = 1/(0.67+0.1*Math.pow(f, 0.5)+0.34*Math.pow(f, 1/3));
	}
	
	

	public double getN(boolean hot) {
		if(hot==true){
			if(vm>=2){
				n = 1; 
			} else{
				if(vm>=0.5 && vm<2){
					 n = 0.532*Math.pow(vm, 2)-2.13*vm+3.13;
				} else{
					   n = 4.4*vm;
				}
				
			}
		}else{
			if(vm1 >= 2){
				n = 1;
			}else{
				if(vm1 >= 0.5 && vm1 < 2){
					n = 0.532*Math.pow(vm1, 2) - 2.13 * vm1 +3.13;
				}else{
					   n = 4.4 * vm1;
				}
			}
		}
		
		return n;
	}
	
	

	
	public double getkF() {
		return kF;
	}

	public void setkF() {
		if(n3>=0.9){
			this.kF=2;
		}else{
			if(n3>0.75 && n3<0.9){
				this.kF=2.5;
			}else{
				this.kF=3;
			}
			
		}
	
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
