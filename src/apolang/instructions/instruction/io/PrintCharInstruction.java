package apolang.instructions.instruction.io;

import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.BaseInstruction;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class PrintCharInstruction
        implements BaseInstruction
{
    private final IOConnector connector;

    public PrintCharInstruction()
    {
        connector = IOConnector.getInstance();
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_READ};
    }

    @Override
    public Void execute(Environment environment, String... arguments)
    {
        int argValue = environment.getVariableValue(arguments[0]);

        connector.printChar(argValue);
        return null;
    }
}
