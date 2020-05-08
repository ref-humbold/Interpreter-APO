package apolang.instructions;

import apolang.errors.SymbolException;

public enum InstructionName
{
    ADD,
    ADDI,
    SUB,
    SUBI,
    MUL,
    MULI,
    DIV,
    DIVI,
    SHLT,
    SHRT,
    SHRS,
    AND,
    ANDI,
    OR,
    ORI,
    XOR,
    XORI,
    NAND,
    NOR,
    JUMP,
    JPEQ,
    JPNE,
    JPLT,
    JPGT,
    LDW,
    LDB,
    STW,
    STB,
    PTLN,
    PTINT,
    PTCHR,
    RDINT,
    RDCHR,
    NOP;

    public static InstructionName fromName(String name)
            throws SymbolException
    {
        try
        {
            return InstructionName.valueOf(name);
        }
        catch(IllegalArgumentException e)
        {
            throw new SymbolException(SymbolException.NO_SUCH_INSTRUCTION, e);
        }
    }

    public int getArgumentsCount()
    {
        switch(this)
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

        return 0;
    }

    public boolean hasValueSet()
    {
        switch(this)
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

        return false;
    }
}
