package hayden.atma_mod.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class CooldownBaubles implements ICooldown, INBTSerializable<NBTTagCompound>
{

	private float ticks = 0;
	private float maxTicks = 1;
	
	
	@Override
	public float getTicks() 
	{
		return this.ticks;
	}

	@Override
	public void addTicks() 
	{
		this.ticks++;
	}

	@Override
	public void setTicks(float value) 
	{
		this.ticks = value;
	}

	@Override
	public void setMaxTicks(float value) 
	{
		this.maxTicks = value;
	}

	@Override
	public float getMaxTicks() 
	{
		return this.maxTicks;
	}

	@Override
	public NBTTagCompound serializeNBT() 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("currentTicks", this.ticks);
		nbt.setFloat("maxTicks", this.maxTicks);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) 
	{
		this.ticks = nbt.getFloat("currentTicks");
		this.maxTicks = nbt.getFloat("maxTicks");
	}
	
}
