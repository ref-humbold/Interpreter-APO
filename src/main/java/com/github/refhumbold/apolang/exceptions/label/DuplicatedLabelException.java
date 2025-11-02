package com.github.refhumbold.apolang.exceptions.label;

public class DuplicatedLabelException
        extends LabelException
{
    private static final long serialVersionUID = 2627508592196304633L;

    public DuplicatedLabelException(String label, int lineNumber)
    {
        super(String.format("Duplicated label `%s`", label), lineNumber);
    }
}
