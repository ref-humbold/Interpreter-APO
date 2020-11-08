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
        String filename = args[0];

        if(args.length > 1)
        {
            try
            {
                memorySize = Integer.parseInt(args[1]);
            }
            catch(Exception e)
            {
                System.err.println("Invalid memory size. Execution stopped.");
                return;
            }
        }

        if(!filename.endsWith(".apo"))
        {
            System.err.println("Wrong filename extension. Execution stopped.");
            return;
        }

        Path path = Paths.get(filename);
        Controller controller = new Controller(memorySize, path);

        try
        {
            controller.parse();
        }
        catch(Exception e)
        {
            System.err.printf("parser error>> %s\n\tExecution stopped.\n", e);
            e.printStackTrace(System.err);
            return;
        }

        try
        {
            controller.run();
        }
        catch(Exception e)
        {
            System.err.printf("interpreter error>> %s\n\tExecution stopped.\n", e);
            e.printStackTrace(System.err);
        }
    }
}
