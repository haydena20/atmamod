package hayden.atma_mod.capabilities;

import hayden.atma_mod.capabilities.PlayerAtma.MessageUpdateClientAtma;
import hayden.atma_mod.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public interface IAtma 
{
	public float getAtma();
	
	public void addAtma(float amount);
	public void removeAtma(float amount);
	public void setAtma(float value);
	
	public float getMaxAtma();
	
	public void setMaxAtma(float value);
	
	public void updateClient(EntityPlayer player);
}
