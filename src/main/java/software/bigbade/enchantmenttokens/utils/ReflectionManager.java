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

package software.bigbade.enchantmenttokens.utils;

import org.bukkit.Bukkit;
import software.bigbade.enchantmenttokens.EnchantmentTokens;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.logging.Level;

public final class ReflectionManager {
    private ReflectionManager() {
    }

    public static final int VERSION = Integer.parseInt(Bukkit.getVersion().split("\\.")[1]);

    private static final String ERROR_MESSAGE = "Version changes with enchantments, please report this and the MC version";

    @Nonnull
    public static Field getField(@Nonnull Class<?> clazz, @Nonnull String name) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(name);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, ERROR_MESSAGE);
        }
        Objects.requireNonNull(field);
        return field;
    }

    @Nonnull
    public static Method getMethod(@Nonnull Class<?> clazz, @Nonnull String name, Class<?>... params) {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(name, params);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, ERROR_MESSAGE);
        }
        Objects.requireNonNull(method);
        return method;
    }

    @Nonnull
    public static Class<?> getClass(@Nonnull String name) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, ERROR_MESSAGE);
        }
        Objects.requireNonNull(clazz);
        return clazz;
    }

    @Nonnull
    public static Object getValue(@Nonnull Field field, @Nullable Object instance) {
        try {
            return field.get(instance);
        } catch (IllegalAccessException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, "Could not get field reflexively");
        }
        throw new IllegalStateException("Could not get value for field " + field.getName() + " with instance " + instance);
    }

    public static void setValue(@Nonnull Field field, @Nullable Object value, @Nullable Object instance) {
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, "Could not set field reflexively");
        }
    }

    @Nonnull
    public static <T> T instantiate(@Nonnull Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, e, () -> "Could not instantiate class " + clazz.getSimpleName());
        }
        throw new IllegalStateException("Could not instantiate class " + clazz.getSimpleName());
    }

    @Nonnull
    public static <T> T instantiate(@Nonnull Constructor<T> constructor, @Nullable Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, e, () -> "Error instantiating constructor " + constructor.getName());
        }
        throw new IllegalStateException("Could not instantiate constructor " + constructor.getName());
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T invoke(@Nonnull Method method, @Nullable Object instance, @Nullable Object... args) {
        try {
            return (T) method.invoke(instance, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, "Problem invoking method", e);
        }
        return null;
    }

    @Nullable
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... arguments) {
        try {
            return clazz.getConstructor(arguments);
        } catch (NoSuchMethodException e) {
            EnchantmentTokens.getEnchantLogger().log(Level.SEVERE, "Problem getting constructor", e);
        }
        return null;
    }
}
