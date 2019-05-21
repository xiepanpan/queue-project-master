package com.queue.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.queue.pojo.Queues;
import com.queue.service.QueueManageService;
import com.queue.service.feignService.UserFeign;
import com.queue.util.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queueManage")
public class QueueController {
    @Autowired
    private QueueManageService queueManageService;
//    @Autowired
//    private UserFeign userFeign;
    private Logger logger= LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getQueueList", method = RequestMethod.POST)
    public ResultBean getQueueList(@RequestBody String params) throws Exception {
        logger.info("查询参数:"+params);
        Queues queues=JSON.parseObject(params,new TypeReference<Queues>(){});
        System.out.println(queues.getBusinessId());
        System.out.println(queues.getTransact());
        return queueManageService.getQueueList(queues);
    }

    @RequestMapping(value = "/addQueue", method = RequestMethod.POST)
    public ResultBean addQueue(@RequestBody String params) throws Exception {
        Queues queues = JSON.parseObject(params, new TypeReference<Queues>() {
        });
        return queueManageService.addQueue(queues);
    }

    @RequestMapping(value = "/missTurnHandle", method = RequestMethod.POST)
    public ResultBean missTurnHandle(@RequestBody String params) throws Exception {
        Queues queues = JSON.parseObject(params, new TypeReference<Queues>() {
        });
        return queueManageService.jumpQueue(queues);
    }

    @RequestMapping(value = "/transactQueue", method = RequestMethod.POST)
    public ResultBean transactQueue(@RequestBody String params) throws Exception {
        Queues queues = JSON.parseObject(params, new TypeReference<Queues>() {
        });
        return queueManageService.transactQueue(queues);
    }
    @RequestMapping(value = "/repealQueue", method = RequestMethod.POST)
    public ResultBean repealQueue(@RequestBody String params) throws Exception {
        Queues queues = JSON.parseObject(params, new TypeReference<Queues>() {
        });
        return queueManageService.repealQueue(queues);
    }

    @RequestMapping(value = "/overQueue", method = RequestMethod.POST)
    public ResultBean overQueue(@RequestBody String params) throws Exception {
        Queues queues = JSON.parseObject(params, new TypeReference<Queues>() {
        });
        return queueManageService.overQueue(queues);
    }
}
