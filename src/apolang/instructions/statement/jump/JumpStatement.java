package apolang.instructions.statement.jump;

import apolang.instructions.ArgumentType;
import apolang.instructions.statement.JumpBaseStatement;
import apolang.interpreter.Environment;

public class JumpStatement
        implements JumpBaseStatement
{
    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.LABEL};
    }

    @Override
    public Boolean execute(Environment environment, String... arguments)
    {
        return true;
    }
}
