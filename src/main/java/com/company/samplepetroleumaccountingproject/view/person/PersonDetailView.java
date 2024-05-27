package com.company.samplepetroleumaccountingproject.view.person;

import com.company.samplepetroleumaccountingproject.entity.Person;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "persons/:id", layout = MainView.class)
@ViewController("Person.detail")
@ViewDescriptor("person-detail-view.xml")
@EditedEntityContainer("personDc")
public class PersonDetailView extends StandardDetailView<Person> {
}