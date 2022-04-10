package apolang.instructions.instruction.jump;

import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.Instruction;
import apolang.interpreter.Environment;

public class JumpGreaterInstruction
        implements Instruction<Boolean>
{
    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_READ, ArgumentType.VARIABLE_READ,
                                  ArgumentType.LABEL};
    }

    @Override
    public Boolean execute(Environment environment, String... arguments)
    {
        int argValue0 = environment.getVariableValue(arguments[0]);
        int argValue1 = environment.getVariableValue(arguments[1]);

        return argValue0 > argValue1;
    }
}