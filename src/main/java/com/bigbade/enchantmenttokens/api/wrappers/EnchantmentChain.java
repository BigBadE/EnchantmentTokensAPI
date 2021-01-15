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

package com.bigbade.enchantmenttokens.api.wrappers;

import co.aikar.taskchain.SplitTaskChain;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainException;
import com.bigbade.enchantmenttokens.EnchantmentTokens;

import java.util.concurrent.TimeUnit;

public class EnchantmentChain<T> {
    private final TaskChain<T> chain;

    public EnchantmentChain() {
        chain = EnchantmentTokens.newChain();
    }

    public EnchantmentChain(TaskChain<T> chain) {
        this.chain = chain;
    }

    public EnchantmentChain(String name) {
        chain = EnchantmentTokens.newSharedChain(name);
    }

    public EnchantmentChain<T> async(EnchantmentTasks.EnchantmentTask task) {
        chain.async(task);
        return this;
    }

    public EnchantmentChain<T> asyncFirst(EnchantmentTasks.FirstEnchantmentTask<T> task) {
        chain.asyncFirst(task);
        return this;
    }

    public EnchantmentChain<T> asyncLast(EnchantmentTasks.LastEnchantmentTask<T> task) {
        chain.asyncLast(task);
        return this;
    }

    public EnchantmentChain<T> sync(EnchantmentTasks.EnchantmentTask task) {
        chain.sync(task);
        return this;
    }

    public EnchantmentChain<T> split() {
        return new EnchantmentChain<>(chain.split());
    }

    public EnchantmentChain<T> collect() {
        if (!(chain instanceof SplitTaskChain)) {
            throw new TaskChainException("Tried collecting a non-split chain!");
        }

        return new EnchantmentChain<>(((SplitTaskChain<T>) chain).collect());
    }

    public EnchantmentChain<T> delay(int delay) {
        chain.delay(delay);
        return this;
    }

    public EnchantmentChain<T> delay(int delay, TimeUnit unit) {
        chain.delay(delay, unit);
        return this;
    }

    public void execute() {
        chain.execute();
    }

    public void execute(EnchantmentTasks.EnchantmentTask done) {
        chain.execute(done::runGeneric);
    }
}
