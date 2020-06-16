package software.bigbade.enchantmenttokens.api.wrappers;

import co.aikar.taskchain.TaskChain;
import software.bigbade.enchantmenttokens.EnchantmentTokens;

import java.util.concurrent.TimeUnit;

public class EnchantmentChain {
    private final TaskChain<?> chain;

    public EnchantmentChain() {
        chain = EnchantmentTokens.newChain();
    }

    public EnchantmentChain(String name) {
        chain = EnchantmentTokens.newSharedChain(name);
    }

    public EnchantmentChain async(Runnable task) {
        chain.async(task::run);
        return this;
    }

    public EnchantmentChain sync(Runnable task) {
        chain.sync(task::run);
        return this;
    }

    public EnchantmentChain delay(int delay) {
        chain.delay(delay);
        return this;
    }

    public EnchantmentChain delay(int delay, TimeUnit unit) {
        chain.delay(delay, unit);
        return this;
    }

    public void execute() {
        chain.execute();
    }

    public void execute(Runnable done) {
        chain.execute(done);
    }
}
