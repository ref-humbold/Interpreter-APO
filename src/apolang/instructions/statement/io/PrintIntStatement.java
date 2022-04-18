package apolang.instructions.statement.io;

import apolang.instructions.ArgumentType;
import apolang.instructions.statement.Statement;
import apolang.interpreter.Environment;
import apolang.interpreter.io.IOConnector;

public class PrintIntStatement
        implements Statement<Void>
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
