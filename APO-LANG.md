#### Assignment instructions

+ `ASGN <var0> <var1>` - Assign value from variable `<var1>` to variable `<var0>`.
+ `ASGNC <var0> <const>` - Assign value of constant `<const>` to variable `<var0>`.

#### Arithmetic instructions

+ `ADD <var0> <var1> <var2>` - Add values in variables `<var1>` and `<var2>` and
  save the result to variable `<var0>`.
+ `ADDC <var0> <var1> <const>` - Add constant `<const>` to value in variable `<var1>`
  and save the result to variable `<var0>`.
+ `SUB <var0> <var1> <var2>` - Subtract value in variable `<var2>` from value in
  variable `<var1>` and save the result to variable `<var0>`.
+ `SUBC <var0> <var1> <const>` - Subtract constant `<const>` from value in variable `<var1>`
  and save the result to variable `<var0>`.
+ `MUL <var0> <var1> <var2>` - Multiply values in variables `<var1>` and `<var2>` and
  save the result to variable `<var0>`.
+ `MULC <var0> <var1> <const>` - Multiply value in variable `<var1>` by constant
  `<const>` and save the result to variable `<var0>`.
+ `DIV <var0> <var1> <var2>` - Divide value in variable `<var1>` by value in variable
  `<var2>` and save the result to variable `<var0>`.
+ `DIVC <var0> <var1> <const>` - Divide value in variable `<var1>` by constant
  `<const>` and save the result to variable `<var0>`.

#### Logical instructions

+ `AND <var0> <var1> <var2>` - Perform bitwise AND on values in variables `<var1>` and
  `<var2>` and save the result to variable `<var0>`.
+ `ANDC <var0> <var1> <const>` - Perform bitwise AND on value in variables `<var1>` and
  constant `<const>` and save the result to variable `<var0>`.
+ `OR <var0> <var1> <var2>` - Perform bitwise OR on values in variables `<var1>` and
  `<var2>` and save the result to variable `<var0>`.
+ `ORC <var0> <var1> <const>` - Perform bitwise OR on value in variables `<var1>` and
  constant `<const>` and save the result to variable `<var0>`.
+ `XOR <var0> <var1> <var2>` - Perform bitwise XOR on values in variables `<var1>` and
  `<var2>` and save the result to variable `<var0>`.
+ `XORC <var0> <var1> <const>` - Perform bitwise XOR on value in variables `<var1>` and
  constant `<const>` and save the result to variable `<var0>`.
+ `NOT <var0> <var1>` - Negate bits of value in variable `<var1>` and save the result to
  variable `<var0>`.
+ `SHLT <var0> <var1> <const>` - Shift bits of value in variable `<var1>` `<const>`
  positions left and save the result to variable `<var0>`.
+ `SHRT <var0> <var1> <const>` - Shift bits of value in variable `<var1>` `<const>`
  positions right and save the result to variable `<var0>`. Bits will be filled with zeroes.
+ `SHRS <var0> <var1> <const>` - Shift bits of value in variable `<var1>` `<const>`
  positions right and save the result to variable `<var0>`. Bits will be filled with value of
  the sign bit.

#### Jump instructions

+ `JUMP <Label>` - Jump unconditionally to label `<Label>`.
+ `JPEQ <var1> <var2> <Label>` - Jump to label `<Label>` if values of variables `<var1>`
  and `<var2>` are equal.
+ `JPNE <var1> <var2> <Label>` - Jump to label `<Label>` if values of variables `<var1>`
  and `<var2>` are not equal.
+ `JPLT <var1> <var2> <Label>` - Jump to label `<Label>` if value in variable `<var1>`
  is less than value in variable `<var2>`.
+ `JPGT <var1> <var2> <Label>` - Jump to label `<Label>` if value in variable `<var1>`
  is greater than value in variable `<var2>`.

#### Memory instructions

+ `LDB <var0> <var1>` - Load byte (8-bit) value from memory address in variable `<var1>` and
  save it to variable `<var0>`.
+ `LDW <var0> <var1>` - Load word (32-bit) value from memory address in variable `<var1>` and
  save it to variable `<var0>`. Address has to be dividable by 4.
+ `STB <var1> <var2>` - Store byte (8-bit) value from variable `<var1>` in memory address in
  variable `<var2>`.
+ `STW <var1> <var2>` - Store word (32-bit) value from variable `<var1>` in memory address in
  variable `<var2>`. Address has to be dividable by 4.

#### I/O instructions

+ `PTLN` - Print new line character.
+ `PTINT <var1>` - Print value in variable `<var1>` as an integer.
+ `PTCHR <var1>` - Print value in variable `<var1>` as a character.
+ `RDINT <var0>` - Read an integer value to variable `<var0>`.
+ `RDCHR <var0>` - Read a character value to variable `<var0>`.

#### Other instructions

+ `NOP` - Do nothing.
+ `EXIT` - End the programme execution immediately.
