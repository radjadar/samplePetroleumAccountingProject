package com.company.samplepetroleumaccountingproject.security;

import com.company.samplepetroleumaccountingproject.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Manager", code = ManagerRole.CODE, scope = "UI")
public interface ManagerRole {
    String CODE = "manager";

    @MenuPolicy(menuIds = {"Partner.list", "Petroleum.list", "Tank.list", "Vehicle.list", "PetroleumOperation.list", "WarehouseStat.list"})
    @ViewPolicy(viewIds = {"Partner.list", "Petroleum.list", "Tank.list", "Vehicle.list", "PetroleumOperation.list", "Partner.detail", "BankAccount.detail", "BankAccount.list", "Person.detail", "Petroleum.detail", "Tank.detail", "Vehicle.detail", "TankOperationDetail.detail", "PetroleumOperationDetail.detail", "PetroleumOperation.detail", "WarehouseStat.list"})
    void screens();

    @EntityAttributePolicy(entityClass = Partner.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Partner.class, actions = EntityPolicyAction.ALL)
    void partner();

    @EntityAttributePolicy(entityClass = BankAccount.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = BankAccount.class, actions = EntityPolicyAction.ALL)
    void bankAccount();

    @EntityAttributePolicy(entityClass = Person.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Person.class, actions = EntityPolicyAction.ALL)
    void person();

    @EntityAttributePolicy(entityClass = Petroleum.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Petroleum.class, actions = EntityPolicyAction.ALL)
    void petroleum();

    @EntityAttributePolicy(entityClass = PetroleumPriceDetail.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PetroleumPriceDetail.class, actions = EntityPolicyAction.READ)
    void petroleumPriceDetail();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @EntityAttributePolicy(entityClass = Tank.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Tank.class, actions = EntityPolicyAction.ALL)
    void tank();

    @EntityAttributePolicy(entityClass = Vehicle.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Vehicle.class, actions = EntityPolicyAction.ALL)
    void vehicle();

    @EntityAttributePolicy(entityClass = PetroleumOperation.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PetroleumOperation.class, actions = EntityPolicyAction.READ)
    void petroleumOperation();

    @EntityAttributePolicy(entityClass = PetroleumOperationDetail.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PetroleumOperationDetail.class, actions = EntityPolicyAction.READ)
    void petroleumOperationDetail();

    @EntityAttributePolicy(entityClass = TankOperationDetail.class, attributes = "approved", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = TankOperationDetail.class, attributes = {"id", "date", "user", "temperature", "density", "tank"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = TankOperationDetail.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void tankOperationDetail();

    @EntityAttributePolicy(entityClass = WarehouseStat.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = WarehouseStat.class, actions = EntityPolicyAction.READ)
    void warehouseStat();

    @EntityAttributePolicy(entityClass = PetroleumCostDetail.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PetroleumCostDetail.class, actions = EntityPolicyAction.READ)
    void petroleumCostDetail();
}