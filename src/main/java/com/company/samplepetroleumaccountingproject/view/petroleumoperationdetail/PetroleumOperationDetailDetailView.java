package com.company.samplepetroleumaccountingproject.view.petroleumoperationdetail;

import com.company.samplepetroleumaccountingproject.app.WarehouseService;
import com.company.samplepetroleumaccountingproject.entity.Petroleum;
import com.company.samplepetroleumaccountingproject.entity.PetroleumOperationDetail;

import com.company.samplepetroleumaccountingproject.entity.PetroleumOperationType;
import com.company.samplepetroleumaccountingproject.entity.Tank;
import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textfield.JmixNumberField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "petroleumOperationDetails/:id", layout = MainView.class)
@ViewController("PetroleumOperationDetail.detail")
@ViewDescriptor("petroleum-operation-detail-detail-view.xml")
@EditedEntityContainer("petroleumOperationDetailDc")
public class PetroleumOperationDetailDetailView extends StandardDetailView<PetroleumOperationDetail> {
    @Autowired
    private MessageBundle messageBundle;
    @ViewComponent
    private CollectionContainer<Tank> tanksDc;
    @ViewComponent
    private JmixNumberField densityField;
    @Autowired
    private WarehouseService warehouseService;

    @Subscribe("petroleumField")
    public void onPetroleumFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<Petroleum>, Petroleum> event) {
        if(event.isFromClient() && getEditedEntity().getTank() != null) {
            if(!tanksDc.containsItem(getEditedEntity().getTank())) {
                getEditedEntity().setTank(null);
                warehouseService.densityCalculate(getEditedEntity(), getEditedEntity().getPetroleumOperation().getDate(), getEditedEntity().getPetroleumOperation().getOperationType(), getEditedEntity().getTank());
            }
        }
        if(event.isFromClient() && tanksDc.getItems().size() == 1) {
            getEditedEntity().setTank(tanksDc.getItems().get(0));
            warehouseService.densityCalculate(getEditedEntity(), getEditedEntity().getPetroleumOperation().getDate(), getEditedEntity().getPetroleumOperation().getOperationType(), getEditedEntity().getTank());
        }
    }

    @Subscribe("tankField")
    public void onTankFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<Tank>, Tank> event) {
        if(event.isFromClient()) {
            warehouseService.densityCalculate(getEditedEntity(), getEditedEntity().getPetroleumOperation().getDate(), getEditedEntity().getPetroleumOperation().getOperationType(), event.getValue());
        }
    }

    @Subscribe("densityField")
    public void onDensityFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixNumberField, ?> event) {
        if(event.isFromClient()) {
            warehouseService.densityCalculate(getEditedEntity(), getEditedEntity().getPetroleumOperation().getDate(), getEditedEntity().getPetroleumOperation().getOperationType(), getEditedEntity().getTank());
        }
    }
}