package com.company.samplepetroleumaccountingproject.listener;

import com.company.samplepetroleumaccountingproject.entity.BankAccount;
import com.company.samplepetroleumaccountingproject.entity.Partner;
import com.company.samplepetroleumaccountingproject.entity.Person;
import io.jmix.core.event.EntityLoadingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PartnerEventListener {

    @EventListener
    public void onPartnerLoading(final EntityLoadingEvent<Partner> event) {
        if(event.getEntity().getContactPersons() != null) {
            for(Person person : event.getEntity().getContactPersons()) {
                if(person.getCeo() != null && person.getCeo()) {
                    event.getEntity().setCeo(person);
                    break;
                }
            }
        }
        if(event.getEntity().getOperatingAccounts() != null) {
            for(BankAccount bankAccount : event.getEntity().getOperatingAccounts()) {
                if(bankAccount.getOperatingAccount() != null && bankAccount.getOperatingAccount()) {
                    event.getEntity().setOperatingAccount(bankAccount);
                    break;
                }
            }
        }
    }
}