package com.queue.service.impl;

import com.queue.mapper.QueueMapper;
import com.queue.pojo.Business;
import com.queue.pojo.Queues;
import com.queue.pojo.Window;
import com.queue.service.QueueManageService;
import com.queue.util.DateUtil;
import com.queue.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class QueueManageServiceImpl implements QueueManageService {
    @Autowired
    private QueueMapper queueMapper;

    @Override
    public ResultBean getQueueList(Queues queues) throws Exception {
        ResultBean resultBean = new ResultBean();
        List<Queues> queuesList = queueMapper.getQueueList(queues);
        resultBean.setSuccess(true);
        resultBean.setMessage("查找成功");
        resultBean.setDataList(queuesList);
        return resultBean;
    }

    @Override
    public ResultBean addQueue(Queues queues) throws Exception {
        ResultBean resultBean = new ResultBean();
        Queues queues1 = new Queues();
        queues1.setBusinessId(queues.getBusinessId());
        queues1.setTransact(0);
        System.out.println(queues1.getTransact());
        List<Queues> queuesList = queueMapper.getQueueList(queues1);
        queues.setQueueNum(queuesList.size() + 1);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String dates = sdf.format(date);
        queues1.setBusinessId(null);
        queues1.setTransact(null);
        List<Queues> queuesList1 = queueMapper.getQueueList(queues1);
//        queues.setCreateTime(dates);
        if (queuesList1 == null || queuesList1.size() == 0)
            queues.setQueueNo(dates + "0001");
        else {
            queues.setQueueNo(DateUtil.getnumber(queuesList1.get(queuesList1.size() - 1).getQueueNo()));
        }
        //是否办理
        Window window = new Window();
        window.setBusinessId(queues.getBusinessId());
        List<Window> windows = queueMapper.selectWindow(window);
        if (windows.size()==0){
            resultBean.setSuccess(false);
            resultBean.setMessage("预约失败");
            return resultBean;
        }
        for(Window window1:windows){
            if(window1.getWindowStatus()==1){
                resultBean.setSuccess(false);
                resultBean.setMessage("预约失败");
                return resultBean;
            }
        }
        for (Window window1 : windows) {
            if (window1.getTransact().equals(0)) {
                queues.setWindowId(window1.getId());
                queues.setQueueNum(0);
                queues.setTransact(1);
                break;
            }
        }
        if (queues.getTransact() == null || !queues.getTransact().equals(1))
            queues.setTransact(0);
        queueMapper.addQueue(queues);
        windows = queueMapper.selectWindow(window);
        int num=0;
        for(Window window1:windows){
            num+=window1.getBusinessNum();
        }
        Business business=new Business();
        business.setId(window.getBusinessId());
        Business businesses=queueMapper.selectBusiness(business);
        if (num==businesses.getWaitNum()){
            for (Window window1:windows){
                window1.setWindowStatus(1);
                queueMapper.updateWindowStatus(window1);
            }
        }
        resultBean.setSuccess(true);
        resultBean.setMessage("预约成功");
        return resultBean;
    }

    @Override
    public ResultBean jumpQueue(Queues queues) throws Exception {
        ResultBean resultBean = new ResultBean();
        Queues queues1 = new Queues();
        queues1.setBusinessId(queues.getBusinessId());
        queues1.setTransact(0);
        System.out.println(queues1.getTransact());
        List<Queues> queuesList = queueMapper.getQueueList(queues1);
        queues.setQueueNum(queuesList.size());
        queueMapper.jumpQueue(queues);

        resultBean.setSuccess(true);
        resultBean.setMessage("插队成功");
        return resultBean;
    }

    @Override
    public ResultBean transactQueue(Queues queues) throws Exception {
        ResultBean resultBean = new ResultBean();
        queueMapper.transactQueue(queues);
        Queues queues1=new Queues();
        queues1.setWindowId(queues.getWindowId());
        queues1.setTransact(1);
        List<Queues> queuesList=queueMapper.getQueueList(queues1);
        if (queuesList.size()==0){
            Window window=new Window();
            window.setId(queues.getWindowId());
            queueMapper.updateWindow(window);
        }
        Window window = new Window();
        window.setBusinessId(queues.getBusinessId());
        List<Window> windows = queueMapper.selectWindow(window);
        for (Window window1 : windows) {
            if (window1.getWindowStatus()==1){
                window1.setWindowStatus(0);
                queueMapper.updateWindowStatus(window1);
            }
        }
        Business business=new Business();
        business.setId(queues.getBusinessId());
        Business business1=queueMapper.selectBusiness(business);
        if(business1.getWaitNum()!=0)
            queueMapper.updateBusiness(business);
        resultBean.setSuccess(true);
        resultBean.setMessage("办理成功");
        return resultBean;
    }

    @Override
    public ResultBean repealQueue(Queues queues) throws Exception {
        ResultBean resultBean = new ResultBean();
        resultBean.setData(queueMapper.repealQueue(queues));
        Window window = new Window();
        window.setBusinessId(queues.getBusinessId());
        List<Window> windows = queueMapper.selectWindow(window);
        for (Window window1 : windows) {
            if (window1.getWindowStatus()==1){
                window1.setWindowStatus(0);
                queueMapper.updateWindowStatus(window1);
            }
        }
        resultBean.setSuccess(true);
        resultBean.setMessage("删除成功");
        return resultBean;
    }

    @Override
    public ResultBean overQueue(Queues queues) throws Exception {
        ResultBean resultBean = new ResultBean();
        queueMapper.overQueue(queues);
        Queues queues1=new Queues();
        queues1.setWindowId(queues.getWindowId());
        queues1.setTransact(1);
        List<Queues> queuesList=queueMapper.getQueueList(queues1);
        if (queuesList.size()==0){
            Window window=new Window();
            window.setId(queues.getWindowId());
            queueMapper.updateWindow(window);
        }
        Business business=new Business();
        business.setId(queues.getBusinessId());
        Business business1=queueMapper.selectBusiness(business);
        if(business1.getWaitNum()!=0)
            queueMapper.updateBusiness(business);
        Window window = new Window();
        window.setBusinessId(queues.getBusinessId());
        List<Window> windows = queueMapper.selectWindow(window);
        for (Window window1 : windows) {
            if (window1.getWindowStatus()==1){
                window1.setWindowStatus(0);
                queueMapper.updateWindowStatus(window1);
            }

        }
        resultBean.setSuccess(true);
        resultBean.setMessage("过号成功");
        return resultBean;
    }
}
