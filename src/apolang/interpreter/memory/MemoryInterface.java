package apolang.interpreter.memory;

import apolang.exceptions.memory.MemoryException;

public interface MemoryInterface
{
    int loadWord(int address)
            throws MemoryException;

    void storeWord(int address, int value)
            throws MemoryException;

    int loadByte(int address)
            throws MemoryException;

    void storeByte(int address, int value)
            throws MemoryException;
}
