package apolang.instructions.statement;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.interpreter.Environment;

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
