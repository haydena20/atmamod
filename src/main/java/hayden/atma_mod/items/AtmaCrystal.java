package hayden.atma_mod.items;

import hayden.atma_mod.Main;
import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.utils.handlers.Events;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AtmaCrystal extends ItemBase
{

	public AtmaCrystal(String name, int maxCharge) 
	{
		super(name);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(maxCharge);
		this.setDamage(getDefaultInstance(), 1);
		setCreativeTab(Main.creativeTab);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		IAtma atma = playerIn.getCapability(AtmaProvider.MAX_ATMA, null);
		
		ItemStack item = playerIn.getHeldItem(handIn);
		if((item.getItemDamage() >= item.getMaxDamage()) || (item.getItemDamage()+item.getMaxDamage()/10) >= item.getMaxDamage())
		{
			atma.addAtma((item.getMaxDamage()-item.getItemDamage()));
			item.setItemDamage(item.getMaxDamage());
//			if(!item.getDisplayName().contains("Drained"))
//				item.setStackDisplayName("§rDrained " + item.getDisplayName());
		}
		else
		{
//			if(item.getDisplayName().contains("Drained"))
//				item.setStackDisplayName(item.getDisplayName().substring(7));
			
			item.damageItem((item.getMaxDamage()/10)-1, playerIn);
			atma.addAtma((item.getMaxDamage()/10));
			((EntityPlayer)playerIn).getCooldownTracker().setCooldown(this, 20);
			Events.updatePlayerAtma((EntityPlayer) playerIn);
		}
			
			
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
}
