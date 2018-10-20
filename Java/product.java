
public abstract class Product {
	
	private String name;
	private String ram;
	private String cpu;
	private String type;
	
	public Product(String name,String ram,String cpu,String type)
	{
		this.setName(name);
		this.setRam(ram);
		this.setCpu(cpu);
		this.setType(type);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
