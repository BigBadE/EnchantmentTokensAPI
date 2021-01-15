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

package com.bigbade.enchantmenttokens.configuration;

import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Used for safe casting of unknown configuration types
 */
public class ConfigurationType<T> {
    @Nullable
    private final T defaultValue;

    public ConfigurationType() {
        this.defaultValue = null;
    }

    public ConfigurationType(@Nonnull T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public T getValue(@Nonnull String location, @Nonnull ConfigurationSection section) {
        try {
            T value = (T) section.get(location);
            if (value == null) {
                section.set(location, defaultValue);
                if(defaultValue != null) {
                    return defaultValue;
                }
                throw new IllegalArgumentException("No value for required configuration value!");
            }
            return value;
        } catch (ClassCastException e) {
            section.set(location, defaultValue);
            if(defaultValue != null) {
                return defaultValue;
            }
            throw new IllegalArgumentException("No value for required configuration value!");
        }
    }
}
