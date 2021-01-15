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

package com.bigbade.enchantmenttokens;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.bigbade.enchantmenttokens.currency.CurrencyFactory;
import com.bigbade.enchantmenttokens.gui.EnchantButton;
import com.bigbade.enchantmenttokens.gui.EnchantmentMenuFactory;
import com.bigbade.enchantmenttokens.utils.ButtonFactory;
import com.bigbade.enchantmenttokens.utils.ItemUtils;
import com.bigbade.enchantmenttokens.utils.SchedulerHandler;
import com.bigbade.enchantmenttokens.utils.SignHandler;
import com.bigbade.enchantmenttokens.utils.enchants.EnchantUtils;
import com.bigbade.enchantmenttokens.utils.enchants.EnchantmentHandler;
import com.bigbade.enchantmenttokens.utils.enchants.EnchantmentLoader;
import com.bigbade.enchantmenttokens.utils.listeners.ListenerHandler;
import com.bigbade.enchantmenttokens.utils.players.PlayerHandler;

import java.io.File;
import java.util.Locale;
import java.util.logging.Logger;

public abstract class EnchantmentTokens extends JavaPlugin {
    private static final ItemStack glassPane = ItemUtils.createItem(Material.BLACK_STAINED_GLASS_PANE, " ");

    public static final String NAME = "enchantmenttokens";

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private static Locale defaultLocale;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private static EnchantButton emptyButton;

    @Setter(AccessLevel.PRIVATE)
    private static TaskChainFactory taskChainFactory;

    @Getter
    private static Logger enchantLogger;

    public abstract void unregisterEnchants();

    public abstract void registerEnchants();

    protected void setEnchantLogger(Logger logger) {
        EnchantmentTokens.updateLogger(logger);
    }

    private static void updateLogger(Logger logger) {
        synchronized (EnchantmentTokens.class) {
            EnchantmentTokens.enchantLogger = logger;
        }
    }

    public abstract EnchantmentHandler getEnchantmentHandler();

    public abstract ListenerHandler getListenerHandler();

    public abstract PlayerHandler getPlayerHandler();

    public abstract CurrencyFactory getCurrencyHandler();

    public abstract SignHandler getSignHandler();

    public abstract EnchantUtils getUtils();

    public abstract EnchantmentMenuFactory getMenuFactory();

    public abstract SchedulerHandler getScheduler();

    public abstract File getEnchantmentFolder();

    public abstract boolean isOverridingEnchantTables();

    public abstract EnchantmentLoader getEnchantmentLoader();

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public static <T> TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }

    //Class is loaded before the button factory is instanced, so this must be set manually
    protected void setup(Plugin plugin, Locale defaultLocale) {
        if (emptyButton != null) {
            throw new IllegalStateException("Cannot setup an already-setup EnchantmentTokens!");
        }
        setEmptyButton(ButtonFactory.getInstance().createButton(glassPane, null));
        setTaskChainFactory(BukkitTaskChainFactory.create(plugin));
        setDefaultLocale(defaultLocale);
    }
}
