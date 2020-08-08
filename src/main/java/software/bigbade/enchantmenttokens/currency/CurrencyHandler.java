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

package software.bigbade.enchantmenttokens.currency;

import org.bukkit.NamespacedKey;
import software.bigbade.enchantmenttokens.api.EnchantmentPlayer;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public interface CurrencyHandler {
    CompletableFuture<Long> getAmount();

    void setAmount(long amount);

    void addAmount(long amount);

    /**
     * Saves the player to the used saving method. Can be called off of the main thread, BE THREAD SAFE PLEASE.
     *
     * @param player The player to save.
     */
    void savePlayer(EnchantmentPlayer player);

    void storePlayerData(NamespacedKey key, String value);

    String getPlayerData(NamespacedKey key);

    void removePlayerData(NamespacedKey key);

    Locale getLocale();

    void setLocale(Locale language);

    String name();
}
