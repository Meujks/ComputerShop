
public class Desktop extends Product {

	private String chassi;
	private String gpu;

	public Desktop(String name, String ram, String cpu, String type, String chassi, String gpu, int cost, String path) {
		super(name, ram, cpu, type, cost, path);
		this.setChassi(chassi);
		this.setGpu(gpu);
	}

	public String getChassi() {
		return this.chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getGpu() {
		return this.gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String toStringSpec() {
		return (this.gpu + " " + this.chassi);
	}
}
