package com.yingzixiyin.core.facade;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.yingzixiyin.api.facade.ConsultantFacade;
import com.yingzixiyin.core.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author song.shi
 * @date 2015-07-04
 */

public class UserFacadeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserFacadeTest.class.getName());

    @Resource
    ConsultantFacade consultantFacade;

    @Test
    public void addTest() {
        Set<Long> set = Sets.newHashSet(1L, 2L, 3L);
        logger.info(set.toString());
    }

}
