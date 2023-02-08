package net.xunto.roleplaychat.dices.parser.roll;

import net.xunto.roleplaychat.dices.parser.IResult;
import net.xunto.roleplaychat.dices.parser.IRoll;
import net.xunto.roleplaychat.dices.parser.result.ResultDice;
import net.xunto.roleplaychat.dices.parser.result.ResultDices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RollDice implements IRoll {
    public static Random random = new Random();

    private int amount;
    private int sides;

    public RollDice(int amount, int sides) {
        this.amount = amount;
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    @Override
    public IResult roll() {
        List<ResultDice> dices = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            dices.add(new ResultDice(this, random.nextInt(this.sides) + 1));
        }

        return new ResultDices(dices);
    }

    @Override
    public String getHumanReadable() {
        StringBuilder builder = new StringBuilder();

        if (this.amount > 1) builder.append(this.amount);

        builder.append("d");
        builder.append(this.sides);

        return builder.toString();
    }
}
