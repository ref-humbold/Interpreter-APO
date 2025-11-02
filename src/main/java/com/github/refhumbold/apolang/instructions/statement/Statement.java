package com.github.refhumbold.apolang.instructions.statement;

import com.github.refhumbold.apolang.exceptions.LanguageException;
import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.interpreter.Environment;

public interface Statement
{
    StatementName getName();

    ArgumentType[] getArgumentsTypes();

    StatementResult execute(Environment environment, String... arguments)
            throws LanguageException;

    default boolean hasValueSet()
    {
        ArgumentType[] argumentsTypes = getArgumentsTypes();

        return argumentsTypes.length > 0 && argumentsTypes[0] == ArgumentType.VARIABLE_WRITE;
    }
}
