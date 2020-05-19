package apolang.exceptions.arithmetic;

public class NegativeShiftException
        extends ArithmeticException
{
    private static final long serialVersionUID = -872827790358284108L;

    public NegativeShiftException(int lineNumber)
    {
        super("Shift by negative value", lineNumber);
    }
}
