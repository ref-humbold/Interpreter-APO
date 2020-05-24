package apolang.instruction.instructions;

import apolang.instruction.InstructionName;
import apolang.interpreter.Environment;

public class JumpInstruction
        extends Instruction
{
    private Instruction link = null;
    private boolean isJump = false;

    public JumpInstruction(int lineNumber, InstructionName name, String... arguments)
    {
        super(lineNumber, name, arguments);
    }

    @Override
    public Instruction getNextExecuted()
    {
        return isJump ? link : next;
    }

    public Instruction getLink()
    {
        return link;
    }

    public void setLink(Instruction link)
    {
        this.link = link;
    }

    @Override
    public void execute(Environment environment)
    {
        int argValue0;
        int argValue1;

        switch(name)
        {
            case JUMP:
                isJump = true;
                break;

            case JPEQ:
                argValue0 = environment.getVariableValue(arguments[0]);
                argValue1 = environment.getVariableValue(arguments[1]);
                isJump = argValue0 == argValue1;
                break;

            case JPNE:
                argValue0 = environment.getVariableValue(arguments[0]);
                argValue1 = environment.getVariableValue(arguments[1]);
                isJump = argValue0 != argValue1;
                break;

            case JPLT:
                argValue0 = environment.getVariableValue(arguments[0]);
                argValue1 = environment.getVariableValue(arguments[1]);
                isJump = argValue0 < argValue1;
                break;

            case JPGT:
                argValue0 = environment.getVariableValue(arguments[0]);
                argValue1 = environment.getVariableValue(arguments[1]);
                isJump = argValue0 > argValue1;
                break;

            default:
                break;
        }
    }
}
