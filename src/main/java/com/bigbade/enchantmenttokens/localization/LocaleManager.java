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

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import com.bigbade.enchantmenttokens.EnchantmentTokens;
import com.bigbade.enchantmenttokens.api.EnchantmentAddon;
import com.bigbade.enchantmenttokens.configuration.ConfigurationType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;

public final class LocaleManager {
    private static final Map<Locale, List<AddonResourceBundle>> bundles = new HashMap<>();

    @Getter
    private static final Locale[] supportedLocales = new Locale[]{Locale.US, Locale.UK};

    private LocaleManager() {
    }

    public static void updateLocale(Collection<EnchantmentAddon> addons) {
        try {
            for (Locale locale : supportedLocales) {
                InputStream stream = getStream(EnchantmentTokens.NAME, locale);
                if (stream != null) {
                    createOrAddLocale(locale, new AddonResourceBundle(EnchantmentTokens.NAME, new PropertyResourceBundle(stream)));
                }
            }

            for (EnchantmentAddon addon : addons) {
                for (Locale locale : addon.supportedLocales()) {
                    InputStream stream = getStream(addon.getName(), locale);
                    if (stream != null) {
                        createOrAddLocale(locale, new AddonResourceBundle(addon.getName(), new PropertyResourceBundle(stream)));
                    }
                }
            }
        } catch (IOException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, "Could not load Localization files.", e);
        }
    }

    private static void createOrAddLocale(Locale locale, AddonResourceBundle bundle) {
        List<AddonResourceBundle> found = bundles.get(locale);
        if (found == null) {
            found = new ArrayList<>();
            found.add(bundle);
            bundles.put(locale, found);
        } else {
            found.add(bundle);
        }
    }

    public static Locale getLocale(ConfigurationSection section) {
        String language = new ConfigurationType<>("US").getValue("country-language", section);

        for (Locale foundLocale : Locale.getAvailableLocales()) {
            if (foundLocale.getCountry() != null && foundLocale.getDisplayCountry().equals(language)) {
                return foundLocale;
            }
        }
        return Locale.US;
    }

    private static InputStream getStream(String name, Locale locale) {
        InputStream languageStream = EnchantmentTokens.class
                .getResourceAsStream("/localization/" + name + "-" + locale.getLanguage() + "_"
                        + locale.getCountry() + ".properties");
        if (languageStream == null) {
            languageStream = EnchantmentTokens.class.getResourceAsStream("localization/"
                    + EnchantmentTokens.NAME + "-en_US.properties");
        }
        return languageStream;
    }

    public static ResourceBundle getBundle(Locale locale, String namespace) {
        List<AddonResourceBundle> addonBundle = bundles.get(locale);
        if (addonBundle == null) {
            if (locale != Locale.US) {
                return getBundle(Locale.US, namespace);
            } else {
                throw new IllegalArgumentException("Unknown addon bundle: " + namespace);
            }
        }
        for (AddonResourceBundle bundle : addonBundle) {
            if (bundle.getAddon().equals(namespace)) {
                return bundle.getBundle();
            }
        }
        throw new IllegalArgumentException("Unknown addon bundle: " + namespace);
    }
}
