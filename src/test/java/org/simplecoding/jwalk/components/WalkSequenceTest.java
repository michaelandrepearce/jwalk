package org.simplecoding.jwalk.components;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.simplecoding.jwalk.structures.ComplexStructure;
import org.simplecoding.jwalk.structures.SimpleStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

/**
 *
 * @author fred
 */
public class WalkSequenceTest {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(WalkSequenceTest.class);

    private Integer number;
    private String  message;

    private WalkField simpleField;
    private WalkField numberField;
    private WalkField messageField;

    private WalkMethod simpleMethod;
    private WalkMethod numberMethod;
    private WalkMethod messageMethod;

    private ComplexStructure bean;

    public WalkSequenceTest() {
        this.number     = 16;
        this.message    = "Ca marche";

        this.simpleField    = new WalkField("simple");
        this.numberField    = new WalkField("number");
        this.messageField   = new WalkField("message");

        this.simpleMethod   = new WalkMethod("getSimple");
        this.numberMethod   = new WalkMethod("getNumber");
        this.messageMethod  = new WalkMethod("getMessage");

        this.bean =
            new ComplexStructure()
                .setSimple(
                    new SimpleStructure()
                        .setNumber(16)
                        .setMessage("Ca marche"));
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
     * Test of evaluate method, of class WalkField.
     */
    @Test
    public void testEvaluateNumberFields()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateNumberFields");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.number,
            new WalkSequence()
                .add(this.simpleField)
                .add(this.numberField)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class WalkField.
     */
    @Test
    public void testEvaluateMessageFields()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateMessageFields");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.message,
            new WalkSequence()
                .add(this.simpleField)
                .add(this.messageField)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class WalkField.
     */
    @Test
    public void testEvaluateNumberMethods()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateNumberMethods");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.number,
            new WalkSequence()
                .add(this.simpleMethod)
                .add(this.numberMethod)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class WalkField.
     */
    @Test
    public void testEvaluateMessageMethods()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateMessageMethods");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.message,
            new WalkSequence()
                .add(this.simpleMethod)
                .add(this.messageMethod)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class WalkField.
     */
    @Test
    public void testEvaluateNumberMixed()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateNumberMixed");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.number,
            new WalkSequence()
                .add(this.simpleField)
                .add(this.numberMethod)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class WalkField.
     */
    @Test
    public void testEvaluateMessageMixed()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateMessageMixed");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.message,
            new WalkSequence()
                .add(this.simpleMethod)
                .add(this.messageField)
                .evaluate(this.bean));
    }
}
