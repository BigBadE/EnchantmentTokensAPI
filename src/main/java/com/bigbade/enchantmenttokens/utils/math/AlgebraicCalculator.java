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

package com.bigbade.enchantmenttokens.utils.math;

import com.bigbade.enchantmenttokens.utils.RegexPatterns;
import org.bukkit.configuration.ConfigurationSection;
import com.bigbade.enchantmenttokens.EnchantmentTokens;
import com.bigbade.enchantmenttokens.configuration.ConfigurationType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.logging.Level;

public class AlgebraicCalculator {
    private final ScriptEngine engine;
    private final String equation;
    private static AlgebraicCalculator instance;

    public AlgebraicCalculator(ConfigurationSection section) {
        equation = new ConfigurationType<>("x^2+2x-4").getValue("equation", section);
        if (instance != null) {
            throw new IllegalStateException("Already initialized!");
        }
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
        setInstance(this);
    }

    private static void setInstance(AlgebraicCalculator instance) {
        AlgebraicCalculator.instance = instance;
    }

    public static AlgebraicCalculator getInstance() {
        return instance;
    }

    public int getPrice(int level) {
        try {
            return (int) engine.eval(RegexPatterns.X_PATTERN.matcher(equation).replaceAll(String.valueOf(level)));
        } catch (ScriptException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, "Invalid equation {0}", equation);
        }
        return 1;
    }
}
