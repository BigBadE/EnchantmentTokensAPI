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

public final class RomanNumeralConverter {
    private static final String[] c = {"", "C", "CC", "CCC", "CD", "D",
            "DC", "DCC", "DCCC", "CM"};
    private static final String[] x = {"", "X", "XX", "XXX", "XL", "L",
            "LX", "LXX", "LXXX", "XC"};
    private static final String[] i = {"", "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX"};

    //Private constructor to hide implicit public one
    private RomanNumeralConverter() {
    }

    public static String getRomanNumeral(int level) {
        return c[level / 100] +
                x[(level % 100) / 10] +
                i[level % 10];
    }
}
