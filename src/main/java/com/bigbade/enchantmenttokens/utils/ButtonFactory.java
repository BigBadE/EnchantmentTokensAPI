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

package com.bigbade.enchantmenttokens.utils;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import com.bigbade.enchantmenttokens.api.EnchantmentPlayer;
import com.bigbade.enchantmenttokens.gui.EnchantButton;
import com.bigbade.enchantmenttokens.gui.EnchantmentGUI;
import com.bigbade.enchantmenttokens.localization.LocaleMessages;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class ButtonFactory {
    @Getter
    private static ButtonFactory instance = null;

    public static void setInstance(ButtonFactory instance) {
        if (ButtonFactory.instance != null) {
            throw new IllegalStateException("Tried setting already-set instance in ButtonFactory!");
        }
        ButtonFactory.instance = instance;
    }

    public abstract EnchantButton createButton(@Nonnull ItemStack item,
                                               @Nullable Function<EnchantmentPlayer, EnchantmentGUI> callable);

    public abstract EnchantButton createButton(@Nonnull ItemStack item,
                                               @Nullable Function<EnchantmentPlayer, EnchantmentGUI> callable,
                                               @Nullable LocaleMessages translation);
}
