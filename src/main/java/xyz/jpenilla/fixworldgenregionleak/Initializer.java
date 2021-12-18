package xyz.jpenilla.fixworldgenregionleak;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Initializer implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("fix-worldgenregion-leak");

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized fix-worldgenregion-leak");
	}
}
