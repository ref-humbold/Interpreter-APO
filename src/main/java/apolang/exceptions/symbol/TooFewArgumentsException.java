package apolang.exceptions.symbol;

import apolang.instructions.statement.StatementName;

public class TooFewArgumentsException
        extends SymbolException
{
    private static final long serialVersionUID = 921781665153657780L;

    public TooFewArgumentsException(StatementName statementName)
    {
        super(String.format("Too few arguments for instruction `%s`", statementName));
    }
}
