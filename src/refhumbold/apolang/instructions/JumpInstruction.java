package refhumbold.apolang.instructions;

import refhumbold.apolang.errors.SymbolException;
import refhumbold.apolang.interpret.VariableSet;

/**
 * Klasa przechowujaca pojedyncza instrukcje skoku w liscie rozkazow.
 * @see Instruction
 */
public class JumpInstruction
    extends Instruction
{
    private Instruction link = null;
    private boolean isJump = false;

    public JumpInstruction(int lineNumber, InstructionName name, int... args)
    {
        super(lineNumber, name, args);
    }

    /**
     * Przechodzi do nastepnej instrukcji zaleznej od wykonania skoku.
     * @return nastepna instrukcja do wykonania
     */
    @Override
    public Instruction getNext()
    {
        return isJump ? link : next;
    }

    /**
     * Ustawia wskaznik do instrukcji, do ktorej moze zostac wykonany skok.
     * @param link referencja do instrukcji
     */
    public void setLink(Instruction link)
    {
        this.link = link;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(!(obj instanceof JumpInstruction))
            return false;

        JumpInstruction other = (JumpInstruction)obj;

        return super.equals(other) && (link == null ? other.link == null : link.equals(other.link));
    }

    @Override
    public int hashCode()
    {
        int prime = 37;

        return prime * super.hashCode() + (link == null ? 0 : link.hashCode());
    }

    @Override
    public void execute(VariableSet variables)
        throws SymbolException
    {
        int argValue0;
        int argValue1;

        try
        {
            argValue0 = variables.getValue(args[0]);
            argValue1 = variables.getValue(args[1]);
        }
        catch(SymbolException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }

        switch(name)
        {
            case JUMP:
                isJump = true;
                break;

            case JPEQ:
                isJump = argValue0 == argValue1;
                break;

            case JPNE:
                isJump = argValue0 != argValue1;
                break;

            case JPLT:
                isJump = argValue0 < argValue1;
                break;

            case JPGT:
                isJump = argValue0 > argValue1;
                break;

            default:
                break;
        }
    }
}
