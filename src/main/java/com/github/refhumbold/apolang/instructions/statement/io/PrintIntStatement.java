package com.github.refhumbold.apolang.instructions.statement.io;

import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;
import com.github.refhumbold.apolang.interpreter.io.IOConnector;

public class PrintIntStatement
        implements Statement
{
    private final IOConnector connector;

    public PrintIntStatement()
    {
        connector = IOConnector.getInstance();
    }

    @Override
    public StatementName getName()
    {
        return StatementName.PTINT;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ ArgumentType.VARIABLE_READ };
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
    {
        int argValue = environment.getVariableValue(arguments[0]);

        connector.printInt(argValue);
        return StatementResult.NEXT;
    }
}
