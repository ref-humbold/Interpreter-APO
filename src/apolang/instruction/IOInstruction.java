package apolang.instruction;

import apolang.errors.LanguageException;
import apolang.errors.SymbolException;
import apolang.interpreter.environment.VariableEnvironment;
import apolang.interpreter.external.IOConnector;

public class IOInstruction
        extends Instruction
{
    private final IOConnector connector;

    public IOInstruction(int lineNumber, InstructionName name, int... args)
    {
        super(lineNumber, name, args);
        connector = IOConnector.getInstance();
    }

    @Override
    public void execute(VariableEnvironment variables)
            throws LanguageException
    {
        int argValue;

        switch(name)
        {
            case PTLN:
                connector.printLine();
                break;

            case PTINT:
                try
                {
                    argValue = variables.getValue(arguments[0]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                connector.printInt(argValue);
                break;

            case PTCHR:
                try
                {
                    argValue = variables.getValue(arguments[0]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                connector.printChar(argValue);
                break;

            case RDINT:
                argValue = connector.readInt();

                try
                {
                    variables.setValue(arguments[0], argValue);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case RDCHR:
                argValue = connector.readChar();

                try
                {
                    variables.setValue(arguments[0], argValue);
                }
                catch(SymbolException e)
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
