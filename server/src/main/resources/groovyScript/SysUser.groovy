package groovyScript

import com.hitqz.zhxf.core.model.ResultObj
import com.hitqz.zhxf.dto.user.QueryUserParam
import com.hitqz.zhxf.dto.user.SysUserPageCondition
import com.hitqz.zhxf.util.ShiroUtils
import org.apache.shiro.util.StringUtils

def static fillQuerySysUserParam(QueryUserParam queryUserParam, SysUserPageCondition param) {

    /**
     * 只能查询当前用户的下属一级用户
     * */
    if (!Optional.ofNullable(ShiroUtils.getUserId()).isPresent()) {
        return ResultObj.fail("请先登录！")
    } else {
        //queryWrapper.in(TableOrFieldNameByMysql.SUPERIOR_USER_ID, ShiroUtils.getUserId());
        /**
         * 2022-04-21 取消只查询一层结构的限制，改成查询当前登录用户下的所有子用户
         * */
        queryUserParam.setUserId(ShiroUtils.getUserId())
    }
    /**
     * 角色类型
     * */
    if (StringUtils.hasLength(param.getUserRole())) {
        queryUserParam.setUserRole(param.getUserRole())
    }
}






