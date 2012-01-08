// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.mediapool.web.ui;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.select.Award;
import java.lang.Class;
import java.lang.Long;
import java.lang.String;
import java.util.List;

privileged aspect AwardView_Roo_VaadinEntityView {
    
    public String AwardView.getEntityName() {
        return "Award";
    }
    
    public Class<Award> AwardView.getEntityClass() {
        return Award.class;
    }
    
    public boolean AwardView.isCreateAllowed() {
        return true;
    }
    
    public boolean AwardView.isUpdateAllowed() {
        return true;
    }
    
    public boolean AwardView.isDeleteAllowed() {
        return true;
    }
    
    public List<Award> AwardView.listEntities() {
        List<Award> list = Award.findAllAwards();
        return list;
    }
    
    public Award AwardView.saveEntity(Award entity) {
        if (entity == null) {
            return null;
        }
        return (Award) entity.merge();
    }
    
    public void AwardView.deleteEntity(Award entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean AwardView.isNewEntity(Award entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String AwardView.getIdProperty() {
        return "id";
    }
    
    public String AwardView.getVersionProperty() {
        return "version";
    }
    
    public Award AwardView.createEntityInstance() {
        return new Award();
    }
    
    public BeanContainer<Long, Award> AwardView.getTableContainer() {
        BeanContainer<Long, Award> container = new BeanContainer<Long, Award>(Award.class);
        container.setBeanIdProperty("id");
        for (Award entity : Award.findAllAwards()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item AwardView.getItemForEntity(Award entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<Award>(entity);
        }
        return item;
    }
    
    public Award AwardView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<Award>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object AwardView.getIdForEntity(Award entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void AwardView.setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
    
}
