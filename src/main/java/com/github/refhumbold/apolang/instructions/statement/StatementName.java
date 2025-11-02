package com.github.refhumbold.apolang.instructions.statement;

import java.util.function.Supplier;
import com.github.refhumbold.apolang.exceptions.symbol.NotExistingInstructionException;
import com.github.refhumbold.apolang.instructions.statement.arithmetic.*;
import com.github.refhumbold.apolang.instructions.statement.assignment.AssignConstStatement;
import com.github.refhumbold.apolang.instructions.statement.assignment.AssignmentStatement;
import com.github.refhumbold.apolang.instructions.statement.io.PrintCharStatement;
import com.github.refhumbold.apolang.instructions.statement.io.PrintIntStatement;
import com.github.refhumbold.apolang.instructions.statement.io.PrintLineStatement;
import com.github.refhumbold.apolang.instructions.statement.io.ReadCharStatement;
import com.github.refhumbold.apolang.instructions.statement.io.ReadIntStatement;
import com.github.refhumbold.apolang.instructions.statement.jump.JumpEqualStatement;
import com.github.refhumbold.apolang.instructions.statement.jump.JumpGreaterStatement;
import com.github.refhumbold.apolang.instructions.statement.jump.JumpLessStatement;
import com.github.refhumbold.apolang.instructions.statement.jump.JumpNotEqualStatement;
import com.github.refhumbold.apolang.instructions.statement.jump.JumpStatement;
import com.github.refhumbold.apolang.instructions.statement.logical.*;
import com.github.refhumbold.apolang.instructions.statement.memory.LoadByteStatement;
import com.github.refhumbold.apolang.instructions.statement.memory.LoadWordStatement;
import com.github.refhumbold.apolang.instructions.statement.memory.StoreByteStatement;
import com.github.refhumbold.apolang.instructions.statement.memory.StoreWordStatement;

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

    public static StatementName parse(String name)
            throws NotExistingInstructionException
    {
        try
        {
            return StatementName.valueOf(name);
        }
        catch(IllegalArgumentException e)
        {
            throw new NotExistingInstructionException(name, e);
        }
    }
}
