package com.github.refhumbold.apolang.instructions.statement;

public interface JumpBaseStatement
        extends Statement
{
    @Override
    default StatementName getName()
    {
        return null;
    }
}
