package apolang.instructions.statement.memory;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.interpreter.Environment;

public class LoadByteStatement
        extends AbstractMemoryStatement
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
        int address = environment.getVariableValue(arguments[1]);
        int result = memory.loadByte(address);

        environment.setVariableValue(arguments[0], result);
        return null;
    }
}
