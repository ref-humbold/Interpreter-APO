package apolang.instructions.statement.memory;

import apolang.exceptions.LanguageException;
import apolang.instructions.ArgumentType;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

public class LoadWordStatement
        extends AbstractMemoryStatement
{
    @Override
    public ArgumentType[] getArgumentsTypes()
    {
        return new ArgumentType[]{ArgumentType.VARIABLE_WRITE, ArgumentType.VARIABLE_READ};
    }

    @Override
    public StatementResult execute(Environment environment, String... arguments)
            throws LanguageException
    {
        int address = environment.getVariableValue(arguments[1]);
        int result = memory.loadWord(address);

        environment.setVariableValue(arguments[0], result);
        return StatementResult.NEXT;
    }
}
