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

import org.bukkit.configuration.ConfigurationSection;
import com.bigbade.enchantmenttokens.configuration.ConfigurationType;
import com.bigbade.enchantmenttokens.utils.math.AlgebraicCalculator;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public enum PriceIncreaseTypes {
    CUSTOM((level, section) ->
            section.getInt(level + ""), enchant -> {
        for (int i = enchant.getStartLevel(); i < enchant.getMaxLevel() + 1; i++) {
            new ConfigurationType<>(i * 10).getValue(i + "", enchant.getPriceSection());
        }
    }),
    LINEAR((level, section) -> level * new ConfigurationType<>(10).getValue("increase", section),
            enchant ->
                    new ConfigurationType<>(10).getValue("increase", enchant.getPriceSection())),
    ALGEBRAIC((level, section) -> AlgebraicCalculator.getInstance().getPrice(level),
            enchant -> new AlgebraicCalculator(enchant.getPriceSection()));

    private final BiFunction<Integer, ConfigurationSection, Integer> function;
    private final Consumer<EnchantmentBase> setup;

    PriceIncreaseTypes(BiFunction<Integer, ConfigurationSection, Integer> function, Consumer<EnchantmentBase> setup) {
        this.function = function;
        this.setup = setup;
    }

    public long getPrice(int level, ConfigurationSection section) {
        return function.apply(level, section);
    }

    public void loadConfig(EnchantmentBase base) {
        setup.accept(base);
    }
}
