package apolang.instruction;

import apolang.interpreter.environment.VariableEnvironment;

public class NOPInstruction
        extends Instruction
{
    public NOPInstruction(int lineNumber)
    {
        super(lineNumber, InstructionName.NOP);
    }

    @Override
    public void execute(VariableEnvironment variables)
    {
    }
}
