package apolang.exceptions.symbol;

import apolang.instruction.InstructionName;

public class TooFewArgumentsException
        extends SymbolException
{
    private static final long serialVersionUID = 921781665153657780L;

    public TooFewArgumentsException(InstructionName instructionName)
    {
        super(String.format("Too few arguments for instruction `%s`", instructionName.toString()));
    }
}
