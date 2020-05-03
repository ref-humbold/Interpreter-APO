package apolang.interpreter.environment;

import java.util.HashMap;
import java.util.Map;

import apolang.errors.SymbolException;

/**
 * Klasa przechowujaca liste zmiennych wraz z ich wartosciami.
 */
public class VariableEnvironment
{
    private static final Integer DEFAULT_VALUE = 0;
    private int variableCount = 0;
    private Map<String, Integer> variableNumbers = new HashMap<>();
    private Map<Integer, Integer> variableValues = new HashMap<>();

    public VariableEnvironment()
    {
    }

    /**
     * Przypisuje domyslna wartosc do zmiennej okreslona nazwa.
     * @param name nazwa zmiennej
     */
    public void setValue(String name)
    {
        setValue(name, DEFAULT_VALUE);
    }

    /**
     * Okreslanie numeru zmiennej wzgledem jej nazwy.
     * @param name nazwa zmiennej
     * @return numer zmiennej
     */
    public int getNumber(String name)
    {
        return variableNumbers.get(name);
    }

    /**
     * Sprawdzanie, czy zmienna o okreslonej nazwie juz istnieje.
     * @param index numer zmiennej
     * @return czy zmienna istnieje
     */
    public boolean contains(int index)
    {
        return variableValues.containsKey(index);
    }

    /**
     * Sprawdzanie, czy zmienna o okreslonej nazwie juz istnieje.
     * @param name nazwa zmiennej
     * @return czy zmienna istnieje
     */
    public boolean contains(String name)
    {
        return variableNumbers.containsKey(name);
    }

    /**
     * Pobiera wartosc zmiennej okreslona nazwa.
     * @param name nazwa zmiennej
     * @return wartosc zmiennej
     */
    public int getValue(String name)
            throws SymbolException
    {
        if(!contains(name))
            throw new SymbolException(SymbolException.VARIABLE_NOT_INIT);

        return getValue(getNumber(name));
    }

    /**
     * Pobiera wartosc zmiennej okreslonej numerem.
     * @param index numer zmiennej
     * @return wartosc zmiennej
     */
    public int getValue(int index)
            throws SymbolException
    {
        if(!contains(index))
            throw new SymbolException(SymbolException.VARIABLE_NOT_INIT);

        return variableValues.get(index);
    }

    /**
     * Przypisuje nowa wartosc do zmiennej okreslona nazwa.
     * @param name nazwa zmiennej
     * @param value wartosc do zapisu
     */
    public void setValue(String name, int value)
    {
        if(!contains(name))
        {
            variableNumbers.put(name, variableCount);
            ++variableCount;
        }

        variableValues.put(getNumber(name), value);
    }

    /**
     * Przypisuje nowa wartosc do zmiennej okeslonej numerem.
     * @param index numer zmiennej
     * @param value wartosc do zapisu
     */
    public void setValue(int index, int value)
            throws SymbolException
    {
        if(!contains(index))
            throw new SymbolException(SymbolException.VARIABLE_NOT_INIT);

        variableValues.put(index, value);
    }
}
