package com.sln.service.message;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.member.Member;
import com.sln.entity.message.Message;

public interface IMessageService {

	/**
     * 根据id取得消息记录表
     * @param  messageId
     * @return
     */
    ServiceResult<Message> getMessageById(Integer messageId);
    
    /**
     * 根据messageCode取得消息记录表
     * @param  messageCode
     * @return
     */
    ServiceResult<Message> getMessageByMessageCode(String messageCode);
    
    /**
     * 保存消息记录表
     * @param  message
     * @return
     */
     ServiceResult<Integer> saveMessage(Message message);
     
     /**
     * 更新消息记录表
     * @param  message
     * @return
     */
     ServiceResult<Integer> updateMessage(Message message);
     
     /**
      * 消息分页
      * @param queryMap
      * @param pager
      * @return
      */
     ServiceResult<List<Message>> page(Map<String, String> queryMap, PagerInfo pager);
     
     /**
      * 删除
      * @param id
      * @return
      */
     ServiceResult<Boolean> del(Integer id);
     
     /**
      * 发送消息到用户
      * @param queryMap		参数
      * @param member		接收用户
      * @param messageCode  消息唯一标识
      *  DDFHTZ:订单发货通知  需要参数 time 购买时间 productName 商品名称
      *  DDQXTZ:订单取消通知 需要参数 time 购买时间 orderNo 订单号
      *  QHSPSHTG: 缺货商品审核通过  memberName 用户名称 productName 商品名称 productCode 商品编号  productAddress 商品地址
      *  QHSPSHSB: 缺货商品审核失败  memberName 用户名称 productName 商品名称 productCode 商品编号  retroactionReason 失败原因
      * @return
      */
     ServiceResult<Boolean> sendMessageToMessage(Map<String, Object> queryMap,Integer memberId,String messageCode);

	
}