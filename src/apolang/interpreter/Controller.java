package apolang.interpreter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import apolang.exceptions.LanguageException;
import apolang.instruction.Instruction;
import apolang.instruction.InstructionFactory;
import apolang.instruction.InstructionList;
import apolang.interpreter.external.Memory;
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
        instructionParser = new InstructionParser(lines, environment);
    }

    public void parse()
            throws LanguageException
    {
        System.out.print("parsing>> ");
        environment = environmentParser.parse();
        System.out.println(". ");
        instructionList = instructionParser.parse();
        System.out.println(". done");
    }

    public void run()
            throws LanguageException
    {
        for(Instruction instruction : instructionList)
            instruction.execute(environment);
    }
}

