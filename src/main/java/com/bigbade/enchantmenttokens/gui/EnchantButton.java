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

import org.bukkit.inventory.ItemStack;
import com.bigbade.enchantmenttokens.api.EnchantmentPlayer;
import com.bigbade.enchantmenttokens.localization.LocaleMessages;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface EnchantButton extends Cloneable {
    /**
     * Called on item click
     * @param player The player who clicked
     * @return The inventory to open. Return null to close the inventory
     */
    @Nullable
    EnchantmentGUI click(@Nonnull EnchantmentPlayer player);

    @Nonnull
    ItemStack getItem();

    void setItem(ItemStack item);

    @Nullable
    LocaleMessages getTranslation();
}
