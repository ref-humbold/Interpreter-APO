package apolang.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apolang.errors.LabelException;
import apolang.errors.SymbolException;

public class Environment
{
    public static final String ZERO_VARIABLE = "zero";
    public static final String END_LABEL = "End";
    private static final String LABEL_REGEX = "[A-Z][a-z]*";
    private static final String VARIABLE_REGEX = "[a-z]+";
    private final Map<String, Integer> codesMap = new HashMap<>();
    private final List<String> namesList = new ArrayList<>();

    public Environment()
    {
        add(ZERO_VARIABLE);
        add(END_LABEL);
    }

    public void validateLabel(String label)
            throws LabelException
    {
        if(!label.matches(LABEL_REGEX))
            throw new LabelException(LabelException.INVALID_CHARACTERS);
    }

    public void validateVariable(String variable)
            throws SymbolException
    {
        if(!variable.matches(VARIABLE_REGEX))
            throw new SymbolException(SymbolException.INVALID_CHARACTERS);
    }

    public void addLabel(String label)
            throws LabelException
    {
        validateLabel(label);
        add(label);
    }

    public void addVariable(String variable)
            throws SymbolException
    {
        validateVariable(variable);
        add(variable);
    }

    public boolean contains(String name)
    {
        return codesMap.containsKey(name);
    }

    public Integer getCode(String name)
    {
        return codesMap.get(name);
    }

    private void add(String name)
    {
        if(!contains(name))
        {
            namesList.add(name);
            codesMap.put(name, namesList.size() - 1);
        }
    }
}
