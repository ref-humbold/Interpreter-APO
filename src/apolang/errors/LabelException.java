package apolang.errors;

public class LabelException
        extends LanguageException
{
    public static final String LABEL_NOT_FOUND = "Label not found";
    public static final String DUPLICATED = "Duplicated label";
    public static final String INVALID_CHARACTERS = "Invalid characters in label name";
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
