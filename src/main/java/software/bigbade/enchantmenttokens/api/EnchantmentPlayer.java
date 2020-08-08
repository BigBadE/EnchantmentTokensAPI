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

package software.bigbade.enchantmenttokens.api;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import software.bigbade.enchantmenttokens.gui.EnchantmentGUI;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface EnchantmentPlayer {
    void save();

    Player getPlayer();

    CompletableFuture<Long> getGems();

    void addGems(long amount);

    EnchantmentGUI getCurrentGUI();

    long getDoubler();

    void removeDoubler(long removing);

    void resetDoubler();

    /**
     * Adds a doubler to the user
     *
     * @param adding the amount to add, adding 1 gives a 2x bonus, 3 goes to a 4x bonus, .5 goes to a 1.5x bonus, etc...
     */
    void addDoubler(long adding);

    <T> void storeValue(NamespacedKey namespacedKey, T t);

    <T> void storeValue(NamespacedKey namespacedKey, T t, Function<T, String> function);

    @Nullable
    <T> T getValue(NamespacedKey namespacedKey, Function<String, T> function);

    void setCurrentGUI(EnchantmentGUI currentGUI);

    Locale getLanguage();

    void setLanguage(Locale language);
}
