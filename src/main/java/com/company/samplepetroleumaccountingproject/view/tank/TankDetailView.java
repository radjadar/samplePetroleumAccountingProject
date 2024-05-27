package com.company.samplepetroleumaccountingproject.view.tank;

import com.company.samplepetroleumaccountingproject.entity.Petroleum;
import com.company.samplepetroleumaccountingproject.entity.Tank;

import com.company.samplepetroleumaccountingproject.entity.TankOperationDetail;
import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.action.list.RemoveAction;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "tanks/:id", layout = MainView.class)
@ViewController("Tank.detail")
@ViewDescriptor("tank-detail-view.xml")
@EditedEntityContainer("tankDc")
public class TankDetailView extends StandardDetailView<Tank> {
    @Autowired
    private MessageBundle messageBundle;
    @ViewComponent("operationDetailsDataGrid.remove")
    private RemoveAction<TankOperationDetail> operationDetailsDataGridRemove;

    @Subscribe
    public void onValidation(final ValidationEvent event) {
        //final String value = messageBundle.getMessage("");
        if(getEditedEntity().getMaxVolume() - getEditedEntity().getMinVolume() <= 0) {
            event.getErrors().add(messageBundle.getMessage("tankDetailView.validation.NegativeOrZero"));
        }
    }
    @ViewComponent
    private EntityPicker<Petroleum> petroleumField;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Tank> event) {
        petroleumField.setReadOnly(false);
    }

    @Subscribe(id = "operationDetailsDc", target = Target.DATA_CONTAINER)
    public void onOperationDetailsDcItemChange(final InstanceContainer.ItemChangeEvent<TankOperationDetail> event) {
        if(event.getItem() != null && event.getItem().getApproved() != null) {
            operationDetailsDataGridRemove.setEnabled(!event.getItem().getApproved());
        }
    }
}