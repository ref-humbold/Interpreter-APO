package com.github.refhumbold.apolang.instructions.instruction;

import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;

public class SimpleInstruction
        extends Instruction
{
    public SimpleInstruction(int lineNumber, Statement statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
    }

    @Override
    public Instruction getNextExecuted()
    {
        return result == StatementResult.EXIT ? null : next;
    }
}
