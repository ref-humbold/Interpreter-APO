package apolang.instructions.statement.memory;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.StatementName;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

public class StoreByteStatement
        extends AbstractMemoryStatement
{
    @Override
    public StatementName getName()
    {
        return StatementName.STB;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_READ, ArgumentType.VARIABLE_READ};
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue = environment.getVariableValue(arguments[0]);
        int address = environment.getVariableValue(arguments[1]);

        memory.storeByte(address, argValue);
        return StatementResult.NEXT;
    }
}
