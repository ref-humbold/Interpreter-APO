package com.github.refhumbold.apolang.exceptions.memory;

public class OutOfMemoryException
        extends MemoryException
{
    private static final long serialVersionUID = -8712305748532440779L;

    public OutOfMemoryException()
    {
        super("Outside of available memory");
    }
}
