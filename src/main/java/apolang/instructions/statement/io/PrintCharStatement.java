package apolang.instructions.statement.io;

import apolang.instructions.ArgumentType;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementName;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;
import apolang.interpreter.io.IOConnector;

public class PrintCharStatement
        implements Statement
{
    private final IOConnector connector;

    public PrintCharStatement()
    {
        connector = IOConnector.getInstance();
    }

    @Override
    public StatementName getName()
    {
        return StatementName.PTCHR;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_READ};
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
    {
        int argValue = environment.getVariableValue(arguments[0]);

        connector.printChar(argValue);
        return StatementResult.NEXT;
    }
}
