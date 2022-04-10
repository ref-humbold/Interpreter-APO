package apolang.instructions.instruction.jump;

import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.JumpBaseInstruction;
import apolang.interpreter.Environment;

public class JumpInstruction
        implements JumpBaseInstruction
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
