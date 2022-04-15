package apolang.instructions.statement.io;

import apolang.instructions.ArgumentType;
import apolang.instructions.statement.BasicStatement;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class PrintIntStatement
        implements BasicStatement
{
    private final IOConnector connector;

    public PrintIntStatement()
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

        connector.printInt(argValue);
        return null;
    }
}
