package apolang.interpreter.externals;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import apolang.exceptions.LanguageException;

public final class IOConnector
{
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
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
        String read;
        System.out.print("input>> ");

        try
        {
            read = stdin.readLine();
        }
        catch(Exception e)
        {
            throw new LanguageException("IOException while reading", e);
        }

        return Integer.parseInt(read);
    }

    public int readChar()
            throws LanguageException
    {
        String read;
        System.out.print("input>> ");

        try
        {
            read = stdin.readLine();
        }
        catch(Exception e)
        {
            throw new LanguageException("IOException while reading", e);
        }

        return read.charAt(0);
    }
}
