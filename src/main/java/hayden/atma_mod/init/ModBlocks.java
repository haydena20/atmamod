package hayden.atma_mod.init;

import java.util.ArrayList;
import java.util.List;

import hayden.atma_mod.blocks.AdvBlockBase;
import hayden.atma_mod.blocks.AtmaProjector;
import hayden.atma_mod.blocks.BlockBase;
import hayden.atma_mod.blocks.BlockOre;
import hayden.atma_mod.blocks.SolarAccumulator;
import hayden.atma_mod.blocks.tileentities.TileEntityAccumulator;
import hayden.atma_mod.blocks.tileentities.BlockCounter;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	//TileEntities
	public static BlockCounter counter = new BlockCounter();
	public static SolarAccumulator accumulator = new SolarAccumulator();
	
	//Machines
//	public static final SolarAccumulator SOLAR_ACCUMULATOR = new SolarAccumulator("solar_accumulator");
//	public static final BlockBase SOLAR_ACCUMULATOR = new BlockBase("solar_accumulator", Material.ROCK);
//	public static final BlockBase CRUDE_PROJECTOR = new BlockBase("CRUDE_PROJECTOR", Material.ROCK);
	public static final AtmaProjector CRUDE_PROJECTOR = new AtmaProjector("CRUDE_PROJECTOR", Material.ROCK);
	
	//Ores
	public static final BlockOre orePyrite = new BlockOre("pyrite_ore", Material.ROCK, SoundType.STONE, 3F, 1F, "pickaxe", 2, 0F, 0, CreativeTabs.DECORATIONS, ModItems.PYRITESHARDS, 1, 5);
	public static final BlockOre oreQuartz = new BlockOre("overworldquartz", Material.ROCK, SoundType.STONE, 3F, 1F, "pickaxe", 2, 0F, 0, CreativeTabs.DECORATIONS, Items.QUARTZ, 1, 2);
	
	//Building blocks
	public static final AdvBlockBase CARMINE_BRICKS = new AdvBlockBase("carmine_bricks", Material.ROCK, SoundType.STONE, 1F, 2F, "pickaxe", 0, 0F, 0, CreativeTabs.BUILDING_BLOCKS);
	public static final AdvBlockBase PYR_BLOCK = new AdvBlockBase("pyr_block", Material.GLASS, SoundType.GLASS, 3F, 2F, "pickaxe", 0, 0.2F, 0, CreativeTabs.BUILDING_BLOCKS);
	
	public static final AdvBlockBase PAPER_BLOCK = new AdvBlockBase("paper_block", Material.CLOTH, SoundType.CLOTH, 0.5F, 0F, "pickaxe", 0, 0F, 0, CreativeTabs.BUILDING_BLOCKS);
	
	//public static final AdvBlockBase POWER_BLOCK = new AdvBlockBase("power_block", Material.IRON, SoundType.METAL, 25F, 50F, "pickaxe", 3, 16F, 1, CreativeTabs.MATERIALS);

	

	
	public static void register(/*IForgeRegistry<Block> registry*/) 
	{
//		registry.registerAll(counter, accumulator);
		
		GameRegistry.registerTileEntity(accumulator.getTileEntityClass(), accumulator.getRegistryName().toString());
		GameRegistry.registerTileEntity(counter.getTileEntityClass(), counter.getRegistryName().toString());
	}
}
