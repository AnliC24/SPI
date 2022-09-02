package com.hitqz.sjtc.core.dict;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author windc
 * 用户级字典
 * */

public final class UserDataDict {


    public static final String ROLE_VERSION = "two";

    public static final String STATUS_VERSION = "one";

    public static final String USER_PREFIX = "user";

    public static final String ROLE_TYPE = "role";

    public static final String STATUS_TYPE = "status";

    //角色池
    private final static ConcurrentMap<String,SysDataDict> userRepo = new ConcurrentHashMap<>(10);

    public static SysDataDict illegalAdmin = new SysDataDict("00","非法用户","user_role","用户角色");

    public static SysDataDict superAdmin =
            new SysDataDict("01", "超级管理员", "user_role", "用户角色");

    public static SysDataDict operationAdmin =
            new SysDataDict("02", "作业人员", "user_role", "用户角色");

    public static SysDataDict userStatusByBlock =
            new SysDataDict("01", "停用", "user_status", "用户状态");

    public static SysDataDict userStatusByNormal =
            new SysDataDict("02", "正常", "user_status", "用户状态");

    static {
        userRepo.put("user-roleModel-two-01",superAdmin);
        userRepo.put("user-roleModel-two-02",operationAdmin);
    }

    //对外提供获取池信息
    public static Map getUserRepo(){
        return userRepo;
    }

    //对外提供更新池信息功能
    public static boolean updateUserRepo(String key,SysDataDict update){
        if(!userRepo.containsKey(key)){
            return false;
        }
        SysDataDict userDict = userRepo.replace(key,update);
        return Optional.of(userDict).isPresent();
    }

    //对外提供批量更新信息功能
    public static boolean batchUpdateRepo(Map<String,SysDataDict> batch){
        if(batch == null || batch.size() == 0){
            return false;
        }
        batch.keySet().forEach(item -> {
            SysDataDict update = batch.get(item);
            updateUserRepo(item,update);
        });
        return true;
    }

    //根据给定的dictCode和dictType 返回SysDataDict对象
    public static SysDataDict getUserDict(String version,String dictType,String dictCode){
        StringBuilder builder = new StringBuilder();
        builder.append(USER_PREFIX+dictType+"Model-"+version+"-"+dictCode);
        if(!userRepo.containsKey(builder)){
            throw new NullPointerException("未找到对应对象");
        }
        return userRepo.get(builder);
    }


}
