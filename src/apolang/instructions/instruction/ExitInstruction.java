package apolang.instructions.instruction;

import apolang.instructions.statement.ExitStatement;
import apolang.interpreter.Environment;

public class ExitInstruction
        extends Instruction<ExitStatement>
{
    public ExitInstruction(int lineNumber, ExitStatement statement, String... arguments)
    {
        super(lineNumber, statement, arguments);
    }

    @Override
    public boolean execute(Environment environment)
    {
        statement.execute(environment, arguments);
        return false;
    }
}
