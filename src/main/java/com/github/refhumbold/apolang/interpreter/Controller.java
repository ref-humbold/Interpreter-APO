package com.github.refhumbold.apolang.interpreter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import com.github.refhumbold.apolang.exceptions.LanguageException;
import com.github.refhumbold.apolang.instructions.list.ExecutionIterator;
import com.github.refhumbold.apolang.instructions.list.InstructionList;
import com.github.refhumbold.apolang.interpreter.memory.MemoryAccessor;
import com.github.refhumbold.apolang.interpreter.parser.EnvironmentParser;
import com.github.refhumbold.apolang.interpreter.parser.InstructionParser;

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

        MemoryAccessor.getInstance().allocate(memorySize);
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
        System.out.print(". ");
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

