// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package entity.user;

import entity.Media;
import entity.user.MUser;
import java.lang.Boolean;
import java.lang.String;
import java.util.Calendar;

privileged aspect Holding_Roo_JavaBean {
    
    public String Holding.getKnowm() {
        return this.knowm;
    }
    
    public void Holding.setKnowm(String knowm) {
        this.knowm = knowm;
    }
    
    public String Holding.getSince() {
        return this.since;
    }
    
    public void Holding.setSince(String since) {
        this.since = since;
    }
    
    public String Holding.getRating() {
        return this.rating;
    }
    
    public void Holding.setRating(String rating) {
        this.rating = rating;
    }
    
    public Boolean Holding.getVisible() {
        return this.visible;
    }
    
    public void Holding.setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public Calendar Holding.getLastUsed() {
        return this.lastUsed;
    }
    
    public void Holding.setLastUsed(Calendar lastUsed) {
        this.lastUsed = lastUsed;
    }
    
    public String Holding.getSituation() {
        return this.situation;
    }
    
    public void Holding.setSituation(String situation) {
        this.situation = situation;
    }
    
    public Media Holding.getMedia() {
        return this.media;
    }
    
    public void Holding.setMedia(Media media) {
        this.media = media;
    }
    
    public MUser Holding.getMuser() {
        return this.muser;
    }
    
    public void Holding.setMuser(MUser muser) {
        this.muser = muser;
    }
    
    public String Holding.getInventoryplace() {
        return this.inventoryplace;
    }
    
    public void Holding.setInventoryplace(String inventoryplace) {
        this.inventoryplace = inventoryplace;
    }
    
    public String Holding.getInventorynumber() {
        return this.inventorynumber;
    }
    
    public void Holding.setInventorynumber(String inventorynumber) {
        this.inventorynumber = inventorynumber;
    }
    
}
