package apolang.instructions;

import apolang.instructions.instruction.Instruction;
import apolang.instructions.instruction.JumpInstruction;
import apolang.instructions.instruction.SimpleInstruction;
import apolang.instructions.statement.JumpBaseStatement;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementName;

public final class InstructionFactory
{
    private static InstructionFactory instance;

    private InstructionFactory()
    {
    }

    public static InstructionFactory getInstance()
    {
        if(instance == null)
            instance = new InstructionFactory();

        return instance;
    }

    public Instruction create(int lineNumber, StatementName statementName, String... arguments)
    {
        Statement statement = statementName.getStatement();

        if(statement instanceof JumpBaseStatement)
            return new JumpInstruction(lineNumber, (JumpBaseStatement)statement, arguments);

        return new SimpleInstruction(lineNumber, statement, arguments);
    }
}
