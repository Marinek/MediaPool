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
public class MovieDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Movie> data;

	public Movie getNewTransientMovie(int index) {
        Movie obj = new Movie();
        setApprovedage(obj, index);
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

	public void setApprovedage(Movie obj, int index) {
        int approvedage = index;
        obj.setApprovedage(approvedage);
    }

	public void setCarrier(Movie obj, int index) {
        String carrier = "carrier_" + index;
        obj.setCarrier(carrier);
    }

	public void setContenttype(Movie obj, int index) {
        String contenttype = "contenttype_" + index;
        obj.setContenttype(contenttype);
    }

	public void setCover(Movie obj, int index) {
        String cover = "cover_" + index;
        obj.setCover(cover);
    }

	public void setDescription(Movie obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }

	public void setDuration(Movie obj, int index) {
        int duration = index;
        obj.setDuration(duration);
    }

	public void setEan(Movie obj, int index) {
        String ean = "ean_" + index;
        obj.setEan(ean);
    }

	public void setGenre(Movie obj, int index) {
        String genre = "genre_" + index;
        obj.setGenre(genre);
    }

	public void setLaunchyear(Movie obj, int index) {
        String launchyear = "launchyear_" + index;
        obj.setLaunchyear(launchyear);
    }

	public void setNumberdiscs(Movie obj, int index) {
        int numberdiscs = index;
        obj.setNumberdiscs(numberdiscs);
    }

	public void setOriginaltitle(Movie obj, int index) {
        String originaltitle = "originaltitle_" + index;
        obj.setOriginaltitle(originaltitle);
    }

	public void setPrice(Movie obj, int index) {
        double price = new Integer(index).doubleValue();
        obj.setPrice(price);
    }

	public void setSpecial(Movie obj, int index) {
        String special = "special_" + index;
        obj.setSpecial(special);
    }

	public void setTitle(Movie obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public Movie getSpecificMovie(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Movie obj = data.get(index);
        return Movie.findMovie(obj.getId());
    }

	public Movie getRandomMovie() {
        init();
        Movie obj = data.get(rnd.nextInt(data.size()));
        return Movie.findMovie(obj.getId());
    }

	public boolean modifyMovie(Movie obj) {
        return false;
    }

	public void init() {
        data = Movie.findMovieEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Movie' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<entity.Movie>();
        for (int i = 0; i < 10; i++) {
            Movie obj = getNewTransientMovie(i);
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
