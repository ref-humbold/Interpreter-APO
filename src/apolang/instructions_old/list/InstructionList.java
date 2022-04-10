package apolang.instructions_old.list;

import java.util.Iterator;
import java.util.Objects;

import apolang.instructions_old.instruction.Instruction;

public class InstructionList
        implements Iterable<Instruction>
{
    private Instruction begin = null;
    private Instruction end = null;
    private int linesCount = 0;

    public boolean isEmpty()
    {
        return begin == null && end == null;
    }

    public int getLinesCount()
    {
        return linesCount;
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
        linesCount = end.getLineNumber();
    }

    public Instruction getByLineNumber(int lineNumber)
    {
        Instruction iter = begin;

        while(iter != null && iter.getLineNumber() < lineNumber)
            iter = iter.getNext();

        return iter;
    }
}
