package apolang.interpreter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import apolang.errors.LanguageException;
import apolang.instructions.Instruction;
import apolang.instructions.InstructionFactory;
import apolang.instructions.InstructionList;
import apolang.interpreter.external.IOConnector;
import apolang.interpreter.external.Memory;
import apolang.interpreter.parser.OldParser;

/**
 * Klasa kontrolujaca przebieg pracy interpretera. Odpowiada za rozpoczecie parsowania oraz poprawne
 * wykonanie interpretacji.
 */
public class Controller
{
    private final List<String> lines;
    // TODO replace OldParser with Parser
    private OldParser oldParser;
    private Environment environment;
    private InstructionList instructionList;

    /**
     * Rozpoczyna prace interpretera i inicjalizuje jego skladniki.
     * @param memorySize rozmiar pamieci do alokacji
     * @param path sciezka dostepu do pliku programu
     */
    public Controller(int memorySize, Path path)
            throws IOException
    {
        lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        oldParser = new OldParser(path);
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
        instructionList = oldParser.parse();
        System.out.println("done");
    }

    /**
     * Dokonuje interpretacji programu. Kolejno odczytuje instrukcje z listy, pobiera zmienne,
     * wykonuje operacje i zapisuje nowe wartosci do zmiennych. W razie potrzeby wywoluje dodatkowe
     * skladniki interpretera.
     * @see Memory
     * @see IOConnector
     */
    public void run()
            throws LanguageException
    {
        for(Instruction instr : instructionList)
            instr.execute(oldParser.variables);
    }
}

