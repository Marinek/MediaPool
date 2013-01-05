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
public class GameDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Game> data;

	public Game getNewTransientGame(int index) {
        Game obj = new Game();
        setApprovedage(obj, index);
        setCarrier(obj, index);
        setContenttype(obj, index);
        setCover(obj, index);
        setDescription(obj, index);
        setEan(obj, index);
        setEuipmentneeded(obj, index);
        setExtension(obj, index);
        setGenre(obj, index);
        setLaunchyear(obj, index);
        setModus(obj, index);
        setOriginaltitle(obj, index);
        setPlayerto(obj, index);
        setPrice(obj, index);
        setRequirement(obj, index);
        setSpecial(obj, index);
        setTitle(obj, index);
        return obj;
    }

	public void setApprovedage(Game obj, int index) {
        int approvedage = index;
        obj.setApprovedage(approvedage);
    }

	public void setCarrier(Game obj, int index) {
        String carrier = "carrier_" + index;
        obj.setCarrier(carrier);
    }

	public void setContenttype(Game obj, int index) {
        String contenttype = "contenttype_" + index;
        obj.setContenttype(contenttype);
    }

	public void setCover(Game obj, int index) {
        String cover = "cover_" + index;
        obj.setCover(cover);
    }

	public void setDescription(Game obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }

	public void setEan(Game obj, int index) {
        String ean = "ean_" + index;
        obj.setEan(ean);
    }

	public void setEuipmentneeded(Game obj, int index) {
        Boolean euipmentneeded = Boolean.TRUE;
        obj.setEuipmentneeded(euipmentneeded);
    }

	public void setExtension(Game obj, int index) {
        int extension = index;
        obj.setExtension(extension);
    }

	public void setGenre(Game obj, int index) {
        String genre = "genre_" + index;
        obj.setGenre(genre);
    }

	public void setLaunchyear(Game obj, int index) {
        String launchyear = "launchyear_" + index;
        obj.setLaunchyear(launchyear);
    }

	public void setModus(Game obj, int index) {
        String modus = "modus_" + index;
        obj.setModus(modus);
    }

	public void setOriginaltitle(Game obj, int index) {
        String originaltitle = "originaltitle_" + index;
        obj.setOriginaltitle(originaltitle);
    }

	public void setPlayerto(Game obj, int index) {
        int playerto = index;
        obj.setPlayerto(playerto);
    }

	public void setPrice(Game obj, int index) {
        double price = new Integer(index).doubleValue();
        obj.setPrice(price);
    }

	public void setRequirement(Game obj, int index) {
        String requirement = "requirement_" + index;
        obj.setRequirement(requirement);
    }

	public void setSpecial(Game obj, int index) {
        String special = "special_" + index;
        obj.setSpecial(special);
    }

	public void setTitle(Game obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public Game getSpecificGame(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Game obj = data.get(index);
        return Game.findGame(obj.getId());
    }

	public Game getRandomGame() {
        init();
        Game obj = data.get(rnd.nextInt(data.size()));
        return Game.findGame(obj.getId());
    }

	public boolean modifyGame(Game obj) {
        return false;
    }

	public void init() {
        data = Game.findGameEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Game' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<entity.Game>();
        for (int i = 0; i < 10; i++) {
            Game obj = getNewTransientGame(i);
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
