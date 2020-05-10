package apolang.interpreter.external;

import apolang.errors.MemoryException;

public class Memory
{
    private final int[] memory;

    public Memory(int memorySize)
    {
        memory = new int[memorySize * 256];
        System.out.println("memory>> " + memorySize + "kB allocated");
    }

    public int loadWord(int address)
            throws MemoryException
    {
        validateMemory(address, true);
        return memory[address / 4];
    }

    public void storeWord(int address, int value)
            throws MemoryException
    {
        validateMemory(address, true);
        memory[address / 4] = value;
    }

    public int loadByte(int address)
            throws MemoryException
    {
        validateMemory(address, false);

        int shift = (3 - address % 4) * 8;

        return (memory[address / 4] & 0xFF << shift) >>> shift;
    }

    public void storeByte(int address, int value)
            throws MemoryException
    {
        validateMemory(address, false);

        if(value < 0 || value >= 256)
            throw new MemoryException(
                    String.format("Number %d to store does not fit in a byte", value));

        int shift = (3 - address % 4) * 8;
        int memoryValueMask = memory[address / 4] & ~(0xFF << shift);

        memory[address / 4] = memoryValueMask | value << shift;
    }

    private void validateMemory(int address, boolean isAligned)
            throws MemoryException
    {
        if(address < 0 || address >= memory.length)
            throw new MemoryException("Outside of available memory");

        if(isAligned && address % 4 > 0)
            throw new MemoryException(
                    String.format("Address %d is not aligned to a word", address));
    }
}
