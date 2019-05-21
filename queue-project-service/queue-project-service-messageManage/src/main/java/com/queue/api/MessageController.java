package com.queue.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.queue.pojo.Message;
import com.queue.service.MessageService;
import com.queue.util.ResultBean;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messageManage")
@Api(value = "/messageManage", description = "留言管理子系统")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "获取留言列表", notes = "获取留言列表")
    @RequestMapping(value = "/getMessages", method = RequestMethod.POST)
    public ResultBean getMessages(@RequestBody @ApiParam(value = "获取留言的参数", required = false, name = "Message") Message message) throws Exception {
//        Message message = JSON.parseObject(params, new TypeReference<Message>() {
//        });
        return messageService.getMessageList(message);
    }

    @ApiOperation(value = "添加留言", notes = "添加留言")
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public ResultBean addMessage(@RequestBody String params) throws Exception {
        Message message = JSON.parseObject(params, new TypeReference<Message>() {
        });

        return messageService.addMessage(message);
    }

    @ApiOperation(value = "修改留言", notes = "修改留言")
    @ApiImplicitParam(name="params",value="根据id修改留言",required = true,dataType = "Integer")
    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
    public ResultBean updateMessage(@RequestBody String params) throws Exception {
        Message message = JSON.parseObject(params, new TypeReference<Message>() {
        });

        return messageService.updateMessage(message);
    }

    @ApiOperation(value = "删除留言", notes = "删除留言")
    @ApiImplicitParam(name = "params", value = "留言ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/removeMessage", method = RequestMethod.POST)
    public ResultBean removeMessage(@RequestBody String params) throws Exception {
        Message message = JSON.parseObject(params, new TypeReference<Message>() {
        });

        return messageService.removeMessage(message);
    }
}
