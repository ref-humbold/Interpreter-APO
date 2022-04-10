package apolang.instructions_old.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import apolang.instructions_old.instruction.Instruction;

public class InstructionIterator
        implements Iterator<Instruction>
{
    private Instruction current;

    InstructionIterator(Instruction current)
    {
        this.current = current;
    }

    @Override
    public boolean hasNext()
    {
        return current != null;
    }

    @Override
    public Instruction next()
            throws NoSuchElementException
    {
        if(!hasNext())
            throw new NoSuchElementException();

        Instruction previous = current;
        current = current.getNext();

        return previous;
    }
}
