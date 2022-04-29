package apolang.interpreter.parser;

import java.util.List;

import apolang.exceptions.label.DuplicatedLabelException;
import apolang.exceptions.label.LabelException;
import apolang.exceptions.symbol.SymbolException;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementName;
import apolang.interpreter.Environment;

public class EnvironmentParser
        extends AbstractParser<Environment>
{
    public EnvironmentParser(List<String> lines)
    {
        super(lines);
    }

    @Override
    protected Environment create()
    {
        return new Environment();
    }

    @Override
    protected void parseLine(List<String> splitLine, int lineNumber, Environment result)
            throws LabelException, SymbolException
    {
        String label = extractLabel(splitLine);

        if(label != null)
        {
            if(Environment.END_LABEL.equals(label))
                throw new LabelException(
                        String.format("Label `%s` is predefined", Environment.END_LABEL),
                        lineNumber);

            if(result.contains(label))
                throw new DuplicatedLabelException(label, lineNumber);

            try
            {
                result.addLabel(label);
            }
            catch(LabelException e)
            {
                e.setLineNumber(lineNumber);
                throw e;
            }
        }

        if(splitLine.isEmpty())
            return;

        try
        {
            Statement statement = StatementName.parse(splitLine.get(0)).getStatement();

            if(!statement.hasValueSet())
                return;

            result.addVariable(splitLine.get(1));
        }
        catch(SymbolException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }
    }

    @Override
    protected void afterParsing(Environment result)
    {
    }
}
