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
public class BoardgameDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Boardgame> data;

	public Boardgame getNewTransientBoardgame(int index) {
        Boardgame obj = new Boardgame();
        setApprovedage(obj, index);
        setCarrier(obj, index);
        setContenttype(obj, index);
        setCover(obj, index);
        setDescription(obj, index);
        setDuration(obj, index);
        setEan(obj, index);
        setExtension(obj, index);
        setGenre(obj, index);
        setLaunchyear(obj, index);
        setOriginaltitle(obj, index);
        setPlayerfrom(obj, index);
        setPlayerto(obj, index);
        setPrice(obj, index);
        setSpecial(obj, index);
        setTitle(obj, index);
        return obj;
    }

	public void setApprovedage(Boardgame obj, int index) {
        int approvedage = index;
        obj.setApprovedage(approvedage);
    }

	public void setCarrier(Boardgame obj, int index) {
        String carrier = "carrier_" + index;
        obj.setCarrier(carrier);
    }

	public void setContenttype(Boardgame obj, int index) {
        String contenttype = "contenttype_" + index;
        obj.setContenttype(contenttype);
    }

	public void setCover(Boardgame obj, int index) {
        String cover = "cover_" + index;
        obj.setCover(cover);
    }

	public void setDescription(Boardgame obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }

	public void setDuration(Boardgame obj, int index) {
        int duration = index;
        obj.setDuration(duration);
    }

	public void setEan(Boardgame obj, int index) {
        String ean = "ean_" + index;
        obj.setEan(ean);
    }

	public void setExtension(Boardgame obj, int index) {
        int extension = index;
        obj.setExtension(extension);
    }

	public void setGenre(Boardgame obj, int index) {
        String genre = "genre_" + index;
        obj.setGenre(genre);
    }

	public void setLaunchyear(Boardgame obj, int index) {
        String launchyear = "launchyear_" + index;
        obj.setLaunchyear(launchyear);
    }

	public void setOriginaltitle(Boardgame obj, int index) {
        String originaltitle = "originaltitle_" + index;
        obj.setOriginaltitle(originaltitle);
    }

	public void setPlayerfrom(Boardgame obj, int index) {
        int playerfrom = index;
        obj.setPlayerfrom(playerfrom);
    }

	public void setPlayerto(Boardgame obj, int index) {
        int playerto = index;
        obj.setPlayerto(playerto);
    }

	public void setPrice(Boardgame obj, int index) {
        double price = new Integer(index).doubleValue();
        obj.setPrice(price);
    }

	public void setSpecial(Boardgame obj, int index) {
        String special = "special_" + index;
        obj.setSpecial(special);
    }

	public void setTitle(Boardgame obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public Boardgame getSpecificBoardgame(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Boardgame obj = data.get(index);
        return Boardgame.findBoardgame(obj.getId());
    }

	public Boardgame getRandomBoardgame() {
        init();
        Boardgame obj = data.get(rnd.nextInt(data.size()));
        return Boardgame.findBoardgame(obj.getId());
    }

	public boolean modifyBoardgame(Boardgame obj) {
        return false;
    }

	public void init() {
        data = Boardgame.findBoardgameEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Boardgame' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<entity.Boardgame>();
        for (int i = 0; i < 10; i++) {
            Boardgame obj = getNewTransientBoardgame(i);
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
