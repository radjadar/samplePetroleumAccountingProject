package com.company.samplepetroleumaccountingproject.view.tankoperationdetail;

import com.company.samplepetroleumaccountingproject.entity.TankOperationDetail;

import com.company.samplepetroleumaccountingproject.entity.User;
import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.core.TimeSource;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.datepicker.TypedDatePicker;
import io.jmix.flowui.component.textfield.JmixNumberField;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Route(value = "tankOperationDetails/:id", layout = MainView.class)
@ViewController("TankOperationDetail.detail")
@ViewDescriptor("tank-operation-detail-detail-view.xml")
@EditedEntityContainer("tankOperationDetailDc")
public class TankOperationDetailDetailView extends StandardDetailView<TankOperationDetail> {
    @Autowired
    private TimeSource timeSource;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private TypedDatePicker<LocalDate> dateField;
    @ViewComponent
    private JmixNumberField temperatureField;
    @ViewComponent
    private JmixNumberField densityField;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<TankOperationDetail> event) {
        //final ZonedDateTime zonedDateTime = timeSource.now();
        event.getEntity().setDate(timeSource.now().toLocalDate()); //.toLocalDateTime()
        //final User user = (User) currentAuthentication.getUser();
        event.getEntity().setUser((User) currentAuthentication.getUser());
        event.getEntity().setApproved(false);
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        if(getEditedEntity().getApproved() != null && getEditedEntity().getApproved()) {
            dateField.setReadOnly(getEditedEntity().getApproved());
            temperatureField.setReadOnly(getEditedEntity().getApproved());
            densityField.setReadOnly(getEditedEntity().getApproved());
        }
    }
}