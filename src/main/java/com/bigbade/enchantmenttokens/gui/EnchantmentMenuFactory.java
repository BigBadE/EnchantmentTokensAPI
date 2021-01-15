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

package com.bigbade.enchantmenttokens.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.bigbade.enchantmenttokens.api.EnchantmentBase;
import com.bigbade.enchantmenttokens.api.EnchantmentPlayer;

import java.util.List;

public interface EnchantmentMenuFactory {
    void addButtons(List<EnchantButton> button);

    /**
     * Generate the GUI with every enchantment in it
     *
     * @param itemStack Item target
     * @param player    Target player
     * @return Generated enchantment inventory
     */
    EnchantmentGUI generateGUI(ItemStack itemStack, EnchantmentPlayer player);

    EnchantmentGUI genInventory(Player player);

    EnchantmentGUI genItemInventory(EnchantmentPlayer enchantPlayer, ItemStack item, List<EnchantmentBase> added);
}
