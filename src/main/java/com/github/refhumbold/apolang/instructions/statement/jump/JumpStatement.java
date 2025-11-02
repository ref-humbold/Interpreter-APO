package com.github.refhumbold.apolang.instructions.statement.jump;

import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.JumpBaseStatement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;

public class JumpStatement
        implements JumpBaseStatement
{
    @Override
    public StatementName getName()
    {
        return StatementName.JUMP;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ ArgumentType.LABEL };
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
    {
        return StatementResult.JUMP;
    }
}
