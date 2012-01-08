package de.mediapool.web.ui;

import de.mediapool.web.EntityEditor;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

import com.vaadin.spring.roo.addon.annotations.RooVaadinVisuallyComposableEntityForm;

/**
 * Form for editing an entity. The layout of this form can be edited with the
 * Vaadin Visual Editor.
 * 
 * Fields are automatically bound to container data sources and item properties
 * based on their names (propertyId + "Field") in the aspect. Implementing
 * methods with the same name as used in the aspect allows "overriding"
 * functionality as such methods replace those from the aspect.
 */
@RooVaadinVisuallyComposableEntityForm(formBackingObject = entity.Boardgame.class)
public class BoardgameForm extends CustomComponent implements EntityEditor {

    @AutoGenerated
    private AbsoluteLayout mainLayout;
    @AutoGenerated
    private Panel scrollPanel;
    @AutoGenerated
    private VerticalLayout scrollContent;
    @AutoGenerated
    private HorizontalLayout buttonLayout;
    @AutoGenerated
    private Button deleteButton;
    @AutoGenerated
    private Button cancelButton;
    @AutoGenerated
    private Button saveButton;
	@AutoGenerated
	private Label errorMessageLabel;
    @AutoGenerated
    private VerticalLayout fieldLayout;
    @AutoGenerated
    private TextField titleField;
    @AutoGenerated
    private TextField originaltitleField;
    @AutoGenerated
    private TextField launchyearField;
    @AutoGenerated
    private TextField coverField;
    @AutoGenerated
    private TextField priceField;
    @AutoGenerated
    private TextField eanField;
    @AutoGenerated
    private TextField descriptionField;
    @AutoGenerated
    private TextField specialField;
    @AutoGenerated
    private TextField contenttypeField;
    @AutoGenerated
    private TextField genreField;
    @AutoGenerated
    private TextField carrierField;
    @AutoGenerated
    private TwinColSelect mlanguageField;
    @AutoGenerated
    private TwinColSelect awardField;
    @AutoGenerated
    private TwinColSelect participationField;
    @AutoGenerated
    private TextField durationField;
    @AutoGenerated
    private TextField playerfromField;
    @AutoGenerated
    private TextField playertoField;
    @AutoGenerated
    private TextField approvedageField;
    @AutoGenerated
    private TextField extensionField;


    // data item being edited
    private Item item;

    public BoardgameForm() {
        buildMainLayout();
        setCompositionRoot(mainLayout);

        configure();

        // make saving the form the default action on Enter keypress
        saveButton.setClickShortcut(KeyCode.ENTER);

        // TODO add user code here
    }

    public void addSaveActionListener(ClickListener listener) {
        saveButton.addListener(listener);
    }

    public void addCancelActionListener(ClickListener listener) {
        cancelButton.addListener(listener);
    }

    public void addDeleteActionListener(ClickListener listener) {
        deleteButton.addListener(listener);
    }

    public void setSaveAllowed(boolean canSave) {
        saveButton.setVisible(canSave);
        cancelButton.setVisible(canSave);
        saveButton.setEnabled(canSave);
        cancelButton.setEnabled(canSave);

        // do not change the enabled state of the delete button
        fieldLayout.setEnabled(canSave);
    }

    public void setDeleteAllowed(boolean canDelete) {
        deleteButton.setVisible(canDelete);
        deleteButton.setEnabled(canDelete);
    }

    public void setCommitErrorMessage(String message) {
        errorMessageLabel.setVisible(message != null);
   	    errorMessageLabel.setValue(message);
    }

    public void commit() {
        if (getItemDataSource() != null) {
            validateFields();
            setCommitErrorMessage(null);
            commitFields();
        }
    }

    public void setItemDataSource(Item item) {
        // TODO implement

        this.item = item;

        setFieldValues(item);
        setCommitErrorMessage(null);
    }

    public Item getItemDataSource() {
        return item;
    }

    @Override
    public void focus() {
        Field field = getFirstField();
        if (field != null) {
            field.focus();
        }
    }

    @AutoGenerated
    private AbsoluteLayout buildMainLayout() {
        // common part: create layout
        mainLayout = new AbsoluteLayout();

        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");

        // scrollPanel
        scrollPanel = buildScrollPanel();
        mainLayout.addComponent(scrollPanel);

        return mainLayout;
    }

    @AutoGenerated
    private Panel buildScrollPanel() {
        // common part: create layout
        scrollPanel = new Panel();
        scrollPanel.setWidth("100.0%");
        scrollPanel.setHeight("100.0%");
        scrollPanel.setImmediate(false);

        // scrollContent
        scrollContent = buildScrollContent();
        scrollPanel.setContent(scrollContent);

        return scrollPanel;
    }

    @AutoGenerated
    private VerticalLayout buildScrollContent() {
        // common part: create layout
        scrollContent = new VerticalLayout();
        scrollContent.setWidth("100.0%");
        scrollContent.setHeight("-1px");
        scrollContent.setImmediate(false);
        scrollContent.setMargin(true);
        scrollContent.setSpacing(true);

        // fieldLayout
        fieldLayout = buildFieldLayout();
        scrollContent.addComponent(fieldLayout);

		// errorMessageLabel
		errorMessageLabel = new Label();
		errorMessageLabel.setWidth("-1px");
		errorMessageLabel.setHeight("-1px");
		errorMessageLabel.setStyleName("errormessage");
		errorMessageLabel.setValue("");
		errorMessageLabel.setImmediate(false);
		scrollContent.addComponent(errorMessageLabel);

        // buttonLayout
        buttonLayout = buildButtonLayout();
        scrollContent.addComponent(buttonLayout);

        return scrollContent;
    }

    @AutoGenerated
    private VerticalLayout buildFieldLayout() {
        // common part: create layout
        fieldLayout = new VerticalLayout();
        fieldLayout.setWidth("100.0%");
        fieldLayout.setHeight("-1px");
        fieldLayout.setImmediate(false);
        fieldLayout.setMargin(false);
        fieldLayout.setSpacing(true);

        // titleField
        titleField = new TextField();
        titleField.setWidth("-1px");
        titleField.setHeight("-1px");
        titleField.setCaption("Title");
        titleField.setImmediate(true);
        fieldLayout.addComponent(titleField);

        // originaltitleField
        originaltitleField = new TextField();
        originaltitleField.setWidth("-1px");
        originaltitleField.setHeight("-1px");
        originaltitleField.setCaption("Originaltitle");
        originaltitleField.setImmediate(true);
        fieldLayout.addComponent(originaltitleField);

        // launchyearField
        launchyearField = new TextField();
        launchyearField.setWidth("-1px");
        launchyearField.setHeight("-1px");
        launchyearField.setCaption("Launchyear");
        launchyearField.setImmediate(true);
        fieldLayout.addComponent(launchyearField);

        // coverField
        coverField = new TextField();
        coverField.setWidth("-1px");
        coverField.setHeight("-1px");
        coverField.setCaption("Cover");
        coverField.setImmediate(true);
        fieldLayout.addComponent(coverField);

        // priceField
        priceField = new TextField();
        priceField.setWidth("-1px");
        priceField.setHeight("-1px");
        priceField.setCaption("Price");
        priceField.setImmediate(true);
        fieldLayout.addComponent(priceField);

        // eanField
        eanField = new TextField();
        eanField.setWidth("-1px");
        eanField.setHeight("-1px");
        eanField.setCaption("Ean");
        eanField.setImmediate(true);
        fieldLayout.addComponent(eanField);

        // descriptionField
        descriptionField = new TextField();
        descriptionField.setWidth("-1px");
        descriptionField.setHeight("-1px");
        descriptionField.setCaption("Description");
        descriptionField.setImmediate(true);
        fieldLayout.addComponent(descriptionField);

        // specialField
        specialField = new TextField();
        specialField.setWidth("-1px");
        specialField.setHeight("-1px");
        specialField.setCaption("Special");
        specialField.setImmediate(true);
        fieldLayout.addComponent(specialField);

        // contenttypeField
        contenttypeField = new TextField();
        contenttypeField.setWidth("-1px");
        contenttypeField.setHeight("-1px");
        contenttypeField.setCaption("Contenttype");
        contenttypeField.setImmediate(true);
        fieldLayout.addComponent(contenttypeField);

        // genreField
        genreField = new TextField();
        genreField.setWidth("-1px");
        genreField.setHeight("-1px");
        genreField.setCaption("Genre");
        genreField.setImmediate(true);
        fieldLayout.addComponent(genreField);

        // carrierField
        carrierField = new TextField();
        carrierField.setWidth("-1px");
        carrierField.setHeight("-1px");
        carrierField.setCaption("Carrier");
        carrierField.setImmediate(true);
        fieldLayout.addComponent(carrierField);

        // mlanguageField
        mlanguageField = new TwinColSelect();
        mlanguageField.setWidth("-1px");
        mlanguageField.setHeight("-1px");
        mlanguageField.setCaption("Mlanguage");
        mlanguageField.setImmediate(true);
        fieldLayout.addComponent(mlanguageField);

        // awardField
        awardField = new TwinColSelect();
        awardField.setWidth("-1px");
        awardField.setHeight("-1px");
        awardField.setCaption("Award");
        awardField.setImmediate(true);
        fieldLayout.addComponent(awardField);

        // participationField
        participationField = new TwinColSelect();
        participationField.setWidth("-1px");
        participationField.setHeight("-1px");
        participationField.setCaption("Participation");
        participationField.setImmediate(true);
        fieldLayout.addComponent(participationField);

        // durationField
        durationField = new TextField();
        durationField.setWidth("-1px");
        durationField.setHeight("-1px");
        durationField.setCaption("Duration");
        durationField.setImmediate(true);
        fieldLayout.addComponent(durationField);

        // playerfromField
        playerfromField = new TextField();
        playerfromField.setWidth("-1px");
        playerfromField.setHeight("-1px");
        playerfromField.setCaption("Playerfrom");
        playerfromField.setImmediate(true);
        fieldLayout.addComponent(playerfromField);

        // playertoField
        playertoField = new TextField();
        playertoField.setWidth("-1px");
        playertoField.setHeight("-1px");
        playertoField.setCaption("Playerto");
        playertoField.setImmediate(true);
        fieldLayout.addComponent(playertoField);

        // approvedageField
        approvedageField = new TextField();
        approvedageField.setWidth("-1px");
        approvedageField.setHeight("-1px");
        approvedageField.setCaption("Approvedage");
        approvedageField.setImmediate(true);
        fieldLayout.addComponent(approvedageField);

        // extensionField
        extensionField = new TextField();
        extensionField.setWidth("-1px");
        extensionField.setHeight("-1px");
        extensionField.setCaption("Extension");
        extensionField.setImmediate(true);
        fieldLayout.addComponent(extensionField);


        return fieldLayout;
    }

    @AutoGenerated
    private HorizontalLayout buildButtonLayout() {
        // common part: create layout
        buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth("-1px");
        buttonLayout.setHeight("-1px");
        buttonLayout.setImmediate(false);
        buttonLayout.setMargin(false);
        buttonLayout.setSpacing(true);

        // saveButton
        saveButton = new Button();
        saveButton.setWidth("-1px");
        saveButton.setHeight("-1px");
        saveButton.setCaption("Save");
        saveButton.setStyleName("primary");
        saveButton.setImmediate(true);
        buttonLayout.addComponent(saveButton);

        // cancelButton
        cancelButton = new Button();
        cancelButton.setWidth("-1px");
        cancelButton.setHeight("-1px");
        cancelButton.setCaption("Cancel");
        cancelButton.setImmediate(true);
        buttonLayout.addComponent(cancelButton);

        // deleteButton
        deleteButton = new Button();
        deleteButton.setWidth("-1px");
        deleteButton.setHeight("-1px");
        deleteButton.setCaption("Delete");
        deleteButton.setStyleName("link");
        deleteButton.setImmediate(true);
        buttonLayout.addComponent(deleteButton);
        buttonLayout.setComponentAlignment(deleteButton, new Alignment(48));

        return buttonLayout;
    }

}
