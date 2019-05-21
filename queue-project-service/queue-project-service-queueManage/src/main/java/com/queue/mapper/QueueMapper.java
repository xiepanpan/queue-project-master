package com.queue.mapper;

import com.netflix.discovery.converters.jackson.EurekaXmlJacksonCodec;
import com.queue.pojo.Business;
import com.queue.pojo.Queues;
import com.queue.pojo.Window;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QueueMapper {
    List<Queues> getQueueList(Queues queues)throws Exception;

    void addQueue(Queues queues) throws Exception;

    Integer jumpQueue(Queues queues) throws Exception;

    Integer transactQueue(Queues queues) throws Exception;

    Integer repealQueue(Queues queues) throws Exception;

    List<Window> selectWindow(Window window) throws Exception;

    void overQueue(Queues queues)throws Exception;

    void updateWindow(Window window)throws Exception;

    void updateBusiness(Business business) throws Exception;

    Business selectBusiness(Business business) throws Exception;

    void updateWindowStatus(Window window) throws Exception;

}

