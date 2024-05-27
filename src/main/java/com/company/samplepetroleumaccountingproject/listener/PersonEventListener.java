package com.company.samplepetroleumaccountingproject.listener;

import com.company.samplepetroleumaccountingproject.entity.Person;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PersonEventListener {

    @EventListener
    public void onPersonSaving(final EntitySavingEvent<Person> event) {
        event.getEntity().setCeo(event.getEntity().getCeo() == null ? false : event.getEntity().getCeo());
    }
}