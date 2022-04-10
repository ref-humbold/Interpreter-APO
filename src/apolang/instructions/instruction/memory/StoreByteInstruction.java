package apolang.instructions.instruction.memory;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.BaseInstruction;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.Memory;

public class StoreByteInstruction
        implements BaseInstruction
{
    private final Memory memory;

    public StoreByteInstruction(Memory memory)
    {
        this.memory = memory;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_READ, ArgumentType.VARIABLE_READ};
    }

    @Override
    public Void execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue = environment.getVariableValue(arguments[0]);
        int address = environment.getVariableValue(arguments[1]);

        memory.storeByte(address, argValue);
        return null;
    }
}
