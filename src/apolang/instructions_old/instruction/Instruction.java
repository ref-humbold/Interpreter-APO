package apolang.instructions_old.instruction;

import java.util.Arrays;
import java.util.Objects;

import apolang.exceptions.LanguageException;
import apolang.instructions_old.InstructionName;
import apolang.interpreter.Environment;

public abstract class Instruction
{
    final int lineNumber;
    final InstructionName name;
    final String[] arguments;
    Instruction next = null;

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

    public Instruction getNextExecuted()
    {
        return next;
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

        return Objects.equals(lineNumber, other.lineNumber) && Objects.equals(name, other.name)
                && Arrays.equals(arguments, other.arguments);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(lineNumber, name, arguments);
    }

    public abstract void execute(Environment environment)
            throws LanguageException;
}
