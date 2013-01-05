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
public class BookIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    private BookDataOnDemand dod;

	@Test
    public void testCountBooks() {
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", dod.getRandomBook());
        long count = entity.Book.countBooks();
        org.junit.Assert.assertTrue("Counter for 'Book' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindBook() {
        entity.Book obj = dod.getRandomBook();
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to provide an identifier", id);
        obj = entity.Book.findBook(id);
        org.junit.Assert.assertNotNull("Find method for 'Book' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Book' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllBooks() {
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", dod.getRandomBook());
        long count = entity.Book.countBooks();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Book', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<entity.Book> result = entity.Book.findAllBooks();
        org.junit.Assert.assertNotNull("Find all method for 'Book' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Book' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindBookEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", dod.getRandomBook());
        long count = entity.Book.countBooks();
        if (count > 20) count = 20;
        java.util.List<entity.Book> result = entity.Book.findBookEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Book' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Book' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        entity.Book obj = dod.getRandomBook();
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to provide an identifier", id);
        obj = entity.Book.findBook(id);
        org.junit.Assert.assertNotNull("Find method for 'Book' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyBook(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Book' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMerge() {
        entity.Book obj = dod.getRandomBook();
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to provide an identifier", id);
        obj = entity.Book.findBook(id);
        boolean modified =  dod.modifyBook(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        entity.Book merged = (entity.Book) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Book' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", dod.getRandomBook());
        entity.Book obj = dod.getNewTransientBook(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Book' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Book' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        entity.Book obj = dod.getRandomBook();
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Book' failed to provide an identifier", id);
        obj = entity.Book.findBook(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Book' with identifier '" + id + "'", entity.Book.findBook(id));
    }
}
