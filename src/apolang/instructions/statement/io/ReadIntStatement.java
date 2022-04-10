package apolang.instructions.statement.io;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.BaseStatement;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class ReadIntStatement
        implements BaseStatement
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
    public Void execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int value = connector.readInt();

        environment.setVariableValue(arguments[0], value);
        return null;
    }
}
