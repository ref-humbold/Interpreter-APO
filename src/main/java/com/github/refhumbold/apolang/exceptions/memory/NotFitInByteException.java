package com.github.refhumbold.apolang.exceptions.memory;

public class NotFitInByteException
        extends MemoryException
{
    private static final long serialVersionUID = 4928920087224720365L;

    public NotFitInByteException(int number)
    {
        super(String.format("Number %d to store does not fit in a byte", number));
    }
}
