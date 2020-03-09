package Builder;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertTrue;

public class BuiderFactoryTest {
    BuiderFactory testbuild=new BuiderFactory();
    @Test
    public void testCreate(String buildername) {
        testbuild.create("UserBuilder");
    }
}