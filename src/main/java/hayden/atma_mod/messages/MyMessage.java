package hayden.atma_mod.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MyMessage implements IMessage
{
	// A default constructor is always required
	public MyMessage(){}
	
	private int toSend;
	public MyMessage(int toSend) 
	{
		this.toSend = toSend;
	}
	
	@Override public void toBytes(ByteBuf buf) 
	{
		// Writes the int into the buf
		buf.writeInt(toSend);
	}
	
	@Override public void fromBytes(ByteBuf buf) 
	{
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
		toSend = buf.readInt();
	}
	
	
	/* Packet Handler */
	public static class MessageHandler implements IMessageHandler<MyMessage, IMessage>
	{
		//Do note that the default constructor is required, but implicitly defined in this case

		@Override public IMessage onMessage(MyMessage message, MessageContext ctx) 
			{
//			 This is the player the packet was sent to the server from
//			EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
			// The value that was sent
			int amount = message.toSend;
			 
			// Execute the action on the main server thread by adding it as a scheduled task
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(()-> 
			{
//				ctx.getServerHandler().player.inventory.addItemStackToInventory(new ItemStack(Items.DIAMOND, amount));
				System.out.println("yuh yeet");
			});

							 
			// No response packet
			return null;
			}
	}
}

