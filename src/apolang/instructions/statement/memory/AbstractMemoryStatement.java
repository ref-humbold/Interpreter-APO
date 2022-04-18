package apolang.instructions.statement.memory;

import apolang.instructions.statement.Statement;
import apolang.interpreter.memory.MemoryAccessor;
import apolang.interpreter.memory.MemoryInterface;

public abstract class AbstractMemoryStatement
        implements Statement<Void>
{
    protected final MemoryInterface memory;

    protected AbstractMemoryStatement()
    {
        memory = MemoryAccessor.getInstance().getMemory();
    }
}
