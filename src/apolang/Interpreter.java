package apolang;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import apolang.interpreter.Controller;

public final class Interpreter
{

    public static void main(String[] args)
            throws IOException
    {
        int memorySize = 1;
        String address = args[0];

        if(args.length > 1)
        {
            try
            {
                memorySize = Integer.parseInt(args[1]);
            }
            catch(Exception e)
            {
                System.err.println("Memory could not be allocated.\n\tExecution stopped.");
                return;
            }
        }

        if(!address.endsWith(".apo"))
        {
            System.err.println("Wrong filename extension.\n\tExecution stopped.");
            return;
        }

        Path path = Paths.get(address);
        Controller controller = new Controller(memorySize, path);

        try
        {
            controller.parse();
        }
        catch(Exception e)
        {
            System.err.println("\nparser error>> " + e.toString() + "\n\tExecution stopped.");
            return;
        }

        try
        {
            controller.run();
        }
        catch(Exception e)
        {
            System.err.println("\ninterpreter error>> " + e.toString() + "\n\tExecution stopped.");
        }
    }
}
