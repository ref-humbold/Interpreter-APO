package apolang.errors;

public class SymbolException
        extends LanguageException
{
    private static final long serialVersionUID = -7197078580020744575L;

    public SymbolException(String message)
    {
        super(message);
    }

    public SymbolException(String message, Throwable t)
    {
        super(message, t);
    }
}
