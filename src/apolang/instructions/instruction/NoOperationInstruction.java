package apolang.instructions.instruction;

import apolang.instructions.ArgumentType;
import apolang.interpreter.Environment;

public class NoOperationInstruction
        implements Instruction<Void>
{
    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[0];
    }

    @Override
    public Void execute(Environment environment, String... arguments)
    {
        return null;
    }
}