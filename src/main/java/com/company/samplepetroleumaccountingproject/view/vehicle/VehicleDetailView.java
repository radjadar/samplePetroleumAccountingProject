package com.company.samplepetroleumaccountingproject.view.vehicle;

import com.company.samplepetroleumaccountingproject.entity.Vehicle;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "vehicles/:id", layout = MainView.class)
@ViewController("Vehicle.detail")
@ViewDescriptor("vehicle-detail-view.xml")
@EditedEntityContainer("vehicleDc")
public class VehicleDetailView extends StandardDetailView<Vehicle> {
}