
public class Desktop extends Product {
	
	private String chassi;
	private String gfx;
	
	public Desktop(String name,String ram,String cpu,String type, String chassi, String gfx){      
		super(name, ram, cpu, type);
		this.chassi = chassi;
		this.gfx = gfx;
	}
	
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getGfx() {
		return gfx;
	}
	public void setGfx(String gfx) {
		this.gfx = gfx;
	}
}
