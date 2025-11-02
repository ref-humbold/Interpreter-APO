package com.github.refhumbold.apolang.instructions.statement.logical;

import com.github.refhumbold.apolang.exceptions.LanguageException;
import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;

public class NotStatement
        implements Statement
{
    @Override
    public StatementName getName()
    {
        return StatementName.NOT;
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
        int argValue = environment.getVariableValue(arguments[1]);

        environment.setVariableValue(arguments[0], ~argValue);
        return StatementResult.NEXT;
    }
}
