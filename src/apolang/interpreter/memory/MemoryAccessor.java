package apolang.interpreter.memory;

public final class MemoryAccessor
{
    private static MemoryAccessor instance = null;
    private MemoryInterface memory = null;

    private MemoryAccessor()
    {
    }

    public static MemoryAccessor getInstance()
    {
        if(instance == null)
            instance = new MemoryAccessor();

        return instance;
    }

    public MemoryInterface getMemory()
    {
        if(memory == null)
            allocate(0);

        return memory;
    }

    public void allocate(int size)
    {
        memory = size <= 0 ? new EmptyMemory() : new Memory(size);
    }

    public void deallocate()
    {
        memory = null;
    }
}
