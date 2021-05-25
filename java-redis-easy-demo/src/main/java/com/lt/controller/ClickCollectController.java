package com.lt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangtao
 * @description 点击测试controller,用于收集接口的点击
 * @date 2021年05月25 10:50
 **/
@RestController
public class ClickCollectController {
    @GetMapping("clickA")
    public String clickCollectA() {
        return "success";
    }

    @GetMapping("clickB")
    public String clickCollectB() {
        return "success";
    }

    @GetMapping("clickC")
    public String clickCollectC() {
        return "success";
    }

    @GetMapping("clickD")
    public String clickCollectD() {
        return "success";
    }

    @GetMapping("clickE")
    public String clickCollectE() {
        return "success";
    }

}
