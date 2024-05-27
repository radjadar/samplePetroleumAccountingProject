package com.company.samplepetroleumaccountingproject.view.vehicle;

import com.company.samplepetroleumaccountingproject.entity.Vehicle;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "vehicles", layout = MainView.class)
@ViewController("Vehicle.list")
@ViewDescriptor("vehicle-list-view.xml")
@LookupComponent("vehiclesDataGrid")
@DialogMode(width = "64em")
public class VehicleListView extends StandardListView<Vehicle> {
}