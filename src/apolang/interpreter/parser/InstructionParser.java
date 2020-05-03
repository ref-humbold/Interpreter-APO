package apolang.interpreter.parser;

import java.util.List;

import apolang.instructions.InstructionList;

public class InstructionParser
{
    private final List<String> lines;

    public InstructionParser(List<String> lines)
    {
        this.lines = lines;
    }

    public InstructionList parse()
    {
        InstructionList instructionList = new InstructionList();

        for(String line : lines)
        {

        }

        return instructionList;
    }
}
