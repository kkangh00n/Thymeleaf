package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

    //======================= 템플릿 조각 ============================
    @GetMapping("/fragment")
    public String template() {
        return "template/fragment/fragmentMain";
    }

    //======================= 템플릿 레이아웃  =========================
    @GetMapping("/layout")
    public String layout() {
        return "template/layout/layoutMain";
    }
    //======================= 템플릿 레이아웃 확장 =========================
    @GetMapping("/layoutExtend")
    public String layoutExtends() {
        return "template/layoutExtend/layoutExtendMain";
    }
}
