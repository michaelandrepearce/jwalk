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
public class XSequenceTest {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(XSequenceTest.class);

    private Integer number;
    private String  message;

    private XField simpleField;
    private XField numberField;
    private XField messageField;

    private XMethod simpleMethod;
    private XMethod numberMethod;
    private XMethod messageMethod;

    private ComplexStructure bean;

    public XSequenceTest() {
        this.number     = 16;
        this.message    = "Ca marche";

        this.simpleField    = new XField("simple");
        this.numberField    = new XField("number");
        this.messageField   = new XField("message");

        this.simpleMethod   = new XMethod("getSimple");
        this.numberMethod   = new XMethod("getNumber");
        this.messageMethod  = new XMethod("getMessage");

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
     * Test of evaluate method, of class XField.
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
            new XSequence()
                .add(this.simpleField)
                .add(this.numberField)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class XField.
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
            new XSequence()
                .add(this.simpleField)
                .add(this.messageField)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class XField.
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
            new XSequence()
                .add(this.simpleMethod)
                .add(this.numberMethod)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class XField.
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
            new XSequence()
                .add(this.simpleMethod)
                .add(this.messageMethod)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class XField.
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
            new XSequence()
                .add(this.simpleField)
                .add(this.numberMethod)
                .evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class XField.
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
            new XSequence()
                .add(this.simpleMethod)
                .add(this.messageField)
                .evaluate(this.bean));
    }
}
