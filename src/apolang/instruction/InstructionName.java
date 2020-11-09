package apolang.instruction;

import java.util.Set;

import apolang.exceptions.symbol.NotExistingInstructionException;

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
            throws NotExistingInstructionException
    {
        try
        {
            return InstructionName.valueOf(name);
        }
        catch(IllegalArgumentException e)
        {
            throw new NotExistingInstructionException(name, e);
        }
    }

    public ArgumentType[] getArgumentsTypes()
    {
        switch(this)
        {
            case PTLN:
            case NOP:
                return new ArgumentType[0];

            case JUMP:
                return new ArgumentType[]{ArgumentType.LABEL};

            case PTINT:
            case PTCHR:
            case RDINT:
            case RDCHR:
                return new ArgumentType[]{ArgumentType.VARIABLE};

            case ASGN:
            case LDW:
            case LDB:
            case STW:
            case STB:
                return new ArgumentType[]{ArgumentType.VARIABLE, ArgumentType.VARIABLE};

            case ASGNC:
                return new ArgumentType[]{ArgumentType.VARIABLE, ArgumentType.CONSTANT};

            case JPEQ:
            case JPNE:
            case JPLT:
            case JPGT:
                return new ArgumentType[]{ArgumentType.VARIABLE, ArgumentType.VARIABLE,
                                          ArgumentType.LABEL};

            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case AND:
            case OR:
            case XOR:
            case NAND:
            case NOR:
                return new ArgumentType[]{ArgumentType.VARIABLE, ArgumentType.VARIABLE,
                                          ArgumentType.VARIABLE};

            case ADDC:
            case SUBC:
            case MULC:
            case DIVC:
            case SHLT:
            case SHRT:
            case SHRS:
            case ANDC:
            case ORC:
            case XORC:
                return new ArgumentType[]{ArgumentType.VARIABLE, ArgumentType.VARIABLE,
                                          ArgumentType.CONSTANT};
        }

        return new ArgumentType[0];
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
                return Set.of(0);

            case LDW:
            case LDB:
                return Set.of(0, 1);

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
