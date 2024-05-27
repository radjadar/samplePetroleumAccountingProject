package com.company.samplepetroleumaccountingproject.view.petroleum;

import com.company.samplepetroleumaccountingproject.entity.Petroleum;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "petroleums/:id", layout = MainView.class)
@ViewController("Petroleum.detail")
@ViewDescriptor("petroleum-detail-view.xml")
@EditedEntityContainer("petroleumDc")
public class PetroleumDetailView extends StandardDetailView<Petroleum> {
}