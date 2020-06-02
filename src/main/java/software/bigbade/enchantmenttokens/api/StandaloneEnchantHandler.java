package software.bigbade.enchantmenttokens.api;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

public abstract class StandaloneEnchantHandler {
    @Getter
    private static StandaloneEnchantHandler instance = null;

    public static void setInstance(StandaloneEnchantHandler instance) {
        if (StandaloneEnchantHandler.instance != null) {
            throw new IllegalStateException("Tried setting already-set instance in StandaloneEnchantHandler!");
        }
        StandaloneEnchantHandler.instance = instance;
    }

    public abstract void createEnchantment(Plugin plugin, Class<? extends EnchantmentBase> clazz);
}
