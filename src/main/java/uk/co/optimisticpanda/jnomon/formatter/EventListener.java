package uk.co.optimisticpanda.jnomon.formatter;

import uk.co.optimisticpanda.jnomon.ColourChooser;

public interface EventListener {
    
    default void onBeforeAll() {
        // NO-OP
    }
    
    default void onLineStart(long processStartTime, long lastStartTime, String line) {
        // NO-OP
    }

    default void onUpdate(long processStartTime, long currentStartTime) {
        // NO-OP
    }

    default void onLineEnd(long processStartTime, long lastStartTime, String lastLine) {
        // NO-OP
    }

    default void onFinally(long processStartTime, long currentStartTime) {
        // NO-OP
    }
    
    public interface EventListenerFactory {
        EventListener build(ColourChooser colourChooser);
    }



}
