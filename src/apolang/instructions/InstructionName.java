package apolang.instructions;

import java.util.Set;

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
            case JUMP:
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

            default:
                return 0;
        }
    }

    public boolean isJump()
    {
        return this == JUMP || this == JPEQ || this == JPNE || this == JPLT || this == JPGT;
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

            default:
                return false;
        }
    }

    public boolean hasImmediate()
    {
        switch(this)
        {
            case ADDI:
            case SUBI:
            case MULI:
            case DIVI:
            case ANDI:
            case ORI:
            case XORI:
            case SHLT:
            case SHRT:
            case SHRS:
                return true;

            default:
                return false;
        }
    }

    public Set<Integer> zeroVariablePosition()
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
                return Set.of(1);

            case LDW:
            case LDB:
                return Set.of(1, 2);

            default:
                return Set.of();
        }
    }
}
