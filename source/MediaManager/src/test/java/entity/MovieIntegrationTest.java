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
public class MovieIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    private MovieDataOnDemand dod;

	@Test
    public void testCountMovies() {
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", dod.getRandomMovie());
        long count = entity.Movie.countMovies();
        org.junit.Assert.assertTrue("Counter for 'Movie' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMovie() {
        entity.Movie obj = dod.getRandomMovie();
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to provide an identifier", id);
        obj = entity.Movie.findMovie(id);
        org.junit.Assert.assertNotNull("Find method for 'Movie' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Movie' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMovies() {
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", dod.getRandomMovie());
        long count = entity.Movie.countMovies();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Movie', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<entity.Movie> result = entity.Movie.findAllMovies();
        org.junit.Assert.assertNotNull("Find all method for 'Movie' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Movie' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMovieEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", dod.getRandomMovie());
        long count = entity.Movie.countMovies();
        if (count > 20) count = 20;
        java.util.List<entity.Movie> result = entity.Movie.findMovieEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Movie' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Movie' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        entity.Movie obj = dod.getRandomMovie();
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to provide an identifier", id);
        obj = entity.Movie.findMovie(id);
        org.junit.Assert.assertNotNull("Find method for 'Movie' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMovie(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Movie' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMerge() {
        entity.Movie obj = dod.getRandomMovie();
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to provide an identifier", id);
        obj = entity.Movie.findMovie(id);
        boolean modified =  dod.modifyMovie(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        entity.Movie merged = (entity.Movie) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Movie' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", dod.getRandomMovie());
        entity.Movie obj = dod.getNewTransientMovie(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Movie' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Movie' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        entity.Movie obj = dod.getRandomMovie();
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Movie' failed to provide an identifier", id);
        obj = entity.Movie.findMovie(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Movie' with identifier '" + id + "'", entity.Movie.findMovie(id));
    }
}
