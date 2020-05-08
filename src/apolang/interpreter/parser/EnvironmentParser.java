package apolang.interpreter.parser;

import java.util.List;

import apolang.errors.LabelException;
import apolang.errors.SymbolException;
import apolang.instructions.InstructionName;
import apolang.interpreter.Environment;

public class EnvironmentParser
        extends AbstractParser<Environment>
{
    public EnvironmentParser(List<String> lines)
    {
        super(lines);
    }

    @Override
    protected Environment create()
    {
        return new Environment();
    }

    @Override
    protected void parseLine(List<String> splitLine, int lineNumber, Environment result)
            throws LabelException, SymbolException
    {
        String label = extractLabel(splitLine);

        if(label != null)
        {
            if(result.contains(label))
                throw new LabelException(LabelException.DUPLICATED, lineNumber);

            try
            {
                result.addLabel(label);
            }
            catch(LabelException e)
            {
                e.setLineNumber(lineNumber);
                throw e;
            }
        }

        if(splitLine.isEmpty())
            return;

        try
        {
            InstructionName instructionName = InstructionName.fromName(splitLine.get(0));

            if(!instructionName.hasValueSet())
                return;

            result.addVariable(splitLine.get(1));
        }
        catch(SymbolException e)
        {
            e.setLineNumber(lineNumber);
            throw e;
        }
    }
}
