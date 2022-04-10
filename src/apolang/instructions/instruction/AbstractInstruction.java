package apolang.instructions.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.Statement;
import apolang.interpreter.Environment;

public abstract class AbstractInstruction<S extends Statement<?>>
{
    protected final int lineNumber;
    protected final S statement;
    protected final String[] arguments;
    protected AbstractInstruction<?> next = null;

    public AbstractInstruction(int lineNumber, S statement, String... arguments)
    {
        this.lineNumber = lineNumber;
        this.statement = statement;
        this.arguments = arguments;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public S getStatement()
    {
        return statement;
    }

    public int getArgumentsCount()
    {
        return arguments.length;
    }

    public AbstractInstruction<?> getNext()
    {
        return next;
    }

    public void setNext(AbstractInstruction<?> next)
    {
        this.next = next;
    }

    public AbstractInstruction<?> getNextInstruction()
    {
        return next;
    }

    public String getArgument(int index)
    {
        return arguments[index];
    }

    public abstract boolean execute(Environment environment)
            throws LanguageException;
}
