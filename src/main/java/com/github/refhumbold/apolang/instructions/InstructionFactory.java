package com.github.refhumbold.apolang.instructions;

import com.github.refhumbold.apolang.instructions.instruction.Instruction;
import com.github.refhumbold.apolang.instructions.instruction.JumpInstruction;
import com.github.refhumbold.apolang.instructions.instruction.SimpleInstruction;
import com.github.refhumbold.apolang.instructions.statement.JumpBaseStatement;
import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;

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
        return create(lineNumber, statementName.getStatement(), arguments);
    }

    public Instruction create(int lineNumber, Statement statement, String... arguments)
    {
        if(statement instanceof JumpBaseStatement)
            return new JumpInstruction(lineNumber, (JumpBaseStatement)statement, arguments);

        return new SimpleInstruction(lineNumber, statement, arguments);
    }
}
