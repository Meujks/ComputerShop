
public abstract class Product {

	private String name;
	private String ram;
	private String cpu;
	private String type;
	private int cost;
	private String pathOfImage;
	public abstract String toStringSpec();

	public Product(String name, String ram, String cpu, String type, int cost, String path) {
		this.setName(name);
		this.setRam(ram);
		this.setCpu(cpu);
		this.setType(type);
		this.setCost(cost);
		this.setPathOfImage(path);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRam() {
		return this.ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getCpu() {
		return this.cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getPathOfImage() {
		return this.pathOfImage;
	}

	public void setPathOfImage(String path) {
		this.pathOfImage = path;
	}

	public String toString() {
		return (this.name + " " + this.type + " " + this.cpu + " " + this.ram + " " + this.cost) + " " + this.toStringSpec();
	}

}
