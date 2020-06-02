package software.bigbade.enchantmenttokens.utils.enchants;

import org.bukkit.plugin.Plugin;
import software.bigbade.enchantmenttokens.api.EnchantmentAddon;
import software.bigbade.enchantmenttokens.api.EnchantmentBase;

import java.util.Map;
import java.util.Set;

public interface EnchantmentLoader {
    void loadEnchantments(Map<EnchantmentAddon, Set<Class<EnchantmentBase>>> enchants);

    void loadEnchantment(Plugin plugin, Class<? extends EnchantmentBase> base);

    void loadAddon(EnchantmentAddon addons);
}
