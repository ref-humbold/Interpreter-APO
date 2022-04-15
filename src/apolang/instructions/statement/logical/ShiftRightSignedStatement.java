package apolang.instructions.statement.logical;

import apolang.exceptions.LanguageException;
import apolang.exceptions.arithmetic.NegativeShiftException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.BasicStatement;
import apolang.interpreter.Environment;

public class ShiftRightSignedStatement
        implements BasicStatement
{
    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE, ArgumentType.VARIABLE_READ,
                                  ArgumentType.CONSTANT};
    }

    @Override
    public Void execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue1 = environment.getVariableValue(arguments[1]);
        int argValue2 = Integer.parseInt(arguments[2]);

        if(argValue2 < 0)
            throw new NegativeShiftException();

        environment.setVariableValue(arguments[0], argValue1 >> argValue2);
        return null;
    }
}
