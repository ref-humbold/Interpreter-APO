package apolang;

import org.apache.commons.cli.*;

class CliParser
{
    private final CommandLineParser parser = new DefaultParser();
    private final Options options = new Options();
    private final HelpFormatter helpFormatter = new HelpFormatter();

    CliParser()
    {
        options.addOption(Option.builder("m")
                                .longOpt("memory")
                                .hasArg()
                                .argName("SIZE")
                                .type(Integer.class)
                                .desc("Provide memory size in kB (default is 1 kB)")
                                .build());
        options.addOption("h", "help", false, "Display this help message and exit");
    }

    Arguments parse(String[] args)
            throws ParseException
    {
        Arguments arguments = new Arguments();
        CommandLine commandLine = parser.parse(options, args);
        String[] remainingArgs = commandLine.getArgs();

        arguments.help = commandLine.hasOption('h');

        if(arguments.help)
        {
            return arguments;
        }

        try
        {
            arguments.memorySize = Integer.parseInt(commandLine.getOptionValue('m', "1"));
        }
        catch(NumberFormatException e)
        {
            throw new ParseException("Invalid memory size. %s".formatted(e.getMessage()));
        }

        if(remainingArgs.length == 0)
        {
            throw new ParseException("File not provided.");
        }

        arguments.filename = remainingArgs[0];

        if(!arguments.filename.endsWith(".apo"))
        {
            throw new ParseException("Invalid file extension, should be '.apo'.");
        }

        return arguments;
    }

    void printHelp()
    {
        helpFormatter.printHelp("apolang [OPTIONS]... FILE", options);
    }

    static class Arguments
    {
        private boolean help;
        private int memorySize;
        private String filename;

        boolean isHelp()
        {
            return help;
        }

        int getMemorySize()
        {
            return memorySize;
        }

        String getFilename()
        {
            return filename;
        }
    }
}
