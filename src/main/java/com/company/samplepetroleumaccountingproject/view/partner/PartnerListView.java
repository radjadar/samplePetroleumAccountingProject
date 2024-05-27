package com.company.samplepetroleumaccountingproject.view.partner;

import com.company.samplepetroleumaccountingproject.entity.Partner;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "partners", layout = MainView.class)
@ViewController("Partner.list")
@ViewDescriptor("partner-list-view.xml")
@LookupComponent("partnersDataGrid")
@DialogMode(width = "64em")
public class PartnerListView extends StandardListView<Partner> {
}