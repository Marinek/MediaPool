package entity;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Game extends Media {

    private int extension;

    private int playerto;

    private int approvedage;

    private String modus;

    private String requirement;

    private Boolean euipmentneeded;
}
