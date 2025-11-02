package com.github.refhumbold.apolang.instructions.statement.memory;

import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.interpreter.memory.MemoryAccessor;
import com.github.refhumbold.apolang.interpreter.memory.MemoryInterface;

public abstract class AbstractMemoryStatement
        implements Statement
{
    protected final MemoryInterface memory;

    protected AbstractMemoryStatement()
    {
        memory = MemoryAccessor.getInstance().getMemory();
    }
}
