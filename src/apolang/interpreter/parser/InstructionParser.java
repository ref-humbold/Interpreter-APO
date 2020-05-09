package apolang.interpreter.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apolang.errors.ArithmeticException;
import apolang.errors.LabelException;
import apolang.errors.LanguageException;
import apolang.errors.SymbolException;
import apolang.instructions.Instruction;
import apolang.instructions.InstructionFactory;
import apolang.instructions.InstructionList;
import apolang.instructions.InstructionName;
import apolang.instructions.JumpInstruction;
import apolang.interpreter.Environment;

public class InstructionParser
        extends AbstractParser<InstructionList>
{
    private Environment environment;
    private Map<Integer, Instruction> labelledInstructions = new HashMap<>();

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
            throws LanguageException
    {
        String label = extractLabel(splitLine);

        if(label != null && !environment.contains(label))
            throw new LabelException(LabelException.LABEL_NOT_FOUND, lineNumber);

        Instruction instruction;

        if(splitLine.isEmpty())
            instruction = InstructionFactory.create(lineNumber, InstructionName.NOP);
        else
            try
            {
                InstructionName instructionName = InstructionName.fromName(splitLine.get(0));
                int[] arguments = parseArguments(splitLine, instructionName, environment);

                instruction = InstructionFactory.create(lineNumber, instructionName, arguments);
            }
            catch(LanguageException e)
            {
                e.setLineNumber(lineNumber);
                throw e;
            }

        result.add(instruction);

        if(label != null)
            labelledInstructions.put(environment.getCode(label), instruction);
    }

    @Override
    protected void afterParsing(InstructionList result)
    {
        for(Instruction instruction : result)
        {
            if(instruction instanceof JumpInstruction)
            {
                int labelCode = instruction.getArgument(instruction.getArgumentsCount() - 1);

                ((JumpInstruction)instruction).setLink(labelledInstructions.get(labelCode));
            }
        }
    }

    private int[] parseArguments(List<String> splitLine, InstructionName instructionName,
                                 Environment environment)
            throws LanguageException
    {
        int argumentsCount = instructionName.getArgumentsCount();
        int[] arguments = new int[argumentsCount];

        if(splitLine.size() <= argumentsCount)
            throw new SymbolException(SymbolException.TOO_FEW_ARGUMENTS);

        for(int i = 1; i <= argumentsCount; ++i)
        {
            if(i == argumentsCount)
            {
                if(instructionName.hasImmediate())
                    arguments[i - 1] = parseImmediate(splitLine.get(i));
                else if(instructionName.isJump())
                    arguments[i - 1] = parseLabel(splitLine.get(i), environment);
            }
            else
                arguments[i - 1] = parseVariable(splitLine.get(i), environment,
                                                 instructionName.zeroVariablePosition()
                                                                .contains(i));
        }

        return arguments;
    }

    private int parseImmediate(String value)
            throws ArithmeticException
    {
        try
        {
            return value.startsWith("0x") ? Integer.parseInt(value.substring(2), 16)
                                          : Integer.parseInt(value);
        }
        catch(NumberFormatException e)
        {
            throw new ArithmeticException(ArithmeticException.INVALID_FORMAT, e);
        }
    }

    private int parseLabel(String value, Environment environment)
            throws LabelException
    {
        environment.validateLabel(value);

        Integer labelCode = environment.getCode(value);

        if(labelCode == null)
            throw new LabelException(LabelException.LABEL_NOT_FOUND);

        return labelCode;
    }

    private int parseVariable(String value, Environment environment, boolean checkZero)
            throws SymbolException
    {
        environment.validateVariable(value);

        if(checkZero && Environment.ZERO_VARIABLE.equals(value))
            throw new SymbolException(SymbolException.CHANGE_ZERO);

        Integer variableCode = environment.getCode(value);

        if(variableCode == null)
            throw new SymbolException(SymbolException.VARIABLE_NOT_INIT);

        return variableCode;
    }
}
