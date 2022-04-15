package apolang.instructions.statement.logical;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.BasicStatement;
import apolang.interpreter.Environment;

public class NotStatement
        implements BasicStatement
{
    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE, ArgumentType.VARIABLE_READ};
    }

    @Override
    public Void execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue = environment.getVariableValue(arguments[1]);

        environment.setVariableValue(arguments[0], ~argValue);
        return null;
    }
}
