package net.xunto.roleplaychat.dices;

import org.jparsec.error.ParserException;
import net.xunto.roleplaychat.RoleplayChatCore;
import net.xunto.roleplaychat.api.ICommand;
import net.xunto.roleplaychat.api.ISpeaker;
import net.xunto.roleplaychat.dices.parser.DiceParser;
import net.xunto.roleplaychat.dices.parser.IResult;
import net.xunto.roleplaychat.dices.parser.IRoll;
import net.xunto.roleplaychat.features.middleware.distance.Distance;
import net.xunto.roleplaychat.features.middleware.distance.DistanceMiddleware;
import net.xunto.roleplaychat.framework.api.Environment;
import net.xunto.roleplaychat.framework.api.Request;
import net.xunto.roleplaychat.framework.commands.CommandException;
import net.xunto.roleplaychat.framework.pebble.PebbleChatTemplate;
import net.xunto.roleplaychat.framework.renderer.ITemplate;
import net.xunto.roleplaychat.framework.text.TextColor;
import net.xunto.roleplaychat.framework.state.IProperty;
import net.xunto.roleplaychat.framework.state.MessageState;
import net.xunto.roleplaychat.framework.state.Property;
import net.xunto.roleplaychat.framework.state.values.colored_array.ColoredText;

import java.util.List;

public class RollCommand implements ICommand {
    public static IProperty<IRoll> roll = new Property<>("roll");
    public static IProperty<List<ColoredText>> result = new Property<>("result");
    public static IProperty<Integer> finalResult = new Property<>("final_result");

    private static ITemplate template = new PebbleChatTemplate("assets/roleplaychatdices/templates/dices.twig");

    public void sendDiceResult(ISpeaker speaker, IRoll roll, IResult result) {
        Request request = new Request("", speaker);
        Environment environment = new Environment(speaker.getName(), "");
        MessageState state = environment.getState();
        environment.setProcessed(true);
        environment.setTemplate(template);
        environment.getColors().put("default", TextColor.GRAY);
        environment.getColors().put("critical_success", TextColor.GREEN);
        environment.getColors().put("critical_failure", TextColor.RED);

        state.setValue(RollCommand.roll, roll);
        state.setValue(RollCommand.result, result.getColoredResult().build());
        state.setValue(RollCommand.finalResult, result.getFinalResult());
        state.setValue(DistanceMiddleware.FORCE_ENVIRONMENT, true);
        state.setValue(DistanceMiddleware.DISTANCE, Distance.NORMAL);

        RoleplayChatCore.instance.process(request, environment);
    }

    @Override
    public String getCommandName() {
        return "roll";
    }

    @Override
    public String[] getTabCompletion(ISpeaker iSpeaker, String[] strings) {
        return new String[0];
    }

    @Override
    public boolean canExecute(ISpeaker iSpeaker) {
        return true;
    }

    @Override
    public void execute(ISpeaker speaker, String[] args) throws CommandException {
        String arg = String.join("", args);

        IRoll roll;
        try {
            roll = DiceParser.parser().parse(arg);
            IResult result = roll.roll();
            this.sendDiceResult(speaker, roll, result);
        } catch (ParserException e) {
            throw new CommandException("Невозможно бросить такой дайс.");
        }
    }
}
