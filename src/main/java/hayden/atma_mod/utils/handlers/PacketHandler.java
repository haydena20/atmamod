package hayden.atma_mod.utils.handlers;

import hayden.atma_mod.messages.MyMessage;
import hayden.atma_mod.messages.MyMessage.MessageHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler 
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("atmamod");
	
	public static void init()
	{
		int discrim = 0;
		INSTANCE.registerMessage(MessageHandler.class, MyMessage.class, discrim++, Side.CLIENT);
	}
}
