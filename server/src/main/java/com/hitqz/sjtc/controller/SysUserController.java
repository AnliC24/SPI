                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 public ResultObj deleteById(Integer id) {

        if (!Optional.ofNullable(id).isPresent()) {
            return ResultObj.fail("id不能为空");
        }

        SysUser user = sysUserService.getById(id);

        if (user.getRoleType().equalsIgnoreCase(UserDataDict.superAdmin.getDictCode())) {
            return ResultObj.fail("无权停用超级管理账户");
        }
        //将用户的状态变更为停用
        user.setUserStatus(UserDataDict.userStatusByBlock.getDictCode());

        if (!sysUserService.updateById(user)) {
            return ResultObj.fail("停用失败");
        }

        return ResultObj.success("停用成功");
    }

    /**
     * 需要对批量删除的id进行验证,
     * 不允许
     * */
    @DeleteMapping("/batchDeleteUser")
    @ApiOperation(value = "根据ID批量停用系统用户信息")
    public ResultObj deleteByIds(@ApiParam(name = "ids",value="用户批量的id eg 1,2,3") String ids) {

        if (!StringUtils.hasLength(ids)) {
            return ResultObj.fail("id不能为空");
        }

        String loginRoleType = ShiroUtils.getUserEntity().getRoleType();

        if(!loginRoleType.equals(UserDataDict.superAdmin.getDictCode())){
            return ResultObj.fail("不允许非超级管理员进行批量停用");
        }
        String[] idStr = ids.split(",");
        List<Integer> delIds = new ArrayList<>();

        for(String id : idStr){
            Integer idI = Integer.valueOf(id);
            delIds.add(idI);
        }

        List<String> roleTypes = sysUserService.getRoleTypeByIds(delIds);

        if(roleTypes.contains(UserDataDict.superAdmin.getDictCode())){
            return ResultObj.fail("批量停用的系统用户信息内,包含超级管理员,请排除");
        }

        int row = sysUserService.updateUserStatus(delIds,UserDataDict.userStatusByBlock.getDictCode(),new Date());

        if(row == 0){
            return ResultObj.fail("批量停用失败");
        }

        return ResultObj.success("批量停用成功");
    }

    /**
     * 获取用户详情
     * */
    @GetMapping("/getUserById")
    @ApiOperation(value = "根据ID获取用户信息")
    public ResultObj getById(@RequestParam("id") Integer id) {

        if (!Optional.ofNullable(id).isPresent()) {
            return ResultObj.fail("id不能为空");
        }

        SysUserDto user = sysUserService.getSysUserById(id);

        if (!Optional.ofNullable(user).isPresent()) {
            return ResultObj.fail("信息不存在");
        }

        return ResultObj.success().withData(user);
    }

}
