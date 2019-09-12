package hayden.atma_mod.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class PlayerAtma implements IAtma, INBTSerializable<NBTTagCompound>
{
	
	private int currentAtma;
	private int maxAtma;
	
	public PlayerAtma(int maxAtma)
	{
		this.currentAtma = 0;
		this.maxAtma = maxAtma;
	}
	
	@Override
	public int getAtma() 
	{
		return this.currentAtma;
	}

	@Override
	public void addAtma(int amount) 
	{
		this.currentAtma += amount;
	}

	@Override
	public void removeAtma(int amount) 
	{
		this.currentAtma -= amount;
	}

	@Override
	public void setAtma(int value) 
	{
		this.currentAtma = value;
	}
	
	@Override
	public NBTTagCompound serializeNBT() 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("currentAtma", this.currentAtma);
		nbt.setInteger("maxAtma", this.maxAtma);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) 
	{
		this.currentAtma = nbt.getInteger("currentAtma");
		this.maxAtma = nbt.getInteger("maxAtma");
	}

	
}
