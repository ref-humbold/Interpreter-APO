package apolang;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.cli.ParseException;

import apolang.interpreter.Controller;

public final class Interpreter
{
    private static final CliParser cliParser = new CliParser();

    public static void main(String[] args)
            throws IOException
    {
        CliParser.Arguments arguments;

        try
        {
            arguments = cliParser.parse(args);
        }
        catch(ParseException e)
        {
            System.err.printf("Error while parsing arguments:%n\t%s%n\tExecution stopped.%n",
                              e.getMessage());
            return;
        }

        if(arguments.isHelp())
        {
            cliParser.printHelp();
            return;
        }

        Path path = Paths.get(arguments.getFilename());
        Controller controller = new Controller(arguments.getMemorySize(), path);

        try
        {
            controller.parse();
        }
        catch(Exception e)
        {
            System.err.printf("parser error>> %s%n\tExecution stopped.%n", e);
            e.printStackTrace(System.err);
            return;
        }

        try
        {
            controller.run();
        }
        catch(Exception e)
        {
            System.err.printf("interpreter error>> %s%n\tExecution stopped.%n", e);
            e.printStackTrace(System.err);
        }
    }
}
