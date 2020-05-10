package apolang.errors;

public class LanguageException
        extends Exception
{
    private static final long serialVersionUID = 6805782578123143695L;
    private int lineNumber;

    public LanguageException(String message, int lineNumber)
    {
        super(message);
        this.lineNumber = lineNumber;
    }

    public LanguageException(String message)
    {
        this(message, -1);
    }

    public LanguageException(String message, Throwable t)
    {
        super(message, t);
        lineNumber = -1;
    }

    public void setLineNumber(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString()
    {
        return lineNumber >= 0 ? String.format("%s at line %d: %s", getClass().getSimpleName(),
                                               lineNumber, getMessage())
                               : String.format("%s: %s", getClass().getSimpleName(), getMessage());
    }
}
