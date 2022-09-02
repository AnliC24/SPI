package com.hitqz.sjtc.controller;

import com.hitqz.sjtc.config.GroovyScriptConfig;
import com.hitqz.sjtc.constant.ipage.IpageConstant;
import com.hitqz.sjtc.core.model.PageCondition;
import com.hitqz.sjtc.core.model.ResultObj;
import groovy.lang.Script;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public interface PageTemplate {

    default ResultObj pageTemplate(PageCondition param)  {

        try {
            Script script = GroovyScriptConfig.createScriptByScriptName("ValidatePage.groovy");
            Object[] validateParam = new Object[]{param.getCurrentPage(), param.getPageSize()};

            boolean result = (boolean) script.invokeMethod("validatePageParam", validateParam);

            if (!result) {
                return ResultObj.fail(IpageConstant.PAGE_ERROR);
            }

            return executePage(param);
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return ResultObj.fail("分页失败,请查看服务端日志");
    }

    ResultObj executePage(PageCondition param);
}
