package com.sln.model.seller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sln.dao.shop.read.seller.SellerEliminateReadDao;
import com.sln.dao.shop.read.seller.SellerParkOperationReadDao;
import com.sln.dao.shop.read.system.SystemAdminReadDao;
import com.sln.dao.shop.write.system.SystemRolesWriteDao;
import com.sln.entity.seller.SellerParkOperation;
import com.sln.entity.system.SystemAdmin;
import com.sln.entity.system.SystemRoles;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sln.core.ConstantsEJS;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.compain.ComplainRegisterReadDao;
import com.sln.dao.shop.read.member.MemberCollectionSellerReadDao;
import com.sln.dao.shop.read.order.OrdersReadDao;
import com.sln.dao.shop.read.product.ProductCommentsReadDao;
import com.sln.dao.shop.read.product.ProductReadDao;
import com.sln.dao.shop.read.seller.SellerReadDao;
import com.sln.dao.shop.write.compain.ComplainRegisterWriteDao;
import com.sln.dao.shop.write.member.MemberWriteDao;
import com.sln.dao.shop.write.product.ProductWriteDao;
import com.sln.dao.shop.write.seller.SellerApplyWriteDao;
import com.sln.dao.shop.write.seller.SellerWriteDao;
import com.sln.dao.shop.write.system.RegionsWriteDao;
import com.sln.dto.CommentsDto;
import com.sln.dto.OrderDayDto;
import com.sln.entity.message.Message;
import com.sln.entity.product.Product;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerApply;
import com.sln.entity.seller.SellerEliminate;
import com.sln.entity.system.Regions;
import com.sln.model.message.MessageModel;
import com.sln.service.seller.ISellerService;

@Component(value = "sellerModel")
public class SellerModel {
    private static Logger                 log = LogManager.getLogger(SellerModel.class);
    @Resource
    private SellerWriteDao                sellerWriteDao;
    @Resource
    private SellerReadDao                 sellerReadDao;
    @Resource
    private SellerApplyWriteDao           sellerApplyDao;
    @Resource
    private DataSourceTransactionManager  transactionManager;
    @Resource
    private MemberWriteDao                memberWriteDao;
    @Resource
    private RegionsWriteDao               regionsDao;
    @Resource
    private ProductReadDao                productReadDao;
    @Resource
    private ProductWriteDao               productWriteDao;
    @Resource
    private ProductCommentsReadDao        productCommentsReadDao;
    @Resource
    private MemberCollectionSellerReadDao memberCollectionSellerReadDao;
    @Resource
    private OrdersReadDao                 ordersReadDao;
    @Resource
    private SystemRolesWriteDao           systemRolesWriteDao;
    @Resource
    private SystemAdminReadDao            systemAdminReadDao;
    @Resource
    private SellerParkOperationReadDao    sellerParkOperationReadDao;
    @Resource
    private ComplainRegisterWriteDao complainRegisterWriteDao;
    @Resource
    private ComplainRegisterReadDao complainRegisterReadDao;
    @Resource
    private SellerEliminateReadDao sellerEliminateReadDao;
    @Resource
    private MessageModel messageModel;
    @Resource
    private ISellerService sellerService;
    public Seller getSellerById(Integer sellerId) {
        return sellerWriteDao.get(sellerId);
    }
    public Integer saveSeller(Seller seller) {
        return sellerWriteDao.save(seller);
    }
    public Integer updateSeller(Seller seller) {
        return sellerWriteDao.update(seller);
    }

    public Integer getSellersCount(Map<String, String> queryMap) {
        return sellerReadDao.getSellersCount(queryMap);
    }

    public List<Seller> getSellers(Map<String, String> queryMap, Integer start, Integer size) {
        List<Seller> list = sellerReadDao.getSellers(queryMap, start, size);
        for (Seller seller : list) {
            seller.setMemberName(memberWriteDao.get(seller.getMemberId()).getName());
        }
        return list;
    }
    public Seller getSellerByMemberId(Integer memberId) {
        return sellerWriteDao.getSellerByMemberId(memberId);
    }

    /**
     * 冻结商家，修改商家状态，修改商家下所有商品状态
     * @param sellerId
     * @return
     */
    public Boolean freezeSeller(Integer sellerId) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 修改商家状态
            Integer row = sellerWriteDao.freezeSeller(sellerId, Seller.AUDIT_STATE_3_FREEZE);
            if (row == 0) {
                throw new BusinessException("冻结商家时失败！");
            }

            // 修改商家下的商品状态
            productWriteDao.freezeProductsBySellerId(sellerId, Product.SELLER_STATE_2);

            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 解冻商家，修改商家状态，修改商家下所有商品状态
     * @param sellerId
     * @return
     */
    public Boolean unFreezeSeller(Integer sellerId) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 修改商家状态
            Integer row = sellerWriteDao.freezeSeller(sellerId, Seller.AUDIT_STATE_2_DONE);
            if (row == 0) {
                throw new BusinessException("解冻商家时失败！");
            }

            // 修改商家下的商品状态
            productWriteDao.freezeProductsBySellerId(sellerId, Product.SELLER_STATE_1);
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
    
    /**
     * 解冻商家，修改商家状态，修改商家下所有商品状态
     * @param sellerId
     * @return
     */
    public Boolean updateIsSelf(Integer sellerId) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 设置商家为自营店铺
            Integer row = sellerWriteDao.updateIsSelf(sellerId, ConstantsEJS.YES_NO_1);
            if (row == 0) {
                throw new BusinessException("设置自营店铺时失败！");
            }
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
    
    /**
     * 根据商家的用户ID，获取商家所在的地址（省市级）
     * @param memberId
     * @return
     */
    public String getSellerLocationByMId(Integer memberId) {
        //获得商家申请信息
        SellerApply sellerApply = sellerApplyDao.getSellerApplyByUserId(memberId);
        String location = "";
        if (sellerApply != null && !StringUtil.isEmpty(sellerApply.getCompanyProvince())
            && !StringUtil.isEmpty(sellerApply.getCompanyCity())) {
            Regions province = regionsDao.get(Integer.valueOf(sellerApply.getCompanyProvince()));

            Regions city = regionsDao.get(Integer.valueOf(sellerApply.getCompanyCity()));
            location = province.getRegionName() + city.getRegionName();
        }
        return location;
    }

    /**
     * 定时任务设定商家的评分，用户评论各项求平均值设置为商家各项的综合评分
     * @return
     */
    public boolean jobSetSellerScore() {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_auditStatus", Seller.AUDIT_STATE_2_DONE + "");
        List<Seller> sellers = sellerReadDao.getSellers(queryMap, 0, 0);
        log.info("sellers的大小是"+sellers.size());
        if (sellers != null && sellers.size() > 0) {
            for (Seller seller : sellers) {
                try {
                    CommentsDto commentsDto = productCommentsReadDao
                        .getSellerScoreSum(seller.getId());
                    if (commentsDto != null && commentsDto.getNumber() != null
                        && commentsDto.getNumber() > 0) {
                        Seller sellerNew = new Seller();
                        sellerNew.setId(seller.getId());
                        BigDecimal scoreDescription = (new BigDecimal(commentsDto.getDescription()))
                            .divide((new BigDecimal(commentsDto.getNumber())), 1,
                                BigDecimal.ROUND_HALF_UP);
                        sellerNew.setScoreDescription(scoreDescription.toString());

                        BigDecimal scoreService = (new BigDecimal(commentsDto.getServiceAttitude()))
                            .divide((new BigDecimal(commentsDto.getNumber())), 1,
                                BigDecimal.ROUND_HALF_UP);
                        sellerNew.setScoreService(scoreService.toString());

                        BigDecimal scoreDeliverGoods = (new BigDecimal(
                            commentsDto.getProductSpeed())).divide(
                                (new BigDecimal(commentsDto.getNumber())), 1,
                                BigDecimal.ROUND_HALF_UP);
                        sellerNew.setScoreDeliverGoods(scoreDeliverGoods.toString());

                        Integer update = sellerWriteDao.update(sellerNew);
                        if (update == 0) {
                            throw new BusinessException("修改商家评分时失败：sellerId=" + seller.getId());
                        }
                    }
                }catch (BusinessException be){
                    be.printStackTrace();
                    log.error(be.getMessage());
                    throw be;
                }catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                    throw e;
                }
            }
        }
        return true;
    }

    /**
     * 定时任务设定商家各项统计数据
     * @return
     */
    public boolean jobSellerStatistics() {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_auditStatus", Seller.AUDIT_STATE_2_DONE + "");
        List<Seller> sellers = sellerReadDao.getSellers(queryMap, 0, 0);
        if (sellers != null && sellers.size() > 0) {
            for (Seller seller : sellers) {
                try {
                    Seller sellerNew = new Seller();
                    sellerNew.setId(seller.getId());
                    
                    // 商品数量
                    Integer prdCount = productReadDao.getUpProductCountBySellerId(seller.getId());
                    sellerNew.setProductNumber(prdCount);

                    // 店铺收藏
                    Integer countBySellerId = memberCollectionSellerReadDao.getCountBySellerId(seller.getId());
                    sellerNew.setCollectionNumber(countBySellerId);

                    
                    //3-定时统计商家投诉量，如果结果大于0就注入，否则就设置为0
                    Integer compainCount=complainRegisterReadDao.getCountBySellerId(seller.getId());
                    if(compainCount==null){
                    	//调用投诉管理
                    	sellerNew.setCompainCount(0);
                    }else{
                    	sellerNew.setCompainCount(compainCount);
                    }
                    //定时统计商家综合评分-2
                    BigDecimal scoreService = new BigDecimal(seller.getScoreService());  
                    BigDecimal scoreDeliverGoods = new BigDecimal(seller.getScoreDeliverGoods()); 
                    BigDecimal scoreDescription = new BigDecimal(seller.getScoreDescription()); 
                    BigDecimal avg=(scoreService.add(scoreDeliverGoods).add(scoreDescription)).divide(new BigDecimal("3"),1,BigDecimal.ROUND_HALF_DOWN);
                   double f1 = avg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                   if(f1==0.0){
                	   //如果求得综合评分是0.0，则默认设置为5.0，否则将求得平均分注入
                	   sellerNew.setComparkMark("5.0");
                   }else{
                    sellerNew.setComparkMark(String.valueOf(f1));
                   }
                   //设置淘汰值
                    List<SellerEliminate> sellEliminateList=sellerEliminateReadDao.getSellerEliminate();
            			//商家完成的订单量
            			Integer orderCount = seller.getOrderCountOver();
            			BigDecimal comprehensiveMark=null;
            			if(seller.getComparkMark()!=null){
            				//商家的综合评分
            				comprehensiveMark = new BigDecimal(seller.getComparkMark());
            			}else{
            				comprehensiveMark=new BigDecimal(5.0);
            			}
            			//商家收到的投诉量
            			Integer compalinSeller = seller.getCompainCount();
            			
            			Integer a;
            			//先对完成订单量进行状态判定,-1提示 0警告 1淘汰
            			if(orderCount>sellEliminateList.get(0).getWarnValue()&&(orderCount<=sellEliminateList.get(0).getTipValue())){
            				//提示
            				a=13;
            			}else if((orderCount>sellEliminateList.get(0).getEliminateValue())&&(orderCount<=sellEliminateList.get(0).getWarnValue())){
            				//警告区间
            				a=25;
            			}else if(orderCount>sellEliminateList.get(0).getTipValue()){
            				//良好
            				a=10;
            			}else{
            				//淘汰
            				a=27;
            			}
            			Integer b;
            			if((compalinSeller<sellEliminateList.get(2).getWarnValue())&&(compalinSeller>=sellEliminateList.get(2).getTipValue())){
            				//提示区间
            				b=13;
            			}else if((compalinSeller>=sellEliminateList.get(2).getWarnValue())&&(compalinSeller<sellEliminateList.get(2).getEliminateValue())){
            				//警告区间
            				b=25;
            			}else if(compalinSeller<sellEliminateList.get(2).getTipValue()){
            				//良好区间
            				b=10;
            			}else{
            				//淘汰
            				b=27;
            			}
            			//创建临时变量判断
            			Integer c;
            			if(((comprehensiveMark.compareTo(BigDecimal.valueOf(sellEliminateList.get(1).getTipValue())) == -1)||(comprehensiveMark.compareTo(BigDecimal.valueOf(sellEliminateList.get(1).getTipValue())) == 0))&&(comprehensiveMark.compareTo(BigDecimal.valueOf(sellEliminateList.get(1).getWarnValue())) ==1)){
            				//提示
            				c=13;
            			}else if(((comprehensiveMark.compareTo(BigDecimal.valueOf(sellEliminateList.get(1).getEliminateValue())) == 1))&&((comprehensiveMark.compareTo(BigDecimal.valueOf(sellEliminateList.get(1).getWarnValue())) == -1)||(comprehensiveMark.compareTo(BigDecimal.valueOf(sellEliminateList.get(1).getWarnValue())) == 0))){
            				//警告
            				c=25;
            			}else if(comprehensiveMark.compareTo(BigDecimal.valueOf(sellEliminateList.get(1).getTipValue())) == 1){
            				//良好
            				c=10;
            			}else{
            				//淘汰
            				c=27;
            			}
            			int flag=b+c+a;
            			
            			switch(flag){
            			case 39:
            			case 51:
            			case 53:
            			case 63:
            			case 67:
            				//提示操作
            				sellerNew.setBusinessState(1);
    						messageModel.sendMessageToMember(null,sellerNew.getId(), Message.SJTTTS_1);
    						break;
            			case 65:
            			case 79:
            			case 75:
            			case 77:
            				//警告操作
            				sellerNew.setBusinessState(2);
    						messageModel.sendMessageToMember(null,sellerNew.getId(), Message.SJTTJG_1);
    						break;
            			case 81:
            				//淘汰操作
            				sellerNew.setBusinessState(3);
    						// 发送淘汰消息
    						messageModel.sendMessageToMember(null,sellerNew.getId(),Message.SJTTTT_1);
    						// 屏蔽商家前端展示
    						sellerService.freezeSeller(seller.getId());
    						break;
    					default:
    						//默认是良好
    						sellerNew.setBusinessState(4);
    							break;
            			}
						
                    // 店铺总销售金额、店铺完成订单量
                    OrderDayDto dto = ordersReadDao.getSumMoneyOrderBySellerId(seller.getId());
                    if (dto != null) {
                        BigDecimal moneyOrder = dto.getMoneyOrder() == null ? BigDecimal.ZERO
                            : dto.getMoneyOrder();
                        BigDecimal moneyBack = dto.getMoneyBack() == null ? BigDecimal.ZERO : dto.getMoneyBack();
                        sellerNew.setSaleMoney(moneyOrder.subtract(moneyBack));
                        sellerNew.setOrderCountOver(dto.getCount());
                    }

                    // 店铺总订单量
                    Integer count = ordersReadDao.getCountBySellerId(seller.getId());
                    sellerNew.setOrderCount(count);

                    Integer update = sellerWriteDao.update(sellerNew);
                    if (update == 0) {
                        throw new BusinessException("统计商家数据时失败：sellerId=" + seller.getId());
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                    log.error(e.getMessage());
                }
            }
        }
        return true;
    }

    /**
     * 根据名称取商家
     * @param name
     * @return
     */
    public List<Seller> getSellerByName(String name) {
        return sellerWriteDao.getSellerByName(name);
    }

    /**
     * 根据店铺名称取商家
     * @param sellerName
     * @return
     */
    public List<Seller> getSellerBySellerName(String sellerName) {
        return sellerWriteDao.getSellerBySellerName(sellerName);
    }

    /**
     * 商家列表进行数据隔离
     * @param queryMap
     */
    public Integer getSellersCountByRole(Map<String, String> queryMap){
        String roleId=queryMap.get("roleId");
        SystemRoles systemRoles=systemRolesWriteDao.get(Integer.parseInt(roleId));
        if(systemRoles.getRoleType().equals("1")){//当前角色是平台查看所有商家
            return sellerReadDao.getSellersCount(queryMap);
        }else{//当前角色是业务管理方查看当前业务管理方下的商家
            String adminId=queryMap.get("adminId");
            SystemAdmin admin=systemAdminReadDao.get(Integer.parseInt(adminId));
            List<SellerParkOperation> sellerParkOperations=sellerParkOperationReadDao.getSellerParkOperations(admin.getParkId(),admin.getOperationId());
            if(sellerParkOperations!=null&&sellerParkOperations.size()>0){
                return sellerReadDao.getSellersCount(queryMap);
            }else{
                return 0;
            }
        }
    }
    
    /**
     * 商家列表进行数据隔离
     * @param queryMap
     * @param start
     * @param size
     */
    public List<Seller> getSellersByRole(Map<String, String> queryMap,Integer start,Integer size){
        String roleId=queryMap.get("roleId");// 角色类型 1:平台 0:业务管理方
        SystemRoles systemRoles=systemRolesWriteDao.get(Integer.parseInt(roleId));
        if(systemRoles.getRoleType().equals("1")){//当前角色是平台查看所有商家
            List<Seller> list=sellerReadDao.getSellers(queryMap, start, size);
            for (Seller seller : list) {
                seller.setMemberName(memberWriteDao.get(seller.getMemberId()).getName());
            }
            return list;
        }else{//当前角色是业务管理方查看当前业务管理下的所有商家
            String adminId=queryMap.get("adminId");
            SystemAdmin admin=systemAdminReadDao.get(Integer.parseInt(adminId));
            List<SellerParkOperation> sellerParkOperations=sellerParkOperationReadDao.getSellerParkOperations(admin.getParkId(),admin.getOperationId());
            if(sellerParkOperations!=null&&sellerParkOperations.size()>0){
                List<Seller> list= sellerReadDao.getSellersByRole(queryMap,sellerParkOperations,start,size);
                for (Seller seller : list) {
                    seller.setMemberName(memberWriteDao.get(seller.getMemberId()).getName());
                }
                return list;
            }else{
                return new ArrayList<Seller>();
            }
        }
    }

    //获取相应字段
	public List<Seller> getSellEliminate(Map<String, String> queryMap,
			Integer start, Integer size) {
		return sellerReadDao.getSellEliminate(queryMap,start,size);
	}

	//获取商家名与账户
	public List<Seller> getSellerNameAndName(Map<String, String> queryMap,
			Integer start, Integer size) {
		// TODO Auto-generated method stub
		return sellerReadDao.getSellerNameAndName(queryMap,start,size);
	}

	public Integer getSellerIdByName(String complainSeller) {
		// TODO Auto-generated method stub
		return sellerReadDao.getSellerIdByName(complainSeller);
	}
}
