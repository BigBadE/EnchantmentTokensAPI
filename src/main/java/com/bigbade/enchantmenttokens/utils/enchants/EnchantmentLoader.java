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

import org.bukkit.plugin.Plugin;
import com.bigbade.enchantmenttokens.api.EnchantmentAddon;
import com.bigbade.enchantmenttokens.api.EnchantmentBase;

import java.util.Set;

public interface EnchantmentLoader {
    void loadEnchantments(EnchantmentAddon addon,EnchantmentHandler handler, Set<Class<EnchantmentBase>> enchants);

    void loadEnchantment(Plugin plugin, Class<? extends EnchantmentBase> base);

    void loadAddon(EnchantmentAddon addons);
}
