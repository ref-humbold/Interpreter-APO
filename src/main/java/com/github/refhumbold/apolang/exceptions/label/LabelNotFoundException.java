package com.github.refhumbold.apolang.exceptions.label;

public class LabelNotFoundException
        extends LabelException
{
    private static final long serialVersionUID = -6765472897765411388L;

    public LabelNotFoundException(String label)
    {
        super(String.format("Label `%s` not found", label));
    }

    public LabelNotFoundException(String label, int lineNumber)
    {
        super(String.format("Label `%s` not found", label), lineNumber);
    }
}
