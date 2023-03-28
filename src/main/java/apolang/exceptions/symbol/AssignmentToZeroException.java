package apolang.exceptions.symbol;

import apolang.interpreter.Environment;

public class AssignmentToZeroException
        extends SymbolException
{
    private static final long serialVersionUID = 7065380244090516093L;

    public AssignmentToZeroException()
    {
        super(String.format("Cannot assign to variable `%s`", Environment.ZERO_VARIABLE));
    }
}
