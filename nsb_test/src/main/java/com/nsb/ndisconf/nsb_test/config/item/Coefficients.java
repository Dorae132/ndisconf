package com.nsb.ndisconf.nsb_test.config.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfItem;

/**
 * 金融系数文件
 *
 **/
@Service
public class Coefficients {

    public static final String key = "discountRate";

    @Value(value = "2.0d")
    private Double discount;


    /**
     * 折扣率，分布式配置
     *
     * @return
     */
    @DisconfItem(key = key)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
    public String toString() {
    	return String.valueOf(discount);
    }
}