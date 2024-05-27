package com.company.samplepetroleumaccountingproject.listener;

import com.company.samplepetroleumaccountingproject.app.WarehouseService;
import com.company.samplepetroleumaccountingproject.entity.PetroleumOperation;
import com.company.samplepetroleumaccountingproject.entity.PetroleumOperationDetail;
import com.company.samplepetroleumaccountingproject.entity.PetroleumOperationType;
import com.company.samplepetroleumaccountingproject.entity.PetroleumPriceDetail;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
public class PetroleumOperationEventListener {

    private final DataManager dataManager;
    private final WarehouseService warehouseService;

    public PetroleumOperationEventListener(DataManager dataManager, WarehouseService warehouseService) {
        this.dataManager = dataManager;
        this.warehouseService = warehouseService;
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onPetroleumOperationChangedAfterCommit(final EntityChangedEvent<PetroleumOperation> event) {
        if(event.getType() != EntityChangedEvent.Type.DELETED) {
            warehouseService.priceDetailsCalculate(dataManager.load(event.getEntityId()).one());
            warehouseService.costDetailsCalculate(dataManager.load(event.getEntityId()).one());
        }
    }
}