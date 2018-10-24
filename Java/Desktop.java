
public class Desktop extends Product {
	
	private String chassi;
	private String gpu;
	
	public Desktop(String name,String ram,String cpu,String type, String chassi, String gpu,int cost){      
		super(name, ram, cpu, type,cost);
		this.setChassi(chassi);
		this.setGpu(gpu);
	}
	
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	public String toStringSpec()
	{
		return (this.gpu + " " + this.chassi);
	} 
}
