package apolang.instruction;

import java.util.Arrays;
import java.util.Objects;

import apolang.exceptions.LanguageException;
import apolang.interpreter.Environment;

public abstract class Instruction
{
    protected int lineNumber;
    protected InstructionName name;
    protected String[] arguments;
    protected Instruction next = null;

    public Instruction(int lineNumber, InstructionName name, String... arguments)
    {
        this.lineNumber = lineNumber;
        this.name = name;
        this.arguments = arguments;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public InstructionName getName()
    {
        return name;
    }

    public int getArgumentsCount()
    {
        return arguments.length;
    }

    public Instruction getNext()
    {
        return next;
    }

    public void setNext(Instruction next)
    {
        this.next = next;
    }

    public String getArgument(int index)
    {
        return arguments[index];
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(!(obj instanceof Instruction))
            return false;

        Instruction other = (Instruction)obj;

        return lineNumber == other.lineNumber && name.equals(other.name) && Arrays.equals(arguments,
                                                                                          other.arguments);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, arguments);
    }

    public abstract void execute(Environment environment)
            throws LanguageException;
}
