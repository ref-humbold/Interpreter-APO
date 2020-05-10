package apolang.instruction;

import apolang.interpreter.Environment;

public class NOPInstruction
        extends Instruction
{
    public NOPInstruction(int lineNumber)
    {
        super(lineNumber, InstructionName.NOP);
    }

    @Override
    public void execute(Environment environment)
    {
    }
}
