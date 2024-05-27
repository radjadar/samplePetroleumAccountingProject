package com.company.samplepetroleumaccountingproject.view.petroleumoperation;

import com.company.samplepetroleumaccountingproject.entity.PetroleumOperation;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "petroleumOperations", layout = MainView.class)
@ViewController("PetroleumOperation.list")
@ViewDescriptor("petroleum-operation-list-view.xml")
@LookupComponent("petroleumOperationsDataGrid")
@DialogMode(width = "64em")
public class PetroleumOperationListView extends StandardListView<PetroleumOperation> {
}