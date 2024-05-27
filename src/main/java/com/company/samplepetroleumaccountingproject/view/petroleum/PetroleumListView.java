package com.company.samplepetroleumaccountingproject.view.petroleum;

import com.company.samplepetroleumaccountingproject.entity.Petroleum;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "petroleums", layout = MainView.class)
@ViewController("Petroleum.list")
@ViewDescriptor("petroleum-list-view.xml")
@LookupComponent("petroleumsDataGrid")
@DialogMode(width = "64em")
public class PetroleumListView extends StandardListView<Petroleum> {
}