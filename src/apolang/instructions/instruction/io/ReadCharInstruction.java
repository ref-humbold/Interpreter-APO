package apolang.instructions.instruction.io;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.BaseInstruction;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class ReadCharInstruction
        implements BaseInstruction
{
    private final IOConnector connector;

    public ReadCharInstruction()
    {
        connector = IOConnector.getInstance();
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE};
    }

    @Override
    public Void execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int value = connector.readChar();

        environment.setVariableValue(arguments[0], value);
        return null;
    }
}
