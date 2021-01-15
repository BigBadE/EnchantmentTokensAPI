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

package com.bigbade.enchantmenttokens.localization;

import com.bigbade.enchantmenttokens.utils.RegexPatterns;
import org.bukkit.ChatColor;
import com.bigbade.enchantmenttokens.EnchantmentTokens;
import com.bigbade.enchantmenttokens.utils.currency.CurrencyAdditionHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Locale messages for the base plugin.
 * Addons should create their own version of this class, and copy the methods.
 */
public enum LocaleMessages {
    ENCHANTMENT("enchantment"),
    //Args: enchant name
    ENCHANTMENT_ADD("enchantment.add"),
    ENCHANTMENT_ADD_FAIL("enchantment.add.fail"),
    MAXED("enchantment.max"),
    MAXED_MESSAGE("enchantment.max.message"),
    NOT_APPLICABLE("enchantment.not-applicable"),
    //Args: enchant name, level
    ENCHANTMENT_BOUGHT_SUCCESS("enchantment.bought.success"),
    //Args: price
    ENCHANTMENT_BOUGHT_FAIL("enchantment.bought.fail"),
    //Args: price
    PRICE("enchantment.price"),
    //Args: level
    LEVEL("enchantment.level"),
    PRICE_MAXED("enchantment.price.maxed"),
    //ARgs: amount of gems
    GEMS_FIND("enchantment.gems.get"),

    /**
     * GUI Messages
     */
    GUI_CONFIRM("enchant.confirm"),
    GUI_CANCEL("enchant.cancel"),
    GUI_BACK("enchant.back"),

    /**
     * Tool names
     */
    //Args: tool name
    TOOL_ENCHANTS("tool.enchants"),
    TOOL_CROSSBOW("tool.crossbow"),
    TOOL_TRIDENT("tool.trident"),
    TOOL_FISHING_ROD("tool.fishing-rod"),
    TOOL_TOOLS("tool.tool"),
    TOOL_SWORD("tool.sword"),
    TOOL_ARMOR("tool.armor"),
    TOOL_BOW("tool.bow"),
    TOOL_SHIELD("tool.shield"),

    /**
     * Command messages
     */
    COMMAND_ERROR_PERMISSION("command.error.permission"),
    //Args: incorrect argument
    COMMAND_ERROR_NOT_NUMBER("command.error.not-number"),
    //Args: name
    COMMAND_ERROR_NO_PLAYER("command.error.no-player"),
    COMMAND_ERROR_TOO_MANY_ARGUMENTS("command.error.arguments"),
    COMMAND_ERROR_NO_ENCHANTMENT("command.error.no-enchantment"),

    //Args: amount added
    COMMAND_ADD("command.add"),
    //Args: player balance
    COMMAND_BALANCE("command.balance"),
    COMMAND_ENCHANT_USAGE("command.enchant.usage"),
    COMMAND_ENCHANT_HELD("command.enchant.held"),
    COMMAND_ERROR_BAD_LEVEL("command.enchant.wrong-level"),
    COMMAND_PAY_USAGE("command.pay.usage"),
    //Args: minimum payment
    COMMAND_PAY_NOT_ENOUGH("command.pay.not-enough"),
    //Args: amount, receiver
    COMMAND_PAY("command.pay"),
    //Args: amount, sender
    COMMAND_PAY_RECEIVE("command.pay.receive"),
    //Args: list of enchants
    COMMAND_LIST("command.list"),
    COMMAND_ENCHANTMENT_TOKENS_USAGE("command.enchantment-tokens.usage"),
    COMMAND_ENCHANTMENT_TOKENS_LANGUAGES("command.enchantment-tokens.languages"),

    DOLLAR_SYMBOL("dollar.symbol"),
    GEMS_SYMBOL("gems.symbol"),
    LEVELS("levels");


    private final String key;

    private static final LocaleMessages PRICES = getPriceMessages();

    LocaleMessages(String key) {
        this.key = key;
    }

    public static String translatePrice(@Nullable Locale locale, @Nonnull Object price) {
        if(locale == null) {
            locale = EnchantmentTokens.getDefaultLocale();
        }
        return PRICES.translate(locale, price);
    }

    public String translate(@Nullable Locale locale, @Nonnull Object... args) {
        if(locale == null) {
            locale = EnchantmentTokens.getDefaultLocale();
        }
        String text = LocaleManager.getBundle(locale, EnchantmentTokens.NAME).getString(key);
        if(args.length == 0) {
            return text;
        }
        Matcher matcher = RegexPatterns.PLACEHOLDER_PATTERN.matcher(text);
        StringBuffer output = new StringBuffer();
        int i = 0;
        while (matcher.find()) {
            if(matcher.group(1).equals("%s")) {
                matcher.appendReplacement(output, args[i++].toString());
            }
        }
        return output.toString();
    }

    private static LocaleMessages getPriceMessages() {
        if (CurrencyAdditionHandler.isUsingGems()) {
            return GEMS_SYMBOL;
        } else if (CurrencyAdditionHandler.isUsingExperience()) {
            return LEVELS;
        } else {
            return DOLLAR_SYMBOL;
        }
    }
}
