package com.sln.job;

import com.sln.core.ServiceResult;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.service.cart.ICartService;
import com.sln.service.member.IMemberBalancePayLogService;
import com.sln.service.member.IMemberGradeDownLogsService;
import com.sln.service.member.IMemberProductBackService;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductFrontService;
import com.sln.service.promotion.IActBiddingService;
import com.sln.service.search.ISolrProductService;
import com.sln.service.seller.ISellerService;
import com.sln.service.settlement.ISettlementService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

public class AdminJob {
    private static final Logger         log = LogManager.getLogger(AdminJob.class);
    @Resource
    private     ISettlementService          settlementService;
    @Resource
    private IOrdersService              ordersService;
    @Resource
    private ICartService                cartService;
    @Resource
    private     ISellerService              sellerService;
    @Resource
    private     ISolrProductService         solrProductService;
    @Resource
    private     IActBiddingService          actBiddingService;
    @Resource
    private     IMemberGradeDownLogsService memberGradeDownLogsService;
    @Resource
    private     IMemberBalancePayLogService memberBalancePayLogService;
    @Resource
    private     IMemberProductBackService   memberProductBackService;
    @Resource
    private     IProductFrontService        ProductFrontService;

    /**
     * 商家结算账单生成定时任务<br>
     * <li>查询所有商家，每个商家每个结算周期生成一条结算账单
     * <li>计算周期内商家所有的订单金额合计
     * <li>计算所有订单下网单的佣金
     * <li>计算周期内发生的退货退款（当前周期结算的订单如果在下个结算周期才退款，退款结算在下个周期计算）
     * <li>每个商家一个事务，某个商家结算时发生错误不影响其他结算
     */
    public void jobSettlement() {
        log.info("jobSettlement() start");
        ServiceResult<Boolean> jobResult = settlementService.jobSettlement();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error(
                "[sln-admin][AdminJob][jobSettlement] 商家结算账单生成时失败：" + jobResult.getMessage());
        }
        log.info("jobSettlement() end");
    }

    /**
     * 系统自动完成订单<br>
     * <li>对已发货状态的订单发货时间超过15个自然日的订单进行自动完成处理
     */
    public void jobSystemFinishOrder() {
        log.info("jobSystemFinishOrder() start");
        ServiceResult<Boolean> jobResult = ordersService.jobSystemFinishOrder();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobSystemFinishOrder] 系统自动完成订单时失败："
                      + jobResult.getMessage());
        }
        log.info("jobSystemFinishOrder() end");
    }

    /**
     * 系统定时任务清除7天之前添加的购物车数据
     */
    public void jobClearCart() {
        log.info("jobClearCart() start");
        ServiceResult<Boolean> jobResult = cartService.jobClearCart();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobClearCart] 系统定时任务清除7天之前添加的购物车数据时失败："
                      + jobResult.getMessage());
        }
        log.info("jobClearCart() end");
    }

    /**
     * 系统定时更新solr索引
     */
    public void jobSearchSolr() {
        log.info("jobSearchSolr() start");
        String solrUrl = DomainUrlUtil.getSLN_SOLR_URL();
        String solrServer = DomainUrlUtil.getSLN_SOLR_SERVER();
        ServiceResult<Boolean> jobResult = solrProductService.jobCreateIndexesSolr(solrUrl,
            solrServer);
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobSearchSolr] 系统定时任务定时生成Solr索引失败："
                      + jobResult.getMessage());
        }
        log.info("jobSearchSolr() end");
    }

    /**
     * 更新敏感词的索引值
     */
    public void jobUpdateSearchRecordIndex() {
        log.info("jobUpdateSearchRecordIndex() start");
        String solrUrl = DomainUrlUtil.getSLN_SOLR_URL();
        String solrServer = DomainUrlUtil.getSLN_SOLR_SERVER();
        ServiceResult<Boolean> jobResult = solrProductService.jobUpdateSearchRecordIndex(solrUrl,
            solrServer);
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobUpdateSearchRecordIndex] 系统定时任务定时更新敏感词的索引值："
                      + jobResult.getMessage());
        }
        log.info("jobUpdateSearchRecordIndex() end");
    }

    /**
     * 定时任务设定商家的评分，用户评论各项求平均值设置为商家各项的综合评分
     * @return
     */
    public void jobSetSellerScore() {
        log.info("jobSetSellerScore() start");
        ServiceResult<Boolean> jobResult = sellerService.jobSetSellerScore();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobSetSellerScore] 定时任务设定商家的评分时失败："
                      + jobResult.getMessage());
        }
        log.info("jobSetSellerScore() end");
    }

    /**
     * 系统自动取消24小时没有付款订单
     * @return
     */
    public void jobSystemCancelOrder() {
        log.info("jobSystemCancelOrder() start");
        ServiceResult<Boolean> jobResult = ordersService.jobSystemCancelOrder();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobSystemCancelOrder] 定时任务系统自动取消24小时没有付款订单时失败："
                      + jobResult.getMessage());
        }
        log.info("jobSystemCancelOrder() end");
    }

    /**
     * 定时任务设定商家各项统计数据
     * @return
     */
    public void jobSellerStatistics() {
        log.info("jobSellerStatistics() start");
        ServiceResult<Boolean> jobResult = sellerService.jobSellerStatistics();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobSellerStatistics] 定时任务设定商家各项统计数据时失败："
                      + jobResult.getMessage());
        }
        log.info("jobSellerStatistics() end");
    }

    /**
     * 集合竞价定时器，结束活动，生成尾款订单
     */
    public void jobBiddingBidding() {
        log.info("jobBiddingBidding() start");
        ServiceResult<Boolean> jobResult = actBiddingService.jobBiddingService();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobBiddingService] 集合竞价定时器，结束活动，生成尾款订单时失败："
                      + jobResult.getMessage());
        }
        log.info("jobBiddingBidding() end");
    }

    /**
     * 每天执行一次，对每年当天注册的会员减去相应的经验值数量，影响会员等级（处理完成后检查前2天的执行情况防止服务器维护等原因导致的定时任务未执行）
     */
    public void jobGradeValueDown() {
        log.info("jobGradeValueDown() start");
        ServiceResult<Boolean> jobResult = memberGradeDownLogsService.jobGradeValueDown();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobGradeValueDown] 执行会员年度经验值递减任务时失败："
                      + jobResult.getMessage());
        }
        log.info("jobGradeValueDown() end");
    }

    /*
    *
    * 每天执行一次，每天将需要退款的订单保存，将相关信息，以邮件的形式发送。
    * */
    public void jobProductBackOrder(){
        log.info("jobProductBackOrder() start");
        ServiceResult<Boolean> jobResult = memberProductBackService.jobBackOrder();
        if (!jobResult.getSuccess() || jobResult.getResult() == null||!jobResult.getResult()) {
            log.error("[sln-admin][AdminJob][jobProductBackOrder] 执行保存退货订单任务时失败："
                      + jobResult.getMessage());
        }
        log.info("jobProductBackOrder() end");
    }
    /*
    *每天执行一次，将到期的商品下架
    * */
    public void jobProductDown(){
        log.info("jobProductDown() start");
        ServiceResult<Boolean> jobResult=ProductFrontService.jobProductDown();
        if(!jobResult.getSuccess()|| jobResult.getResult()==null||!jobResult.getResult()){
            log.error("[sln-admin][AdminJob][jobProductDown]检查商品下架失败"+jobResult.getMessage());
        }
        log.info("jobProductDown() end");
    }
}
