package apolang.instructions.statement.io;

import apolang.instructions.ArgumentType;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;
import apolang.interpreter.io.IOConnector;

public class PrintLineStatement
        implements Statement
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
    public StatementResult execute(Environment environment, String... arguments)
    {
        connector.printLine();
        return StatementResult.NEXT;
    }
}
