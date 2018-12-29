package refhumbold.apolang.errors;

public class LanguageException
    extends Exception
{
    private static final long serialVersionUID = 6805782578123143695L;
    private int lineNumber;

    public LanguageException(String message, int lineNumber)
    {
        super(message);

        setLineNumber(lineNumber);
    }

    public LanguageException(String message)
    {
        this(message, null);
    }

    public LanguageException(String message, Throwable t)
    {
        super(message, t);
        lineNumber = -1;
    }

    public void setLineNumber(int lineNumber)
    {
        if(lineNumber < 0)
            throw new IllegalArgumentException("Line number has to be non-negative.");

        this.lineNumber = lineNumber;
    }

    @Override
    public String toString()
    {
        return lineNumber >= 0 ? getClass().getSimpleName() + " at line " + lineNumber + ": "
            + getMessage() : getClass().getSimpleName() + ": " + getMessage();
    }
}
