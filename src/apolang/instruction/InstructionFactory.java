package apolang.instruction;

import apolang.instruction.instructions.*;
import apolang.interpreter.external.Memory;

public final class InstructionFactory
{
    public static Memory memory;

    public static Instruction create(int lineNumber, InstructionName name, String... args)
    {
        switch(name)
        {
            case ASGN:
            case ASGNC:
                return new AssignInstruction(lineNumber, name, args);

            case ADD:
            case ADDC:
            case SUB:
            case SUBC:
            case MUL:
            case MULC:
            case DIV:
            case DIVC:
                return new ArithmeticInstruction(lineNumber, name, args);

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
                return new LogicalInstruction(lineNumber, name, args);

            case JUMP:
            case JPEQ:
            case JPNE:
            case JPLT:
            case JPGT:
                return new JumpInstruction(lineNumber, name, args);

            case LDW:
            case LDB:
            case STW:
            case STB:
                return new MemoryInstruction(memory, lineNumber, name, args);

            case PTLN:
            case PTINT:
            case PTCHR:
            case RDINT:
            case RDCHR:
                return new IOInstruction(lineNumber, name, args);

            case NOP:
                return new NOPInstruction(lineNumber);
        }

        return null;
    }
}
