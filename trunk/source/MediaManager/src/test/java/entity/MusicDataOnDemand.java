package entity;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class MusicDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Music> data;

	public Music getNewTransientMusic(int index) {
        Music obj = new Music();
        setCarrier(obj, index);
        setContenttype(obj, index);
        setCover(obj, index);
        setDescription(obj, index);
        setDuration(obj, index);
        setEan(obj, index);
        setGenre(obj, index);
        setLaunchyear(obj, index);
        setNumberdiscs(obj, index);
        setOriginaltitle(obj, index);
        setPrice(obj, index);
        setSpecial(obj, index);
        setTitle(obj, index);
        return obj;
    }

	public void setCarrier(Music obj, int index) {
        String carrier = "carrier_" + index;
        obj.setCarrier(carrier);
    }

	public void setContenttype(Music obj, int index) {
        String contenttype = "contenttype_" + index;
        obj.setContenttype(contenttype);
    }

	public void setCover(Music obj, int index) {
        String cover = "cover_" + index;
        obj.setCover(cover);
    }

	public void setDescription(Music obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }

	public void setDuration(Music obj, int index) {
        int duration = index;
        obj.setDuration(duration);
    }

	public void setEan(Music obj, int index) {
        String ean = "ean_" + index;
        obj.setEan(ean);
    }

	public void setGenre(Music obj, int index) {
        String genre = "genre_" + index;
        obj.setGenre(genre);
    }

	public void setLaunchyear(Music obj, int index) {
        String launchyear = "launchyear_" + index;
        obj.setLaunchyear(launchyear);
    }

	public void setNumberdiscs(Music obj, int index) {
        int numberdiscs = index;
        obj.setNumberdiscs(numberdiscs);
    }

	public void setOriginaltitle(Music obj, int index) {
        String originaltitle = "originaltitle_" + index;
        obj.setOriginaltitle(originaltitle);
    }

	public void setPrice(Music obj, int index) {
        double price = new Integer(index).doubleValue();
        obj.setPrice(price);
    }

	public void setSpecial(Music obj, int index) {
        String special = "special_" + index;
        obj.setSpecial(special);
    }

	public void setTitle(Music obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public Music getSpecificMusic(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Music obj = data.get(index);
        return Music.findMusic(obj.getId());
    }

	public Music getRandomMusic() {
        init();
        Music obj = data.get(rnd.nextInt(data.size()));
        return Music.findMusic(obj.getId());
    }

	public boolean modifyMusic(Music obj) {
        return false;
    }

	public void init() {
        data = Music.findMusicEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Music' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<entity.Music>();
        for (int i = 0; i < 10; i++) {
            Music obj = getNewTransientMusic(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
}
