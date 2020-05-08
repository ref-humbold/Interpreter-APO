package apolang.interpreter.parser;

import java.util.Arrays;
import java.util.List;

import apolang.errors.LabelException;
import apolang.errors.SymbolException;

public abstract class AbstractParser<T>
{
    protected final List<String> lines;

    public AbstractParser(List<String> lines)
    {
        this.lines = lines;
    }

    public final T parse()
            throws LabelException, SymbolException
    {
        T result = create();

        for(int i = 0; i < lines.size(); ++i)
        {
            String line = lines.get(i).trim();

            int commentIndex = line.indexOf("#");

            if(commentIndex >= 0)
                line = line.substring(0, commentIndex);

            if(!line.isEmpty())
                parseLine(Arrays.asList(line.split("\\s+")), i + 1, result);
        }

        return result;
    }

    protected abstract T create();

    protected abstract void parseLine(List<String> splitLine, int lineNumber, T result)
            throws LabelException, SymbolException;

    protected final String extractLabel(List<String> splitLine)
    {
        if(splitLine.get(0).endsWith(":"))
        {
            String label = splitLine.get(0);

            splitLine.remove(0);
            return label.substring(0, splitLine.get(0).length() - 1);
        }

        return null;
    }
}
