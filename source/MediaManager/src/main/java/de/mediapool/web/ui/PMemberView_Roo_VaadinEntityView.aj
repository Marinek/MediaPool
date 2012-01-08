// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.mediapool.web.ui;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;
import de.mediapool.web.EntityTableColumnGenerator;
import entity.meta.PMember;
import java.lang.Class;
import java.lang.Long;
import java.lang.String;
import java.util.List;

privileged aspect PMemberView_Roo_VaadinEntityView {
    
    public String PMemberView.getEntityName() {
        return "P Member";
    }
    
    public Class<PMember> PMemberView.getEntityClass() {
        return PMember.class;
    }
    
    public boolean PMemberView.isCreateAllowed() {
        return true;
    }
    
    public boolean PMemberView.isUpdateAllowed() {
        return true;
    }
    
    public boolean PMemberView.isDeleteAllowed() {
        return true;
    }
    
    public List<PMember> PMemberView.listEntities() {
        List<PMember> list = PMember.findAllPMembers();
        return list;
    }
    
    public PMember PMemberView.saveEntity(PMember entity) {
        if (entity == null) {
            return null;
        }
        return (PMember) entity.merge();
    }
    
    public void PMemberView.deleteEntity(PMember entity) {
        if (entity != null) {
            entity.remove();
        }
    }
    
    public boolean PMemberView.isNewEntity(PMember entity) {
        return (entity != null && getIdForEntity(entity) == null);
    }
    
    public String PMemberView.getIdProperty() {
        return "id";
    }
    
    public String PMemberView.getVersionProperty() {
        return "version";
    }
    
    public PMember PMemberView.createEntityInstance() {
        return new PMember();
    }
    
    public BeanContainer<Long, PMember> PMemberView.getTableContainer() {
        BeanContainer<Long, PMember> container = new BeanContainer<Long, PMember>(PMember.class);
        container.setBeanIdProperty("id");
        for (PMember entity : PMember.findAllPMembers()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Item PMemberView.getItemForEntity(PMember entity) {
        Item item = getTable().getItem(entity.getId());
        if (item == null) {
            item = new BeanItem<PMember>(entity);
        }
        return item;
    }
    
    public PMember PMemberView.getEntityForItem(Item item) {
        if (item != null) {
            return ((BeanItem<PMember>) item).getBean();
        } else {
            return null;
        }
    }
    
    public Object PMemberView.getIdForEntity(PMember entity) {
        return entity != null ? entity.getId() : null;
    }
    
    public void PMemberView.setupGeneratedColumns(Table table) {
        // Add generated columns here
    }
    
}
