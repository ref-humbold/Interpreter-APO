package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

public class SimpleInstruction
        extends Instruction
{
    public SimpleInstruction(int lineNumber, Statement statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
    }

    @Override
    public Instruction getNextExecuted()
    {
        return next;
    }

    @Override
    public boolean execute(Environment environment)
            throws LanguageException
    {
        try
        {
            return statement.execute(environment, arguments) != StatementResult.EXIT;
        }
        catch(LanguageException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }
    }
}
