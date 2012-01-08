package entity;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Set;
import entity.meta.Track;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity
public class Music extends Media {

    private int duration;

    private int numberdiscs;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Track> tracks = new HashSet<Track>();
}
