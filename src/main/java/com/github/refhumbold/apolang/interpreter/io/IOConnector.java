package com.github.refhumbold.apolang.interpreter.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.github.refhumbold.apolang.exceptions.LanguageException;

public final class IOConnector
{
    private static final BufferedReader stdin =
            new BufferedReader(new InputStreamReader(System.in), 1);
    private static IOConnector instance = null;

    private IOConnector()
    {
    }

    public static IOConnector getInstance()
    {
        if(instance == null)
            instance = new IOConnector();

        return instance;
    }

    public void printLine()
    {
        System.out.println();
    }

    public void printInt(int number)
    {
        System.out.print(number);
    }

    public void printChar(int code)
    {
        System.out.print((char)code);
    }

    public int readInt()
            throws LanguageException
    {
        return Integer.parseInt(read());
    }

    public int readChar()
            throws LanguageException
    {
        return read().charAt(0);
    }

    private String read()
            throws LanguageException
    {
        System.out.print("input>> ");

        try
        {
            return stdin.readLine();
        }
        catch(Exception e)
        {
            throw new LanguageException("IOException while reading", e);
        }
    }
}
