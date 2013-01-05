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

@Configurable
@Component
public class MediaDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Media> data;

	public Media getNewTransientMedia(int index) {
        Media obj = new Media();
        setCarrier(obj, index);
        setContenttype(obj, index);
        setCover(obj, index);
        setDescription(obj, index);
        setEan(obj, index);
        setGenre(obj, index);
        setLaunchyear(obj, index);
        setOriginaltitle(obj, index);
        setPrice(obj, index);
        setSpecial(obj, index);
        setTitle(obj, index);
        return obj;
    }

	public void setCarrier(Media obj, int index) {
        String carrier = "carrier_" + index;
        obj.setCarrier(carrier);
    }

	public void setContenttype(Media obj, int index) {
        String contenttype = "contenttype_" + index;
        obj.setContenttype(contenttype);
    }

	public void setCover(Media obj, int index) {
        String cover = "cover_" + index;
        obj.setCover(cover);
    }

	public void setDescription(Media obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }

	public void setEan(Media obj, int index) {
        String ean = "ean_" + index;
        obj.setEan(ean);
    }

	public void setGenre(Media obj, int index) {
        String genre = "genre_" + index;
        obj.setGenre(genre);
    }

	public void setLaunchyear(Media obj, int index) {
        String launchyear = "launchyear_" + index;
        obj.setLaunchyear(launchyear);
    }

	public void setOriginaltitle(Media obj, int index) {
        String originaltitle = "originaltitle_" + index;
        obj.setOriginaltitle(originaltitle);
    }

	public void setPrice(Media obj, int index) {
        double price = new Integer(index).doubleValue();
        obj.setPrice(price);
    }

	public void setSpecial(Media obj, int index) {
        String special = "special_" + index;
        obj.setSpecial(special);
    }

	public void setTitle(Media obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public Media getSpecificMedia(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Media obj = data.get(index);
        return Media.findMedia(obj.getId());
    }

	public Media getRandomMedia() {
        init();
        Media obj = data.get(rnd.nextInt(data.size()));
        return Media.findMedia(obj.getId());
    }

	public boolean modifyMedia(Media obj) {
        return false;
    }

	public void init() {
        data = Media.findMediaEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Media' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<entity.Media>();
        for (int i = 0; i < 10; i++) {
            Media obj = getNewTransientMedia(i);
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
