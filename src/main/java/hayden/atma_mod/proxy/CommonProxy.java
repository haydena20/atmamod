package hayden.atma_mod.proxy;

//import hayden.atma_mod.capabilities.PlayerAtma.MessageUpdateClientAtma;
import hayden.atma_mod.items.ItemBase;
import hayden.atma_mod.utils.Reference;
import hayden.atma_mod.utils.handlers.PacketHandler;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


public class CommonProxy 
{
	
	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		
	}
	
	public String localize(String unlocalized, Object... args) 
	{
		return I18n.translateToLocalFormatted(unlocalized, args);
	}
	
	private void registerNetworkPackets() 
	{
		PacketHandler.init();
	}
	
	public void registerRenderers() {}
}
