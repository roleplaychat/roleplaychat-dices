package net.xunto.roleplaychat.dices.parser;

public interface IRoll {
    IResult roll();

    String getHumanReadable();
}
