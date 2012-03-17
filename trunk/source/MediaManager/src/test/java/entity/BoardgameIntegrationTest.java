package entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")
@Transactional
public class BoardgameIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    private BoardgameDataOnDemand dod;

	@Test
    public void testCountBoardgames() {
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to initialize correctly", dod.getRandomBoardgame());
        long count = entity.Boardgame.countBoardgames();
        org.junit.Assert.assertTrue("Counter for 'Boardgame' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindBoardgame() {
        entity.Boardgame obj = dod.getRandomBoardgame();
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to provide an identifier", id);
        obj = entity.Boardgame.findBoardgame(id);
        org.junit.Assert.assertNotNull("Find method for 'Boardgame' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Boardgame' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllBoardgames() {
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to initialize correctly", dod.getRandomBoardgame());
        long count = entity.Boardgame.countBoardgames();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Boardgame', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<entity.Boardgame> result = entity.Boardgame.findAllBoardgames();
        org.junit.Assert.assertNotNull("Find all method for 'Boardgame' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Boardgame' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindBoardgameEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to initialize correctly", dod.getRandomBoardgame());
        long count = entity.Boardgame.countBoardgames();
        if (count > 20) count = 20;
        java.util.List<entity.Boardgame> result = entity.Boardgame.findBoardgameEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Boardgame' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Boardgame' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        entity.Boardgame obj = dod.getRandomBoardgame();
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to provide an identifier", id);
        obj = entity.Boardgame.findBoardgame(id);
        org.junit.Assert.assertNotNull("Find method for 'Boardgame' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyBoardgame(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Boardgame' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMerge() {
        entity.Boardgame obj = dod.getRandomBoardgame();
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to provide an identifier", id);
        obj = entity.Boardgame.findBoardgame(id);
        boolean modified =  dod.modifyBoardgame(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        entity.Boardgame merged = (entity.Boardgame) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Boardgame' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to initialize correctly", dod.getRandomBoardgame());
        entity.Boardgame obj = dod.getNewTransientBoardgame(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Boardgame' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Boardgame' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        entity.Boardgame obj = dod.getRandomBoardgame();
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Boardgame' failed to provide an identifier", id);
        obj = entity.Boardgame.findBoardgame(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Boardgame' with identifier '" + id + "'", entity.Boardgame.findBoardgame(id));
    }
}