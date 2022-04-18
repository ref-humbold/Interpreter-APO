package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.Statement;
import apolang.interpreter.Environment;

public class SimpleInstruction<S extends Statement<?>>
        extends Instruction<S>
{
    public SimpleInstruction(int lineNumber, S statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
    }

    @Override
    public Instruction<?> getNextExecuted()
    {
        return next;
    }

    @Override
    public boolean execute(Environment environment)
            throws LanguageException
    {
        try
        {
            statement.execute(environment, arguments);
            return true;
        }
        catch(LanguageException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }
    }
}
