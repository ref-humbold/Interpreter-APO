package apolang.instruction;

import apolang.errors.ArithmeticException;
import apolang.errors.LanguageException;
import apolang.interpreter.Environment;

public class LogicalInstruction
        extends Instruction
{
    public LogicalInstruction(int lineNumber, InstructionName name, String... args)
    {
        super(lineNumber, name, args);
    }

    @Override
    public void execute(Environment environment)
            throws LanguageException
    {
        int argValue1;
        int argValue2;

        switch(name)
        {
            case SHLT:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                validateShift(argValue2);
                environment.setVariableValue(arguments[0], argValue1 << argValue2);
                break;

            case SHRT:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                validateShift(argValue2);
                environment.setVariableValue(arguments[0], argValue1 >>> argValue2);
                break;

            case SHRS:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                validateShift(argValue2);
                environment.setVariableValue(arguments[0], argValue1 >> argValue2);
                break;

            case AND:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 & argValue2);
                break;

            case ANDC:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 & argValue2);
                break;

            case OR:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 | argValue2);
                break;

            case ORC:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 | argValue2);
                break;

            case XOR:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 ^ argValue2);
                break;

            case XORC:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);
                environment.setVariableValue(arguments[0], argValue1 ^ argValue2);
                break;

            case NAND:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                environment.setVariableValue(arguments[0], ~(argValue1 & argValue2));
                break;

            case NOR:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = environment.getVariableValue(arguments[2]);
                environment.setVariableValue(arguments[0], ~(argValue1 | argValue2));
                break;

            default:
                break;
        }
    }

    private void validateShift(int shiftValue)
            throws ArithmeticException
    {
        if(shiftValue < 0)
            throw new ArithmeticException("Shift by negative value", lineNumber);
    }
}
