package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.dto.Exposer;
import com.jk.dto.SeckillExecution;
import com.jk.dto.SeckillResult;
import com.jk.model.Members;
import com.jk.model.Seckill;
import com.jk.enums.SeckillStatEnum;
import com.jk.exception.RepeatKillException;
import com.jk.exception.SeckillCloseException;
import com.jk.exception.SeckillException;
import com.jk.model.SeckillOrder;
import com.jk.service.SeckillService;
import com.sun.net.httpserver.HttpsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 秒杀商品的控制层
 *
 * @auther TyCoding
 * @date 2018/10/6
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Reference
    private SeckillService seckillService;
    /*@Autowired
    private AmqpTemplate amqpTemplate;*/

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/list")
    public String findSeckillList(Model model) throws ParseException {
        List<Seckill> list = seckillService.findAll();
        for (Seckill seckill :list) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime =format.format(format.parse(seckill.getStarttime()));
            String endTime = format.format(format.parse(seckill.getEndtime()));
            seckill.setStarttime(startTime);
            seckill.setEndtime(endTime);
        }

        model.addAttribute("list", list);
        return "page/seckill";
    }

    @ResponseBody
    @RequestMapping("findById")
    public Seckill findById(@RequestParam("id") Long id) {
        return seckillService.findById(id);
    }

    @RequestMapping("/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model, HttpServletRequest request) {
        Members members = (Members) request.getSession().getAttribute("members");
        if(members==null){
            return "redirect:/toshow/tofrontLogin";
        }
        if (seckillId == null) {
            return "page/seckill";
        }
        Seckill seckill = seckillService.findById(seckillId);
        model.addAttribute("seckill", seckill);
        if (seckill == null) {
            return "page/seckill";
        }
        return "page/seckill_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @RequestParam("money") BigDecimal money
                                                   ,HttpServletRequest request) {
        Members members = (Members) request.getSession().getAttribute("members");
        Long userPhone=Long.parseLong(members.getPhone());

        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try {
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setUserPhone(userPhone);
            seckillOrder.setMd5(md5);
            seckillOrder.setMoney(money);
            seckillOrder.setSeckillId(seckillId);
           /* SeckillExecution execution = (SeckillExecution) amqpTemplate.convertSendAndReceive(seckillOrder);*/
            SeckillExecution execution = seckillService.executeSeckill(seckillId, money, userPhone, md5,members);
            return new SeckillResult<SeckillExecution>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillCloseException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
    }

    @ResponseBody
    @GetMapping(value = "/time/now")
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult(true, now.getTime());
    }
}
