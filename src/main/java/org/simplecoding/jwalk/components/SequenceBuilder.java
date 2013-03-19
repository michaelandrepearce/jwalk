package org.simplecoding.jwalk.components;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fred
 */
public class SequenceBuilder {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(SequenceBuilder.class);

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private Pattern idPattern;
    private Pattern argumentPattern;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    public SequenceBuilder() {
        //--------------------------------------------------------------------------------------------------------------
        // methodName()
        // methodName().
        // methodName(arg1,arg2)
        // methodName(arg1, arg2)
        // methodName(arg1, arg2).
        //--------------------------------------------------------------------------------------------------------------
        this.idPattern          = Pattern.compile("(\\w+)(\\(([^)]*)\\))?\\.?");
        this.argumentPattern    = Pattern.compile("([^\\s,]+)\\s*,?\\s*");
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    public WalkSequence parse(String expression, Map<String, Class<?>> definitions) {

        LOGGER.debug(
            new StringBuilder("parse expression : '")
                .append(expression)
                .append("'")
                .toString());

        WalkSequence sequence = new WalkSequence();

        //--------------------------------------------------------------------------------------------------------------
        // Search for component (Field or Method)
        //--------------------------------------------------------------------------------------------------------------
        Matcher idMatcher = this.idPattern.matcher(expression);
        while(idMatcher.find()) {
            String id           = idMatcher.group(1);
            String parenthesis  = idMatcher.group(2);

            //----------------------------------------------------------------------------------------------------------
            // Field
            //----------------------------------------------------------------------------------------------------------
            if(StringUtils.isEmpty(parenthesis)) {
                LOGGER.debug(
                    new StringBuilder("add field : '")
                        .append(id)
                        .append("'")
                        .toString());

                sequence.add(new WalkField(id));
            }
            //----------------------------------------------------------------------------------------------------------
            // Method
            //----------------------------------------------------------------------------------------------------------
            else {
                String argumentsLine = idMatcher.group(3);

                LOGGER.debug(
                    new StringBuilder("add method : ")
                        .append("'")
                            .append(id).append("(").append(argumentsLine).append(")")
                        .append("'")
                        .toString());

                WalkMethod method = new WalkMethod(id);

                Matcher argumentMatcher = this.argumentPattern.matcher(argumentsLine);
                while(argumentMatcher.find()) {
                    String      argId           = argumentMatcher.group(1);
                    Class<?>    argDefinition   = definitions.get(argId);

                    LOGGER.debug(
                        new StringBuilder("add method argument : ")
                            .append("'")
                                .append(argId)
                                .append("(")
                                    .append(argDefinition)
                                .append(")")
                            .append("'")
                            .toString());

                    method.add(
                        new MethodArgument()
                            .setName(argId)
                            .setDefinition(argDefinition));
                }

                sequence.add(method);
            }
        }

        return sequence;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */

}
