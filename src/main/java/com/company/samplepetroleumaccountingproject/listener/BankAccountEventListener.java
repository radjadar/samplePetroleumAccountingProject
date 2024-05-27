package com.company.samplepetroleumaccountingproject.listener;

import com.company.samplepetroleumaccountingproject.entity.BankAccount;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BankAccountEventListener {

    @EventListener
    public void onBankAccountSaving(final EntitySavingEvent<BankAccount> event) {
        event.getEntity().setOperatingAccount(event.getEntity().getOperatingAccount() == null ? false : event.getEntity().getOperatingAccount());
    }
}