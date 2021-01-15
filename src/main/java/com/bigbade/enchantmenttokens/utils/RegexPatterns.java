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

import java.util.regex.Pattern;

public final class RegexPatterns {
    public static final Pattern X_PATTERN = Pattern.compile("x");
    public static final Pattern SPACE_PATTERN = Pattern.compile(" ");
    public static final Pattern DIVIDER_PATTERN = Pattern.compile("/");
    public static final Pattern UNDERSCORE_PATTERN = Pattern.compile("_");
    public static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("%s");

    private RegexPatterns() {}
}
