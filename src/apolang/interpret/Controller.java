package apolang.interpret;

import java.nio.file.Path;

import apolang.errors.LanguageException;
import apolang.instructions.Instruction;
import apolang.instructions.InstructionFactory;

/**
 * Klasa kontrolujaca przebieg pracy interpretera. Odpowiada za rozpoczecie parsowania oraz poprawne
 * wykonanie interpretacji.
 */
public class Controller
{
    // TODO replace OldParser with Parser
    private OldParser oldParser;
    private InstructionList instructions;

    /**
     * Rozpoczyna prace interpretera i inicjalizuje jego skladniki.
     * @param memorySize rozmiar pamieci do alokacji
     * @param path sciezka dostepu do pliku programu
     */
    public Controller(int memorySize, Path path)
    {
        oldParser = new OldParser(path);
        instructions = null;

        InstructionFactory.memory = new Memory(memorySize);
    }

    /**
     * Dokonuje parsowania programu. Uruchamia parser {@link OldParser} tworzacy liste
     * instrukcji.
     */
    public void parse()
            throws Exception
    {
        System.out.print("parsing>> ");
        instructions = oldParser.parse();
        System.out.println("done");
    }

    /**
     * Dokonuje interpretacji programu. Kolejno odczytuje instrukcje z listy, pobiera zmienne,
     * wykonuje operacje i zapisuje nowe wartosci do zmiennych. W razie potrzeby wywoluje dodatkowe
     * skladniki interpretera.
     * @see Memory
     * @see IOConnector
     */
    public void make()
            throws LanguageException
    {
        for(Instruction instr : instructions)
            instr.execute(oldParser.variables);
    }
}

