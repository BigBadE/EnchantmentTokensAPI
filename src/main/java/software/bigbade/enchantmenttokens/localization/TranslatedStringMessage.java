/*
 * Addons for the Custom Enchantment API in Minecraft
 * Copyright (C) 2020 BigBadE
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

package software.bigbade.enchantmenttokens.localization;

import org.bukkit.ChatColor;
import software.bigbade.enchantmenttokens.EnchantmentTokens;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TranslatedStringMessage implements ITranslatedMessage {
    private final String message;

    private static final Pattern REPLACE_PLACEHOLDERS = Pattern.compile("%s");

    public TranslatedStringMessage(String namespace, Locale locale, String key) {
        ResourceBundle bundle = LocaleManager.getBundle(locale, namespace);
        if (bundle != null) {
            message = ChatColor.translateAlternateColorCodes('&', bundle.getString(key));
        } else {
            bundle = LocaleManager.getBundle(Locale.US, namespace);
            if (bundle != null) {
                message = ChatColor.translateAlternateColorCodes('&', bundle.getString(key));
            } else {
                message = "NO NAMESPACE FOUND";
            }
        }
    }

    public TranslatedStringMessage(Locale locale, String key) {
        this(EnchantmentTokens.NAME, locale, key);
    }

    @Override
    public String translate(String... args) {
        String text = message;
        for (String argument : args) {
            text = REPLACE_PLACEHOLDERS.matcher(text).replaceFirst(argument.replace("$", "\\$"));
        }
        return text;
    }
}
