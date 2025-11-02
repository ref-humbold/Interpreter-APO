package com.github.refhumbold.apolang.instructions.statement.jump;

import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.JumpBaseStatement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;

public class JumpNotEqualStatement
        implements JumpBaseStatement
{
    @Override
    public StatementName getName()
    {
        return StatementName.JPNE;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ ArgumentType.VARIABLE_READ, ArgumentType.VARIABLE_READ,
                ArgumentType.LABEL };
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
    {
        int argValue0 = environment.getVariableValue(arguments[0]);
        int argValue1 = environment.getVariableValue(arguments[1]);

        return argValue0 == argValue1 ? StatementResult.NEXT : StatementResult.JUMP;
    }
}
