package com.queue.service;

import com.queue.pojo.Queues;
import com.queue.util.ResultBean;
import com.sun.org.apache.regexp.internal.RE;

public interface QueueManageService {
    ResultBean getQueueList(Queues queues) throws Exception;
    ResultBean addQueue(Queues queues) throws Exception;
    ResultBean jumpQueue(Queues queues) throws Exception;
    //办理业务
    ResultBean transactQueue(Queues queues) throws Exception;
    //撤销预约
    ResultBean repealQueue(Queues queues) throws Exception;
    ResultBean overQueue(Queues queues) throws Exception;
}
