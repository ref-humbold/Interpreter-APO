package apolang.instructions.instruction.memory;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.instruction.Instruction;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.Memory;

public class LoadByteInstruction
        implements Instruction<Void>
{
    private final Memory memory;

    public LoadByteInstruction(Memory memory)
    {
        this.memory = memory;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE, ArgumentType.VARIABLE_READ};
    }

    @Override
    public Void execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int address = environment.getVariableValue(arguments[1]);
        int result = memory.loadByte(address);

        environment.setVariableValue(arguments[0], result);
        return null;
    }
}
