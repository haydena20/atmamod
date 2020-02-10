package hayden.atma_mod;


import hayden.atma_mod.gui.CreativeTab;
import hayden.atma_mod.init.ModBlocks;
import hayden.atma_mod.init.ModCapabilities;
import hayden.atma_mod.init.ModRecipes;
import hayden.atma_mod.proxy.CommonProxy;
import hayden.atma_mod.utils.Reference;
import hayden.atma_mod.utils.handlers.CapabilityHandler;
import hayden.atma_mod.utils.handlers.Events;
import hayden.atma_mod.utils.handlers.GuiRenderHandler;
import hayden.atma_mod.utils.handlers.PacketHandler;
import hayden.atma_mod.utils.handlers.RegistryHandler;
import hayden.atma_mod.world.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.discovery.ModCandidate;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main 
{
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
    
    public static final CreativeTab creativeTab = new CreativeTab();
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new Events());
    	
    	MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    	ModCapabilities.registerCapabilities();
    	
    	PacketHandler.init();
    	
    	proxy.registerRenderers();
    	
    	ModBlocks.register();
    	
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
    }    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	ModRecipes.init();
    	
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new GuiRenderHandler());
    }
    
//    @EventHandler
//    public void serverInt(FMLServerStartingEvent event)
//    {
//    	RegistryHandler.serverRegistries(event);
//    }

}
