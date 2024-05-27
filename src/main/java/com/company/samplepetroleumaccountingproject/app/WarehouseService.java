package com.company.samplepetroleumaccountingproject.app;

import com.company.samplepetroleumaccountingproject.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.NoResultException;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class WarehouseService {
    private final DataManager dataManager;

    public WarehouseService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void densityCalculate(PetroleumOperationDetail petroleumOperationDetail, LocalDateTime localDateTime, PetroleumOperationType petroleumOperationType, Tank tank) {
        if(petroleumOperationType == PetroleumOperationType.OUTBOUND) {
            petroleumOperationDetail.setDensity(tankDensity(tank, localDateTime.toLocalDate()));
        }
        if(tankDensity(tank, localDateTime.toLocalDate()) == 0) {
            petroleumOperationDetail.setDensity(null);
        }
    }

    public double tankDensity(Tank tank, LocalDate localDate) {
        return dataManager.loadValue("select t.density " +
                        "from TankOperationDetail t " +
                        "where t.approved = :approved " +
                        "and t.tank = :tank " +
                        "and t.date <= :date " +
                        "order by t.date desc", Double.class)
                .parameter("approved", true)
                .parameter("tank", tank)
                .parameter("date", localDate).optional().orElse((double) 0);
    }

    public double tankVolume(Tank tank, LocalDateTime localDateTime) {
        return tankDensity(tank, localDateTime.toLocalDate()) == 0 ? 0
                : tankMass(tank, localDateTime) / tankDensity(tank, localDateTime.toLocalDate());
    }

    public double tankMass(Tank tank, LocalDateTime localDateTime) {
        return dataManager.loadValue("select SUM(t.mass) " +
                        "from PetroleumCostDetail t " +
                        "where t.tank = :tank " +
                        "and t.date <= :date", Double.class)
                .parameter("tank", tank)
                .parameter("date", localDateTime).optional().orElse((double) 0);
    }

    public void priceDetailsCalculate(PetroleumOperation petroleumOperation) {
        dataManager.unconstrained().remove(dataManager.load(PetroleumPriceDetail.class).condition(PropertyCondition.equal("petroleumOperation", petroleumOperation)).list());
        if(petroleumOperation.getOperationType() == PetroleumOperationType.INBOUND) {
            PetroleumPriceDetail petroleumPriceDetail;
            for(PetroleumOperationDetail petroleumOperationDetail : petroleumOperation.getOperationDetails()) {
                petroleumPriceDetail = dataManager.create(PetroleumPriceDetail.class);
                petroleumPriceDetail.setDate(petroleumOperation.getDate().toLocalDate());
                petroleumPriceDetail.setPrice(petroleumOperationDetail.getPrice());
                petroleumPriceDetail.setPetroleum(petroleumOperationDetail.getPetroleum());
//                petroleumPriceDetail.setTank(petroleumOperationDetail.getTank());
                petroleumPriceDetail.setPetroleumOperation(petroleumOperation);
                dataManager.unconstrained().save(petroleumPriceDetail);
            }
        }
    }

    public void costDetailsCalculate(PetroleumOperation petroleumOperation) {
        dataManager.unconstrained().remove(dataManager.load(PetroleumCostDetail.class).condition(PropertyCondition.equal("petroleumOperation", petroleumOperation)).list());
        PetroleumCostDetail petroleumCostDetail;
        for(PetroleumOperationDetail petroleumOperationDetail : petroleumOperation.getOperationDetails()) {
            petroleumCostDetail = dataManager.create(PetroleumCostDetail.class);
            petroleumCostDetail.setDate(petroleumOperation.getDate());
            if(petroleumOperation.getOperationType() == PetroleumOperationType.OUTBOUND) {
                petroleumCostDetail.setMass(petroleumOperationDetail.getMass() * -1);
                petroleumCostDetail.setCost(petroleumOperationDetail.getMass() * petroleumCost(petroleumOperationDetail.getPetroleum(), petroleumOperationDetail.getTank(), petroleumOperation.getDate()) * -1);
            } else {
                petroleumCostDetail.setMass(petroleumOperationDetail.getMass());
                petroleumCostDetail.setCost(petroleumOperationDetail.getCost());
            }
            petroleumCostDetail.setPetroleum(petroleumOperationDetail.getPetroleum());
            petroleumCostDetail.setTank(petroleumOperationDetail.getTank());
            petroleumCostDetail.setPetroleumOperation(petroleumOperation);
            dataManager.unconstrained().save(petroleumCostDetail);
        }
    }

    public double petroleumCost(Petroleum petroleum, Tank tank, LocalDateTime localDateTime) {
        KeyValueEntity costDetails = dataManager.loadValues("select SUM(p.mass), SUM(p.cost) " +
                        "from PetroleumCostDetail p " +
                        "where p.petroleum = :petroleum " +
                        "and p.date <= :date").properties("mass", "cost")
                .parameter("petroleum", petroleum)
                .parameter("date", localDateTime).one();
        if(costDetails.getValue("cost") != null && costDetails.getValue("mass") != null && (double) costDetails.getValue("mass") != 0) {
            return (double) costDetails.getValue("cost") / (double) costDetails.getValue("mass");
        } else {return 0;}
    }

    public double tankCost(Tank tank, LocalDateTime localDateTime) {
        return tankMass(tank, localDateTime) * petroleumCost(tank.getPetroleum(), null, localDateTime);
    }

    public double petroleumPrice(Petroleum petroleum, Tank tank, LocalDate localDate) {
        return dataManager.loadValue("select MAX(p.price) " +
                        "from PetroleumPriceDetail p " +
                        "where p.petroleum = :petroleum " +
                        "and p.date <= :date " +
                        "group by p.date " +
                        "order by p.date desc", Double.class)
                .parameter("petroleum", petroleum)
                .parameter("date", localDate).optional().orElse((double) 0);
    }
}