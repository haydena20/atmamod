package hayden.atma_mod.utils;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import hayden.atma_mod.gui.CreativeTab;


public class Reference
{
    public static final String MODID = "atma_mod";
    public static final String NAME = "Atma";
    public static final String VERSION = "0.1";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final String CLIENT_PROXY_CLASS = "hayden.atma_mod.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "hayden.atma_mod.proxy.CommonProxy";
}
