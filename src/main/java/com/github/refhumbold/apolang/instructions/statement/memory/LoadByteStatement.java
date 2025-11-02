package com.github.refhumbold.apolang.instructions.statement.memory;

import com.github.refhumbold.apolang.exceptions.LanguageException;
import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;

public class LoadByteStatement
        extends AbstractMemoryStatement
{
    @Override
    public StatementName getName()
    {
        return StatementName.LDB;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ ArgumentType.VARIABLE_WRITE, ArgumentType.VARIABLE_READ };
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int address = environment.getVariableValue(arguments[1]);
        int result = memory.loadByte(address);

        environment.setVariableValue(arguments[0], result);
        return StatementResult.NEXT;
    }
}
