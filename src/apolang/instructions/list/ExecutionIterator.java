package apolang.instructions.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import apolang.instructions.instruction.Instruction;

public class ExecutionIterator
        implements Iterator<Instruction<?>>
{
    private Instruction<?> previous = null;
    private Instruction<?> current;

    ExecutionIterator(Instruction<?> current)
    {
        this.current = current;
    }

    @Override
    public boolean hasNext()
    {
        return current != null;
    }

    @Override
    public Instruction<?> next()
            throws NoSuchElementException
    {
        if(previous != null)
        {
            Instruction<?> next = previous.getNextExecuted();

            if(next != null && !next.equals(current))
                current = next;
        }

        if(!hasNext())
            throw new NoSuchElementException();

        previous = current;
        current = current.getNextExecuted();

        return previous;
    }
}
