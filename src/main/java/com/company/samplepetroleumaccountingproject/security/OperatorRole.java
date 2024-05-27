package com.company.samplepetroleumaccountingproject.security;

import com.company.samplepetroleumaccountingproject.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Operator", code = OperatorRole.CODE, scope = "UI")
public interface OperatorRole {
    String CODE = "operator";

    @MenuPolicy(menuIds = {"Partner.list", "Petroleum.list", "Tank.list", "Vehicle.list", "PetroleumOperation.list", "WarehouseStat.list"})
    @ViewPolicy(viewIds = {"Partner.list", "Petroleum.list", "Tank.list", "Vehicle.list", "PetroleumOperation.list", "BankAccount.detail", "BankAccount.list", "Partner.detail", "Person.detail", "Petroleum.detail", "PetroleumOperation.detail", "PetroleumOperationDetail.detail", "Tank.detail", "TankOperationDetail.detail", "Vehicle.detail", "WarehouseStat.list"})
    void screens();

    @EntityAttributePolicy(entityClass = BankAccount.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = BankAccount.class, actions = EntityPolicyAction.READ)
    void bankAccount();

    @EntityAttributePolicy(entityClass = Partner.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Partner.class, actions = EntityPolicyAction.READ)
    void partner();

    @EntityAttributePolicy(entityClass = Person.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Person.class, actions = EntityPolicyAction.READ)
    void person();

    @EntityAttributePolicy(entityClass = Petroleum.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Petroleum.class, actions = EntityPolicyAction.READ)
    void petroleum();

    @EntityAttributePolicy(entityClass = Vehicle.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Vehicle.class, actions = EntityPolicyAction.READ)
    void vehicle();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @EntityAttributePolicy(entityClass = PetroleumOperation.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PetroleumOperation.class, actions = EntityPolicyAction.ALL)
    void petroleumOperation();

    @EntityAttributePolicy(entityClass = PetroleumOperationDetail.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PetroleumOperationDetail.class, actions = EntityPolicyAction.ALL)
    void petroleumOperationDetail();

    @EntityAttributePolicy(entityClass = Tank.class, attributes = "operationDetails", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = Tank.class, attributes = {"id", "mark", "petroleum", "maxVolume", "minVolume"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Tank.class, actions = {EntityPolicyAction.UPDATE, EntityPolicyAction.READ})
    void tank();

    @EntityAttributePolicy(entityClass = TankOperationDetail.class, attributes = {"user", "approved"}, action = EntityAttributePolicyAction.VIEW)
    @EntityAttributePolicy(entityClass = TankOperationDetail.class, attributes = {"id", "date", "temperature", "density", "tank"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = TankOperationDetail.class, actions = EntityPolicyAction.ALL)
    void tankOperationDetail();

    @EntityAttributePolicy(entityClass = PetroleumPriceDetail.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PetroleumPriceDetail.class, actions = EntityPolicyAction.READ)
    void petroleumPriceDetail();

    @EntityAttributePolicy(entityClass = PetroleumCostDetail.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PetroleumCostDetail.class, actions = EntityPolicyAction.READ)
    void petroleumCostDetail();

    @EntityAttributePolicy(entityClass = WarehouseStat.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = WarehouseStat.class, actions = EntityPolicyAction.READ)
    void warehouseStat();
}