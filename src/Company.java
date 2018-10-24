import java.util.ArrayList;
import java.util.LinkedList;


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
   private double xmhot;
   private double xmcold;
   private double udhot;
   private double udcold;
   private double rhot;
   private double rcold;
   private double xmihot;
   private double xmicold;


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
			if(e.getCode().equals(Code)){
				tmpel=e;
				break;
			}
		}
		return tmpel;
		
	}
	
	public ArrayList<String> checkSum1(){
		ArrayList<String> names=new ArrayList<String>();
		Element e1 = getElementByCode("0301");
		Element e2 = getElementByCode("0330");
		if(e1!=null && e2!=null){

			names.add(e1.getCode());
			names.add(e2.getCode());
			
		}
		return names;
	}
	
	public ArrayList<String> checkSum2(){
		ArrayList<String> names=new ArrayList<String>();
		Element e1 = getElementByCode("0330");
		Element e2 = getElementByCode("0184");
		if(e1!=null && e2!=null){
			names.add(e1.getCode());
			names.add(e2.getCode());
			
		}
		return names;
	}
	
	public ArrayList<String> checkSum3(){
		ArrayList<String> names=new ArrayList<String>();
		Element e1 = getElementByCode("0342");
		Element e2 = getElementByCode("0330");
		if(e1!=null && e2!=null){
			names.add(e1.getCode());
			names.add(e2.getCode());
			
		}
		return names;
	}
	
	public ArrayList<String> checkSum4(){
		ArrayList<String> names=new ArrayList<String>();
		Element e1 = getElementByCode("0337");
		Element e2 = getElementByCode("2908");
		if(e1!=null && e2!=null){
			names.add(e1.getCode());
			names.add(e2.getCode());
			
		}
		return names;
	}
	
	public ArrayList<String> checkSum5(){
		ArrayList<String> names=new ArrayList<String>();
		Element e1 = getElementByCode("0301");
		Element e2 = getElementByCode("0330");
		Element e3 = getElementByCode("0303");
		Element e4 = getElementByCode("0304");
		if(e1!=null && e2!=null && e3!=null && e4!=null){
			names.add(e1.getCode());
			names.add(e2.getCode());
			names.add(e3.getCode());
			names.add(e4.getCode());

			
		}
		return names;
	}
	
	public double calcd(boolean h){
		double d=0;
		if(h){
			if(vm<=0.5){
				d =2.48*(1+0.28*Math.pow(fe, 1/3));
			}else if( vm >0.5 && vm <= 2){
				    d = 4.95*vm *(1+0.28*Math.pow(fe, 1/3));
			    } else 
			    	d = 7*Math.sqrt(vm)* (1+0.28*Math.pow(fe, 1/3));
			      
		} else{
			if(vm<=0.5){
				d =5.7;
			}else if( vm >0.5 && vm <= 2){
				    d = 11.4*vm;
			    } else 
			    	d = 16*Math.sqrt(vm);

			
		}
		
		return d;
		
	}
	
	


	public double getXmhot() {
		return xmhot;
	}

	public void setXmhot() {
		this.xmhot=((5-kF)*calcd(true)*h)/4;
	}

	public double getXmcold() {
		return xmcold;
	}

	public void setXmcold() {
		this.xmcold =((5-kF)*calcd(false)*h)/4;
	}
	
	public double calcUd(boolean h){
		double um=0;
		if(h){
			if(vm<=0.5){
				um = 0.5;
			}else if( vm >0.5 && vm <= 2){
				    um = vm;
			    } else 
			    	   um = vm*(1+0.12*Math.sqrt(f));
			      
		} else{
			if(vm1<=0.5){
				um = 0.5;
			}else if( vm1 >0.5 && vm1 <= 2){
				    um = vm1;
			    } else 
			    	   um = 2.2* Math.sqrt(vm1);

			
		}
		
		return um;
		
	}
	

	public double getUdhot() {
		return udhot;
	}

	public void setUdhot() {
		this.udhot = calcUd(true);
	}

	public double getUdcold() {
		return udcold;
	}

	public void setUdcold() {
		this.udcold = calcUd(false);
	}
	
	
	public double calcr(boolean h){
		double r = 0;
		if(h){
			if( u/udhot<=1)
				r = 0.67*(u/udhot)+1.67*Math.pow(u/udhot, 2)- 1.34*Math.pow(u/udhot, 3);
			else
				r = (3*u/udhot)/(2*Math.pow(u/udhot, 2)-(u/udhot)+2);
		    }else{
		    	if( u/udcold<=1)
					r = 0.67*(u/udcold)+1.67*Math.pow(u/udcold, 2)- 1.34*Math.pow(u/udcold, 3);
				else
					r = (3*u/udcold)/(2*Math.pow(u/udcold, 2)-(u/udcold)+2);
			 
			
		}
		
		return r;
		
	}
	
	

	public double getRhot() {
		return rhot;
	}

	public void setRhot() {
		this.rhot = calcr(true);
	}

	public double getRcold() {
		return rcold;
	}

	public void setRcold() {
		this.rcold = calcr(false);
	}
	
	
	public double calcp(boolean h){
		double p=0;
		if(h){
			if (u/udhot<=0.25)
				p = 3;
			  else if ( (u/udhot>0.25) && (u/udhot)<=1)
				   p = 8.43*Math.pow(1-u/udhot,5) +1;
			    else
			    	 p = 0.32*(u/udhot) + 0.68;
			
		} else{
			if (u/udcold<=0.25)
				p = 3;
			  else if ( (u/udcold>0.25) && (u/udcold)<=1)
				   p = 8.43*Math.pow(1-u/udcold,5) +1;
			    else
			    	 p = 0.32*(u/udcold) + 0.68;
		}
		
		return p;
		
	}
	
	
	
	

	public double getXmihot() {
		return xmihot;
	}

	public void setXmihot() {
		this.xmihot = xmhot*calcp(true);
	}

	public double getXmicold() {
		return xmicold;
	}

	public void setXmicold() {
		this.xmicold = xmcold *calcp(false);
	}

	@Override
	public String toString() {
				return name;
	}
    
	
}
