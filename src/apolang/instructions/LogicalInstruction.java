package apolang.instructions;

import apolang.errors.ArithmeticException;
import apolang.errors.SymbolException;
import apolang.interpreter.environment.VariableEnvironment;

public class LogicalInstruction
        extends Instruction
{
    public LogicalInstruction(int lineNumber, InstructionName name, int... args)
    {
        super(lineNumber, name, args);
    }

    @Override
    public void execute(VariableEnvironment variables)
            throws ArithmeticException, SymbolException
    {
        int argValue1;
        int argValue2;

        switch(name)
        {
            case SHLT:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                if(arguments[2] < 0)
                    throw new ArithmeticException(ArithmeticException.NEGATIVE_SHIFT, lineNumber);

                try
                {
                    variables.setValue(arguments[0], argValue1 << arguments[2]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case SHRT:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                if(arguments[2] < 0)
                    throw new ArithmeticException(ArithmeticException.NEGATIVE_SHIFT, lineNumber);

                try
                {
                    variables.setValue(arguments[0], argValue1 >>> arguments[2]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case SHRS:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                if(arguments[2] < 0)
                    throw new ArithmeticException(ArithmeticException.NEGATIVE_SHIFT, lineNumber);

                try
                {
                    variables.setValue(arguments[0], argValue1 >> arguments[2]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case AND:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    argValue2 = variables.getValue(arguments[2]);
                    variables.setValue(arguments[0], argValue1 & argValue2);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case ANDI:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    variables.setValue(arguments[0], argValue1 & arguments[2]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case OR:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    argValue2 = variables.getValue(arguments[2]);
                    variables.setValue(arguments[0], argValue1 | argValue2);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case ORI:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    variables.setValue(arguments[0], argValue1 | arguments[2]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case XOR:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    argValue2 = variables.getValue(arguments[2]);
                    variables.setValue(arguments[0], argValue1 ^ argValue2);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case XORI:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    variables.setValue(arguments[0], argValue1 ^ arguments[2]);
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case NAND:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    argValue2 = variables.getValue(arguments[2]);
                    variables.setValue(arguments[0], ~(argValue1 & argValue2));
                }
                catch(SymbolException e)
                {
                    e.setLineNumber(lineNumber);

                    throw e;
                }

                break;

            case NOR:
                try
                {
                    argValue1 = variables.getValue(arguments[1]);
                    argValue2 = variables.getValue(arguments[2]);
                    variables.setValue(arguments[0], ~(argValue1 | argValue2));
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
