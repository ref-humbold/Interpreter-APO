package apolang.interpreter.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apolang.errors.LabelException;
import apolang.errors.SymbolException;
import apolang.instructions.Instruction;
import apolang.instructions.InstructionFactory;
import apolang.instructions.InstructionList;
import apolang.instructions.InstructionName;
import apolang.interpreter.Environment;

public class InstructionParser
        extends AbstractParser<InstructionList>
{
    private Environment environment;
    private Map<Integer, Instruction> labelledInstructions = new HashMap<>();
    private InstructionFactory instructionFactory = new InstructionFactory();

    public InstructionParser(List<String> lines, Environment environment)
    {
        super(lines);
        this.environment = environment;
    }

    @Override
    protected InstructionList create()
    {
        return new InstructionList();
    }

    @Override
    protected void parseLine(List<String> splitLine, int lineNumber, InstructionList result)
            throws SymbolException, LabelException
    {
        String label = extractLabel(splitLine);

        if(label != null)
        {
            if(!environment.contains(label))
                throw new LabelException(LabelException.LABEL_NOT_FOUND, lineNumber);
        }

        if(splitLine.isEmpty())
        {
            Instruction instruction = InstructionFactory.create(lineNumber, InstructionName.NOP);

            result.add(instruction);

            if(label != null)
                labelledInstructions.put(environment.getCode(label), instruction);

            return;
        }

        try
        {
            InstructionName instructionName = InstructionName.fromName(splitLine.get(0));
        }
        catch(SymbolException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }
    }
}
