package apolang.instructions_old.instruction;

import apolang.exceptions.LanguageException;
import apolang.instructions_old.InstructionName;
import apolang.interpreter.Environment;
import apolang.interpreter.externals.IOConnector;

public class IOInstruction
        extends Instruction
{
    private final IOConnector connector;

    public IOInstruction(int lineNumber, InstructionName name, String... arguments)
    {
        super(lineNumber, name, arguments);
        connector = IOConnector.getInstance();
    }

    @Override
    public void execute(Environment environment)
            throws LanguageException
    {
        int argValue;

        switch(name)
        {
            case PTLN:
                connector.printLine();
                break;

            case PTINT:
                argValue = environment.getVariableValue(arguments[0]);
                connector.printInt(argValue);
                break;

            case PTCHR:
                argValue = environment.getVariableValue(arguments[0]);
                connector.printChar(argValue);
                break;

            case RDINT:
                argValue = connector.readInt();
                environment.setVariableValue(arguments[0], argValue);
                break;

            case RDCHR:
                argValue = connector.readChar();
                environment.setVariableValue(arguments[0], argValue);
                break;

            default:
                break;
        }
    }
}
