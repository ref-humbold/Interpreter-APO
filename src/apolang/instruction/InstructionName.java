package apolang.instruction;

import java.util.Set;

import apolang.errors.SymbolException;

public enum InstructionName
{
    ADD,
    ADDC,
    SUB,
    SUBC,
    MUL,
    MULC,
    DIV,
    DIVC,
    SHLT,
    SHRT,
    SHRS,
    AND,
    ANDC,
    OR,
    ORC,
    XOR,
    XORC,
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
            case ADDC:
            case SUB:
            case SUBC:
            case MUL:
            case MULC:
            case DIV:
            case DIVC:
            case SHLT:
            case SHRT:
            case SHRS:
            case AND:
            case ANDC:
            case OR:
            case ORC:
            case XOR:
            case XORC:
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
            case ADDC:
            case SUB:
            case SUBC:
            case MUL:
            case MULC:
            case DIV:
            case DIVC:
            case SHLT:
            case SHRT:
            case SHRS:
            case AND:
            case ANDC:
            case OR:
            case ORC:
            case XOR:
            case XORC:
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
            case ADDC:
            case SUBC:
            case MULC:
            case DIVC:
            case ANDC:
            case ORC:
            case XORC:
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
            case ADDC:
            case SUB:
            case SUBC:
            case MUL:
            case MULC:
            case DIV:
            case DIVC:
            case SHLT:
            case SHRT:
            case SHRS:
            case AND:
            case ANDC:
            case OR:
            case ORC:
            case XOR:
            case XORC:
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
