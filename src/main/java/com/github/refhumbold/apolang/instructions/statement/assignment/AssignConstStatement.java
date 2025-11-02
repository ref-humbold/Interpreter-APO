package com.github.refhumbold.apolang.instructions.statement.assignment;

import com.github.refhumbold.apolang.exceptions.LanguageException;
import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;

public class AssignConstStatement
        implements Statement
{
    @Override
    public StatementName getName()
    {
        return StatementName.ASGNC;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ ArgumentType.VARIABLE_WRITE, ArgumentType.CONSTANT };
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue = Integer.parseInt(arguments[1]);

        environment.setVariableValue(arguments[0], argValue);
        return StatementResult.NEXT;
    }
}
