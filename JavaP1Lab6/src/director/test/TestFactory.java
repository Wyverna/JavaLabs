package director.test;

import director.Director;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertTrue;

@DisplayName("An ArrayList of workers")
public class TestFactory {
    Director director = Director.getInstance();
    @Test
    @BeforeSuite
    @DisplayName("Count of Workers")
    public void testCountWorkers(){
        assertTrue(director.workers.isEmpty());
    }
    @Test
    @AfterSuite
    @DisplayName("Try insert worker")
    public void testAddWorker(){
        director.workers.add(null);
    }
    @Test
    @DisplayName("ChangeWorkers")
    public void tryChangeWorkers(){
        director.addWorker(null);
        assertFalse(director.workers.isEmpty());
    }
    @Test
    @DisplayName("Not worker")
    public void trySizeWorkers(){
        director.addWorker(null);
        assertNotSame(director.workers.size(), 0);
    }
    @Test
    public void trySetWorkers(){
        director.setWorkers(null);
        assertNull(director.getWorkers());
    }
}
