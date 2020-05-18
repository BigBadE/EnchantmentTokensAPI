package software.bigbade.enchantmenttokens.utils.enchants;

import org.bukkit.enchantments.EnchantmentTarget;
import software.bigbade.enchantmenttokens.api.wrappers.EnchantmentTargetWrapper;
import software.bigbade.enchantmenttokens.api.wrappers.ITargetWrapper;
import software.bigbade.enchantmenttokens.api.wrappers.MaterialTargetWrapper;

public final class CustomEnchantmentTargets {
    private CustomEnchantmentTargets() {
    }

    public static final ITargetWrapper TRIDENT = new EnchantmentTargetWrapper("TRIDENT");
    public static final ITargetWrapper CROSSBOW = new EnchantmentTargetWrapper("CROSSBOW");
    public static final ITargetWrapper BOW = new EnchantmentTargetWrapper(EnchantmentTarget.BOW);
    public static final ITargetWrapper SHIELD = new MaterialTargetWrapper("SHIELD");
    public static final ITargetWrapper ELYTRA = new MaterialTargetWrapper("ELYTRA");
    public static final ITargetWrapper SHOOTABLE = new EnchantmentTargetWrapper("BOW", "CROSSBOW");
    public static final ITargetWrapper FISHING_ROD = new EnchantmentTargetWrapper(EnchantmentTarget.FISHING_ROD);
    public static final ITargetWrapper ALL = new EnchantmentTargetWrapper(EnchantmentTarget.ALL);
    public static final ITargetWrapper WEAPON = new EnchantmentTargetWrapper(EnchantmentTarget.WEAPON);
    public static final ITargetWrapper ARMOR = new EnchantmentTargetWrapper(EnchantmentTarget.ARMOR);
    public static final ITargetWrapper TOOL = new EnchantmentTargetWrapper(EnchantmentTarget.TOOL);
}
