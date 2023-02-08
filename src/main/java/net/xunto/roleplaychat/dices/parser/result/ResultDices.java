package net.xunto.roleplaychat.dices.parser.result;

import net.xunto.roleplaychat.dices.parser.IResult;
import net.xunto.roleplaychat.framework.state.values.colored_array.ColoredBuilder;

import java.util.List;
import java.util.Optional;

public class ResultDices implements IResult {
    private List<ResultDice> dices;

    public ResultDices(List<ResultDice> dices) {
        this.dices = dices;
    }

    @Override
    public int getFinalResult() {
        Optional<Integer> reduce = this.dices.stream().map(ResultDice::getFinalResult).reduce(Integer::sum);
        return reduce.get();
    }

    @Override
    public ColoredBuilder getColoredResult() {
        ColoredBuilder coloredBuilder = new ColoredBuilder();
        return this.getColoredResult(coloredBuilder);
    }

    @Override
    public ColoredBuilder getColoredResult(ColoredBuilder builder) {
        if (dices.size() > 10) {
            builder.add("{");
            builder.add(Integer.toString(this.getFinalResult()));
            builder.add("}");
            return builder;
        }

        builder.add("[");
        for (int i = 0; i < this.dices.size(); i++) {
            ResultDice dice = this.dices.get(i);
            dice.getColoredResult(builder);
            if (i != this.dices.size() - 1) builder.add(";");
        }
        builder.add("]");

        return builder;
    }
}
