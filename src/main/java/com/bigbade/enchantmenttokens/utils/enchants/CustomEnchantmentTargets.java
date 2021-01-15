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

import org.bukkit.enchantments.EnchantmentTarget;
import com.bigbade.enchantmenttokens.api.wrappers.EnchantmentTargetWrapper;
import com.bigbade.enchantmenttokens.api.wrappers.ITargetWrapper;
import com.bigbade.enchantmenttokens.api.wrappers.MaterialTargetWrapper;

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
