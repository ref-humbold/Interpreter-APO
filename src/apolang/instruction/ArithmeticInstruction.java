package apolang.instruction;

import apolang.errors.ArithmeticException;
import apolang.interpreter.Environment;

public class ArithmeticInstruction
        extends Instruction
{
    public ArithmeticInstruction(int lineNumber, InstructionName name, String... args)
    {
        super(lineNumber, name, args);
    }

    @Override
    public void execute(Environment environment)
            throws ArithmeticException
    {
        int argValue1;
        int argValue2;

        switch(name)
        {
            case ADD:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 + argValue2);
                break;

            case ADDC:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 + argValue2);
                break;

            case SUB:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 - argValue2);
                break;

            case SUBC:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 - argValue2);
                break;

            case MUL:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 * argValue2);
                break;

            case MULC:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 * argValue2);
                break;

            case DIV:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                validateDivision(argValue1, argValue2);
                environment.setVariableValue(arguments[0], argValue1 / argValue2);
                break;

            case DIVC:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                validateDivision(argValue1, argValue2);
                environment.setVariableValue(arguments[0], argValue1 / argValue2);
                break;

            default:
                break;
        }
    }

    private void validateDivision(int argValue1, int argValue2)
            throws ArithmeticException
    {
        if(argValue2 == 0)
        {
            if(argValue1 == 0)
                throw new ArithmeticException("Not a number", lineNumber);

            throw new ArithmeticException("Division by zero", lineNumber);
        }
    }
}
