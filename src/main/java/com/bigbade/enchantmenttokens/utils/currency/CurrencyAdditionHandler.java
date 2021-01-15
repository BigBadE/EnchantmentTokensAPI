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

package com.bigbade.enchantmenttokens.utils.currency;

import com.bigbade.enchantmenttokens.api.EnchantmentPlayer;
import com.bigbade.enchantmenttokens.localization.LocaleMessages;

import java.util.Locale;

public final class CurrencyAdditionHandler {
    private static Boolean usingGems;
    private static Boolean usingXp;

    //Private constructor to hide implicit public one
    private CurrencyAdditionHandler() {}

    public static void initialize(boolean usingGems, boolean usingXp) {
        if (CurrencyAdditionHandler.usingGems != null || CurrencyAdditionHandler.usingXp != null) {
            throw new IllegalStateException("Already initialized");
        }
        CurrencyAdditionHandler.usingGems = usingGems;
        CurrencyAdditionHandler.usingXp = usingXp;
    }

    public static String formatMoney(Locale locale, long amount) {
        return LocaleMessages.translatePrice(locale, String.format(locale, "%,d", amount));
    }

    public static void addGems(EnchantmentPlayer player, long amount) {
        player.addGems(amount);
        player.getPlayer().sendMessage(LocaleMessages.COMMAND_ADD.translate(player.getLanguage(),
                formatMoney(player.getLanguage(), amount)));
    }

    public static boolean isUsingGems() {
        if (usingGems == null) {
            throw new IllegalStateException("Not initialized");
        }
        return usingGems;
    }

    public static boolean isUsingExperience() {
        if (usingXp == null) {
            throw new IllegalStateException("Not initialized");
        }
        return usingXp;
    }
}
