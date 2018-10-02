
public class Element {
	private String code;
	private String name;
	private Double MPC;
	private Double mass;
	private boolean hot;
	
	
	public Element() {
		
	}
	
    public Element(String name) {
    	this.name=name;
		
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
