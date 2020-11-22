package apolang.instructions.list;

import java.util.Iterator;
import java.util.Objects;

import apolang.instructions.instruction.Instruction;

public class InstructionList
        implements Iterable<Instruction>
{
    private Instruction begin = null;
    private Instruction end = null;

    public boolean isEmpty()
    {
        return begin == null && end == null;
    }

    @Override
    public Iterator<Instruction> iterator()
    {
        return new InstructionIterator(begin);
    }

    public ExecutionIterator run()
    {
        return new ExecutionIterator(begin);
    }

    public void add(Instruction instruction)
    {
        Objects.requireNonNull(instruction);

        if(begin == null)
            begin = instruction;
        else
            end.setNext(instruction);

        end = instruction;
    }

    public Instruction getByLineNumber(int lineNumber)
    {
        Instruction iter = begin;

        while(iter != null && iter.getLineNumber() < lineNumber)
            iter = iter.getNext();

        return iter;
    }
}
