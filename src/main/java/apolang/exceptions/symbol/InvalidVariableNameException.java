package apolang.exceptions.symbol;

public class InvalidVariableNameException
        extends SymbolException
{
    private static final long serialVersionUID = 7653762245438474032L;

    public InvalidVariableNameException()
    {
        super("Variable name should be all in lowercase letters");
    }
}
