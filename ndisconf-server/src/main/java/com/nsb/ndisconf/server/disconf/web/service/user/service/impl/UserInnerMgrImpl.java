package com.nsb.ndisconf.server.disconf.web.service.user.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nsb.ndisconf.server.disconf.web.service.user.dto.Visitor;
import com.nsb.ndisconf.server.disconf.web.service.user.service.UserInnerMgr;
import com.nsb.ndisconf.server.ub.common.commons.ThreadContext;

/**
 * @author knightliao
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserInnerMgrImpl implements UserInnerMgr {

    protected static final Logger LOG = LoggerFactory.getLogger(UserInnerMgrImpl.class);

    @Override
    public Visitor getVisitor(Long userId) {

        if (userId == null || userId <= 0) {
            LOG.error("userId is null or <= 0, return null");
            return null;
        }

        return null;
    }

    /**
     *
     */
    @Override
    public Set<Long> getVisitorAppIds() {

        Visitor visitor = ThreadContext.getSessionVisitor();
        return visitor.getAppIds();
    }
}
