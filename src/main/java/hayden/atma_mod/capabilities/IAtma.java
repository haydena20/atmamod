package hayden.atma_mod.capabilities;

public interface IAtma 
{
	public float getAtma();
	
	public void addAtma(float amount);
	public void removeAtma(float amount);
	public void setAtma(float value);
	
	public float getMaxAtma();
	
	public void setMaxAtma(float value);
}
