package org.simplecoding.jwalk.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.simplecoding.jwalk.JWalkException;
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
    private Pattern componentPattern;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    private ExpressionParser() {
        this.componentPattern = Pattern.compile("(\\w+)(\\(\\))?\\.?");
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
    public XSequence parse(String expression) {

        LOGGER.debug(
            new StringBuilder("parse expression : '")
                .append(expression)
                .append("'")
                .toString());

        XSequence sequence = new XSequence();

        Matcher matcher = this.componentPattern.matcher(expression);
        while(matcher.find()) {
            String id           = matcher.group(1);
            String parenthesis  = matcher.group(2);

            //----------------------------------------------------------------------------------------------------------
            // Field
            //----------------------------------------------------------------------------------------------------------
            if(StringUtils.isEmpty(parenthesis)) {
                LOGGER.debug(
                    new StringBuilder("add field : '")
                        .append(id)
                        .append("'")
                        .toString());
                sequence
                    .add(
                        ComponentFactory.getInstance()
                            .createXField(id));
            }
            //----------------------------------------------------------------------------------------------------------
            // Method
            //----------------------------------------------------------------------------------------------------------
            else {
                LOGGER.debug(
                    new StringBuilder("add method : '")
                        .append(id)
                        .append("'")
                        .toString());
                sequence
                    .add(
                        ComponentFactory.getInstance()
                            .createXMethod(id));
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
