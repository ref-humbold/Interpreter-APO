package apolang.interpreter.external;

import apolang.errors.MemoryException;

public class Memory
{
    private int[] memory;

    public Memory(int memorySize)
    {
        memory = new int[memorySize * 256];
        System.out.println("memory>> " + memorySize + "kB allocated");
    }

    public int loadWord(int address)
            throws MemoryException
    {
        if(address < 0 || address >= memory.length)
            throw new MemoryException(MemoryException.OUT_OF_MEMORY);

        if(address % 4 > 0)
            throw new MemoryException(MemoryException.ADDRESS_NOT_A_WORD);

        return memory[address / 4];
    }

    public void storeWord(int address, int value)
            throws MemoryException
    {
        if(address < 0 || address >= memory.length)
            throw new MemoryException(MemoryException.OUT_OF_MEMORY);

        if(address % 4 > 0)
            throw new MemoryException(MemoryException.ADDRESS_NOT_A_WORD);

        memory[address / 4] = value;
    }

    public int loadByte(int address)
            throws MemoryException
    {
        if(address < 0 || address >= memory.length)
            throw new MemoryException(MemoryException.OUT_OF_MEMORY);

        int shift = (3 - address % 4) * 8;

        return (memory[address / 4] & 0xFF << shift) >>> shift;
    }

    public void storeByte(int address, int value)
            throws MemoryException
    {
        if(address < 0 || address >= memory.length)
            throw new MemoryException(MemoryException.OUT_OF_MEMORY);

        if(value < 0 || value >= 256)
            throw new MemoryException(MemoryException.NUMBER_NOT_IN_BYTE);

        int shift = (3 - address % 4) * 8;
        int tmp = memory[address / 4] & ~(0xFF << shift);

        memory[address / 4] = tmp | value << shift;
    }
}
