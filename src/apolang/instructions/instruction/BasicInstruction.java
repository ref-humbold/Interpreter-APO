package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.BasicStatement;
import apolang.interpreter.Environment;

public class BasicInstruction<S extends BasicStatement>
        extends Instruction<S>
{
    public BasicInstruction(int lineNumber, S statement, String... arguments)
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
