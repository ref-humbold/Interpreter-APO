package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.BaseStatement;
import apolang.interpreter.Environment;

public class BaseInstruction<S extends BaseStatement>
        extends AbstractInstruction<S>
{
    public BaseInstruction(int lineNumber, S statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
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
