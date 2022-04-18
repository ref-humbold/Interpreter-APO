package apolang.instructions.statement.io;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;
import apolang.interpreter.io.IOConnector;

public class ReadIntStatement
        implements Statement
{
    private final IOConnector connector;

    public ReadIntStatement()
    {
        connector = IOConnector.getInstance();
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE};
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int value = connector.readInt();

        environment.setVariableValue(arguments[0], value);
        return StatementResult.NEXT;
    }
}
