package apolang.instructions.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import apolang.instructions.instruction.Instruction;

public class ExecutionIterator
        implements Iterator<Instruction>
{
    private Instruction current = null;
    private Instruction next;

    ExecutionIterator(Instruction next)
    {
        this.next = next;
    }

    @Override
    public boolean hasNext()
    {
        return next != null;
    }

    @Override
    public Instruction next()
            throws NoSuchElementException
    {
        if(current != null)
            next = current.getNextExecuted();

        if(!hasNext())
            throw new NoSuchElementException();

        current = next;
        next = next.getNextExecuted();
        return current;
    }
}
