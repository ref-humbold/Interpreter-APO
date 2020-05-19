package apolang.instruction;

import apolang.exceptions.LanguageException;
import apolang.interpreter.Environment;

public class AssignInstruction
        extends Instruction
{
    public AssignInstruction(int lineNumber, InstructionName name, String... arguments)
    {
        super(lineNumber, name, arguments);
    }

    @Override
    public void execute(Environment environment)
            throws LanguageException
    {
        int argValue;

        switch(name)
        {
            case ASGN:
                argValue = environment.getVariableValue(arguments[1]);
                environment.setVariableValue(arguments[0], argValue);
                break;

            case ASGNC:
                argValue = Integer.parseInt(arguments[2]);
                environment.setVariableValue(arguments[0], argValue);
                break;

            default:
                break;
        }
    }
}
