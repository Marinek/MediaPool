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
public class BookDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Book> data;

	public Book getNewTransientBook(int index) {
        Book obj = new Book();
        setCarrier(obj, index);
        setContenttype(obj, index);
        setCover(obj, index);
        setDescription(obj, index);
        setEan(obj, index);
        setGenre(obj, index);
        setIsbn(obj, index);
        setLaunchyear(obj, index);
        setOriginaltitle(obj, index);
        setPages(obj, index);
        setPrice(obj, index);
        setSpecial(obj, index);
        setTitle(obj, index);
        return obj;
    }

	public void setCarrier(Book obj, int index) {
        String carrier = "carrier_" + index;
        obj.setCarrier(carrier);
    }

	public void setContenttype(Book obj, int index) {
        String contenttype = "contenttype_" + index;
        obj.setContenttype(contenttype);
    }

	public void setCover(Book obj, int index) {
        String cover = "cover_" + index;
        obj.setCover(cover);
    }

	public void setDescription(Book obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }

	public void setEan(Book obj, int index) {
        String ean = "ean_" + index;
        obj.setEan(ean);
    }

	public void setGenre(Book obj, int index) {
        String genre = "genre_" + index;
        obj.setGenre(genre);
    }

	public void setIsbn(Book obj, int index) {
        String isbn = "isbn_" + index;
        obj.setIsbn(isbn);
    }

	public void setLaunchyear(Book obj, int index) {
        String launchyear = "launchyear_" + index;
        obj.setLaunchyear(launchyear);
    }

	public void setOriginaltitle(Book obj, int index) {
        String originaltitle = "originaltitle_" + index;
        obj.setOriginaltitle(originaltitle);
    }

	public void setPages(Book obj, int index) {
        int pages = index;
        obj.setPages(pages);
    }

	public void setPrice(Book obj, int index) {
        double price = new Integer(index).doubleValue();
        obj.setPrice(price);
    }

	public void setSpecial(Book obj, int index) {
        String special = "special_" + index;
        obj.setSpecial(special);
    }

	public void setTitle(Book obj, int index) {
        String title = "title_" + index;
        obj.setTitle(title);
    }

	public Book getSpecificBook(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Book obj = data.get(index);
        return Book.findBook(obj.getId());
    }

	public Book getRandomBook() {
        init();
        Book obj = data.get(rnd.nextInt(data.size()));
        return Book.findBook(obj.getId());
    }

	public boolean modifyBook(Book obj) {
        return false;
    }

	public void init() {
        data = Book.findBookEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Book' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<entity.Book>();
        for (int i = 0; i < 10; i++) {
            Book obj = getNewTransientBook(i);
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