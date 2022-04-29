package apolang.instructions.statement;

import apolang.instructions.ArgumentType;
import apolang.interpreter.Environment;

public class NoOperationStatement
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
        return StatementResult.NEXT;
    }
}
