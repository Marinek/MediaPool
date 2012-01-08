// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package entity;

import entity.MusicDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MusicIntegrationTest_Roo_IntegrationTest {
    
    declare @type: MusicIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: MusicIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: MusicIntegrationTest: @Transactional;
    
    @Autowired
    private MusicDataOnDemand MusicIntegrationTest.dod;
    
    @Test
    public void MusicIntegrationTest.testCountMusics() {
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to initialize correctly", dod.getRandomMusic());
        long count = entity.Music.countMusics();
        org.junit.Assert.assertTrue("Counter for 'Music' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void MusicIntegrationTest.testFindMusic() {
        entity.Music obj = dod.getRandomMusic();
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to provide an identifier", id);
        obj = entity.Music.findMusic(id);
        org.junit.Assert.assertNotNull("Find method for 'Music' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Music' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void MusicIntegrationTest.testFindAllMusics() {
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to initialize correctly", dod.getRandomMusic());
        long count = entity.Music.countMusics();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Music', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<entity.Music> result = entity.Music.findAllMusics();
        org.junit.Assert.assertNotNull("Find all method for 'Music' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Music' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void MusicIntegrationTest.testFindMusicEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to initialize correctly", dod.getRandomMusic());
        long count = entity.Music.countMusics();
        if (count > 20) count = 20;
        java.util.List<entity.Music> result = entity.Music.findMusicEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Music' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Music' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void MusicIntegrationTest.testFlush() {
        entity.Music obj = dod.getRandomMusic();
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to provide an identifier", id);
        obj = entity.Music.findMusic(id);
        org.junit.Assert.assertNotNull("Find method for 'Music' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMusic(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Music' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void MusicIntegrationTest.testMerge() {
        entity.Music obj = dod.getRandomMusic();
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to provide an identifier", id);
        obj = entity.Music.findMusic(id);
        boolean modified =  dod.modifyMusic(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        entity.Music merged = (entity.Music) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Music' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void MusicIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to initialize correctly", dod.getRandomMusic());
        entity.Music obj = dod.getNewTransientMusic(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Music' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Music' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void MusicIntegrationTest.testRemove() {
        entity.Music obj = dod.getRandomMusic();
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Music' failed to provide an identifier", id);
        obj = entity.Music.findMusic(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Music' with identifier '" + id + "'", entity.Music.findMusic(id));
    }
    
}
