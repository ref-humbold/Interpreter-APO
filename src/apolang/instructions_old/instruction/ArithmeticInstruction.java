package apolang.instructions_old.instruction;

import apolang.exceptions.LanguageException;
import apolang.exceptions.arithmetic.DivisionByZeroException;
import apolang.instructions_old.InstructionName;
import apolang.interpreter.Environment;

public class ArithmeticInstruction
        extends Instruction
{
    public ArithmeticInstruction(int lineNumber, InstructionName name, String... arguments)
    {
        super(lineNumber, name, arguments);
    }

    @Override
    public void execute(Environment environment)
            throws LanguageException
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

                if(argValue2 == 0)
                    throw new DivisionByZeroException(lineNumber);

                environment.setVariableValue(arguments[0], argValue1 / argValue2);
                break;

            case DIVC:
                argValue1 = environment.getVariableValue(arguments[1]);
                argValue2 = Integer.parseInt(arguments[2]);

                if(argValue2 == 0)
                    throw new DivisionByZeroException(lineNumber);

                environment.setVariableValue(arguments[0], argValue1 / argValue2);
                break;

            default:
                break;
        }
    }
}
