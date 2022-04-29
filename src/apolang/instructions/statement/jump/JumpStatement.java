package apolang.instructions.statement.jump;

import apolang.instructions.ArgumentType;
import apolang.instructions.statement.JumpBaseStatement;
import apolang.instructions.statement.StatementName;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

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
        return new ArgumentType[]{ArgumentType.LABEL};
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
    {
        return StatementResult.JUMP;
    }
}
