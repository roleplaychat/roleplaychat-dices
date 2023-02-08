package net.xunto.roleplaychat.dices.parser.roll;

import net.xunto.roleplaychat.dices.parser.IResult;
import net.xunto.roleplaychat.dices.parser.IRoll;
import net.xunto.roleplaychat.dices.parser.result.ResultSub;

public class RollSub implements IRoll {
    private final IRoll roll1;
    private final IRoll roll2;

    public RollSub(IRoll roll1, IRoll roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    @Override
    public IResult roll() {
        return new ResultSub(
                this.roll1.roll(),
                this.roll2.roll()
        );
    }

    @Override
    public String getHumanReadable() {
        return String.format("%s-%s", roll1.getHumanReadable(), roll2.getHumanReadable());
    }
}
