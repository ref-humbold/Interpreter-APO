package apolang.instructions.instruction;

import java.util.Arrays;
import java.util.Objects;

import apolang.exceptions.LanguageException;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementResult;
import apolang.interpreter.Environment;

public abstract class Instruction
{
    protected final int lineNumber;
    protected final Statement statement;
    protected final String[] arguments;
    protected Instruction next = null;
    protected StatementResult result;

    public Instruction(int lineNumber, Statement statement, String... arguments)
    {
        this.lineNumber = lineNumber;
        this.statement = statement;
        this.arguments = arguments;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public Statement getStatement()
    {
        return statement;
    }

    public int getArgumentsCount()
    {
        return arguments.length;
    }

    public Instruction getNext()
    {
        return next;
    }

    public void setNext(Instruction next)
    {
        this.next = next;
    }

    public abstract Instruction getNextExecuted();

    public String getArgument(int index)
    {
        return arguments[index];
    }

    public void execute(Environment environment)
            throws LanguageException
    {
        try
        {
            result = statement.execute(environment, arguments);
        }
        catch(LanguageException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(!(obj instanceof Instruction))
            return false;

        Instruction other = (Instruction)obj;

        return lineNumber == other.lineNumber && Objects.equals(statement, other.statement)
                && Arrays.equals(arguments, other.arguments);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(lineNumber, statement, Arrays.hashCode(arguments));
    }
}
