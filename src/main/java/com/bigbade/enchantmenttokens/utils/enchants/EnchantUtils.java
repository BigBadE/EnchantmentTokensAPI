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

package com.bigbade.enchantmenttokens.utils.enchants;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.bigbade.enchantmenttokens.api.EnchantmentBase;
import com.bigbade.enchantmenttokens.api.EnchantmentPlayer;

public abstract class EnchantUtils {

    private static EnchantUtils instance;

    public static EnchantUtils getInstance() {
        return EnchantUtils.instance;
    }

    static void setInstance(EnchantUtils instance) {
        if (instance == null) {
            throw new IllegalStateException("Instance already set!");
        }
        EnchantUtils.instance = instance;
    }

    /**
     * Adds enchantment with given name to item, removes gems, and sends messages.
     *
     * @param enchantmentPlayer Who to take the gems from
     * @param handler           Enchantment handler
     * @param itemStack         Item to enchant
     * @param name              Name of the enchantment
     */
    public abstract void addEnchantment(EnchantmentPlayer enchantmentPlayer, EnchantmentHandler handler,
                                        ItemStack itemStack, String name);

    /**
     * Adds enchantment to item, with error messages
     *
     * @param item              Item to enchant
     * @param base              EnchantBase to add
     * @param enchantmentPlayer player that holds the item
     */
    public abstract void addEnchantmentBase(ItemStack item, EnchantmentBase base, EnchantmentPlayer enchantmentPlayer);

    /**
     * Adds enchantment to item, without error messages
     *
     * @param item Item to enchant
     * @param base EnchantBase to add
     */
    public abstract void addEnchantmentBaseNoMessages(ItemStack item, EnchantmentBase base, Player player, int level);

    public abstract void removeEnchantmentBase(ItemStack item, EnchantmentBase base);

    public abstract void addEnchantmentBase(ItemStack item, EnchantmentBase base, Player player, int level);

    /**
     * Get the level of the enchantment
     *
     * @param item Item with the enchant
     * @param base The enchant
     * @return The level
     */
    public abstract int getLevel(ItemStack item, EnchantmentBase base);

    public abstract void triggerOnEnchant(ItemStack item, EnchantmentBase base, Player player);
}
