package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.JumpBaseStatement;
import apolang.interpreter.Environment;

public class JumpInstruction<S extends JumpBaseStatement>
        extends Instruction<S>
{
    private Instruction<?> link;
    private boolean isJump = false;

    public JumpInstruction(int lineNumber, S statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
    }

    public Instruction<?> getLink()
    {
        return link;
    }

    public void setLink(Instruction<?> link)
    {
        this.link = link;
    }

    @Override
    public Instruction<?> getNextExecuted()
    {
        return isJump ? link : next;
    }

    @Override
    public boolean execute(Environment environment)
            throws LanguageException
    {
        try
        {
            isJump = statement.execute(environment, arguments);
            return true;
        }
        catch(LanguageException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }
    }
}
