package uk.co.optimisticpanda.jnomon.formatter;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static uk.co.optimisticpanda.jnomon.Utils.BORDER;
import static uk.co.optimisticpanda.jnomon.Utils.SECONDS_FORMAT;
import uk.co.optimisticpanda.jnomon.ColourChooser;
import uk.co.optimisticpanda.jnomon.Utils.Colour;

public class ElapsedTotalWriter implements EventListener {
    private final ColourChooser colourChooser;

    public ElapsedTotalWriter(ColourChooser colourChooser) {
        this.colourChooser = colourChooser;
    }

    @Override
    public void onLineStart(long processStartTime, long lastStartTime, String line) {
        onUpdate(processStartTime, lastStartTime);
        System.out.printf("%8s  %s %s\n", "", BORDER, line);
    }
    
    @Override
    public void onUpdate(long processStartTime, long currentStartTime) {
        String text = format("%8ss %s", SECONDS_FORMAT.format((currentTimeMillis() - processStartTime) / 1000d), BORDER);
        Colour colour = colourChooser.colourForDuration(currentTimeMillis() - currentStartTime);
        System.out.print("\033[1A\r" + colour.colourize(text) + "\n");
    }

    @Override
    public void onLineEnd(long processStartTime, long lastStartTime, String lastLine) {
        System.out.printf("%8ss %s %s\n", SECONDS_FORMAT.format((currentTimeMillis() - processStartTime) / 1000d), BORDER, lastLine);
    }
}
