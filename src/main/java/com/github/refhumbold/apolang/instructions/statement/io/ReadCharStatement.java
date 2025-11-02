package com.github.refhumbold.apolang.instructions.statement.io;

import com.github.refhumbold.apolang.exceptions.LanguageException;
import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;
import com.github.refhumbold.apolang.interpreter.io.IOConnector;

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
        return new ArgumentType[]{ ArgumentType.VARIABLE_WRITE };
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
