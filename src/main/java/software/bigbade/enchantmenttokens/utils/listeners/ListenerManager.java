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

package software.bigbade.enchantmenttokens.utils.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import software.bigbade.enchantmenttokens.api.EnchantmentBase;
import software.bigbade.enchantmenttokens.events.EnchantmentEvent;

import java.util.HashMap;
import java.util.Map;

public class ListenerManager<T extends Event> {
    private final Map<Enchantment, EnchantmentListener<EnchantmentEvent<T>>> listeners = new HashMap<>();

    public void add(EnchantmentListener<EnchantmentEvent<T>> listener, Enchantment base) {
        listeners.put(base, listener);
    }

    public Map<Enchantment, EnchantmentListener<EnchantmentEvent<T>>> getListeners() {
        return listeners;
    }

    public void callEvent(EnchantmentEvent<T> event, Enchantment base) {
        if (event.getItem().getEnchantments().isEmpty())
            return;
        listeners.forEach((enchant, listener) -> {
            if (enchant.equals(base))
                listener.apply(event);
        });
    }

    public void callEvent(EnchantmentEvent<T> event) {
        event.getItem().getEnchantments().keySet().stream()
                .filter(enchantment -> enchantment instanceof EnchantmentBase && listeners.containsKey(enchantment))
                .forEach(enchantment -> listeners.get(enchantment).apply(event));
    }
}
