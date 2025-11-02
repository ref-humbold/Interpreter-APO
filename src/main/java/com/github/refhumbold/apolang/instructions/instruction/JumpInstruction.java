package com.github.refhumbold.apolang.instructions.instruction;

import com.github.refhumbold.apolang.instructions.statement.JumpBaseStatement;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;

public class JumpInstruction
        extends Instruction
{
    private Instruction link;

    public JumpInstruction(int lineNumber, JumpBaseStatement statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
    }

    public Instruction getLink()
    {
        return link;
    }

    public void setLink(Instruction link)
    {
        this.link = link;
    }

    @Override
    public Instruction getNextExecuted()
    {
        return result == StatementResult.JUMP ? link : next;
    }
}
