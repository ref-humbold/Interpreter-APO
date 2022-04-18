package apolang.instructions.statement;

import apolang.instructions.ArgumentType;
import apolang.interpreter.Environment;

public class NoOperationStatement
        implements Statement<Void>
{
    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[0];
    }

    @Override
    public Void execute(Environment environment, String... arguments)
    {
        return null;
    }
}
