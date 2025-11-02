package com.github.refhumbold.apolang.exceptions.label;

public class InvalidLabelNameException
        extends LabelException
{
    private static final long serialVersionUID = 7826638596152840925L;

    public InvalidLabelNameException()
    {
        super("Label name should start with uppercase letter and be followed by at least one lowercase letter");
    }
}
