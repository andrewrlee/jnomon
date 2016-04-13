package uk.co.optimisticpanda.jnomon.formatter;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static uk.co.optimisticpanda.jnomon.Utils.BORDER;
import static uk.co.optimisticpanda.jnomon.Utils.SECONDS_FORMAT;
import uk.co.optimisticpanda.jnomon.ColourChooser;
import uk.co.optimisticpanda.jnomon.Utils.Colour;

public class ElapsedLineOutputWriter implements EventListener {
    private final ColourChooser colourChooser;
    
    public ElapsedLineOutputWriter(ColourChooser colourChooser) {
        this.colourChooser = colourChooser;
    }
    
    @Override
    public void onLineStart(long processStartTime, long lastStartTime, String line) {
        onUpdate(lastStartTime, lastStartTime);
        System.out.printf("%8s  %s %s\n", "", BORDER, line);
    }
    
    @Override
    public void onUpdate(long processStartTime, long currentStartTime) {
        long elapsed = currentTimeMillis() - currentStartTime;
        String text = format("%8ss %s", SECONDS_FORMAT.format((elapsed) / 1000d), BORDER);
        Colour colourForDuration = colourChooser.colourForDuration(elapsed);
        System.out.print("\033[1A\r" + colourForDuration.colourize(text) + "\n");
    }
    
    @Override
    public void onLineEnd(long processStartTime, long lastStartTime, String lastLine) {
        long elapsed = currentTimeMillis() - lastStartTime;
        System.out.printf("%8ss %s %s\n", SECONDS_FORMAT.format((elapsed) / 1000d), BORDER, lastLine);
    }
}