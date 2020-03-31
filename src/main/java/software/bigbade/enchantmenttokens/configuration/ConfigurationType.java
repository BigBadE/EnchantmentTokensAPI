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

package software.bigbade.enchantmenttokens.configuration;

import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nonnull;

/**
 * Used for safe casting of unknown configuration types
 */
public class ConfigurationType<T> {
    private T defaultValue;

    public ConfigurationType(@Nonnull T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public T getValue(@Nonnull String value, @Nonnull ConfigurationSection section) {
        try {
            T foundValue = (T) section.get(value);
            if(foundValue == null) {
                section.set(value, defaultValue);
                return defaultValue;
            }
            return foundValue;
        } catch (ClassCastException e) {
            section.set(value, defaultValue);
            return defaultValue;
        }
    }
}
