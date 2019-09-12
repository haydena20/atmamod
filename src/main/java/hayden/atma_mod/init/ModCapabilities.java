package hayden.atma_mod.init;

import hayden.atma_mod.capabilities.IWork;
import hayden.atma_mod.capabilities.Worker;
import net.minecraft.nbt.NBTBase;
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
		CapabilityManager.INSTANCE.register(IWork.class, new CapabilityWorker(), Worker.class);
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
}
