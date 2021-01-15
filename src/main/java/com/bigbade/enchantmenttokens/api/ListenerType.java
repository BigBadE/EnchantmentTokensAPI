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

import lombok.RequiredArgsConstructor;
import com.bigbade.enchantmenttokens.api.wrappers.ITargetWrapper;
import com.bigbade.enchantmenttokens.utils.enchants.CustomEnchantmentTargets;

@RequiredArgsConstructor
public enum ListenerType {
    //On block starting hit
    BLOCK_DAMAGED(CustomEnchantmentTargets.TOOL),
    //On block destroyed
    BLOCK_BREAK(CustomEnchantmentTargets.TOOL),
    //Armor equipped
    EQUIP(CustomEnchantmentTargets.ARMOR),
    //Armor unequipped
    UNEQUIP(CustomEnchantmentTargets.ARMOR),
    //Item swapped to
    HELD(CustomEnchantmentTargets.ALL),
    //Item swapped off
    SWAPPED(CustomEnchantmentTargets.ALL),
    //On current enchantment applied to an item
    ENCHANT(CustomEnchantmentTargets.ALL),
    //Before user death
    DEATH_BEFORE(CustomEnchantmentTargets.ALL),
    //After user death
    DEATH_AFTER(CustomEnchantmentTargets.ALL),
    //Potion apply
    POTION_APPLY(CustomEnchantmentTargets.ALL),
    //Potion remove
    POTION_REMOVE(CustomEnchantmentTargets.ALL),
    //Shield block
    SHIELD_BLOCK(CustomEnchantmentTargets.SHIELD),
    //Trident throw
    TRIDENT_THROW(CustomEnchantmentTargets.TRIDENT),
    //Bow shoot
    BOW_SHOOT(CustomEnchantmentTargets.BOW),
    //Crossbow shoot
    CROSSBOW_SHOOT(CustomEnchantmentTargets.CROSSBOW),
    //Entity damage
    ON_DAMAGED(CustomEnchantmentTargets.ALL),
    //Dealt damage
    ENTITY_DAMAGED(CustomEnchantmentTargets.WEAPON),
    //Player riptide with trident
    RIPTIDE(CustomEnchantmentTargets.TRIDENT),
    //Player fishing something
    FISHING_ROD_CATCH(CustomEnchantmentTargets.FISHING_ROD),
    //Elytra glide
    ELYTRA_GLIDE(CustomEnchantmentTargets.ELYTRA),
    //Arrow hit
    ARROW_HIT(CustomEnchantmentTargets.SHOOTABLE),
    //Trident hit
    TRIDENT_HIT(CustomEnchantmentTargets.TRIDENT),
    //1.8 and below sword block
    SWORD_BLOCK(CustomEnchantmentTargets.WEAPON);

    private final ITargetWrapper target;

    public boolean canTarget(ITargetWrapper wrapper) {
        return target.canTarget(wrapper);
    }
}
