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
public class MediaIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    private MediaDataOnDemand dod;

	@Test
    public void testCountMedias() {
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to initialize correctly", dod.getRandomMedia());
        long count = entity.Media.countMedias();
        org.junit.Assert.assertTrue("Counter for 'Media' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMedia() {
        entity.Media obj = dod.getRandomMedia();
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to provide an identifier", id);
        obj = entity.Media.findMedia(id);
        org.junit.Assert.assertNotNull("Find method for 'Media' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Media' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMedias() {
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to initialize correctly", dod.getRandomMedia());
        long count = entity.Media.countMedias();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Media', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<entity.Media> result = entity.Media.findAllMedias();
        org.junit.Assert.assertNotNull("Find all method for 'Media' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Media' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMediaEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to initialize correctly", dod.getRandomMedia());
        long count = entity.Media.countMedias();
        if (count > 20) count = 20;
        java.util.List<entity.Media> result = entity.Media.findMediaEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Media' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Media' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        entity.Media obj = dod.getRandomMedia();
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to provide an identifier", id);
        obj = entity.Media.findMedia(id);
        org.junit.Assert.assertNotNull("Find method for 'Media' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMedia(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Media' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMerge() {
        entity.Media obj = dod.getRandomMedia();
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to provide an identifier", id);
        obj = entity.Media.findMedia(id);
        boolean modified =  dod.modifyMedia(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        entity.Media merged =  obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Media' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to initialize correctly", dod.getRandomMedia());
        entity.Media obj = dod.getNewTransientMedia(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Media' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Media' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        entity.Media obj = dod.getRandomMedia();
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Media' failed to provide an identifier", id);
        obj = entity.Media.findMedia(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Media' with identifier '" + id + "'", entity.Media.findMedia(id));
    }
}