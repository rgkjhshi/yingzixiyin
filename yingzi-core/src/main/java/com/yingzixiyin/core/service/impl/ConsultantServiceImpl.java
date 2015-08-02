package com.yingzixiyin.core.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.core.dao.ConsultantDao;
import com.yingzixiyin.core.entity.Consultant;
import com.yingzixiyin.core.service.ConsultantService;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Service("consultantService")
public class ConsultantServiceImpl implements ConsultantService {
	private static final Logger logger = LoggerFactory
			.getLogger(ConsultantServiceImpl.class.getName());

	@Resource
	ConsultantDao consultantDao;

	@Override
	public Integer insert(Consultant consultant) {
		if (null == consultant || StringUtils.isEmpty(consultant.getPhone())
				|| StringUtils.isEmpty(consultant.getPassword())) {
			logger.info("phone和password不能为空");
			return null;
		}
		return consultantDao.insert(consultant);
	}

	@Override
	public void update(Consultant consultant) {
		if (null == consultant || null == consultant.getId()) {
			logger.info("id不能为空");
			return;
		}
		consultantDao.update(consultant);
	}

	@Override
	public ConsultantInfo getConsultant(Consultant consultant) {
		if (null == consultant) {
			logger.info("参数consultant为null");
			return null;
		}
		Consultant result = consultantDao.getConsultant(consultant);
		return Consultant.translateBean(result);
	}

	@Override
	public List<ConsultantInfo> getConsultantList(Consultant consultant) {
		if (null == consultant) {
			logger.info("参数consultant不能为null");
			return null;
		}
		List<Consultant> consultantList = consultantDao
				.getConsultantList(consultant);
		return Consultant.translateBeanList(consultantList);
	}

	@Override
	public List<ConsultantInfo> getConsultantList(Map<String, Object> map) {
		if (CollectionUtils.isEmpty(map)) {
			logger.info("map不能为空");
			return null;
		}
		List<Consultant> consultantList = consultantDao
				.getConsultantListByFilter(map);
		return Consultant.translateBeanList(consultantList);
	}

	@Override
	public List<ConsultantInfo> getConsultantList(String ids) {
		if (StringUtils.isEmpty(ids)) {
			logger.info("ids不能为空");
			return null;
		}
		List<String> idList = Splitter.on(",").trimResults().omitEmptyStrings()
				.splitToList(ids);
		List<Consultant> consultantList = consultantDao
				.getConsultantListByIdList(idList);
		return Consultant.translateBeanList(consultantList);
	}

	@Override
	public List<ConsultantInfo> queryConsultantListPage(Map<String, Object> map) {
		if (CollectionUtils.isEmpty(map)) {
			logger.info("map不能为空");
			return null;
		}
		List<Consultant> consultantList = consultantDao.queryConsultantListPage(map);
		return Consultant.translateBeanList(consultantList);
	}

	@Override
	public Long queryCount(Consultant bean) {
		return consultantDao.queryCount(bean);
	}

	@Override
	public Integer delete(Consultant bean) {
		return consultantDao.delete(bean);
	}
}
