package com.nsb.ndisconf.server.dsp.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nsb.ndisconf.server.dsp.common.form.RequestListBase.Page;
import com.nsb.ndisconf.server.ub.common.db.DaoPage;
import com.nsb.ndisconf.server.unbiz.common.genericdao.operator.Order;

/**
 * DAO有用的Utils
 *
 * @author liaoqiqi
 */
public class DaoUtils {

    /**
     * 将业务的Page转成Dao的Page
     *
     * @param page
     *
     * @return
     */
    public static DaoPage daoPageAdapter(Page page) {

        DaoPage daoPage = new DaoPage();

        daoPage.setPageNo(page.getPageNo());
        daoPage.setPageSize(page.getPageSize());

        List<Order> orderList = new ArrayList<Order>();
        if (!StringUtils.isEmpty(page.getOrderBy())) {
            Order order = new Order(page.getOrderBy(), page.isAsc());
            orderList.add(order);
        }
        daoPage.setOrderList(orderList);

        return daoPage;
    }
}
