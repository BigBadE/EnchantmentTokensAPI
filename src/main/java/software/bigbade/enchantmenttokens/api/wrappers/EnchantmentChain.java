package software.bigbade.enchantmenttokens.api.wrappers;

import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainTasks;
import software.bigbade.enchantmenttokens.EnchantmentTokens;

public class EnchantmentChain {
    private final TaskChain<?> chain;

    public EnchantmentChain() {
        chain = EnchantmentTokens.newChain();
    }

    public void async(TaskChainTasks.GenericTask task) {
        chain.async(task);
    }

    public void execute() {
        chain.execute();
    }
}
