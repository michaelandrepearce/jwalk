package org.simplecoding.jwalk;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.simplecoding.jwalk.components.WalkSequence;
import org.simplecoding.jwalk.exceptions.JWalkException;
import org.simplecoding.jwalk.structures.ComplexStructure;
import org.simplecoding.jwalk.structures.SimpleStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fred
 */
public class WalkerTest {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(WalkerTest.class);

    private Integer number;
    private String  message;

    private ComplexStructure bean;

    public WalkerTest() {
        this.number     = 16;
        this.message    = "Ca marche";

        this.bean =
            new ComplexStructure()
                .setSimple(
                    new SimpleStructure()
                        .setNumber(this.number)
                        .setMessage(this.message));
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class Walker.
     */
    @Test
    public void testEvaluateFields()
        throws
               Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateFields");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.message,
            Walker.getInstance().evaluate(
                this.bean,
                "simple.message",
                String.class));
    }

    /**
     * Test of evaluate method, of class Walker.
     */
    @Test
    public void testEvaluateMethods()
        throws
               Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateMethods");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.message,
            Walker.getInstance().evaluate(
                this.bean,
                "getSimple().getMessage()",
                String.class));
    }

    /**
     * Test of evaluate method, of class Walker.
     */
    @Test
    public void testEvaluate()
        throws
               Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluate");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.number,
            Walker.getInstance().evaluate(
                this.bean,
                "getSimple().number",
                Integer.class));
    }

    /**
     * Test of evaluate method, of class Walker.
     */
    @Test
    public void testEvaluateWithEmptyExpression()
        throws
               Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateWithEmptyExpression");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.bean,
            Walker.getInstance().evaluate(
                this.bean,
                "",
                ComplexStructure.class));
    }

    /**
     * Test of evaluate method, of class Walker.
     */
    @Test
    public void testEvaluateWithArguments()
        throws
               Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateWithArguments");
        LOGGER.debug("--------------------------------------------------------------------------------");

        Map<String, Class<?>> definitions = new HashMap<String, Class<?>>(4);
        definitions.put("index", Integer.TYPE);

        Map<String, Object> values = new HashMap<String, Object>(4);
        values.put("index", 4);

        WalkSequence sequence =
            WalkFactory.getInstance()
                .createSequence(
                    "charAt(index)",
                    definitions);

        assertEquals(
            'a',
            Walker.getInstance()
                .evaluate(
                    this.message,
                    sequence,
                    values));
    }

    /**
     * Test of parse method, of class SequenceBuilder.
     * @throws JWalkException
     */
    @Test
    public void testEvaluateWithDefinitions() throws JWalkException {
        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateWithDefinitions");
        LOGGER.debug("--------------------------------------------------------------------------------");

        BigInteger  value       = BigInteger.valueOf(16);
        String      expression  = "toString(radix).charAt(index)";

        Map<String, Class<?>> definitions = new HashMap<String, Class<?>>(4);
        definitions.put("radix", Integer.TYPE);
        definitions.put("index", Integer.TYPE);

        Map<String, Object> values = new HashMap<String, Object>(4);
        values.put("radix", 16);
        values.put("index", 0);

        Character result =
            (Character)
                WalkFactory.getInstance()
                    .createSequence(expression, definitions)
                        .evaluate(value, values);

        assertEquals(
            '1',
            (char) result);
    }

    /**
     * Test of parse method, of class SequenceBuilder.
     * @throws JWalkException
     */
    @Test
    public void testEvaluateWithoutDefinitions() throws JWalkException {
        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateWithoutDefinitions");
        LOGGER.debug("--------------------------------------------------------------------------------");

        String value        = "this is a string object";
        String expression   = "toUpperCase().replaceAll(from, to).replaceAll(select, group).concat(rest)";

        Map<String, Object> values = new HashMap<String, Object>(4);
        values.put("from",      "IN");
        values.put("to",        "ON");
        values.put("select",    ".*(STRONG).*");
        values.put("group",     "$1");
        values.put("rest",      " ARM OF THE LAW");

        String result =
            (String)
                WalkFactory.getInstance()
                    .createSequence(expression)
                        .evaluate(value, values);

        assertEquals(
            "STRONG ARM OF THE LAW",
            result);
    }

}