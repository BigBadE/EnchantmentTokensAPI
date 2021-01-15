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

import com.bigbade.enchantmenttokens.api.SkriptEnchantments;
import org.bukkit.enchantments.Enchantment;
import com.bigbade.enchantmenttokens.api.EnchantmentBase;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EnchantmentHandler {
    void registerEnchants(Collection<EnchantmentBase> enchantments);

    void registerEnchant(EnchantmentBase base);

    void unregisterEnchants();

    /**
     * Returns all enchantments used
     * @return All registered enchantments, including vanilla and skript enchantments
     */
    List<EnchantmentBase> getAllEnchants();

    /**
     * Gets custom enchantments
     * @return All registered custom enchantments, excluding vanilla or skript enchantments
     */
    List<EnchantmentBase> getCustomEnchants();

    /**
     * Get Skript enchantment handler
     */
    Optional<SkriptEnchantments> getSkriptEnchantmentHandler();

    boolean hasVanillaEnchant(Enchantment enchantment);
}
