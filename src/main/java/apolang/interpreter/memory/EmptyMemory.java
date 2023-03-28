package apolang.interpreter.memory;

import apolang.exceptions.memory.MemoryException;
import apolang.exceptions.memory.OutOfMemoryException;

public class EmptyMemory
        implements MemoryInterface
{
    @Override
    public int loadWord(int address)
            throws MemoryException
    {
        throw new OutOfMemoryException();
    }

    @Override
    public void storeWord(int address, int value)
            throws MemoryException
    {
        throw new OutOfMemoryException();
    }

    @Override
    public int loadByte(int address)
            throws MemoryException
    {
        throw new OutOfMemoryException();
    }

    @Override
    public void storeByte(int address, int value)
            throws MemoryException
    {
        throw new OutOfMemoryException();
    }
}
