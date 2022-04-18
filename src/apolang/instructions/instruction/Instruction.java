package apolang.instructions.instruction;

import java.util.Arrays;
import java.util.Objects;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.Statement;
import apolang.interpreter.Environment;

public abstract class Instruction<S extends Statement<?>>
{
    protected final int lineNumber;
    protected final S statement;
    protected final String[] arguments;
    protected Instruction<?> next = null;

    public Instruction(int lineNumber, S statement, String... arguments)
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

    public Instruction<?> getNext()
    {
        return next;
    }

    public void setNext(Instruction<?> next)
    {
        this.next = next;
    }

    public abstract Instruction<?> getNextExecuted();

    public String getArgument(int index)
    {
        return arguments[index];
    }

    public abstract boolean execute(Environment environment)
            throws LanguageException;

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(!(obj instanceof Instruction))
            return false;

        Instruction<?> other = (Instruction<?>)obj;

        return lineNumber == other.lineNumber && Objects.equals(statement, other.statement)
                && Arrays.equals(arguments, other.arguments);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(lineNumber, statement, Arrays.hashCode(arguments));
    }
}
