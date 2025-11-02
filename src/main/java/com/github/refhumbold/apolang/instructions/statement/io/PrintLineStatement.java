package com.github.refhumbold.apolang.instructions.statement.io;

import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;
import com.github.refhumbold.apolang.interpreter.io.IOConnector;

public class PrintLineStatement
        implements Statement
{
    private final IOConnector connector;

    public PrintLineStatement()
    {
        connector = IOConnector.getInstance();
    }

    @Override
    public StatementName getName()
    {
        return StatementName.PTLN;
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
