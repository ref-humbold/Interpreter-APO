package apolang.interpreter.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apolang.errors.ArithmeticException;
import apolang.errors.LabelException;
import apolang.errors.LanguageException;
import apolang.errors.SymbolException;
import apolang.instruction.Instruction;
import apolang.instruction.InstructionFactory;
import apolang.instruction.InstructionList;
import apolang.instruction.InstructionName;
import apolang.instruction.JumpInstruction;
import apolang.interpreter.Environment;

public class InstructionParser
        extends AbstractParser<InstructionList>
{
    private final Environment environment;
    private final Map<String, Instruction> labelledInstructions = new HashMap<>();

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
            throw new LabelException(String.format("Label `%s` not found", label), lineNumber);

        Instruction instruction;

        if(splitLine.isEmpty())
            instruction = InstructionFactory.create(lineNumber, InstructionName.NOP);
        else
            try
            {
                InstructionName instructionName = InstructionName.fromName(splitLine.get(0));
                String[] arguments = parseArguments(splitLine, instructionName, environment);

                instruction = InstructionFactory.create(lineNumber, instructionName, arguments);
            }
            catch(LanguageException e)
            {
                e.setLineNumber(lineNumber);
                throw e;
            }

        result.add(instruction);

        if(label != null)
            labelledInstructions.put(label, instruction);
    }

    @Override
    protected void afterParsing(InstructionList result)
    {
        for(Instruction instruction : result)
        {
            if(instruction instanceof JumpInstruction)
            {
                String label = instruction.getArgument(instruction.getArgumentsCount() - 1);

                ((JumpInstruction)instruction).setLink(labelledInstructions.get(label));
            }
        }
    }

    private String[] parseArguments(List<String> splitLine, InstructionName instructionName,
                                    Environment environment)
            throws LanguageException
    {
        int argumentsCount = instructionName.getArgumentsCount();
        String[] arguments = new String[argumentsCount];

        if(splitLine.size() <= argumentsCount)
            throw new SymbolException(String.format("Too few arguments for instruction `%s`",
                                                    instructionName.toString()));

        for(int i = 1; i <= argumentsCount; ++i)
        {
            if(i == argumentsCount)
            {
                if(instructionName.hasImmediate())
                    arguments[i - 1] = parseConstant(splitLine.get(i));
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

    private String parseConstant(String value)
            throws ArithmeticException
    {
        try
        {
            int decimalValue = value.startsWith("0x") ? Integer.parseInt(value.substring(2), 16)
                                                      : Integer.parseInt(value);

            return Integer.toString(decimalValue);
        }
        catch(NumberFormatException e)
        {
            throw new ArithmeticException("Invalid number format", e);
        }
    }

    private String parseLabel(String label, Environment environment)
            throws LabelException
    {
        environment.validateLabel(label);

        if(!environment.contains(label))
            throw new LabelException(String.format("Label `%s` not found", label));

        return label;
    }

    private String parseVariable(String variable, Environment environment, boolean checkZero)
            throws SymbolException
    {
        environment.validateVariable(variable);

        if(checkZero && Environment.ZERO_VARIABLE.equals(variable))
            throw new SymbolException("Cannot assign to variable `zero`");

        if(!environment.contains(variable))
            throw new SymbolException(String.format("Variable `%s` was not initialized", variable));

        return variable;
    }
}
