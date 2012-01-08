package entity.user;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Calendar;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import entity.Media;
import javax.persistence.ManyToOne;
import entity.user.MUser;

@RooJavaBean
@RooToString
@RooEntity
public class Holding {

    private String knowm;

    private String since;

    private String rating;

    private Boolean visible;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar lastUsed;

    private String situation;

    @ManyToOne
    private Media media;

    @ManyToOne
    private MUser muser;

    private String inventoryplace;

    private String inventorynumber;
}
