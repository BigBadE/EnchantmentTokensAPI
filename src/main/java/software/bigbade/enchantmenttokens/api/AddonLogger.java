package software.bigbade.enchantmenttokens.api;

import org.bukkit.plugin.Plugin;
import software.bigbade.enchantmenttokens.EnchantmentTokens;

import javax.annotation.Nonnull;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddonLogger extends Logger {
    public AddonLogger(@Nonnull Plugin addon) {
        super(addon.getDescription().getPrefix() != null ? addon.getDescription().getPrefix() : addon.getDescription().getName(), null);
        this.setParent(EnchantmentTokens.getEnchantLogger());
        this.setLevel(Level.ALL);
    }
}
