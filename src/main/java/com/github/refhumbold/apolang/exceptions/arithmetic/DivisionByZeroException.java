package com.github.refhumbold.apolang.exceptions.arithmetic;

public class DivisionByZeroException
        extends ArithmeticException
{
    private static final long serialVersionUID = -500075837106471953L;

    public DivisionByZeroException()
    {
        super("Division by zero");
    }

    public DivisionByZeroException(int lineNumber)
    {
        super("Division by zero", lineNumber);
    }
}
