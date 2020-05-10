package apolang.instruction;

import java.util.Objects;

import apolang.interpreter.Environment;

public class JumpInstruction
        extends Instruction
{
    private Instruction link = null;
    private boolean isJump = false;

    public JumpInstruction(int lineNumber, InstructionName name, String... args)
    {
        super(lineNumber, name, args);
    }

    @Override
    public Instruction getNext()
    {
        return isJump ? link : next;
    }

    public void setLink(Instruction link)
    {
        this.link = link;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(!(obj instanceof JumpInstruction))
            return false;

        JumpInstruction other = (JumpInstruction)obj;

        return super.equals(other) && Objects.equals(link, other.link);
    }

    @Override
    public int hashCode()
    {
        int prime = 37;

        return prime * super.hashCode() + (link == null ? 0 : link.hashCode());
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
