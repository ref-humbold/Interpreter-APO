package com.github.refhumbold.apolang.instructions.statement.arithmetic;

import com.github.refhumbold.apolang.exceptions.LanguageException;
import com.github.refhumbold.apolang.exceptions.arithmetic.DivisionByZeroException;
import com.github.refhumbold.apolang.instructions.ArgumentType;
import com.github.refhumbold.apolang.instructions.statement.Statement;
import com.github.refhumbold.apolang.instructions.statement.StatementName;
import com.github.refhumbold.apolang.instructions.statement.StatementResult;
import com.github.refhumbold.apolang.interpreter.Environment;

public class DivideStatement
        implements Statement
{
    @Override
    public StatementName getName()
    {
        return StatementName.DIV;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ ArgumentType.VARIABLE_WRITE, ArgumentType.VARIABLE_READ,
                ArgumentType.VARIABLE_READ };
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue1 = environment.getVariableValue(arguments[1]);
        int argValue2 = environment.getVariableValue(arguments[2]);

        if(argValue2 == 0)
            throw new DivisionByZeroException();

        environment.setVariableValue(arguments[0], argValue1 / argValue2);
        return StatementResult.NEXT;
    }
}
