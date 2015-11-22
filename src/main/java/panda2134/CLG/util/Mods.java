package panda2134.CLG.util;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionParser;

public class Mods {
	public class IDs {
		public static final String ic2 = "IC2";
		public static final String ic2api = "IC2API";
		public static final String TE = "ThermalExpansion";
		public static final String cofhApiEnergy = "CoFHAPI|energy";
		public static final String fz = "factorization";
		public static final String IE = "ImmersiveEngineering";
	}

	public static final Mod IC2 = new Mod(IDs.ic2, true);
	public static final Mod IC2API = new Mod(IDs.ic2api, false);
	public static final Mod ThermalExpansion = new Mod(IDs.TE, true);
	public static final Mod cofhApiEnergy = new Mod(IDs.cofhApiEnergy, false);
	public static final Mod Factorization = new Mod(IDs.fz, true);
	public static final Mod ImmersiveEngineering = new Mod(IDs.IE, true);

	public static class Mod {
		public String id;
		public boolean available;
		public boolean hasRecipe;

		public Mod(String id, boolean hasRecipe) {
			this.id = id;
			this.hasRecipe = hasRecipe;
			ArtifactVersion ver = VersionParser.parseVersionReference(id);
			if (Loader.isModLoaded(ver.getLabel())) {
				available = ver.containsVersion(Loader.instance()
						.getIndexedModList().get(ver.getLabel())
						.getProcessedVersion());
			} else {
				available = ModAPIManager.INSTANCE.hasAPI(ver.getLabel());
			}
		}
	}
}
