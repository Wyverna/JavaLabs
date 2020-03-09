package director.test;

import director.Director;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertNotNull;

public class TestDirector {
    Director director = Director.getInstance();
    @Test
    @DisplayName("Not new object")
    public void testSingltonDirector(){
        Director director1 = Director.getInstance();
        assertSame(director1, director);
    }
    @Ignore
    @Test
    @DisplayName("Company is not null")
    public void testDirectorsCompany(){
        assertNotNull(director.company);
    }
    @Test(timeout = 2000)
    @DisplayName("There are workers")
    public void testNewObject(){
        assertNotNull(director.workers);
    }
}