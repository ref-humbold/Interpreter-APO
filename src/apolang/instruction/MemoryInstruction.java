package apolang.instruction;

import apolang.errors.MemoryException;
import apolang.errors.SymbolException;
import apolang.interpreter.environment.VariableEnvironment;
import apolang.interpreter.external.Memory;

public class MemoryInstruction
        extends Instruction
{
    private final Memory memory;

    public MemoryInstruction(Memory memory, int lineNumber, InstructionName name, int... args)
    {
        super(lineNumber, name, args);
        this.memory = memory;
    }

    @Override
    public void execute(VariableEnvironment variables)
            throws MemoryException, SymbolException
    {
        int argValue0;
        int argValue1;
        int result;

        switch(name)
        {
            case LDW:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    result = memory.loadWord(argValue1);
                    variables.setValue(arguments[0], result);
                }
                catch(SymbolException | MemoryException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case LDB:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    result = memory.loadByte(argValue1);
                    variables.setValue(arguments[0], result);
                }
                catch(SymbolException | MemoryException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case STW:
                try
                {
                    argValue0 = variables.getValue(arguments[0]);
                    argValue1 = variables.getValue(arguments[1]);
                    memory.storeWord(argValue1, argValue0);
                }
                catch(SymbolException | MemoryException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case STB:
                try
                {
                    argValue0 = variables.getValue(arguments[0]);
                    argValue1 = variables.getValue(arguments[1]);
                    memory.storeByte(argValue1, argValue0);
                }
                catch(SymbolException | MemoryException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            default:
                break;
        }
    }
}
