package hayden.atma_mod.init;

import java.util.ArrayList;
import java.util.List;

import hayden.atma_mod.blocks.AdvBlockBase;
import hayden.atma_mod.blocks.BlockBase;
import hayden.atma_mod.blocks.SolarAccumulator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final SolarAccumulator SOLAR_ACCUMULATOR = new SolarAccumulator("solar_accumulator");
	
	public static final AdvBlockBase PYRITE_ORE = new AdvBlockBase("pyrite_ore", Material.ROCK, SoundType.STONE, 3F, 1F, "pickaxe", 2, 0F, 0, CreativeTabs.DECORATIONS, ModItems.PYRITESHARDS, 1, 5);
	
	public static final AdvBlockBase PAPER_BLOCK = new AdvBlockBase("paper_block", Material.CLOTH, SoundType.CLOTH, 0.5F, 0F, "pickaxe", 0, 0F, 0, CreativeTabs.BUILDING_BLOCKS);
	
	//public static final AdvBlockBase POWER_BLOCK = new AdvBlockBase("power_block", Material.IRON, SoundType.METAL, 25F, 50F, "pickaxe", 3, 16F, 1, CreativeTabs.MATERIALS);
}
