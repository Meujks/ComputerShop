
public class ComponentServer extends Product {
	
	String formFactor;
	String scalability;
	String chassi;
	
	
	public ComponentServer(String name, String type, String formFactor, String scalability,String sChassi, String cpu, String ram, int cost, String path){      
		super(name, ram, cpu, type,cost,path);
		this.setFormFactor(formFactor);
		this.setScalabilty(scalability);
		this.setChassi(sChassi);

	}

	public void setChassi(String value)
	{
		this.chassi = value;
	}
	public String getChassi()
	{
		return this.chassi;
	}
	public void setScalabilty(String value)
	{
		this.scalability = value;
	}
	public String getScalability()
	{
		return this.scalability;
	}
	
	public void setFormFactor(String value)
	{
		this.formFactor = value;
	}
	public String getFormFactor()
	{
		return this.formFactor;
	}


	public String toStringSpec()
	{
		return (this.formFactor + " " + this.scalability);
	}


}
