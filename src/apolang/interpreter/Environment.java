package apolang.interpreter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import apolang.errors.LabelException;
import apolang.errors.SymbolException;

public class Environment
{
    public static final String ZERO_VARIABLE = "zero";
    public static final String END_LABEL = "End";
    public static final Integer DEFAULT_VARIABLE_VALUE = 0;
    private static final String LABEL_REGEX = "[A-Z][a-z]+";
    private static final String VARIABLE_REGEX = "[a-z]+";
    private final Map<String, Integer> variableValues = new HashMap<>();
    private final Set<String> namesSet = new HashSet<>();

    public Environment()
    {
        namesSet.add(ZERO_VARIABLE);
        namesSet.add(END_LABEL);
        variableValues.put(ZERO_VARIABLE, DEFAULT_VARIABLE_VALUE);
    }

    public Integer getVariableValue(String variable)
    {
        return variableValues.get(variable);
    }

    public void setVariableValue(String variable, int value)
            throws SymbolException
    {
        if(ZERO_VARIABLE.equals(variable))
            throw new SymbolException(
                    String.format("Cannot assign to variable `%s`", Environment.ZERO_VARIABLE));

        variableValues.computeIfPresent(variable, (k, v) -> value);
    }

    public boolean contains(String name)
    {
        return namesSet.contains(name);
    }

    public void addLabel(String label)
            throws LabelException
    {
        validateLabel(label);
        namesSet.add(label);
    }

    public void addVariable(String variable)
            throws SymbolException
    {
        validateVariable(variable);
        namesSet.add(variable);
        variableValues.putIfAbsent(variable, DEFAULT_VARIABLE_VALUE);
    }

    public void validateLabel(String label)
            throws LabelException
    {
        if(!label.matches(LABEL_REGEX))
            throw new LabelException(
                    "Invalid label format - should start with uppercase letter and be followed by at least one lowercase letter");
    }

    public void validateVariable(String variable)
            throws SymbolException
    {
        if(!variable.matches(VARIABLE_REGEX))
            throw new SymbolException(
                    "Invalid variable name format - should be all in lowercase letters");
    }
}
