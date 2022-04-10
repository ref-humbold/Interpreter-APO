package apolang.interpreter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import apolang.exceptions.LanguageException;
import apolang.instructions_old.InstructionFactory;
import apolang.instructions_old.list.ExecutionIterator;
import apolang.instructions_old.list.InstructionList;
import apolang.interpreter.externals.Memory;
import apolang.interpreter.parser.EnvironmentParser;
import apolang.interpreter.parser.InstructionParser;

public class Controller
{
    private final EnvironmentParser environmentParser;
    private final InstructionParser instructionParser;
    private Environment environment;
    private InstructionList instructionList;

    public Controller(int memorySize, Path path)
            throws IOException
    {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        InstructionFactory.memory = new Memory(memorySize);
        environmentParser = new EnvironmentParser(lines);
        instructionParser = new InstructionParser(lines);
    }

    public void parse()
            throws LanguageException
    {
        System.out.print("parsing>> ");
        environment = environmentParser.parse();
        System.out.print(". ");
        instructionParser.setEnvironment(environment);
        instructionList = instructionParser.parse();
        System.out.println(". done");
    }

    public void run()
            throws LanguageException
    {
        ExecutionIterator executionIterator = instructionList.run();

        while(executionIterator.hasNext())
            executionIterator.next().execute(environment);
    }
}

