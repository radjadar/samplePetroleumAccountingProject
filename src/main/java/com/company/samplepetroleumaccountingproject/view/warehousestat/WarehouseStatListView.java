package com.company.samplepetroleumaccountingproject.view.warehousestat;

import com.company.samplepetroleumaccountingproject.app.WarehouseService;
import com.company.samplepetroleumaccountingproject.entity.Tank;
import com.company.samplepetroleumaccountingproject.entity.WarehouseStat;
import com.company.samplepetroleumaccountingproject.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.core.TimeSource;
import io.jmix.flowui.component.datepicker.TypedDatePicker;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "warehouseStats", layout = MainView.class)
@ViewController("WarehouseStat.list")
@ViewDescriptor("warehouse-stat-list-view.xml")
@LookupComponent("warehouseStatsDataGrid")
@DialogMode(width = "50em")
public class WarehouseStatListView extends StandardListView<WarehouseStat> {
    @Autowired
    private TimeSource timeSource;
    @ViewComponent
    private TypedDatePicker<LocalDateTime> datePicker;
    @ViewComponent
    private CollectionLoader<WarehouseStat> warehouseStatsDl;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private WarehouseService warehouseService;

    @Subscribe
    public void onInit(final InitEvent event) {
//        final ZonedDateTime zonedDateTime = timeSource.now();
        datePicker.setValue(timeSource.now().toLocalDate());
    }

    @Install(to = "warehouseStatsDl", target = Target.DATA_LOADER)
    protected List<WarehouseStat> warehouseStatsDlLoadDelegate(LoadContext<WarehouseStat> loadContext) {
        // Here you can load entities from an external storage.
        // Set the loaded entities to the not-new state using EntityStates.setNew(entity, false).
//        return List.of();
        return dataManager.load(Tank.class).all().list().stream().map(tank -> {
            WarehouseStat warehouseStat = dataManager.create(WarehouseStat.class);
            warehouseStat.setTank(tank);
            warehouseStat.setPetroleum(tank.getPetroleum());
            warehouseStat.setEcoClass(tank.getPetroleum().getEcoClass());
            warehouseStat.setVolume(warehouseService.tankVolume(tank, datePicker.getValue().atTime(LocalTime.MAX)));
            warehouseStat.setMass(warehouseService.tankMass(tank, datePicker.getValue().atTime(LocalTime.MAX)));
            warehouseStat.setDensity(warehouseService.tankDensity(tank, datePicker.getValue()));
            warehouseStat.setCost(warehouseService.tankCost(tank, datePicker.getValue().atTime(LocalTime.MAX)));
            warehouseStat.setPrice(warehouseService.petroleumPrice(tank.getPetroleum(), null, datePicker.getValue()));
            return warehouseStat;
        }).collect(Collectors.toList());
    }

    @Subscribe("datePicker")
    public void onDatePickerComponentValueChange(final AbstractField.ComponentValueChangeEvent<TypedDatePicker<LocalDateTime>, LocalDateTime> event) {
        if(event.getValue() != null) {
            warehouseStatsDl.load();
        }
    }
//
//    @Install(to = "warehouseStatsDataGrid.remove", subject = "delegate")
//    private void warehouseStatsDataGridRemoveDelegate(final Collection<WarehouseStat> collection) {
//        for (WarehouseStat entity : collection) {
//            // Here you can remove entities from an external storage
//        }
//    }
}
