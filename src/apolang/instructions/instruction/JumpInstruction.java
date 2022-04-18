package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.JumpBaseStatement;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

public class JumpInstruction
        extends Instruction
{
    private Instruction link;
    private boolean isJump = false;

    public JumpInstruction(int lineNumber, JumpBaseStatement statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
    }

    public Instruction getLink()
    {
        return link;
    }

    public void setLink(Instruction link)
    {
        this.link = link;
    }

    @Override
    public Instruction getNextExecuted()
    {
        return isJump ? link : next;
    }

    @Override
    public boolean execute(Environment environment)
            throws LanguageException
    {
        try
        {
            isJump = statement.execute(environment, arguments) == StatementResult.JUMP;
            return true;
        }
        catch(LanguageException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }
    }
}
