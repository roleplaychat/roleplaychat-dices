package net.xunto.roleplaychat.dices.parser.result;

import net.xunto.roleplaychat.dices.parser.IResult;
import net.xunto.roleplaychat.dices.parser.roll.RollDice;
import net.xunto.roleplaychat.framework.state.values.colored_array.ColoredBuilder;

public class ResultDice implements IResult {
    private RollDice dice;
    private int result;

    public ResultDice(RollDice dice, int result) {
        this.dice = dice;
        this.result = result;
    }

    @Override
    public int getFinalResult() {
        return this.result;
    }

    @Override
    public ColoredBuilder getColoredResult() {
        ColoredBuilder coloredBuilder = new ColoredBuilder();
        return this.getColoredResult(coloredBuilder);
    }

    @Override
    public ColoredBuilder getColoredResult(ColoredBuilder builder) {
        String str = Integer.toString(this.result);

        if (dice.getSides() == this.result) builder.add(str, "critical_success");
        else if (this.result == 1) builder.add(str, "critical_failure");
        else builder.add(str);

        return builder;
    }
}
