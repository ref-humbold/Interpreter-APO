package apolang.instructions.statement.assignment;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementName;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

public class AssignConstStatement
        implements Statement
{
    @Override
    public StatementName getName()
    {
        return StatementName.ASGNC;
    }

    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE, ArgumentType.CONSTANT};
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int argValue = Integer.parseInt(arguments[1]);

        environment.setVariableValue(arguments[0], argValue);
        return StatementResult.NEXT;
    }
}
