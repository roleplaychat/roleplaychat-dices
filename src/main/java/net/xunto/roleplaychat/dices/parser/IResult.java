package net.xunto.roleplaychat.dices.parser;

import net.xunto.roleplaychat.framework.state.values.colored_array.ColoredBuilder;

public interface IResult {
    int getFinalResult();

    ColoredBuilder getColoredResult();

    ColoredBuilder getColoredResult(ColoredBuilder builder);
}
