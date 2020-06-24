package software.bigbade.enchantmenttokens.api.wrappers;

import co.aikar.taskchain.SplitTaskChain;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainException;
import software.bigbade.enchantmenttokens.EnchantmentTokens;

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
