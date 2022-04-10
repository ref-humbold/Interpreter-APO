package apolang.instructions.instruction.logical;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.BaseInstruction;
import apolang.interpreter.Environment;

public class NotInstruction
        implements BaseInstruction
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
