package apolang.instructions.statement.io;

import apolang.instructions.ArgumentType;
import apolang.instructions.statement.BasicStatement;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class PrintLineStatement
        implements BasicStatement
{
    private final IOConnector connector;

    public PrintLineStatement()
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
