package com.yingzixiyin.core.facade;

import com.yingzixiyin.api.dto.BaseResponseDto;
import com.yingzixiyin.api.dto.ConsultantInfo;
import com.yingzixiyin.api.dto.ConsultantQueryRequestDto;
import com.yingzixiyin.api.dto.ConsultantQueryResponseDto;
import com.yingzixiyin.api.enums.RangeTypeEnum;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.core.BaseTest;
import com.yingzixiyin.page.Pagination;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class ConsultantFacadeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ConsultantFacadeTest.class.getName());

    @Resource
    ConsultantFacade consultantFacade;

   @Test
    public void addTest() {
//        ConsultantInfo info = new ConsultantInfo();
//        info.setPhone("13121435540");
//        info.setPassword("123123");
//        info.setAge(25);
//        BaseResponseDto responseDto =  consultantFacade.add(info);
//        logger.info(responseDto.toString());
    }

    @Test
    public void updateTest() {
        ConsultantInfo info = new ConsultantInfo();
        info.setId(1L);
        info.setVideoPrice(new BigDecimal("150"));
        info.setFacePrice(new BigDecimal("300"));
        BaseResponseDto responseDto =  consultantFacade.update(info);
        logger.info(responseDto.toString());
    }

    @Test
    public void getConsultantListTest() {
        ConsultantQueryRequestDto requestDto = new ConsultantQueryRequestDto();
        requestDto.setRangeType(RangeTypeEnum.ONE);
        ConsultantQueryResponseDto responseDto =  consultantFacade.query(requestDto);
        logger.info(responseDto.toString());
    }

    @Test
    public void getConsultantListByIdsTest() {
        String ids = "1 ,2, 3,4 ,5";
        ConsultantQueryResponseDto responseDto =  consultantFacade.queryByIds(ids);
        logger.info(responseDto.toString());
    }
    @Test
    public void queryPage(){
    	logger.info("-----查询分页结果---");
    	int pageNum=4;
    	ConsultantQueryRequestDto requestDto = new ConsultantQueryRequestDto();
    	Long count=consultantFacade.queryCount(requestDto);
    	logger.info("-----查询分页结果的总数:"+count+"---");
    	Pagination page=new Pagination();
    	page.setMaxCountAndCurrentPage(count,pageNum);
    	ConsultantQueryResponseDto responseDto=consultantFacade.queryPage(requestDto, page);
    	
    	logger.info(responseDto.toString());
    }
}
