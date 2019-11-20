package hayden.atma_mod.capabilities;

import hayden.atma_mod.messages.MyMessage;
import hayden.atma_mod.proxy.CommonProxy;
import hayden.atma_mod.utils.handlers.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PlayerAtma implements IAtma, INBTSerializable<NBTTagCompound>
{
	
	private float currentAtma = 0.0F;
	private float maxAtma = 10000.0F;
	
	private float atmaEff = 0.0F;
	private float atmaBoost = 0.0F;
	
	@Override
	public float getAtma() 
	{
		return this.currentAtma;
	}

	@Override
	public void addAtma(float amount) 
	{
		this.currentAtma += amount + (amount*(this.atmaBoost/100));
	}

	@Override
	public void removeAtma(float amount) 
	{	
		this.currentAtma -= amount - (amount*(this.atmaEff/100));
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

	
	public static IAtma instanceFor(EntityPlayer player) { return player.getCapability(AtmaProvider.MAX_ATMA, null); }
	
	public void updateClient(EntityPlayer player) 
	{
		if(!player.getEntityWorld().isRemote) 
		{
			PacketHandler.INSTANCE.sendTo(new MyMessage((int)this.getAtma()), (EntityPlayerMP) player);
		}
	}

	@Override
	public float getAtmaEff() 
	{
		return this.atmaEff;
	}

	@Override
	public void setAtmaEff(float amount) 
	{
		this.atmaEff = amount;
	}

	@Override
	public float getAtmaBoost() 
	{
		return this.atmaBoost;
	}

	@Override
	public void setAtmaBoost(float amount) 
	{
		this.atmaBoost = amount;
	}
	
	
}
