package apolang.instructions.instruction.io;

import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.Instruction;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class PrintLineInstruction
        implements Instruction<Void>
{
    private final IOConnector connector;

    public PrintLineInstruction()
    {
        connector = IOConnector.getInstance();
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[0];
    }

    @Override
    public Void execute(Environment environment, String... arguments)
    {
        connector.printLine();
        return null;
    }
}
