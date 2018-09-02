package com.taotao.store.order.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.taotao.store.order.bean.Where;
import com.taotao.store.order.mapper.OrderMapper;
import com.taotao.store.order.pojo.Order;
import com.taotao.store.order.pojo.PageResult;
import com.taotao.store.order.pojo.ResultMsg;

/**
 * mysql版本的实现
 * 
 */

//此处没有使用注解  使用配置 方便后期修改
//之前的业务直接使用mapper  此处添加dao层  方便后期数据库修改
public class OrderDAO implements IOrder {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void createOrder(Order order) {
        this.orderMapper.save(order);
    }

    @Override
    public Order queryOrderById(String orderId) {
        return this.orderMapper.queryByID(orderId);
    }


//    另外一款分页插件 很古老的分页插件  自己替换成分页助手就可以了
    public PageResult<Order> queryOrderByUserNameAndPage(String buyerNick, Integer page, Integer count) {
        PageBounds bounds = new PageBounds();
        bounds.setContainsTotalCount(true);
        bounds.setLimit(count);
        bounds.setPage(page);
//        设置排序
        bounds.setOrders(com.github.miemiedev.mybatis.paginator.domain.Order.formString("create_time.desc"));
        
        PageList<Order> list = this.orderMapper
                .queryListByWhere(bounds, Where.build("buyer_nick", buyerNick));
        return new PageResult<Order>(list.getPaginator().getTotalCount(), list);
    }

    @Override
    public ResultMsg changeOrderStatus(Order order) {
        try {
            order.setUpdateTime(new Date());
            this.orderMapper.update(order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg("500", "更新订单出错!");
        }
        return new ResultMsg("200", "更新成功!");
    }
    
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

}
