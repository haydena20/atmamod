package hayden.atma_mod.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class PlayerAtma implements IAtma, INBTSerializable<NBTTagCompound>
{
	
	private float currentAtma = 0.0F;
	private float maxAtma = 10000.0F;
	
	@Override
	public float getAtma() 
	{
		return this.currentAtma;
	}

	@Override
	public void addAtma(float amount) 
	{
		this.currentAtma += amount;
	}

	@Override
	public void removeAtma(float amount) 
	{	
		this.currentAtma -= amount;
	}

	@Override
	public void setAtma(float value) 
	{
		this.currentAtma = value;
	}
	
		@Override
	public float getMaxAtma() 
	{
		return this.maxAtma;
	}

	@Override
	public void setMaxAtma(float value) 
	{
		this.maxAtma = value;
	}

	@Override
	public NBTTagCompound serializeNBT() 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("currentAtma", this.currentAtma);
		nbt.setFloat("maxAtma", this.maxAtma);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) 
	{
		this.currentAtma = nbt.getFloat("currentAtma");
		this.maxAtma = nbt.getFloat("maxAtma");
	}


	
}
