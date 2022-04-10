package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.interpreter.Environment;

public interface Instruction<R>
{
    ArgumentType[] getArgumentsTypes();

    R execute(Environment environment, String... arguments)
            throws LanguageException;

    default boolean hasValueSet()
    {
        ArgumentType[] argumentsTypes = getArgumentsTypes();

        return argumentsTypes.length > 0 && argumentsTypes[0] == ArgumentType.VARIABLE_WRITE;
    }
}
