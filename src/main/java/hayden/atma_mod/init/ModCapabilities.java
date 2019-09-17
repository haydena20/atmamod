package hayden.atma_mod.init;

import hayden.atma_mod.capabilities.CooldownBaubles;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.capabilities.ICooldown;
import hayden.atma_mod.capabilities.IWork;
import hayden.atma_mod.capabilities.PlayerAtma;
import hayden.atma_mod.capabilities.Worker;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities 
{
	@CapabilityInject(IWork.class)
	public static Capability<IWork> CAPABILITY_WORKER = null;
	
	
	public static void registerCapabilities()
	{
		//CapabilityManager.INSTANCE.register(IWork.class, new CapabilityWorker(), Worker.class);
		CapabilityManager.INSTANCE.register(IAtma.class, new AtmaStorage(), PlayerAtma.class); 
		CapabilityManager.INSTANCE.register(ICooldown.class, new BaubleCooldown(), CooldownBaubles.class); 
		System.out.println("Capabilities Registered");
	}
	
	public static class CapabilityWorker implements IStorage<IWork>
	{

		@Override
		public NBTBase writeNBT(Capability<IWork> capability, IWork instance, EnumFacing side) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void readNBT(Capability<IWork> capability, IWork instance, EnumFacing side, NBTBase nbt) {
			// TODO Auto-generated method stub
			
		}
	}
	public static class AtmaStorage implements IStorage<IAtma>
	{
		@Override
		public NBTBase writeNBT(Capability<IAtma> capability, IAtma instance, EnumFacing side)
		{
			return new NBTTagFloat(instance.getAtma());
		}
		
		@Override
		public void readNBT(Capability<IAtma> capability, IAtma instance, EnumFacing side, NBTBase nbt)
		{
			instance.setAtma(((NBTPrimitive) nbt).getFloat());
		}

	}
	public static class BaubleCooldown implements IStorage<ICooldown>
	{
		@Override
		public NBTBase writeNBT(Capability<ICooldown> capability, ICooldown instance, EnumFacing side)
		{
			return new NBTTagFloat(instance.getTicks());
		}
		
		@Override
		public void readNBT(Capability<ICooldown> capability, ICooldown instance, EnumFacing side, NBTBase nbt)
		{
			instance.setTicks(((NBTPrimitive) nbt).getFloat());
		}
	}
}
