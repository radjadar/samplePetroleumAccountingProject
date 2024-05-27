package com.company.samplepetroleumaccountingproject.view.partner;

import com.company.samplepetroleumaccountingproject.entity.BankAccount;
import com.company.samplepetroleumaccountingproject.entity.Partner;

import com.company.samplepetroleumaccountingproject.entity.Person;
import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.view.*;

@Route(value = "partners/:id", layout = MainView.class)
@ViewController("Partner.detail")
@ViewDescriptor("partner-detail-view.xml")
@EditedEntityContainer("partnerDc")
public class PartnerDetailView extends StandardDetailView<Partner> {
    @ViewComponent
    private CollectionPropertyContainer<Person> contactPersonsDc;
    @ViewComponent
    private CollectionPropertyContainer<BankAccount> operatingAccountsDc;
    @ViewComponent
    private EntityComboBox<Person> ceoField;
    @ViewComponent
    private EntityComboBox<BankAccount> operatingAccountField;

    @Subscribe(id = "contactPersonsDc", target = Target.DATA_CONTAINER)
    public void onContactPersonsDcCollectionChange(final CollectionContainer.CollectionChangeEvent<Person> event) {
        if(getEditedEntity().getCeo() != null) {
            if(!contactPersonsDc.containsItem(getEditedEntity().getCeo())) {
                getEditedEntity().setCeo(null);
            } ceoField.setValue(getEditedEntity().getCeo());
        }
    }

    @Subscribe(id = "operatingAccountsDc", target = Target.DATA_CONTAINER)
    public void onOperatingAccountsDcCollectionChange(final CollectionContainer.CollectionChangeEvent<BankAccount> event) {
        if(getEditedEntity().getOperatingAccount() != null) {
            if(!operatingAccountsDc.containsItem(getEditedEntity().getOperatingAccount())) {
                getEditedEntity().setOperatingAccount(null);
            } operatingAccountField.setValue(getEditedEntity().getOperatingAccount());
        }
    }

    @Subscribe("ceoField")
    public void onCeoFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<Person>, Person> event) {
        if(event.isFromClient()) {
            if(event.getOldValue() != null) {
                event.getOldValue().setCeo(null);
            }
            if(event.getValue() != null) {
                event.getValue().setCeo(true);
            }
        }
    }

    @Subscribe("operatingAccountField")
    public void onOperatingAccountFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<BankAccount>, BankAccount> event) {
        if(event.isFromClient()) {
            if(event.getOldValue() != null) {
                event.getOldValue().setOperatingAccount(null);
            }
            if(event.getValue() != null) {
                event.getValue().setOperatingAccount(true);
            }
        }
    }
}