package apolang.interpreter.parser;

import java.util.List;

import apolang.interpreter.environment.VariableEnvironment;

public class VariablesParser
{
    private final List<String> lines;

    public VariablesParser(List<String> lines)
    {
        this.lines = lines;
    }

    public VariableEnvironment parse()
    {
        VariableEnvironment variableEnvironment = new VariableEnvironment();

        for(String line : lines)
        {

        }

        return variableEnvironment;
    }
}
