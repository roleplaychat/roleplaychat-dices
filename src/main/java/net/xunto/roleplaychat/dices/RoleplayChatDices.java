package net.xunto.roleplaychat.dices;

import net.xunto.roleplaychat.RoleplayChatCore;

public class RoleplayChatDices implements IPlugin {
    public static void init(RoleplayChatCore core) {
        core.register(new RollCommand());
    }
}
