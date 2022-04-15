package apolang.instructions.statement.memory;

import apolang.instructions.statement.BasicStatement;
import apolang.interpreter.memory.MemoryAccessor;
import apolang.interpreter.memory.MemoryInterface;

public abstract class AbstractMemoryStatement
        implements BasicStatement
{
    protected final MemoryInterface memory;

    protected AbstractMemoryStatement()
    {
        memory = MemoryAccessor.getInstance().getMemory();
    }
}
