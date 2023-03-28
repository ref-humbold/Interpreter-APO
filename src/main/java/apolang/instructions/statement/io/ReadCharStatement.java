package apolang.instructions.statement.io;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementName;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;
import apolang.interpreter.io.IOConnector;

public class ReadCharStatement
        implements Statement
{
    private final IOConnector connector;

    public ReadCharStatement()
    {
        connector = IOConnector.getInstance();
    }

    @Override
    public StatementName getName()
    {
        return StatementName.RDCHR;
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
        int value = connector.readChar();

        environment.setVariableValue(arguments[0], value);
        return StatementResult.NEXT;
    }
}
