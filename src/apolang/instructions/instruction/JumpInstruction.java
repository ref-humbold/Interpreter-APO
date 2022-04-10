package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.JumpBaseStatement;
import apolang.interpreter.Environment;

public class JumpInstruction<S extends JumpBaseStatement>
        extends AbstractInstruction<S>
{
    private AbstractInstruction<?> link;
    private boolean isJump = false;

    public JumpInstruction(int lineNumber, S statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
    }

    public AbstractInstruction<?> getLink()
    {
        return link;
    }

    public void setLink(AbstractInstruction<?> link)
    {
        this.link = link;
    }

    @Override
    public AbstractInstruction<?> getNextInstruction()
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
