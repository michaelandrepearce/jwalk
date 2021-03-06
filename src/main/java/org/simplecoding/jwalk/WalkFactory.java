package org.simplecoding.jwalk;

import java.util.HashMap;
import java.util.Map;
import org.simplecoding.jwalk.components.SequenceBuilder;
import org.simplecoding.jwalk.components.WalkField;
import org.simplecoding.jwalk.components.WalkMethod;
import org.simplecoding.jwalk.components.WalkSequence;

/**
 *
 * @author fred
 */
public class WalkFactory {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private static final class SingletonHolder {
        private static final WalkFactory INSTANCE = new WalkFactory();
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private SequenceBuilder sequenceBuilder;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    private WalkFactory() {
        this.sequenceBuilder = new SequenceBuilder();
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */
    public static WalkFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    public WalkSequence createSequence(String expression) {
        return
            this.createSequence(
                expression,
                new HashMap<String, Class<?>>(4));
    }

    public WalkSequence createSequence(String expression, Map<String, Class<?>> definitions) {
        return
            this.sequenceBuilder.parse(expression, definitions);
    }

    public WalkField createField(String id) {
        return new WalkField(id);
    }

    public WalkMethod createMethod(String id) {
        return new WalkMethod(id);
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */

}
