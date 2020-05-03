package apolang.instructions;

import apolang.errors.SymbolException;

public final class Instructions
{
    /**
     * Zamienia nazwe instrukcji podana jako {@link String} na {@link InstructionName}.
     * @param name nazwa instrukcji
     * @return symbol instrukcji
     */
    public static InstructionName convertToName(String name)
            throws SymbolException
    {
        try
        {
            return InstructionName.valueOf(name);
        }
        catch(IllegalArgumentException e)
        {
            throw new SymbolException(SymbolException.NO_SUCH_INSTRUCTION);
        }
    }

    /**
     * @param name nazwa instrukcji
     * @return czy instrukcja zapisuje wartosc
     */
    public static boolean isValueSet(InstructionName name)
            throws SymbolException
    {
        switch(name)
        {
            case ADD:
            case ADDI:
            case SUB:
            case SUBI:
            case MUL:
            case MULI:
            case DIV:
            case DIVI:
            case SHLT:
            case SHRT:
            case SHRS:
            case AND:
            case ANDI:
            case OR:
            case ORI:
            case XOR:
            case XORI:
            case NAND:
            case NOR:
            case RDINT:
            case RDCHR:
            case LDW:
            case LDB:
                return true;

            case JUMP:
            case JPEQ:
            case JPNE:
            case JPLT:
            case JPGT:
            case STW:
            case STB:
            case PTLN:
            case PTINT:
            case PTCHR:
            case NOP:
                return false;
        }

        throw new SymbolException(SymbolException.NO_SUCH_INSTRUCTION);
    }

    /**
     * @param name nazwa instrukcji
     * @return liczba argumentow instrukcji
     */
    public static int getArgsNumber(InstructionName name)
            throws SymbolException
    {
        switch(name)
        {
            case PTLN:
            case NOP:
                return 0;

            case LDW:
            case LDB:
            case PTINT:
            case PTCHR:
            case RDINT:
            case RDCHR:
                return 1;

            case STW:
            case STB:
                return 2;

            case JUMP:
            case JPEQ:
            case JPNE:
            case JPLT:
            case JPGT:
            case ADD:
            case ADDI:
            case SUB:
            case SUBI:
            case MUL:
            case MULI:
            case DIV:
            case DIVI:
            case SHLT:
            case SHRT:
            case SHRS:
            case AND:
            case ANDI:
            case OR:
            case ORI:
            case XOR:
            case XORI:
            case NAND:
            case NOR:
                return 3;
        }

        throw new SymbolException(SymbolException.NO_SUCH_INSTRUCTION);
    }
}
