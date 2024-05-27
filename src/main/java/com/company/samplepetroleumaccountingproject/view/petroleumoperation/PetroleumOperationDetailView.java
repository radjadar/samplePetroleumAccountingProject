package com.company.samplepetroleumaccountingproject.view.petroleumoperation;

import com.company.samplepetroleumaccountingproject.app.WarehouseService;
import com.company.samplepetroleumaccountingproject.entity.*;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.TimeSource;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.action.DialogAction;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Route(value = "petroleumOperations/:id", layout = MainView.class)
@ViewController("PetroleumOperation.detail")
@ViewDescriptor("petroleum-operation-detail-view.xml")
@EditedEntityContainer("petroleumOperationDc")
public class PetroleumOperationDetailView extends StandardDetailView<PetroleumOperation> {
    @Autowired
    private TimeSource timeSource;
    @Autowired
    private MessageBundle messageBundle;
    @ViewComponent
    private EntityPicker<Partner> supplierClientField;
    @ViewComponent
    private EntityPicker<Partner> senderReceiverField;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private TypedDateTimePicker<Comparable> dateField;

    @Subscribe
    public void onValidation(final ValidationEvent event) {
        if(getEditedEntity().getOperationDetails() != null) {
            for(PetroleumOperationDetail petroleumOperationDetails : getEditedEntity().getOperationDetails()) {
                double validationVolume = 0;
                for(PetroleumOperationDetail PetroleumOperationDetail : getEditedEntity().getOperationDetails()) {
                    if(petroleumOperationDetails.getTank() == PetroleumOperationDetail.getTank()) {
                        validationVolume = validationVolume + (PetroleumOperationDetail.getVolume() != null ? PetroleumOperationDetail.getVolume() : 0);
                    }
                }
                if(getEditedEntity().getOperationType() == PetroleumOperationType.INBOUND && validationVolume != 0) {
                    if(tankVolume(petroleumOperationDetails.getTank(), getEditedEntity().getDate(), getEditedEntity()) + validationVolume > petroleumOperationDetails.getTank().getMaxVolume()) {
                        event.getErrors().add(messageBundle.formatMessage("petroleumOperationDetailView.validation.maxVolume", petroleumOperationDetails.getTank().getMark()));
                        break;
                    }
                }
                if(getEditedEntity().getOperationType() == PetroleumOperationType.OUTBOUND && validationVolume != 0) {
                    if(tankVolume(petroleumOperationDetails.getTank(), getEditedEntity().getDate(), getEditedEntity()) - validationVolume < petroleumOperationDetails.getTank().getMinVolume()) {
                        event.getErrors().add(messageBundle.formatMessage("petroleumOperationDetailView.validation.minVolume", petroleumOperationDetails.getTank().getMark()));
                        break;
                    }
                }
            }
        }
    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<PetroleumOperation> event) {
        //final ZonedDateTime zonedDateTime = timeSource.now();
        event.getEntity().setDate(timeSource.now().toLocalDateTime());
        event.getEntity().setOperationType(PetroleumOperationType.OUTBOUND);
    }

    @Subscribe("dateField")
    public void onDateFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<TypedDateTimePicker<Comparable>, Comparable<?>> event) {
        if(event.isFromClient()) {
            if(event.getValue() != null) {
                operationDetailsCalculate((LocalDateTime) event.getValue(), getEditedEntity().getOperationType());
            } else {dateField.setValue((LocalDateTime) event.getOldValue());}
        }
    }

    @Subscribe("operationTypeField")
    public void onOperationTypeFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixSelect<?>, ?> event) {
        //final String value = messageBundle.getMessage("");
        supplierClientField.setLabel(messageBundle.getMessage(event.getValue() == PetroleumOperationType.INBOUND
                ? "petroleumOperationDetailView.supplier"
                : "petroleumOperationDetailView.client"));
        senderReceiverField.setLabel(messageBundle.getMessage(event.getValue() == PetroleumOperationType.INBOUND
                ? "petroleumOperationDetailView.sender"
                : "petroleumOperationDetailView.receiver"));

        if(event.isFromClient()) {
            operationDetailsCalculate(getEditedEntity().getDate(), (PetroleumOperationType) event.getValue());
        }
    }

    @Subscribe("supplierClientField")
    public void onSupplierClientFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<Partner>, Partner> event) {
        if (event.isFromClient()) {
            getEditedEntity().setOperatingAccount(event.getValue() != null ? event.getValue().getOperatingAccount() : null);
            if (getEditedEntity().getSenderReceiver() == null) {
                getEditedEntity().setSenderReceiver(event.getValue());
            }
        }
    }

    @Subscribe("vehicleField")
    public void onVehicleFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<Vehicle>, Vehicle> event) {
        if(event.isFromClient()) {
            getEditedEntity().setVehicleDetails(null);
        }
    }

    private void operationDetailsCalculate(LocalDateTime localDateTime, PetroleumOperationType petroleumOperationType) {
        if(getEditedEntity().getOperationDetails() != null) {
            for(PetroleumOperationDetail petroleumOperationDetail : getEditedEntity().getOperationDetails()) {
                petroleumOperationDetail.getPetroleumOperation().setDate(localDateTime);
                petroleumOperationDetail.getPetroleumOperation().setOperationType(petroleumOperationType);
            }
            dialogs.createOptionDialog().withHeader(messageBundle.getMessage("petroleumOperationDetailView.title"))
                    .withText(messageBundle.getMessage("petroleumOperationDetailView.operationDetailsCalculate"))
                    .withActions(new DialogAction(DialogAction.Type.YES).withHandler(e -> {
                        for(PetroleumOperationDetail petroleumOperationDetail : getEditedEntity().getOperationDetails()) {
                            warehouseService.densityCalculate(petroleumOperationDetail, localDateTime, petroleumOperationType, petroleumOperationDetail.getTank());
                        }}), new DialogAction(DialogAction.Type.NO)).open();
        }
    }

    private double tankVolume(Tank tank, LocalDateTime localDateTime, PetroleumOperation petroleumOperation) {
        return warehouseService.tankDensity(tank, localDateTime.toLocalDate()) == 0 ? 0
                : tankMass(tank, localDateTime, petroleumOperation) / warehouseService.tankDensity(tank, localDateTime.toLocalDate());
    }

    private double tankMass(Tank tank, LocalDateTime localDateTime, PetroleumOperation petroleumOperation) {
        return dataManager.loadValue("select SUM(p.mass) " +
                        "from PetroleumCostDetail p " +
                        "where p.tank = :tank " +
                        "and p.date <= :date " +
                        "and p.petroleumOperation <> :petroleumOperation", Double.class)
                .parameter("tank", tank)
                .parameter("date", localDateTime)
                .parameter("petroleumOperation", petroleumOperation).optional().orElse((double) 0);
    }
}