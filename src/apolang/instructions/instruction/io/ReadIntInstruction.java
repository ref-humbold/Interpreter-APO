package apolang.instructions.instruction.io;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.Instruction;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class ReadIntInstruction
        implements Instruction<Void>
{
    private final IOConnector connector;

    public ReadIntInstruction()
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
        int value = connector.readInt();

        environment.setVariableValue(arguments[0], value);
        return null;
    }
}