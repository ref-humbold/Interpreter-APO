package com.github.refhumbold.apolang.instructions.statement;

import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.interpreter.Environment;

public class ExitStatement
        implements Statement
{
    @Override
    public StatementName getName()
    {
        return null;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[0];
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
    {
        return StatementResult.EXIT;
    }
}
