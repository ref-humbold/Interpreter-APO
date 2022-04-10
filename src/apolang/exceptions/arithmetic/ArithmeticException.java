package apolang.exceptions.arithmetic;

import apolang.exceptions.LanguageException;

public class ArithmeticException
        extends LanguageException
{
    private static final long serialVersionUID = -5079970583212010548L;

    public ArithmeticException(String message)
    {
        super(message);
    }

    public ArithmeticException(String message, int lineNumber)
    {
        super(message, lineNumber);
    }

    public ArithmeticException(String message, Exception e)
    {
        super(message, e);
    }
}
