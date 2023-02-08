package net.xunto.roleplaychat.dices;

import net.xunto.roleplaychat.RoleplayChatCore;
import net.xunto.roleplaychat.plugins.IPlugin;

public class RoleplayChatDices implements IPlugin {
    public void init(RoleplayChatCore core) {
        core.register(new RollCommand());
    }
}
