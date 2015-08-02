package com.yingzixiyin.api.facade;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.page.Pagination;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public interface ConsultantFacade {

    /**
     * 增加，id字段不要填写
     *
     * @param consultantInfo 注册的信息
     * @return 结果
     */
    public BaseResponseDto add(ConsultantInfo consultantInfo);

    /**
     * 修改，id字段必需，修改什么字段就填什么字段，不修改的不要填
     *
     * @param consultantInfo 新信息
     * @return 结果
     */
    public BaseResponseDto update(ConsultantInfo consultantInfo);

    /**
     * 查询一个结果
     * @param requestDto 查询条件
     * @return ConsultantInfo
     */
    public ConsultantInfo queryOne(ConsultantQueryRequestDto requestDto);

    /**
     * 通过分类获取咨询师列表
     * @param requestDto 按照所填字段去查询
     * @return ConsultantQueryResponseDto
     */
    public ConsultantQueryResponseDto query(ConsultantQueryRequestDto requestDto);

    /**
     * 通过id列表查询咨询师
     *
     * @param ids id列表字符串，用","隔开
     * @return ConsultantQueryResponseDto
     */
    public ConsultantQueryResponseDto queryByIds(String ids);
    /**
     * 分页查询的count
     * @param requestDto
     * @return
     */
	public Long queryCount(ConsultantQueryRequestDto requestDto);
	/**
	 * 分页查询的list
	 * @param requestDto
	 * @param page
	 * @return
	 */
	public ConsultantQueryResponseDto queryPage(
			ConsultantQueryRequestDto requestDto, Pagination page);
	/**
	 * 删除咨询师接口
	 * @param rcrDto
	 * @return
	 */
	public Integer delete(ConsultantQueryRequestDto rcrDto);
}
