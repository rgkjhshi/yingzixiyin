package com.yingzi.admin.view;

import com.google.common.collect.Lists;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.enums.GenderTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.StatusEnum;
import org.apache.ibatis.type.Alias;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 咨询师
 *
 * @author song.shi
 * @date 2015-07-02
 */

@Alias("Consultant")
public class ConsultantView {

    private Long id;               // 主键id
    private String phone;          // 电话
    private String email;          // 邮箱
    private String password;       // 密码
    private String nickname;       // 昵称
    private String name;           // 姓名
    private Integer gender;        // 性别
    private Integer age;           // 年龄
    private String address;        // 咨询地址
    private String alipay;         // 支付宝账号
    private String professional;   // 专业背景
    private String background;     // 受训背景
    private String bookTime;       // 可预定时间
    private BigDecimal price;      // 咨询单价
    private String introduce;      // 个人简介
    private String signature;      // 个性签名
    private Integer rangeType;     // 咨询类型
    private String avatar;         // 头像url
    private Integer status;        // 状态

    public static ConsultantView getBean(ConsultantInfo info) {
        if (null == info) {
            return null;
        }
        ConsultantView consultant = new ConsultantView();
        consultant.setId(info.getId());
        consultant.setPassword(info.getPassword());
        consultant.setPhone(info.getPhone());
        consultant.setEmail(info.getEmail());
        consultant.setNickname(info.getNickname());
        consultant.setName(info.getName());
        consultant.setGender(null == info.getGender() ? null : info.getGender().getValue());
        consultant.setAge(info.getAge());
        consultant.setAddress(info.getAddress());
        consultant.setAlipay(info.getAlipay());
        consultant.setProfessional(info.getProfessional());
        consultant.setBackground(info.getBackground());
        consultant.setBookTime(info.getBookTime());
        consultant.setPrice(info.getPrice());
        consultant.setIntroduce(info.getIntroduce());
        consultant.setSignature(info.getSignature());
        consultant.setRangeType(null == info.getRangeType() ? null : info.getRangeType().getValue());
        consultant.setAvatar(info.getAvatar());
        consultant.setStatus(null == info.getStatus() ? null : info.getStatus().getValue());
        return consultant;
    }

    public static ConsultantView getBean(ConsultantQueryRequestDto requestDto) {
        if (null == requestDto) {
            return null;
        }
        ConsultantView consultant = new ConsultantView();
        consultant.setId(requestDto.getId());
        consultant.setPassword(requestDto.getPassword());
        consultant.setPhone(requestDto.getPhone());
        consultant.setEmail(requestDto.getEmail());
        consultant.setName(requestDto.getName());
        consultant.setGender(null == requestDto.getGender() ? null : requestDto.getGender().getValue());
        consultant.setRangeType(null == requestDto.getRangeType() ? null : requestDto.getRangeType().getValue());
        consultant.setStatus(null == requestDto.getStatus() ? null : requestDto.getStatus().getValue());
        return consultant;
    }

    /**
     * 将bean类型转换成api的dto中对应的的类型
     *
     * @param consultant 需要转换的对象
     * @return 返回对应的类型
     */
    public static ConsultantInfo translateBean(ConsultantView consultant) {
        if (null == consultant) {
            return null;
        }
        ConsultantInfo consultantInfo = new ConsultantInfo();
        consultantInfo.setId(consultant.getId());
        consultantInfo.setPhone(consultant.getPhone());
        consultantInfo.setAlipay(consultant.getAlipay());
        consultantInfo.setEmail(consultant.getEmail());
        consultantInfo.setNickname(consultant.getNickname());
        consultantInfo.setName(consultant.getName());
        consultantInfo.setGender(null == consultant.getGender() ? GenderTypeEnum.UNKNOWN : GenderTypeEnum.toEnum(consultant.getGender()));
        consultantInfo.setAge(consultant.getAge());
        consultantInfo.setAddress(consultant.getAddress());
        consultantInfo.setProfessional(consultant.getProfessional());
        consultantInfo.setBackground(consultant.getBackground());
        consultantInfo.setBookTime(consultant.getBookTime());
        consultantInfo.setPrice(consultant.getPrice());
        consultantInfo.setIntroduce(consultant.getIntroduce());
        consultantInfo.setSignature(consultant.getSignature());
        consultantInfo.setAvatar(consultant.getAvatar());
        consultantInfo.setRangeType(null == consultant.getRangeType() ? RangeTypeEnum.ONE : RangeTypeEnum.toEnum(consultant.getRangeType()));
        consultantInfo.setStatus(null == consultant.getStatus() ? StatusEnum.INIT : StatusEnum.toEnum(consultant.getStatus()));
        return consultantInfo;
    }

    /**
     * 将bean的list转换成api的dto中对应的的类型的list
     *
     * @param consultantList 需要转换的beanList
     * @return 对应的类型的list
     */
    public static List<ConsultantInfo> translateBeanList(List<ConsultantView> consultantList) {
        if (CollectionUtils.isEmpty(consultantList)) {
            return null;
        }
        List<ConsultantInfo> consultantInfoList = Lists.newArrayList();
        for (ConsultantView consultant : consultantList) {
            consultantInfoList.add(translateBean(consultant));
        }
        return consultantInfoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getRangeType() {
        return rangeType;
    }

    public void setRangeType(Integer rangeType) {
        this.rangeType = rangeType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", alipay='" + alipay + '\'' +
                ", professional='" + professional + '\'' +
                ", background='" + background + '\'' +
                ", bookTime='" + bookTime + '\'' +
                ", price=" + price +
                ", introduce='" + introduce + '\'' +
                ", signature='" + signature + '\'' +
                ", rangeType=" + rangeType +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                '}';
    }
}
