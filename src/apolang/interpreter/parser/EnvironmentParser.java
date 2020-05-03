package apolang.interpreter.parser;

import java.util.List;

import apolang.interpreter.Environment;

public class EnvironmentParser
{
    private final List<String> lines;

    public EnvironmentParser(List<String> lines)
    {
        this.lines = lines;
    }

    public Environment parse()
    {
        Environment Environment = new Environment();

        for(String line : lines)
        {

        }

        return Environment;
    }
}
