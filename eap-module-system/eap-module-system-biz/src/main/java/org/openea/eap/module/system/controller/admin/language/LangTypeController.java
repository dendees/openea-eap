package org.openea.eap.module.system.controller.admin.language;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - 语言类型")
@RestController
@RequestMapping("/system/lang-type")
@Validated
public class LangTypeController {
}
