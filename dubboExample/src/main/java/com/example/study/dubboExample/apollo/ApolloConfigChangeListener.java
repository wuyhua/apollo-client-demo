package com.example.study.dubboExample.apollo;

import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.enums.PropertyChangeType;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Desc   apollo监听回调处理类
 * @Author wuyh
 * @Date 2019/10/11 8:51
 **/
@Slf4j
public class ApolloConfigChangeListener implements ConfigChangeListener {

    @Override
    public void onChange(ConfigChangeEvent changeEvent) {
        String namespace = changeEvent.getNamespace();
        log.info("Changes for namespace " + namespace);

        for (String key : changeEvent.changedKeys()) {

            ConfigChange change = changeEvent.getChange(key);
            String propertyName = change.getPropertyName();
            String oldValue = change.getOldValue();
            String newValue = change.getNewValue();
            PropertyChangeType changeType = change.getChangeType();

            log.info(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
                    propertyName, oldValue, newValue, changeType));
        }
    }

}
