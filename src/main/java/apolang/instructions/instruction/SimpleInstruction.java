package apolang.instructions.instruction;

import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementResult;

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
        return result == StatementResult.EXIT ? null : next;
    }
}
