package apolang.instructions.statement.arithmetic;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementName;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

public class SubtractConstStatement
        implements Statement
{
    @Override
    public StatementName getName()
    {
        return StatementName.SUBC;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE, ArgumentType.VARIABLE_READ,
                                  ArgumentType.CONSTANT};
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue1 = environment.getVariableValue(arguments[1]);
        int argValue2 = Integer.parseInt(arguments[2]);

        environment.setVariableValue(arguments[0], argValue1 - argValue2);
        return StatementResult.NEXT;
    }
}
