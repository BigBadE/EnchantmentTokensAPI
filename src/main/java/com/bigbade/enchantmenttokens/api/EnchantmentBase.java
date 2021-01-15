/*
 * Custom enchantments for Minecraft
 * Copyright (C) 2021 Big_Bad_E
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

package com.bigbade.enchantmenttokens.api;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import com.bigbade.enchantmenttokens.api.wrappers.ITargetWrapper;
import com.bigbade.enchantmenttokens.utils.enchants.EnchantRarity;

import javax.annotation.Nonnull;

public interface EnchantmentBase {
    @Nonnull
    NamespacedKey getKey();

    Material getIcon();

    void setIcon(Material icon);

    int getStartLevel();

    int getMaxLevel();

    void onDisable();

    long getDefaultPrice(int level);

    void loadConfig();

    boolean canEnchantItem(ItemStack item);

    Enchantment getEnchantment();

    ConfigurationSection getPriceSection();

    void setTarget(ITargetWrapper target);

    ITargetWrapper getTarget();

    @Nonnull
    String getEnchantmentName();

    Integer getLevel(ItemStack item);

    int getRarity();

    int getMaxTableLevel();

    void setRarity(EnchantRarity rarity);

    boolean isCursed();

    boolean isTreasure();

    boolean conflictsWith(Enchantment enchantment);

    int getMinCost(int level);

    int getMaxCost(int level);
}
