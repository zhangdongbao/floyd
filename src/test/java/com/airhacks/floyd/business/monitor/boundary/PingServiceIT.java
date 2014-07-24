package com.airhacks.floyd.business.monitor.boundary;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class PingServiceIT {

    PingService cut;

    @Before
    public void init() {
        this.cut = new PingService();
        this.cut.init();
    }

    @Test
    public void errorOnMalformedUri() throws InterruptedException {
        StringProperty content = new SimpleStringProperty();
        StringProperty error = new SimpleStringProperty();
        this.cut.askForUptime("Malformed", content, error);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await(3, TimeUnit.SECONDS);
        error.addListener(l -> countDownLatch.countDown());
        assertNull(content.get());
        assertNotNull(error.get());

    }

}