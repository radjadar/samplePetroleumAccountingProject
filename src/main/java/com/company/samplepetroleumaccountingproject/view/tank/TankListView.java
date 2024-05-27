package com.company.samplepetroleumaccountingproject.view.tank;

import com.company.samplepetroleumaccountingproject.entity.Tank;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "tanks", layout = MainView.class)
@ViewController("Tank.list")
@ViewDescriptor("tank-list-view.xml")
@LookupComponent("tanksDataGrid")
@DialogMode(width = "64em")
public class TankListView extends StandardListView<Tank> {
}