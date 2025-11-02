package com.github.refhumbold.apolang.exceptions.symbol;

public class NotExistingInstructionException
        extends SymbolException
{
    private static final long serialVersionUID = 2408207594554020692L;

    public NotExistingInstructionException(String name, Exception e)
    {
        super(String.format("Not existing instruction `%s`", name), e);
    }
}
