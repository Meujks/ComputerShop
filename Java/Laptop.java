
public class Laptop extends Product {

	private String inches;
	private String mgpu;
	private String lChassi;

	public Laptop(String name, String type, String lChassi, String mgpu, String cpu, String ram, int cost, String path,
			String inches) {
		super(name, ram, cpu, type, cost, path);
		this.setInches(inches);
		this.setMgpu(mgpu);
		this.setlChassi(lChassi);
	}

	public String getInches() {
		return this.inches;
	}

	public void setInches(String inches) {
		this.inches = inches;
	}

	public String getMgpu() {
		return this.mgpu;
	}

	public void setMgpu(String mgpu) {
		this.mgpu = mgpu;
	}

	public String toStringSpec() {
		return (this.mgpu + " " + this.inches);
	}

	public String getlChassi() {
		return this.lChassi;
	}

	public void setlChassi(String lChassi) {
		this.lChassi = lChassi;
	}

}
