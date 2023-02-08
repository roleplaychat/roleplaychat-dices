package net.xunto.roleplaychat.dices.parser;

import org.jparsec.OperatorTable;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;
import net.xunto.roleplaychat.dices.parser.roll.RollDice;
import net.xunto.roleplaychat.dices.parser.roll.RollNumber;
import net.xunto.roleplaychat.dices.parser.roll.RollSub;
import net.xunto.roleplaychat.dices.parser.roll.RollSum;

import java.util.function.BinaryOperator;

public class DiceParser {
    static final Parser<IRoll> NUMBER = Scanners.INTEGER.map(Integer::valueOf).map(RollNumber::new);

    static final Parser<IRoll> DICE = Parsers.sequence(
            Scanners.DEC_INTEGER.map(Integer::valueOf).asOptional(),
            Scanners.string("d"),
            Scanners.DEC_INTEGER.map(Integer::valueOf),
            (amount, d, sides) -> new RollDice(amount.orElse(1), sides)
    );

    static final BinaryOperator<IRoll> PLUS = RollSum::new;
    static final BinaryOperator<IRoll> MINUS = RollSub::new;

    private static <T> Parser<T> op(char ch, T value) {
        return Scanners.isChar(ch).retn(value);
    }

    public static Parser<IRoll> parser() {
        Parser.Reference<IRoll> ref = Parser.newReference();

        Parser<IRoll> term = DICE.or(NUMBER);

        Parser<IRoll> operator = new OperatorTable<IRoll>()
                .infixl(op('+', PLUS), 10)
                .infixl(op('-', MINUS), 10)
                .build(term);

        ref.set(operator);
        return operator;
    }
}
