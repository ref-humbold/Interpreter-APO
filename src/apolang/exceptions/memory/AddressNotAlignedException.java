package apolang.exceptions.memory;

public class AddressNotAlignedException
        extends MemoryException
{
    private static final long serialVersionUID = -8414974221338687948L;

    public AddressNotAlignedException(int address)
    {
        super(String.format("Address %d is not aligned to a word", address));
    }
}
