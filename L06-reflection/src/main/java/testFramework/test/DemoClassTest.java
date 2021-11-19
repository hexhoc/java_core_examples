package testFramework.test;

import reflection.DemoClass;
import reflection.annotations.After;
import reflection.annotations.Before;
import reflection.annotations.Test;
import testFramework.exceptions.AssertionFailedError;

public class DemoClassTest {

    private DemoClass dm;

    @Before
    public void setUp() {
        dm = new DemoClass("TEST");
    }

    @Test
    public void test1() {
        dm.setValue("hello 1");
        if (!dm.getValue().equals("hello")) {
            throw new AssertionFailedError("not equal");
        }
    }

    @Test
    public void test2() {
        dm.setValue("hello 2");
        if (!dm.getValue().equals("hello 2")) {
            throw new AssertionFailedError("not equal");
        }
    }

    @Test
    public void test3() {
        dm.setValue("hello 3");
        if (!dm.getValue().equals("hello 3")) {
            throw new AssertionFailedError("not equal");
        }
    }

    @After
    public void after() {
        dm = null;
    }


}
