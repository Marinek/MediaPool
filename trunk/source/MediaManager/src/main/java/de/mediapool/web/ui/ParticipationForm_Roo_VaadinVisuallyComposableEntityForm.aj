// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.mediapool.web.ui;

import com.vaadin.addon.beanvalidation.BeanValidationValidator;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import entity.meta.PMember;
import entity.meta.Participation;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.vaadin.addon.customfield.ConvertingValidator;
import org.vaadin.addon.customfield.PropertyConverter;
import org.vaadin.addon.customfield.beanfield.BeanSetFieldPropertyConverter;

privileged aspect ParticipationForm_Roo_VaadinVisuallyComposableEntityForm {
    
    private Map<Object, Field> ParticipationForm.fieldMap = new LinkedHashMap<Object, Field>();
    
    private Map<Object, PropertyConverter> ParticipationForm.converterMap = new LinkedHashMap<Object, PropertyConverter>();
    
    public Collection<Object> ParticipationForm.getBeanPropertyIds() {
        return Arrays.asList(new Object[] { "pmember", "mrole" });
    }
    
    public Field ParticipationForm.getField(Object propertyId) {
        return fieldMap.get(propertyId);
    }
    
    public void ParticipationForm.configure() {
        configureFieldMap();
        configureFields();
        configureContainersForFields();
        configureConverters();
        configureValidators();
    }
    
    public void ParticipationForm.refresh() {
        configureContainersForFields();
        configureConverters();
        configureValidators();
    }
    
    public boolean ParticipationForm.isModified() {
        if (getItemDataSource() != null) {
            for (Object propertyId : getItemDataSource().getItemPropertyIds()) {
                Field field = getField(propertyId);
                if (field != null && field.isModified()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void ParticipationForm.configureFieldMap() {
        fieldMap.put("pmember", pmemberField);
        fieldMap.put("mrole", mroleField);
    }
    
    public void ParticipationForm.configureFields() {
        for (Object propertyId : getBeanPropertyIds()) {
            Field field = getField(propertyId);
            if (field == null) {
                continue;
            }
            if (field instanceof TextField) {
                ((TextField) field).setNullRepresentation("");
            }
            field.setWriteThrough(false);
            field.setInvalidAllowed(true);
        }
    }
    
    public void ParticipationForm.configureContainersForFields() {
        Field field;
        
        field = getField("pmember");
        if (field instanceof AbstractSelect) {
            ((AbstractSelect) field).setContainerDataSource(getContainerForPMembers());
            Object captionId = getPMemberCaptionPropertyId();
            if (captionId != null) {
                ((AbstractSelect) field).setItemCaptionPropertyId(captionId);
            } else {
                ((AbstractSelect) field).setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_ITEM);
            }
        }
    }
    
    public void ParticipationForm.configureConverters() {
        // cannot parametrize PropertyConverter here due to an AJDT bug
        PropertyConverter converter;
        Container container;
        Field field;
        
        field = getField("pmember");
        if (field instanceof AbstractSelect) {
            container = ((AbstractSelect) field).getContainerDataSource();
            converter = new BeanSetFieldPropertyConverter<PMember, Long>(PMember.class, container, "id");
            converterMap.put("pmember", converter);
        }
    }
    
    public void ParticipationForm.configureValidators() {
        for (Object propertyId : getBeanPropertyIds()) {
            Field field = getField(propertyId);
            if (field != null) {
                Collection<Validator> validators = field.getValidators();
                if (validators != null) {
                    for (Validator validator : new ArrayList<Validator>(field.getValidators())) {
                        if (validator instanceof BeanValidationValidator || validator instanceof ConvertingValidator) {
                            field.removeValidator(validator);
                        }
                    }
                }
                BeanValidationValidator validator = new BeanValidationValidator(getEntityClass(), String.valueOf(propertyId));
                if (validator.isRequired()) {
                    field.setRequired(true);
                    field.setRequiredError(validator.getRequiredMessage());
                }
                PropertyConverter converter = getConverter(propertyId);
                if (converter == null) {
                    field.addValidator(validator);
                } else {
                    field.addValidator(new ConvertingValidator(validator, converter));
                }
            }
        }
    }
    
    public PropertyConverter ParticipationForm.getConverter(Object propertyId) {
        return converterMap.get(propertyId);
    }
    
    public BeanContainer<Long, PMember> ParticipationForm.getContainerForPMembers() {
        BeanContainer<Long, PMember> container = new BeanContainer<Long, PMember>(PMember.class);
        container.setBeanIdProperty("id");
        for (PMember entity : PMember.findAllPMembers()) {
            container.addBean(entity);
        }
        return container;
    }
    
    public Object ParticipationForm.getPMemberCaptionPropertyId() {
        return "name";
    }
    
    public String ParticipationForm.getIdProperty() {
        return "id";
    }
    
    public String ParticipationForm.getVersionProperty() {
        return "version";
    }
    
    public void ParticipationForm.validateFields() {
        if (getItemDataSource() != null) {
            for (Object propertyId : getItemDataSource().getItemPropertyIds()) {
                Field field = getField(propertyId);
                if (field != null && !field.isReadOnly()) {
                    field.validate();
                }
            }
        }
    }
    
    public void ParticipationForm.commitFields() {
        if (getItemDataSource() != null) {
            for (Object propertyId : getItemDataSource().getItemPropertyIds()) {
                Field field = getField(propertyId);
                if (field != null && !field.isReadOnly()) {
                    field.commit();
                }
            }
        }
    }
    
    public void ParticipationForm.setFieldPropertyDataSource(Object propertyId, Property property) {
        Field field = getField(propertyId);
        if (field == null) {
            return;
        }
        if (property == null) {
            field.setPropertyDataSource(null);
        } else {
            PropertyConverter converter = getConverter(propertyId);
            if (converter != null) {
                converter.setPropertyDataSource(property);
                field.setPropertyDataSource(converter);
            } else {
                if (field instanceof CheckBox && property.getValue() == null) {
                    property.setValue(Boolean.FALSE);
                }
                field.setPropertyDataSource(property);
            }
        }
    }
    
    public void ParticipationForm.setFieldValues(Item item) {
        if (item != null) {
            // set values for fields in item
            for (Object propertyId : item.getItemPropertyIds()) {
                setFieldPropertyDataSource(propertyId, item.getItemProperty(propertyId));
            }
            // other fields are not touched by default
        } else {
            // reset all fields
            for (Object propertyId : getBeanPropertyIds()) {
                setFieldPropertyDataSource(propertyId, null);
            }
        }
    }
    
    public Field ParticipationForm.getFirstField() {
        Iterator<Object> it = getBeanPropertyIds().iterator();
        if (it.hasNext()) {
            return getField(it.next());
        }
        return null;
    }
    
    public Class<Participation> ParticipationForm.getEntityClass() {
        return Participation.class;
    }
    
}
