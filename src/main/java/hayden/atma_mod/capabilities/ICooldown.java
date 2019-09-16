package hayden.atma_mod.capabilities;

public interface ICooldown 
{
	public float getTicks();
	public float getMaxTicks();
	
	public void addTicks();
	
	public void setTicks(float value);
	public void setMaxTicks(float value);
}
