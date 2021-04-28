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

package com.bigbade.enchantmenttokens.utils.listeners;

import lombok.Getter;
import org.bukkit.event.Event;
import com.bigbade.enchantmenttokens.api.ListenerType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class TypedListenerHandler {
    private final Map<ListenerType, ListenerManager<?>> listenerManager = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends Event> ListenerManager<T> getManager(ListenerType type) {
        return (ListenerManager<T>) listenerManager.get(type);
    }

    public void register(ListenerType type, ListenerManager<?> manager) {
        listenerManager.put(type, manager);
    }
}