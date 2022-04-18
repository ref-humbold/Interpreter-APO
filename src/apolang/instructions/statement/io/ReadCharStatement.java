package apolang.instructions.statement.io;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.Statement;
import apolang.interpreter.Environment;
import apolang.interpreter.io.IOConnector;

public class ReadCharStatement
        implements Statement<Void>
{
    private final IOConnector connector;

    public ReadCharStatement()
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
        int value = connector.readChar();

        environment.setVariableValue(arguments[0], value);
        return null;
    }
}
