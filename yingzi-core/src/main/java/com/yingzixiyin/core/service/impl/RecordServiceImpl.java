package com.yingzixiyin.core.service.impl;

import com.google.common.collect.Lists;
import com.yingzixiyin.api.dto.ConsultantRecordsInfo;
import com.yingzixiyin.api.dto.RecordInfo;
import com.yingzixiyin.api.enums.ConsultTypeEnum;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.enums.YesOrNoEnum;
import com.yingzixiyin.core.dao.RecordDao;
import com.yingzixiyin.core.entity.Record;
import com.yingzixiyin.core.service.RecordService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-02
 */

@Service("recordService")
public class RecordServiceImpl implements RecordService {
    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class.getName());

    @Resource
    RecordDao recordDao;

    @Override
    public Integer insert(Record record) {
        if (null == record || null == record.getUserId() || null == record.getConsultantId() ) {
            logger.info("record内容不能为空");
            return null;
        }
        return recordDao.insert(record);
    }

    @Override
    public void update(Record record) {
        if (null == record || null == record.getId()) {
            logger.info("id不能为空");
            return ;
        }
        recordDao.update(record);
    }

    @Override
    public RecordInfo getRecord(Record record) {
        if (null == record) {
            logger.info("record不能为null");
            return null;
        }
        Record result = recordDao.getRecord(record);
        return Record.translateBean(result);
    }

    @Override
    public List<RecordInfo> getRecordList(Record record) {
        if (null == record) {
            logger.info("record不能为null");
            return null;
        }
        List<Record> consultantList = recordDao.getRecordList(record);
        return Record.translateBeanList(consultantList);
    }

	@Override
	public Long queryCount(Record bean) {
		return recordDao.queryCount(bean);
	}

	@Override
	public List<ConsultantRecordsInfo> queryConsultantRecordsListPage(
			Map<String, Object> map) {
		List<Map<String, Object>> list=recordDao.queryConsultantRecordsListPage(map);
//		logger.info("得到查询咨询记录结果:"+list);
		List<ConsultantRecordsInfo> resList=Lists.newArrayList();
		for(Map<String,Object> bean:list){
			resList.add(translateBean(bean));
		}
//		logger.info("得到list:"+resList);
		return resList;
	}

	private ConsultantRecordsInfo translateBean(Map<String, Object> bean) {
		ConsultantRecordsInfo cinfo=new ConsultantRecordsInfo();
		cinfo.setAvatar(bean.get("avatar")+"");
		cinfo.setConsultantId(Long.parseLong(bean.get("consultantId")+""));
		cinfo.setConsultantName(bean.get("consultantName")+"");
		cinfo.setConsultType(ConsultTypeEnum.toEnum((Integer)bean.get("consultType")));
		cinfo.setCreateTime((Date)bean.get("createTime"));
		cinfo.setId(Long.parseLong(bean.get("id")+""));
		cinfo.setIntroduce(bean.get("introduce")+"");
		cinfo.setIsCompleted(YesOrNoEnum.toEnum((Integer)bean.get("isCompleted")));
		cinfo.setIsPaid(YesOrNoEnum.toEnum((Integer)bean.get("isPaid")));
		cinfo.setIsReplied(YesOrNoEnum.toEnum((Integer)bean.get("isReplied")));
		cinfo.setPrice(new BigDecimal(bean.get("price")+""));
		cinfo.setRangeType(RangeTypeEnum.toEnum((Integer)bean.get("rangeType")));
		cinfo.setSignature(bean.get("signature")+"");
		cinfo.setUserId((Long)bean.get("userId"));
//		logger.info("转换实体类："+cinfo);
		return cinfo;
	}
}
