package disableend;

import disableend.config.ConfigManager;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisableEnd implements ModInitializer
{
    public static final String MOD_ID = "disableend";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize()
    {
        LOGGER.info("DisableEnd initialized.");
		ConfigManager.load();
        ToggleCommand cmd = new ToggleCommand();
        cmd.register();
    }
}
