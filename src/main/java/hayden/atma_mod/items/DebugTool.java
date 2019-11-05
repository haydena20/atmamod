package hayden.atma_mod.items;

import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.CooldownBaubleProvider;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.capabilities.ICooldown;
import hayden.atma_mod.utils.handlers.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class DebugTool extends ItemBase
{

	public DebugTool(String name) 
	{
		super(name);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		IAtma atma = playerIn.getCapability(AtmaProvider.MAX_ATMA, null);
		ICooldown cd = playerIn.getCapability(CooldownBaubleProvider.COOLDOWN, null);
		ItemStack item = playerIn.getHeldItem(handIn);
				
		playerIn.sendMessage(new TextComponentString("Atma " + String.valueOf(atma.getAtma()) + "/" + String.valueOf(atma.getMaxAtma())));
		
		playerIn.sendMessage(new TextComponentString(""));
		
		playerIn.sendMessage(new TextComponentString("Cooldown " + String.valueOf(cd.getTicks()) + "/" + String.valueOf(cd.getMaxTicks())));
		
		Events.updatePlayerAtma(playerIn);
					
		((EntityPlayer)playerIn).getCooldownTracker().setCooldown(this, 5);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
	
}
