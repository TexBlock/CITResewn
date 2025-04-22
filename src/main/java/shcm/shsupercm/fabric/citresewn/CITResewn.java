package shcm.shsupercm.fabric.citresewn;

//? if >=1.20.4 {
import net.neoforged.bus.api.IEventBus;
//?} else if <1.20.4 {
/*import net.minecraftforge.eventbus.api.IEventBus;
*///?}
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shcm.shsupercm.fabric.citresewn.config.CITResewnConfig;
import shcm.shsupercm.fabric.citresewn.cit.CITRegistry;

/**
 * Main initializer for CIT Resewn. Contains various internal utilities(just logging for now).
 */
public class CITResewn {
    public static final Logger LOG = LogManager.getLogger("CITResewn");
    public static final CITResewn INSTANCE = new CITResewn();

    public static void onInitializeClient(IEventBus modEventBus) {
        CITRegistry.registerAll();
        CITResewnCommand.register(modEventBus);
    }

    /**
     * Logs an info line in CIT Resewn's name.
     * @param message log message
     */
    public static void info(String message) {
        LOG.info("[citresewn] " + message);
    }

    /**
     * Logs a warning line in CIT Resewn's name if enabled in config.
     * @see CITResewnConfig#mute_warns
     * @param message warn message
     */
    public static void logWarnLoading(String message) {
        if (CITResewnConfig.INSTANCE.mute_warns)
            return;
        LOG.error("[citresewn] " + message);
    }

    /**
     * Logs an error line in CIT Resewn's name if enabled in config.
     * @see CITResewnConfig#mute_errors
     * @param message error message
     */
    public static void logErrorLoading(String message) {
        if (CITResewnConfig.INSTANCE.mute_errors)
            return;
        LOG.error("{citresewn} " + message);
    }
}
