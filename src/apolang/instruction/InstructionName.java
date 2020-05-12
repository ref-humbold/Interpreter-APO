package apolang.instruction;

import java.util.Set;

import apolang.errors.SymbolException;

public enum InstructionName
{
    ASGN,
    ASGNC,
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
            throw new SymbolException(String.format("Not existing instruction `%s`", name), e);
        }
    }

    public int getArgumentsCount()
    {
        switch(this)
        {
            case PTLN:
            case NOP:
                return 0;

            case ASGN:
            case ASGNC:
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
        }

        return 0;
    }

    public boolean isJump()
    {
        return this == JUMP || this == JPEQ || this == JPNE || this == JPLT || this == JPGT;
    }

    public boolean hasValueSet()
    {
        switch(this)
        {

            case ASGN:
            case ASGNC:
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

    public boolean hasConstant()
    {
        switch(this)
        {
            case ASGNC:
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

            case ASGN:
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case AND:
            case OR:
            case XOR:
            case NAND:
            case NOR:
            case JUMP:
            case JPEQ:
            case JPNE:
            case JPLT:
            case JPGT:
            case LDW:
            case LDB:
            case STW:
            case STB:
            case PTLN:
            case PTINT:
            case PTCHR:
            case RDINT:
            case RDCHR:
            case NOP:
                return false;
        }

        return false;
    }

    public Set<Integer> zeroVariablePosition()
    {
        switch(this)
        {
            case ASGN:
            case ASGNC:
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
                return Set.of();
        }

        return Set.of();
    }
}
