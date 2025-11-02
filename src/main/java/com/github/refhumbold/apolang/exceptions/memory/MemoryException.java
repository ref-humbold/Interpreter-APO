package com.github.refhumbold.apolang.exceptions.memory;

import com.github.refhumbold.apolang.exceptions.LanguageException;

public class MemoryException
        extends LanguageException
{
    private static final long serialVersionUID = 3780871563838319276L;

    public MemoryException(String message)
    {
        super(message);
    }
}
