package apolang.instructions.statement.jump;

import apolang.instructions.ArgumentType;
import apolang.instructions.statement.JumpBaseStatement;
import apolang.instructions.statement.StatementName;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

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
        return new ArgumentType[]{ArgumentType.VARIABLE_READ, ArgumentType.VARIABLE_READ,
                                  ArgumentType.LABEL};
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
    {
        int argValue0 = environment.getVariableValue(arguments[0]);
        int argValue1 = environment.getVariableValue(arguments[1]);

        return argValue0 == argValue1 ? StatementResult.NEXT : StatementResult.JUMP;
    }
}
