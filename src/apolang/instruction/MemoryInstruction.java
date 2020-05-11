package apolang.instruction;

import apolang.errors.LanguageException;
import apolang.errors.MemoryException;
import apolang.interpreter.Environment;
import apolang.interpreter.external.Memory;

public class MemoryInstruction
        extends Instruction
{
    private final Memory memory;

    public MemoryInstruction(Memory memory, int lineNumber, InstructionName name, String... args)
    {
        super(lineNumber, name, args);
        this.memory = memory;
    }

    @Override
    public void execute(Environment environment)
            throws LanguageException
    {
        int argValue0;
        int argValue1;
        int result;

        switch(name)
        {
            case LDW:
                argValue1 = environment.getVariableValue(arguments[1]);
                result = memory.loadWord(argValue1);
                environment.setVariableValue(arguments[0], result);
                break;

            case LDB:
                try
                {
                    argValue1 = environment.getVariableValue(arguments[1]);
                    result = memory.loadByte(argValue1);
                    environment.setVariableValue(arguments[0], result);
                }
                catch(MemoryException e)
                {
                    e.setLineNumber(lineNumber);
                    throw e;
                }

                break;

            case STW:
                try
                {
                    argValue0 = environment.getVariableValue(arguments[0]);
                    argValue1 = environment.getVariableValue(arguments[1]);
                    memory.storeWord(argValue1, argValue0);
                }
                catch(MemoryException e)
                {
                    e.setLineNumber(lineNumber);
                    throw e;
                }

                break;

            case STB:
                try
                {
                    argValue0 = environment.getVariableValue(arguments[0]);
                    argValue1 = environment.getVariableValue(arguments[1]);
                    memory.storeByte(argValue1, argValue0);
                }
                catch(MemoryException e)
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
