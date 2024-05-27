package com.company.samplepetroleumaccountingproject.listener;

import com.company.samplepetroleumaccountingproject.app.WarehouseService;
import com.company.samplepetroleumaccountingproject.entity.PetroleumOperation;
import com.company.samplepetroleumaccountingproject.entity.PetroleumOperationDetail;
import com.company.samplepetroleumaccountingproject.entity.PetroleumOperationType;
import com.company.samplepetroleumaccountingproject.entity.PetroleumPriceDetail;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PetroleumOperationDetailEventListener {

    private final DataManager dataManager;
    private final WarehouseService warehouseService;

    public PetroleumOperationDetailEventListener(DataManager dataManager, WarehouseService warehouseService) {
        this.dataManager = dataManager;
        this.warehouseService = warehouseService;
    }

    @EventListener
    public void onPetroleumOperationDetailChangedBeforeCommit(final EntityChangedEvent<PetroleumOperationDetail> event) {
        if(event.getType() == EntityChangedEvent.Type.UPDATED) {
            warehouseService.priceDetailsCalculate(dataManager.load(event.getEntityId()).one().getPetroleumOperation());
            warehouseService.costDetailsCalculate(dataManager.load(event.getEntityId()).one().getPetroleumOperation());
        }
    }
}