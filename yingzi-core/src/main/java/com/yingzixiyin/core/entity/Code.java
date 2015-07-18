package com.yingzixiyin.core.entity;

import com.yingzixiyin.api.dto.CodeInfo;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 咨询师
 * @author song.shi
 * @date 2015-07-02
 */

@Alias("Code")
public class Code {

    private Long id;               // 主键id
    private String phone;          // 电话
    private String code;           // 验证码
    private Date updateTime;       // 更新时间


    public static Code getBean(CodeInfo info) {
        if (null == info) {
            return null;
        }
        Code bean = new Code();
        bean.setId(info.getId());
        bean.setPhone(info.getPhone());
        bean.setCode(info.getCode());
        bean.setUpdateTime(info.getUpdateTime());
        return bean;
    }

    /**
     * 将bean类型转换成api的dto中对应的的类型
     * @param bean 需要转换的对象
     * @return 返回对应的类型
     */
    public static CodeInfo translateBean(Code bean) {
        if (null == bean) {
            return null;
        }
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setId(bean.getId());
        codeInfo.setPhone(bean.getPhone());
        codeInfo.setCode(bean.getCode());
        codeInfo.setUpdateTime(bean.getUpdateTime());
        return codeInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
