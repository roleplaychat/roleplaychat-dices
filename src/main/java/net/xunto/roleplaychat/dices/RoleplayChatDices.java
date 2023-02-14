package net.xunto.roleplaychat.dices;

import com.google.auto.service.AutoService;
import net.xunto.roleplaychat.RoleplayChatCore;
import net.xunto.roleplaychat.plugins.IPlugin;

@AutoService(IPlugin.class)
public class RoleplayChatDices implements IPlugin {
    public void init(RoleplayChatCore core) {
        core.register(new RollCommand());
    }
}
