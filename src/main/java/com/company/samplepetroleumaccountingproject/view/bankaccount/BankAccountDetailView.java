package com.company.samplepetroleumaccountingproject.view.bankaccount;

import com.company.samplepetroleumaccountingproject.entity.BankAccount;

import com.company.samplepetroleumaccountingproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "bankAccounts/:id", layout = MainView.class)
@ViewController("BankAccount.detail")
@ViewDescriptor("bank-account-detail-view.xml")
@EditedEntityContainer("bankAccountDc")
public class BankAccountDetailView extends StandardDetailView<BankAccount> {
}