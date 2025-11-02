package com.github.refhumbold.apolang.exceptions.symbol;

public class VariableNotInitializedException
        extends SymbolException
{
    private static final long serialVersionUID = -9170383122947011718L;

    public VariableNotInitializedException(String variable)
    {
        super(String.format("Variable `%s` was not initialized", variable));
    }
}
