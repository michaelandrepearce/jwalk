package org.simplecoding.jwalk;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.simplecoding.jwalk.components.Argument;
import org.simplecoding.jwalk.components.XField;
import org.simplecoding.jwalk.components.XMethod;
import org.simplecoding.jwalk.components.XSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fred
 */
public class ExpressionParser {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(ExpressionParser.class);

    private static final class SingletonHolder {
        private static final ExpressionParser INSTANCE = new ExpressionParser();
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private Pattern idPattern;
    private Pattern argumentPattern;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    private ExpressionParser() {
        this.idPattern          = Pattern.compile("(\\w+)(\\(([^)]*)\\))?\\.?");
        this.argumentPattern    = Pattern.compile("(%)\\s*,?\\s*");
    }

    public static ExpressionParser getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    public XSequence parse(String expression, Class<?>... classes) {
        Deque<Class<?>> arguments = new LinkedList<Class<?>>(Arrays.asList(classes));

        LOGGER.debug(
            new StringBuilder("parse expression : '")
                .append(expression)
                .append("'")
                .toString());

        XSequence sequence = new XSequence();

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

                sequence.add(new XField(id));
            }
            //----------------------------------------------------------------------------------------------------------
            // Method
            //----------------------------------------------------------------------------------------------------------
            else {
                String  argumentsLine   = idMatcher.group(3);
                Matcher argumentMatcher = this.argumentPattern.matcher(argumentsLine);

                LOGGER.debug(
                    new StringBuilder("add method : ")
                        .append("'")
                            .append(id).append("(").append(argumentsLine).append(")")
                        .append("'")
                        .toString());

                XMethod method = new XMethod(id);

                while(argumentMatcher.find()) {
                    String placeHolder = argumentMatcher.group(1);

                    LOGGER.debug(
                        new StringBuilder("add method argument : ")
                            .append("'")
                                .append(placeHolder)
                            .append("'")
                            .toString());

                    Class<?> clazz = arguments.pop();

                    method.add(clazz);
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
