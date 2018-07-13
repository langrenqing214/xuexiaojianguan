package com.cxzy.xxjg;

import rx.Subscription;

/**
 * Created by demo on 2018/6/25.
 */

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}
