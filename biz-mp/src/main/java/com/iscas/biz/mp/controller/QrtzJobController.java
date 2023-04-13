package com.iscas.biz.mp.controller;

import com.iscas.biz.mp.aop.enable.ConditionalOnQuartz;
import com.iscas.biz.mp.model.QrtzJob;
import com.iscas.biz.mp.service.common.impl.QrtzJobServiceImpl;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.view.table.TableResponse;
import com.iscas.templet.view.table.TableResponseData;
import com.iscas.templet.view.table.TableSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/26 10:27
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
@RestController
@RequestMapping("/qrtz/jobs")
@RequiredArgsConstructor
@ConditionalOnQuartz
public class QrtzJobController extends BaseController {

    private final QrtzJobServiceImpl jobService;


    /**
     * 查询列表数据
     *
     * @param request 查询条件
     * @return ResponseEntity
     */
    @PostMapping(value = "/data")
    public ResponseEntity listData(@RequestBody TableSearchRequest request) {
        TableResponseData tableResponseData = jobService.select(request);
        return new TableResponse().setValue(tableResponseData);
    }

    /**
     * 更新
     *
     * @param job 定时任务
     * @return ResponseEntity
     */
    @PutMapping(value = "/renew")
    @ResponseBody
    public ResponseEntity update(@RequestBody QrtzJob job) throws BaseException {
        jobService.renew(job);
        return getResponse();
    }

    /**
     * 删除
     *
     * @param id 定时任务的ID
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/{id}/del")
    public ResponseEntity delete(@PathVariable Integer id) throws BaseException {
        jobService.delete(id);
        return getResponse();
    }


    /**
     * 启动
     *
     * @param id 定时任务ID
     * @return ResponseEntity
     * @throws BaseException 异常
     */
    @PutMapping(value = "{id}/startup")
    public ResponseEntity start(@PathVariable Integer id) throws BaseException {
        jobService.startJob(id);
        return getResponse();
    }

    /**
     * 暂停
     *
     * @param id 任务ID
     * @return BaseException 异常
     */
    @PutMapping(value = "{id}/paused")
    public ResponseEntity pasue(@PathVariable Integer id) throws BaseException {
        jobService.pauseJob(id);
        return getResponse();
    }

    /**
     * 立即执行
     *
     * @param id 定时任务id
     * @return BaseException 异常
     */
    @PutMapping(value = "{id}/trigger")
    public ResponseEntity trigger(@PathVariable Integer id) throws BaseException {
        jobService.trigger(id);
        return getResponse();
    }

    /**
     * 判断定时器是否为待机模式
     */
    @RequestMapping(value = "/inStandbyMode")
    @ResponseBody
    public ResponseEntity<Boolean> isInStandbyMode() throws BaseException {
        boolean isInStandbyMode = jobService.isInStandbyMode();
        return getResponse().setValue(isInStandbyMode);
    }

    /**
     * 启动定时器
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/scheduler/start")
    @ResponseBody
    public ResponseEntity startScheduler() throws BaseException {
        jobService.startScheduler();
        return getResponse();
    }

    /**
     * 待机定时器
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/scheduler/standby")
    @ResponseBody
    public ResponseEntity standbyScheduler() throws BaseException {
        jobService.standbyScheduler();
        return getResponse();
    }
}
