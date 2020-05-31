/*
 * Addons for the Custom Enchantment API in Minecraft
 * Copyright (C) 2020 BigBadE
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package software.bigbade.enchantmenttokens.api;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import software.bigbade.enchantmenttokens.EnchantmentTokens;
import software.bigbade.enchantmenttokens.api.wrappers.EnchantmentConflictWrapper;
import software.bigbade.enchantmenttokens.api.wrappers.IConflictWrapper;
import software.bigbade.enchantmenttokens.api.wrappers.ITargetWrapper;
import software.bigbade.enchantmenttokens.utils.enchants.EnchantRarity;
import software.bigbade.enchantmenttokens.utils.enchants.EnchantUtils;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class CustomEnchantment extends Enchantment implements EnchantmentBase {
    @Getter
    @Setter
    private ITargetWrapper target;
    private final IConflictWrapper conflicts = new EnchantmentConflictWrapper();
    @Setter
    private boolean treasure = false;
    @Setter
    private boolean cursed;

    @ConfigurationField
    private String name;

    @ConfigurationField(name = "icon")
    private String iconString = "DEFAULT";

    @Getter
    private Material icon;

    @Getter
    @Setter
    @ConfigurationField
    private int maxLevel = 3;


    @Getter
    @ConfigurationField
    private int maxTableLevel = 3;

    @Getter
    @Setter
    @ConfigurationField
    private int startLevel = 1;

    @Getter
    @ConfigurationField(name = "price")
    private ConfigurationSection priceSection = null;

    @ConfigurationField(location = "price")
    private String type = "custom";

    @Getter
    @ConfigurationField
    private int rarity = 1;

    public CustomEnchantment(NamespacedKey key, Material icon, String defaultName) {
        super(key);
        setEnchantName(defaultName);
        setIcon(icon);
    }

    @Override
    public Enchantment getEnchantment() {
        return this;
    }

    public void onDisable() {
        //Overridden by subclasses
    }

    public long getDefaultPrice(int level) {
        for (PriceIncreaseTypes types : PriceIncreaseTypes.values()) {
            if (type.toUpperCase().replace(" ", "").equals(types.name())) {
                return types.getPrice(level, priceSection);
            }
        }
        return PriceIncreaseTypes.CUSTOM.getPrice(level, priceSection);
    }

    public void loadConfig() {
        for (PriceIncreaseTypes types : PriceIncreaseTypes.values()) {
            if (type.toUpperCase().replace(" ", "").equals(types.name())) {
                types.loadConfig(this);
                return;
            }
        }
        priceSection.set("type", PriceIncreaseTypes.CUSTOM.name().toLowerCase());
        PriceIncreaseTypes.CUSTOM.loadConfig(this);
        if(!iconString.equals("DEFAULT")) {
            Material material = Material.getMaterial(iconString);
            if (material == null)
                EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, "Invalid icon name {0}", iconString);
            else
                setIcon(material);
        }
    }

    public void setIcon(Material icon) {
        if (this.icon != null)
            throw new IllegalStateException("Enchantment already has an icon!");
        this.icon = icon;
    }

    @Nonnull
    public String getEnchantmentName() {
        return name;
    }

    @Override
    public void setRarity(EnchantRarity rarity) {
        this.rarity = rarity.ordinal()+1;
    }

    @Override
    public Integer getLevel(ItemStack item) {
        return EnchantUtils.getInstance().getLevel(item, this);
    }

    public void setEnchantName(String name) {
        if (this.name != null)
            throw new IllegalStateException("Enchantment already has a name " + this.name + ", could not be set to " + name);
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return getEnchantmentName();
    }

    @Nonnull
    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    @Override
    public boolean isTreasure() {
        return treasure;
    }

    @Override
    public boolean conflictsWith(@Nonnull Enchantment enchantment) {
        return conflicts.conflicts(enchantment);
    }

    @Override
    public int getMinCost(int level) {
        return 30/getMaxLevel()*level;
    }

    @Override
    public int getMaxCost(int level) {
        int min = this.getMinCost(level);
        switch(rarity) {
            case 1:
            case 2:
            case 3:
            case 4:
                return min + 50;
            default:
                return min + 5;
        }
    }

    @Override
    public boolean isCursed() {
        return cursed;
    }

    @Override
    public boolean canEnchantItem(@Nonnull ItemStack itemStack) {
        if (itemStack.getItemMeta() == null || !itemStack.getItemMeta().hasEnchants()) {
            return target.canTarget(itemStack.getType());
        }
        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            if (conflictsWith(enchantment)) {
                return false;
            }
        }
        return target.canTarget(itemStack.getType());
    }

    public void addConflict(Enchantment conflict) {
        conflicts.addTarget(conflict);
    }

    public void addConflict(String namespace, String name) {
        conflicts.addTarget(namespace, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomEnchantment)
            return hashCode() == obj.hashCode();
        return false;
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }
}
