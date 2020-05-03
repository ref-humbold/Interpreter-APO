package apolang.instructions;

import java.util.Iterator;
import java.util.NoSuchElementException;

import apolang.interpreter.parser.OldParser;

/**
 * Klasa przechowujaca liste instrukcji programu. Lista zostaje utworzona podczas parsowania.
 * @see OldParser#parse
 */
public class InstructionList
        implements Iterable<Instruction>
{
    private Instruction begin = null;
    private Instruction end = null;

    public InstructionList()
    {
    }

    @Override
    public Iterator<Instruction> iterator()
    {
        return new InstructionIterator(begin);
    }

    /**
     * Dodaje nowa instrukcje do listy.
     * @param instruction instrukcja
     * @see Instruction
     */
    public void add(Instruction instruction)
    {
        if(instruction == null)
            throw new IllegalArgumentException("Instruction in list is null.");

        if(begin == null)
            begin = instruction;
        else
            end.setNext(instruction);

        end = instruction;
    }

    private final class InstructionIterator
            implements Iterator<Instruction>
    {
        private Instruction previous = null;
        private Instruction current;

        private InstructionIterator(Instruction current)
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
            if(previous != null)
            {
                Instruction next = previous.getNext();

                if(next != null && !next.equals(current))
                    current = next;
            }

            if(!hasNext())
                throw new NoSuchElementException();

            previous = current;
            current = current.getNext();

            return previous;
        }
    }
}
