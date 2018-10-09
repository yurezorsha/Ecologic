
public class Element {
	private String code;
	private String name;
	private double MPC;
	private double mass;
	private boolean hot;
	private double cm;
	private static double A=160;
	private double F;
	private static double np=1;
	private double uz;
	
	
	public Element() {
		
	}
	
    public Element(String name) {
    	this.name=name;
		
	}
    
    
	public double getCm() {
		return cm;
	}
	
	

	public double getUz() {
		return uz;
	}

	public void setUz() {
		this.uz = cm/MPC;
	}

	public void setCm(double n, double m, double kF, double h, double delT, double v1, double d) {
		if(hot==true){
			this.cm=A * mass * n * m * kF * np /(Math.pow(h, 2) * Math.pow(v1 * delT,1/3));
		} else{
			this.cm=A * mass * n * kF * np * d / (8 * Math.pow(h, 4/3) * v1); 
		}
	
	}
	
	

	public Element(String code, String name, Double mPC, Double mass,
			boolean hot) {
		super();
		this.code = code;
		this.name = name;
		MPC = mPC;
		this.mass = mass;
		this.hot = hot;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMPC() {
		return MPC;
	}
	public void setMPC(Double mPC) {
		MPC = mPC;
	}
	public Double getMass() {
		return mass;
	}
	public void setMass(Double mass) {
		this.mass = mass;
	}
	public boolean isHot() {
		return hot;
	}
	public void setHot(boolean hot) {
		this.hot = hot;
	}
	
	@Override
	public String toString() {
		
		return code + " : " + name;
	}
	

}
