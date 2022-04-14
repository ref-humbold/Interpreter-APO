package apolang.instructions.statement.io;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.BaseStatement;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class ReadCharStatement
        implements BaseStatement
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