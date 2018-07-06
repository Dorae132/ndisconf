package com.nsb.ndisconf.nsb_test.config.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.disconf.client.common.annotations.DisconfItem;

/**
 *
 * @author liaoqiqi
 * @version 2014-5-16
 */
@Service
public class BaoBaoService {

    protected static final Logger LOGGER = LoggerFactory
            .getLogger(BaoBaoService.class);

    public static final String key = "moneyInvest";

    private Double moneyInvest = 1000d;

    @Autowired
    private Coefficients coefficients;

    /**
     *
     * @return
     */
    public double calcMoney() {
        return coefficients.getDiscount()
                * getMoneyInvest();
    }

    /**
     * 投资的钱，分布式配置 <br/>
     * <br/>
     * 这里切面无法生效，因为SpringAOP不支持。<br/>
     * 但是这里还是正确的，因为我们会将值注入到Bean的值里.
     *
     * @return
     */
    @DisconfItem(key = key)
    public Double getMoneyInvest() {
        return moneyInvest;
    }

    public void setMoneyInvest(Double moneyInvest) {
        this.moneyInvest = moneyInvest;
    }

}
