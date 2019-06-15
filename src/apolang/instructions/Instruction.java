package apolang.instructions;

import java.util.Arrays;

import apolang.errors.LanguageException;
import apolang.interpret.VariableSet;

/** Bazowa klasa do przechowywania pojedynczej instrukcji w liscie rozkazow */
public abstract class Instruction
{
    /** Numer wiersza programu */
    protected int lineNumber;

    /** Nazwa operacji */
    protected InstructionName name;

    /** Argumenty operacji */
    protected int[] args;

    /** Nastepny element listy */
    protected Instruction next = null;

    public Instruction(int lineNumber, InstructionName name, int... args)
    {
        if(name == null)
            throw new IllegalArgumentException("Instruction name is null.");

        this.lineNumber = lineNumber;
        this.name = name;
        this.args = args;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public InstructionName getName()
    {
        return name;
    }

    public int getArgsNumber()
    {
        return args.length;
    }

    public Instruction getNext()
    {
        return next;
    }

    public void setNext(Instruction next)
    {
        this.next = next;
    }

    public int getArg(int index)
    {
        return args[index];
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(!(obj instanceof Instruction))
            return false;

        Instruction other = (Instruction)obj;

        return lineNumber == other.lineNumber && name.equals(other.name) && Arrays.equals(args,
                                                                                          other.args);
    }

    @Override
    public int hashCode()
    {
        int prime = 37;
        int result = 1;

        result = prime * result + Arrays.hashCode(args);
        result = prime * result + (name == null ? 0 : name.hashCode());

        return result;
    }

    public abstract void execute(VariableSet variables)
            throws LanguageException;
}
