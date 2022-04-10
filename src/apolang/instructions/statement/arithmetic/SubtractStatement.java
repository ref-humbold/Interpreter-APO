package apolang.instructions.statement.arithmetic;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.BaseStatement;
import apolang.interpreter.Environment;

public class SubtractStatement
        implements BaseStatement
{
    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE, ArgumentType.VARIABLE_READ,
                                  ArgumentType.VARIABLE_READ};
    }

    @Override
    public Void execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue1 = environment.getVariableValue(arguments[1]);
        int argValue2 = environment.getVariableValue(arguments[2]);

        environment.setVariableValue(arguments[0], argValue1 - argValue2);
        return null;
    }
}
