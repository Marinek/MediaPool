package entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")
@Transactional
@Configurable
public class GameIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    private GameDataOnDemand dod;

	@Test
    public void testCountGames() {
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", dod.getRandomGame());
        long count = entity.Game.countGames();
        org.junit.Assert.assertTrue("Counter for 'Game' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindGame() {
        entity.Game obj = dod.getRandomGame();
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to provide an identifier", id);
        obj = entity.Game.findGame(id);
        org.junit.Assert.assertNotNull("Find method for 'Game' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Game' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllGames() {
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", dod.getRandomGame());
        long count = entity.Game.countGames();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Game', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<entity.Game> result = entity.Game.findAllGames();
        org.junit.Assert.assertNotNull("Find all method for 'Game' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Game' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindGameEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", dod.getRandomGame());
        long count = entity.Game.countGames();
        if (count > 20) count = 20;
        java.util.List<entity.Game> result = entity.Game.findGameEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Game' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Game' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        entity.Game obj = dod.getRandomGame();
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to provide an identifier", id);
        obj = entity.Game.findGame(id);
        org.junit.Assert.assertNotNull("Find method for 'Game' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyGame(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Game' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMerge() {
        entity.Game obj = dod.getRandomGame();
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to provide an identifier", id);
        obj = entity.Game.findGame(id);
        boolean modified =  dod.modifyGame(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        entity.Game merged = (entity.Game) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Game' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", dod.getRandomGame());
        entity.Game obj = dod.getNewTransientGame(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Game' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Game' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        entity.Game obj = dod.getRandomGame();
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Game' failed to provide an identifier", id);
        obj = entity.Game.findGame(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Game' with identifier '" + id + "'", entity.Game.findGame(id));
    }
}
