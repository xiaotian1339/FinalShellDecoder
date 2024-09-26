package com.godwin.finalshellcreak.controller;

import com.alibaba.fastjson2.JSON;
import com.godwin.finalshellcreak.pojo.FinalShellConfig;
import com.godwin.finalshellcreak.service.DecoderService;
import com.godwin.finalshellcreak.util.FinalShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {

    @Autowired
    private DecoderService decoderService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("decode")
    @ResponseBody
    public String decode(@RequestBody FinalShellConfig s) {
        try {
            return decoderService.decode(s);
        } catch (Exception e) {
            e.printStackTrace();
            return "解码失败";
        }
    }

    @PostMapping("generateActiveCode")
    @ResponseBody
    public String generateActiveCode(@RequestBody String code) {
        return JSON.toJSONString(FinalShellUtil.getActiveCode(code));
    }


}
