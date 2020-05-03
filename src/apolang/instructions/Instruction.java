package apolang.instructions;

import java.util.Arrays;
import java.util.Objects;

import apolang.errors.LanguageException;
import apolang.interpret.VariableSet;

/** Bazowa klasa do przechowywania pojedynczej instrukcji w liscie rozkazow */
public abstract class Instruction
{
    protected int lineNumber;
    protected InstructionName name;
    protected int[] arguments;
    protected Instruction next = null;

    public Instruction(int lineNumber, InstructionName name, int... arguments)
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

    public int getArgument(int index)
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

    public abstract void execute(VariableSet variables)
            throws LanguageException;
}
