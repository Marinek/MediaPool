package entity;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Boardgame extends Media {

    private int duration;

    private int playerfrom;

    private int playerto;

    private int approvedage;

    private int extension;
}
