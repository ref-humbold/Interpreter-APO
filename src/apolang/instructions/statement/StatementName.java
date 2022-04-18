package apolang.instructions.statement;

import java.util.function.Supplier;

import apolang.instructions.statement.arithmetic.*;
import apolang.instructions.statement.assignment.AssignConstStatement;
import apolang.instructions.statement.assignment.AssignmentStatement;
import apolang.instructions.statement.io.PrintCharStatement;
import apolang.instructions.statement.io.PrintIntStatement;
import apolang.instructions.statement.io.PrintLineStatement;
import apolang.instructions.statement.io.ReadCharStatement;
import apolang.instructions.statement.io.ReadIntStatement;
import apolang.instructions.statement.jump.JumpEqualStatement;
import apolang.instructions.statement.jump.JumpGreaterStatement;
import apolang.instructions.statement.jump.JumpLessStatement;
import apolang.instructions.statement.jump.JumpNotEqualStatement;
import apolang.instructions.statement.jump.JumpStatement;
import apolang.instructions.statement.logical.*;
import apolang.instructions.statement.memory.LoadByteStatement;
import apolang.instructions.statement.memory.LoadWordStatement;
import apolang.instructions.statement.memory.StoreByteStatement;
import apolang.instructions.statement.memory.StoreWordStatement;

public enum StatementName
{
    ASGN(AssignmentStatement::new),
    ASGNC(AssignConstStatement::new),
    ADD(AddStatement::new),
    ADDC(AddConstStatement::new),
    SUB(SubtractStatement::new),
    SUBC(SubtractConstStatement::new),
    MUL(MultiplyStatement::new),
    MULC(MultiplyConstStatement::new),
    DIV(DivideStatement::new),
    DIVC(DivideConstStatement::new),
    SHLT(ShiftLeftStatement::new),
    SHRT(ShiftRightStatement::new),
    SHRS(ShiftRightSignedStatement::new),
    AND(AndStatement::new),
    ANDC(AndConstStatement::new),
    OR(OrStatement::new),
    ORC(OrConstStatement::new),
    XOR(XorStatement::new),
    XORC(XorConstStatement::new),
    NOT(NotStatement::new),
    JUMP(JumpStatement::new),
    JPEQ(JumpEqualStatement::new),
    JPNE(JumpNotEqualStatement::new),
    JPLT(JumpLessStatement::new),
    JPGT(JumpGreaterStatement::new),
    LDW(LoadWordStatement::new),
    LDB(LoadByteStatement::new),
    STW(StoreWordStatement::new),
    STB(StoreByteStatement::new),
    PTLN(PrintLineStatement::new),
    PTINT(PrintIntStatement::new),
    PTCHR(PrintCharStatement::new),
    RDINT(ReadIntStatement::new),
    RDCHR(ReadCharStatement::new),
    NOP(NoOperationStatement::new),
    EXIT(ExitStatement::new);

    private final Supplier<Statement> statement;

    StatementName(Supplier<Statement> statement)
    {
        this.statement = statement;
    }

    public Statement getStatement()
    {
        return statement.get();
    }
}
