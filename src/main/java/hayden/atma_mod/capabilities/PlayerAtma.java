package hayden.atma_mod.capabilities;

import hayden.atma_mod.proxy.CommonProxy;
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
	private boolean needsUpdate = true;
	
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

	
	public static IAtma instanceFor(EntityPlayer player) { return player.getCapability(AtmaProvider.MAX_ATMA, null); }
	
	public void updateClient(EntityPlayer player) 
	{
		if(!player.getEntityWorld().isRemote) 
		{
			if(needsUpdate) CommonProxy.network.sendTo(new MessageUpdateClientAtma(this), (EntityPlayerMP)player);
			needsUpdate = false;
		}
	}
	
	/** Packet to update client with **/
	public static class MessageUpdateClientAtma implements IMessage 
	{

		private float mana, maxMana, manaRegen;

		public MessageUpdateClientAtma() {}
		public MessageUpdateClientAtma(PlayerAtma mh) 
		{ 
			this.mana = mh.getAtma();
			this.maxMana = mh.getMaxAtma();
		}

		public void fromBytes(ByteBuf buf) 
		{
			mana = buf.readFloat();
			maxMana = buf.readFloat();
		}

		public void toBytes(ByteBuf buf) 
		{
			buf.writeFloat(mana);
			buf.writeFloat(maxMana);
		}

		public static class Handler implements IMessageHandler<MessageUpdateClientAtma, IMessage> {
			public IMessage onMessage(MessageUpdateClientAtma message, MessageContext ctx) {
				Minecraft.getMinecraft().addScheduledTask(() -> {
					EntityPlayer player = Minecraft.getMinecraft().player;
					IAtma mh = PlayerAtma.instanceFor(player);

					mh.setAtma(message.mana);
					mh.setMaxAtma(message.maxMana);
					System.out.println("Client: "+message.mana+","+message.maxMana);
				});
				return null;
			}
		}

	}
	
}
