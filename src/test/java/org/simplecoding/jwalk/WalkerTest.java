package org.simplecoding.jwalk;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.simplecoding.jwalk.components.XSequence;
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
                String.class));
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

        XSequence sequence =
            ExpressionParser.getInstance()
                .parse(
                    "charAt(%)",
                    Integer.TYPE);

        assertEquals(
            'a',
            Walker.getInstance()
                .evaluate(
                    this.message,
                    sequence,
                    4));
    }
}