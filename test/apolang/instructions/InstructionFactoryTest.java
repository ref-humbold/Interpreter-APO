package apolang.instructions;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import apolang.instructions.instruction.Instruction;
import apolang.instructions.instruction.JumpInstruction;
import apolang.instructions.instruction.SimpleInstruction;
import apolang.instructions.statement.StatementName;

public class InstructionFactoryTest
{
    private static Stream<Arguments> getParameters()
    {
        return Stream.of(Arguments.of(StatementName.ASGN, SimpleInstruction.class),
                         Arguments.of(StatementName.SUB, SimpleInstruction.class),
                         Arguments.of(StatementName.DIVC, SimpleInstruction.class),
                         Arguments.of(StatementName.SHRS, SimpleInstruction.class),
                         Arguments.of(StatementName.ORC, SimpleInstruction.class),
                         Arguments.of(StatementName.XOR, SimpleInstruction.class),
                         Arguments.of(StatementName.LDW, SimpleInstruction.class),
                         Arguments.of(StatementName.RDINT, SimpleInstruction.class),
                         Arguments.of(StatementName.NOP, SimpleInstruction.class),
                         Arguments.of(StatementName.EXIT, SimpleInstruction.class),
                         Arguments.of(StatementName.JUMP, JumpInstruction.class),
                         Arguments.of(StatementName.JPEQ, JumpInstruction.class),
                         Arguments.of(StatementName.JPGT, JumpInstruction.class));
    }

    @ParameterizedTest
    @MethodSource("getParameters")
    public void create_WhenStatement_ThenInstruction(StatementName statementName,
                                                     Class<? extends Instruction> instructionClass)
    {
        // when
        Instruction result = InstructionFactory.getInstance().create(1, statementName);
        // then
        Assertions.assertSame(result.getClass(), instructionClass);
        Assertions.assertSame(result.getStatement().getClass(),
                              statementName.getStatement().getClass());
    }
}
