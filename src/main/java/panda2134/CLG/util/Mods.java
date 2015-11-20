package panda2134.CLG.util;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionParser;

public class Mods {
	public class IDs{
		public static final String ic2="IC2";
		public static final String ic2api="IC2API";
		public static final String TE="ThermalExpansion";
		public static final String cofhApiEnergy="CoFHAPI|energy";
		public static final String fz="factorization";
	}
	
	public static final Mod IC2=new Mod(IDs.ic2);
	public static final Mod IC2API=new Mod(IDs.ic2api);
	public static final Mod ThermalExpansion=new Mod(IDs.TE);
	public static final Mod cofhApiEnergy=new Mod(IDs.cofhApiEnergy);
	public static final Mod Factorization=new Mod(IDs.fz);
	
	public static class Mod{
		public static String id;
		public boolean available;
		public Mod(String id){
			this.id=id;
			ArtifactVersion ver=VersionParser.parseVersionReference(id);
			if(Loader.isModLoaded(ver.getLabel())){
				available=ver.containsVersion(Loader.instance().
						getIndexedModList().get(ver.getLabel()).getProcessedVersion());
			}else{
				available=ModAPIManager.INSTANCE.hasAPI(ver.getLabel());
			}
		}
	}
}
