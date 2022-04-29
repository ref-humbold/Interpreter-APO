package apolang.interpreter.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apolang.exceptions.LanguageException;
import apolang.exceptions.arithmetic.ArithmeticException;
import apolang.exceptions.label.LabelException;
import apolang.exceptions.label.LabelNotFoundException;
import apolang.exceptions.symbol.AssignmentToZeroException;
import apolang.exceptions.symbol.SymbolException;
import apolang.exceptions.symbol.TooFewArgumentsException;
import apolang.exceptions.symbol.VariableNotInitializedException;
import apolang.instructions.ArgumentType;
import apolang.instructions.InstructionFactory;
import apolang.instructions.instruction.Instruction;
import apolang.instructions.instruction.JumpInstruction;
import apolang.instructions.list.InstructionList;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementName;
import apolang.interpreter.Environment;

public class InstructionParser
        extends AbstractParser<InstructionList>
{
    private final Map<String, Instruction> labelledInstructions = new HashMap<>();
    private final InstructionFactory instructionFactory = InstructionFactory.getInstance();
    private Environment environment;
    private boolean usesEndLabel = false;

    public InstructionParser(List<String> lines)
    {
        super(lines);
    }

    public Environment getEnvironment()
    {
        return environment;
    }

    public void setEnvironment(Environment environment)
    {
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
            throw new LabelNotFoundException(label, lineNumber);

        Instruction instruction;

        if(splitLine.isEmpty())
            instruction = instructionFactory.create(lineNumber, StatementName.NOP);
        else
            try
            {
                Statement statement = StatementName.parse(splitLine.get(0)).getStatement();
                String[] arguments = parseArguments(splitLine, statement, environment);

                instruction = instructionFactory.create(lineNumber, statement, arguments);
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
        if(usesEndLabel)
        {
            Instruction endInstruction =
                    instructionFactory.create(result.getLinesCount() + 1, StatementName.NOP);

            result.add(endInstruction);
            labelledInstructions.put(Environment.END_LABEL, endInstruction);
        }

        for(Instruction instruction : result)
            if(instruction instanceof JumpInstruction)
            {
                String label = instruction.getArgument(instruction.getArgumentsCount() - 1);

                ((JumpInstruction)instruction).setLink(labelledInstructions.get(label));
            }
    }

    private String[] parseArguments(List<String> splitLine, Statement statement,
                                    Environment environment)
            throws LanguageException
    {
        ArgumentType[] argumentsTypes = statement.getArgumentsTypes();
        int argumentsCount = argumentsTypes.length;
        String[] arguments = new String[argumentsCount];

        if(splitLine.size() <= argumentsCount)
            throw new TooFewArgumentsException(statement.getName());

        for(int i = 0; i < argumentsCount; ++i)
            switch(argumentsTypes[i])
            {
                case VARIABLE_READ:
                    arguments[i] = parseVariable(splitLine.get(i + 1), environment, false);
                    break;

                case VARIABLE_WRITE:
                    arguments[i] = parseVariable(splitLine.get(i + 1), environment, true);
                    break;

                case LABEL:
                    arguments[i] = parseLabel(splitLine.get(i + 1), environment);
                    break;

                case CONSTANT:
                    arguments[i] = parseConstant(splitLine.get(i + 1));
                    break;
            }

        return arguments;
    }

    private String parseConstant(String value)
            throws ArithmeticException
    {
        try
        {
            int decimalValue = value.startsWith("0x")
                    ? Integer.parseInt(value.substring(2), 16)
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
            throw new LabelNotFoundException(label);

        if(Environment.END_LABEL.equals(label))
            usesEndLabel = true;

        return label;
    }

    private String parseVariable(String variable, Environment environment, boolean writable)
            throws SymbolException
    {
        environment.validateVariable(variable);

        if(writable && Environment.ZERO_VARIABLE.equals(variable))
            throw new AssignmentToZeroException();

        if(!environment.contains(variable))
            throw new VariableNotInitializedException(variable);

        return variable;
    }
}
