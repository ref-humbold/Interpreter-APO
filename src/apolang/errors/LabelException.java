package apolang.errors;

public class LabelException
        extends LanguageException
{
    private static final long serialVersionUID = 7536066731780429351L;

    public LabelException(String message, int lineNumber)
    {
        super(message, lineNumber);
    }

    public LabelException(String message)
    {
        super(message);
    }
}
