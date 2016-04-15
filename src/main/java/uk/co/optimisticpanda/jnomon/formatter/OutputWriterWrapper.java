package uk.co.optimisticpanda.jnomon.formatter;

import java.time.Duration;
import java.util.Optional;

import uk.co.optimisticpanda.jnomon.Utils.Colour;

public class OutputWriterWrapper implements EventListener {

    private final EventListener eventListener;
    private final boolean realTime;
    private String lastLine;

    public OutputWriterWrapper(EventListener eventListener, boolean realTime) {
        this.eventListener = eventListener;
        this.realTime = realTime;
    }

    @Override
    public void onBeforeAll() {
        if (realTime) {
            System.out.println();
        }
    }
    
    @Override
    public void onLineStart(Colour colour, Duration sinceProcessStart, Duration sinceLastStart, String line) {
        if (realTime) {
            eventListener.onLineStart(colour, sinceProcessStart, sinceLastStart, line);
        } else {
            eventListener.onLineEnd(sinceProcessStart, sinceLastStart, getLastLine());
        }
        this.lastLine = line;
    }
    
    @Override
    public void onUpdate(Colour colour, Duration sinceProcessStart, Duration sinceLastStart) {
        if (realTime) {
            eventListener.onUpdate(colour, sinceProcessStart, sinceLastStart);
        }
    }
    
    @Override
    public void onFinally(Duration sinceProcessStart, Duration sinceLastStart) {
        if (!realTime) {
            eventListener.onLineEnd(sinceProcessStart, sinceLastStart, getLastLine());
        }
        eventListener.onFinally(sinceProcessStart, sinceLastStart);
    }
    
    private String getLastLine() {
        return Optional.ofNullable(lastLine).orElse("");
    }
}
