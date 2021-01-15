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
