package entity.meta;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Set;
import entity.meta.PMember;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

@RooJavaBean
@RooToString
@RooEntity
public class Participation {

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PMember> pmember = new HashSet<PMember>();

    private String mrole;
}
