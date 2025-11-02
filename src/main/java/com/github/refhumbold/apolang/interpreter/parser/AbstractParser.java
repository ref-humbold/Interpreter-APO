package com.github.refhumbold.apolang.interpreter.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.github.refhumbold.apolang.exceptions.LanguageException;

public abstract class AbstractParser<T>
{
    protected final List<String> lines;

    public AbstractParser(List<String> lines)
    {
        this.lines = lines;
    }

    public final T parse()
            throws LanguageException
    {
        T result = create();

        for(int i = 0; i < lines.size(); ++i)
        {
            String line = lines.get(i).trim();

            int commentIndex = line.indexOf("#");

            if(commentIndex >= 0)
                line = line.substring(0, commentIndex);

            if(!line.isEmpty())
            {
                List<String> splitLine =
                        Arrays.stream(line.split("\\s+")).collect(Collectors.toList());
                parseLine(splitLine, i + 1, result);
            }
        }

        afterParsing(result);
        return result;
    }

    protected abstract T create();

    protected abstract void parseLine(List<String> splitLine, int lineNumber, T result)
            throws LanguageException;

    protected abstract void afterParsing(T result);

    protected final String extractLabel(List<String> splitLine)
    {
        if(splitLine.get(0).endsWith(":"))
        {
            String label = splitLine.get(0);

            splitLine.remove(0);
            return label.substring(0, label.length() - 1);
        }

        return null;
    }
}
