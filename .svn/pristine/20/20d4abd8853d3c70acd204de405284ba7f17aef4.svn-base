package com.sln.model.product;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.PagerInfo;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.jd.JdCategoryReadDao;
import com.sln.dao.shop.read.product.ProductCateJdReadDao;
import com.sln.dao.shop.read.product.ProductCateReadDao;
import com.sln.dao.shop.read.seller.SellerManageCateReadDao;
import com.sln.dao.shop.write.product.ProductCateJdWriteDao;
import com.sln.dao.shop.write.product.ProductCateWriteDao;
import com.sln.dao.shop.write.product.ProductTypeWriteDao;
import com.sln.dao.shop.write.product.ProductWriteDao;
import com.sln.dao.shop.write.seller.SellerManageCateWriteDao;
import com.sln.dao.shop.write.seller.SellerTypeLogsWriteDao;
import com.sln.dao.shop.write.seller.SellerWriteDao;
import com.sln.entity.jd.JdCategory;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductCate;
import com.sln.entity.product.ProductCateJd;
import com.sln.entity.product.ProductType;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerManageCate;
import com.sln.entity.seller.SellerTypeLogs;
import com.sln.entity.system.SystemAdmin;
import com.sln.vo.product.FrontProductCateVO;
import com.sln.vo.product.ProductCateVO;

import net.sf.cglib.beans.BeanCopier;

@Component(value = "productCateModel")
public class ProductCateModel {

	private static Logger log = LogManager.getLogger(ProductCateModel.class);
	
    @Resource
    private ProductCateWriteDao          productCateWriteDao;
    @Resource
    private SellerManageCateWriteDao     sellerManageCateWriteDao;
    @Resource
    private SellerWriteDao               sellerWriteDao;
    @Resource
    private SellerTypeLogsWriteDao       sellerTypeLogsWriteDao;
    @Resource
    private ProductCateReadDao           productCateReadDao;
    @Resource
    private ProductTypeWriteDao          productTypeWriteDao;
    @Resource
    private ProductWriteDao              productWriteDao;
    @Resource
    private SellerManageCateReadDao      sellerManageCateReadDao;
    @Resource
    private JdCategoryReadDao            jdCategoryReadDao;
    @Resource
    private ProductCateJdWriteDao 		 productCateJdWriteDao;
    @Resource
    private ProductCateJdReadDao 		 productCateJdReadDao;

    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;

    public Boolean saveProductCate(ProductCate productCate) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            productCateWriteDao.insert(productCate);
            //记录日志
            SellerTypeLogs logs = new SellerTypeLogs();
            logs.setCreateId(productCate.getCreateId());
            logs.setCreateName(productCate.getCreater());
            logs.setProductCateId(productCate.getId());
            StringBuffer content = new StringBuffer();
            content.append(
                "保存商品分类，佣金:" + productCate.getScaling() == null ? "无" : productCate.getScaling());
            logs.setContent(content.toString());
            sellerTypeLogsWriteDao.insert(logs);
            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status);
            throw e;
        }
    }

    public Boolean updateProductCate(ProductCate productCate) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            productCateWriteDao.update(productCate);
            if(productCate.getScaling()!=null){
            	//记录日志
                SellerTypeLogs logs = new SellerTypeLogs();
                logs.setProductCateId(productCate.getId());
                logs.setCreateId(productCate.getUpdateId());
                logs.setCreateName(productCate.getUpdater());
                StringBuffer content = new StringBuffer();
                content.append("修改商品分类，佣金:" + productCate.getScaling());
                logs.setContent(content.toString());
                sellerTypeLogsWriteDao.insert(logs);
            }
            
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
        return false;
    }

    public Boolean delProductCate(Integer productCateId) {
        //1. business check
        if (null == productCateId || 0 == productCateId)
            throw new BusinessException("删除商品分类失败，id为空");
        List<ProductCate> list = productCateWriteDao.getByPid(productCateId,null);
        if (null != list && list.size() > 0) {
            throw new BusinessException("删除商品分类失败，该分类下还有分类");
        }

        //校验该分类下是否存在商家申请过
        int sellerManageCateCount = sellerManageCateReadDao.countByProductCateId(productCateId);
        if (sellerManageCateCount > 0) {
            throw new BusinessException("删除商品分类失败，该分类已有商家申请");
        }
        int count = productWriteDao.countByCateId(productCateId);
        if (count > 0) {
            throw new BusinessException("删除商品分类失败，该分类下还有商品");
        }
        ProductCate productCate = productCateWriteDao.get(productCateId);
        productCate.setStatus(3);
        return productCateWriteDao.update(productCate) > 0;
    }

    public ProductCate getProductCateById(Integer productCateId) {
        if (null == productCateId)
            throw new BusinessException("根据id获取商品分类失败，id为空");

        return productCateWriteDao.get(productCateId);
    }

    public List<ProductCateVO> pageProductCate(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productCateWriteDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        Map<String, Object> map = new HashMap<String, Object>(queryMap);
        map.put("start", start);
        map.put("size", size);

        List<ProductCateVO> volist = productCateWriteDao.page(map);
        for (ProductCateVO vo : volist) {
            Seller seller = sellerWriteDao.get(Integer.valueOf(vo.getSeller()));
            vo.setSeller(seller.getName());
            vo.setSellerName(seller.getSellerName());
            vo.setPathName(assemblePath(vo));
        }

        return volist;
    }

    /**
     * 组装分类路径
     * @param vo
     * @return
     */
    private String assemblePath(ProductCateVO vo) {
        String[] data = vo.getPath().split("/");
        switch (data.length) {
            case 0:
                return vo.getName();
            case 2:
                return productCateWriteDao.get(Integer.valueOf(data[1])).getName() + "/"
                       + vo.getName();
            case 3:
                ProductCate parent = productCateWriteDao.get(Integer.valueOf(data[2]));
                ProductCate root = productCateWriteDao.get(Integer.valueOf(data[1]));
                return root.getName() + "/" + parent.getName() + "/" + vo.getName();
            default:
                break;
        }
        return null;
    }

    public List<ProductCate> getByPid(Integer pid) {
        if (null == pid)
            throw new BusinessException("根据父id获取商品分类失败，父id为空");
        List<ProductCate> list = productCateWriteDao.getByPid(pid,ProductCate.TYPE_PT);
        for (ProductCate cate : list) {
            ProductType productType = productTypeWriteDao.get(cate.getProductTypeId());
            if (null != productType && !StringUtil.isEmpty(productType.getName())) {
                cate.setTypeName(productType.getName());
            }
        }
        return list;
    }

    public List<ProductCate> getCateBySellerId(Integer sellerId) {
        if (null == sellerId)
            throw new BusinessException("根据商家id获取商品分类失败，商家分类id为空");
        /**根据商家id查询出该商家申请成功的所有的商品分类信息*/
        List<ProductCate> cateList = productCateWriteDao.getBySellerId(sellerId);
        if (null != cateList && cateList.size() > 0) {
            Set<String> cateIdSet = new HashSet<String>();

            /**根据path构造一级分类id*/
            for (ProductCate cate : cateList) {
                String path = cate.getPath();
                String[] cateIds = path.split("/");
                if (null != cateIds && cateIds.length > 2) {
                    String bigCateStr = cateIds[1];//一级分类信息
                    cateIdSet.add(bigCateStr);
                }
            }
            List<ProductCate> list = new ArrayList<ProductCate>(cateIdSet.size());

            /**根据一级分类id查询分类信息，放到list中*/
            if (null != cateIdSet && cateIdSet.size() > 0) {
                for (String cateId : cateIdSet) {
                    ProductCate cate = productCateWriteDao.get(Integer.valueOf(cateId));
                    if (null != cate) {
                        list.add(cate);
                    }
                }
            }

            /**设置返回*/
            return list;
        } else {
            return new ArrayList<ProductCate>(0);
        }
    }

    public List<ProductCate> getCateBySellerId(Integer sellerId, Integer pid) {
        if (null == sellerId)
            throw new BusinessException("根据商家id和商品分类id获取商品分类信息失败，商家id为空");
        if (null == pid)
            throw new BusinessException("根据商家id和商品分类id获取商品分类信息失败，商品分类id为空");
        /**根据商家id查询出该商家申请成功的所有的商品分类信息*/
        List<ProductCate> cateList = productCateWriteDao.getBySellerId(sellerId);
        if (null != cateList && cateList.size() > 0) {
            Set<String> cateIdSet = new HashSet<String>();
            List<ProductCate> list = new ArrayList<ProductCate>(cateIdSet.size());
            /**根据path构造pid下一级分类id*/
            for (ProductCate cate : cateList) {
                String path = cate.getPath();//path规则:一级分类 / ;二级分类 /1 ;三级分类 /1/2
                String[] split = path.split("/");
                if (null != split && split.length == 3
                    && Integer.valueOf(split[1]).intValue() == pid) {
                    String[] cateIds = path.split("/");
                    if (cateIds != null && cateIds.length > 2) {
                        String secondCateId = cateIds[2];//二级分类id
                        cateIdSet.add(secondCateId);
                    }
                } else if (null != split && split.length == 3
                           && Integer.valueOf(split[2]).intValue() == pid) {
                    list.add(cate);//三级分类
                }
            }

            /**根据二级分类id查询分类信息，放到list中*/
            if (null != cateIdSet && cateIdSet.size() > 0) {
                for (String cateId : cateIdSet) {
                    ProductCate cate = productCateWriteDao.get(Integer.valueOf(cateId));
                    if (null != cate) {
                        list.add(cate);
                    }
                }
            }
            Collections.reverse(list);
            /**设置返回*/
            return list;
        } else {
            return new ArrayList<ProductCate>(0);
        }
    }

    public String getCatePathStrById(Integer productCateId) {
        if (null == productCateId)
            throw new BusinessException("获取商品分类路径失败，商品分类id为空");
        ProductCate productCate = productCateWriteDao.get(productCateId);
        if (null == productCate)
            throw new BusinessException("获取商品分类路径失败，该商品分类不存在");
        String pathStr = "";
        String path = productCate.getPath();
        String[] split = path.split("/");
        for (int i = 0; i < split.length; i++) {
            if (StringUtil.isEmpty(split[i]))
                continue;
            ProductCate cate = productCateWriteDao.get(Integer.valueOf(split[i]));
            if (null != cate)
                pathStr += cate.getName() + " > ";
        }

        if (!StringUtil.isEmpty(pathStr)) {
            pathStr += productCate.getName();
        }
        return pathStr;
    }

    public SellerManageCate getSellerManageCate(Integer id) {
        return sellerManageCateWriteDao.get(id);
    }

    public Boolean updateSellerManageCate(SellerManageCate cate) {
        return sellerManageCateWriteDao.update(cate) > 0;
    }

    public List<ProductCateVO> getByPidAndSeller(Integer pid,
                                                 Integer seller) throws IllegalAccessException,
                                                                 InvocationTargetException,
                                                                 NoSuchMethodException {
        if (null == pid)
            throw new BusinessException("根据父id获取商品分类失败，父id为空");

        List<ProductCate> list = productCateWriteDao.getByPid(pid,ProductCate.TYPE_PT);
        List<ProductCateVO> volist = new ArrayList<ProductCateVO>();

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("q_seller", seller.toString());
        map1.put("q_state", "2");
        List<SellerManageCate> sellerCatelist = sellerManageCateWriteDao.page(map1, -1, -1);

        for (ProductCate cate : list) {
            ProductCateVO vo = new ProductCateVO();
            PropertyUtils.copyProperties(vo, cate);

            for (SellerManageCate sc : sellerCatelist) {
                if (sc.getProductCateId() == vo.getId()) {
                    vo.setChecked("true");
                }
            }

            volist.add(vo);
        }

        return volist;
    }

    /**
     * 取得所有显示状态的商品分类
     * @param  queryMap
     * @return
     */
    public List<FrontProductCateVO> getProductCateList(Map<String, Object> queryMap) {
        List<FrontProductCateVO> returnList = new ArrayList<FrontProductCateVO>();

        //状态为1：显示
        queryMap.put("status", ConstantsEJS.PRODUCT_CATE_STATUS_1);

        //1、取第一级分类
        queryMap.put("pid", "0");
        queryMap.put("type", ProductCate.TYPE_PT);
        List<ProductCate> beanList = productCateReadDao.queryList(queryMap);
        //取第二级分类
        for (ProductCate bean : beanList) {
            FrontProductCateVO vo = new FrontProductCateVO();
            final BeanCopier copier = BeanCopier.create(ProductCate.class, FrontProductCateVO.class,
                false);
            copier.copy(bean, vo, null);

            //取第二级 
            queryMap.put("pid", bean.getId());
            List<ProductCate> beanListLevel2 = productCateReadDao.queryList(queryMap);
            List<FrontProductCateVO> cateList2 = new ArrayList<FrontProductCateVO>();
            for (ProductCate temp : beanListLevel2) {
                FrontProductCateVO vo2 = new FrontProductCateVO();
                copier.copy(temp, vo2, null);

                //取第三级
                queryMap.put("pid", temp.getId());
                List<ProductCate> beanListLevel3 = productCateReadDao.queryList(queryMap);
                List<FrontProductCateVO> cateList3 = new ArrayList<FrontProductCateVO>();
                for (ProductCate cate : beanListLevel3) {
                    FrontProductCateVO vo3 = new FrontProductCateVO();
                    copier.copy(cate, vo3, null);
                    cateList3.add(vo3);
                }

                vo2.setChilds(cateList3);
                cateList2.add(vo2);
            }

            vo.setChilds(cateList2);
            returnList.add(vo);
        }

        return returnList;
    }

    public List<ProductCate> getProductCate(Map<String, Object> param) {
        return productCateReadDao.queryList(param);
    }
    
    /**
     * 同步JD分类信息
     */
    public int syJdCate(){
    	TransactionStatus status = null;
    	try{
    		//获取未同步的JD分类信息 根据分类级别排序
    		
    		List<ProductCate> productCates = new ArrayList<ProductCate>();
    		List<JdCategory> list = jdCategoryReadDao.getUnCategory();
    		for (int i = 0; i < list.size(); i++) {
				JdCategory jdCategory = new JdCategory();
				jdCategory = list.get(i);
				ProductCate productCate = new ProductCate();
				//productCate.
				//判断是什么类型的分类
				if(jdCategory.getCatClass()==JdCategory.CATCLASS_0){
					//如果是一级分类则不需要设置父类
					productCate.setPid(0);
					productCate.setPath("/");
				}else{
					//否则则需要找到父类的编号
					ProductCate fCate = productCateReadDao.getCateByjdCatId(jdCategory.getParentId());
					productCate.setPid(fCate.getId());
					//如果不是一级分类则需要找到分类路径
					if(fCate.getPid()!= null && fCate.getPid() !=0){
						ProductCate fFCate = new ProductCate();
						fFCate = productCateReadDao.get(fCate.getPid());
						productCate.setPath("/"+fFCate.getId()+"/"+fCate.getId());
					}else{
						productCate.setPath("/"+fCate.getId());
					}
				}
				productCate.setProductTypeId(0);
				productCate.setCreateTime(new Date());
				productCate.setName(jdCategory.getName());
				productCate.setScaling(new BigDecimal("0"));
				productCate.setCreateId(1);
				productCate.setUpdateId(1);
				productCate.setSort(1);
				//验证如果是三级分类则加入费率
				if(jdCategory.getCatClass() ==JdCategory.CATCLASS_2){
					productCate.setServiceRate(new BigDecimal("0"));
				}
				productCate.setStatus(1);
				productCate.setType(ProductCate.TYPE_JD);
				productCate.setJdCatId(jdCategory.getCatId());
				productCates.add(productCate);
				
				//ProductCateJd
				//判断当前分类和下一个循环的分类 级别是否一样如果是一样则新增到集合中，如果不是则新增数据
				//System.out.println("判断："+(i != 0 && jdCategory.getCatClass() !=list.get(i+1).getCatClass()));
				if( i == list.size()-1 || (i != 0 && jdCategory.getCatClass() !=list.get(i+1).getCatClass()) || productCates.size()>=200){
					DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    		    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    		    status = transactionManager.getTransaction(def);
					productCateWriteDao.batchInsertProductCate(productCates);
					productCates.clear();
					transactionManager.commit(status);
					//提交了分类信息之后在对提交的分类信息做关系关联
					VerifJDCate();
				}
			}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		transactionManager.rollback(status);
    		throw new BusinessException("同步京东分类失败");
    	}
    	return 1;
    }
    
    /**
     * 添加京东分类到平台（有关联的则更新）
     * @param jdCatId
     * @param systemAdmin
     * @return
     */
    public Boolean addJdCategory(String jdCatIds,SystemAdmin systemAdmin) {
    	String[] idArr = jdCatIds.split(",");
    	if(idArr==null || idArr.length==0){
    		return false;
    	}
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
        	for (String value : idArr) {
        		int jdCatId = ConvertUtil.toInt(value, 0);
        		if(jdCatId==0){
        			continue;
        		}
        		
        		//查询京东三级分类
            	JdCategory jdCate = jdCategoryReadDao.getByCatId(jdCatId);
            	if(jdCate==null){
            		transactionManager.rollback(status);
            		throw new BusinessException("找不到京东分类，请联系管理员！");
            	}
            	Map<String,Object> map = new HashMap<>();
            	map.put("jdCatId", jdCatId);
            	//查询三级分类
            	List<ProductCate> threeCateList = productCateReadDao.queryList(map);
            	
            	//查询京东二级分类
            	JdCategory secondJdCate = jdCategoryReadDao.getByCatId(jdCate.getParentId());
            	if(secondJdCate==null){
            		transactionManager.rollback(status);
            		throw new BusinessException("找不到京东分类，请联系管理员！");
            	}
            	map.put("jdCatId", secondJdCate.getCatId());
            	//查询二级分类
            	List<ProductCate> secondCateList = productCateReadDao.queryList(map);
            	
            	//查询京东一级分类
            	JdCategory firstJdCate = jdCategoryReadDao.getByCatId(secondJdCate.getParentId());
            	if(firstJdCate==null){
            		transactionManager.rollback(status);
            		throw new BusinessException("找不到京东分类，请联系管理员！");
            	}
            	map.put("jdCatId", firstJdCate.getCatId());
            	//查询一级分类
            	List<ProductCate> firstCateList = productCateReadDao.queryList(map);
            	
            	
            	/** 开始添加 或 更新 分类 **/
            	ProductCate firstCate = this.saveOrUpdate(firstCateList, firstJdCate, systemAdmin, 0, "",1);
            	ProductCate secondCate = this.saveOrUpdate(secondCateList, secondJdCate, systemAdmin, firstCate.getId(),firstCate.getPath(), 2);
            	this.saveOrUpdate(threeCateList, jdCate, systemAdmin, secondCate.getId(),secondCate.getPath(), 3);
			}
        	
        	/** 结束 添加 或更新 分类 **/
            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status);
            throw e;
        }
    }
    
    /**
     * 保存或更新分类（从京东添加）
     * @param cateList
     * 				平台分类集合
     * @param jdCategory
     * 				京东分类
     * @param systemAdmin
     * @param pid
     * 				父级ID
     * @param level
     * 				分类等级，1：一级，2：二级，3：三级
     * @return
     */
    private ProductCate saveOrUpdate(List<ProductCate> cateList,JdCategory jdCategory,SystemAdmin systemAdmin,Integer pid,String path,int level){
    	//如果分类是二级或三级的，父级id为null或0，则表示数据有异常
    	pid = ConvertUtil.toInt(pid, 0);
    	if(level>1 && pid==0){
    		throw new BusinessException("服务异常，请联系管理员！");
    	}
    	ProductCate pc = new ProductCate();
    	if(CollectionUtils.isEmpty(cateList)){
        	pc.setCreateId(systemAdmin.getId());
        	pc.setUpdateId(systemAdmin.getId());
        	pc.setSort(1);
        	pc.setStatus(ConstantsEJS.PRODUCT_CATE_STATUS_1);
    		pc.setPid(pid);
    		pc.setProductTypeId(0);
    		pc.setScaling(new BigDecimal(0));
    		if (pid == 0) {
                path = "/";//一级分类
            } else {
                if ("/".equals(path)) {
                    path = path + pid;
                } else {
                    path = path + "/" + pid;
                }
            }
    		pc.setPath(path);
    		pc.setType(ProductCate.TYPE_JD);
    		pc.setJdCatId(jdCategory.getCatId());
    		pc.setName(jdCategory.getName());
    		productCateWriteDao.insert(pc);
    	}else{
    		for (ProductCate productCate : cateList) {
    			productCate.setName(jdCategory.getName());
    			productCate.setUpdateId(systemAdmin.getId());
    			productCate.setUpdateTime(new Date());
    			productCate.setType(ProductCate.TYPE_JD);
				productCateWriteDao.update(productCate);
				pc = productCate;
			}
    	}
    	return pc;
    }
    
    /**
     * 批量新增平台分类和京东分类关系信息
     * @param productCateId
     * @param jdCategoryIds
     */
    public int batchInsertProductCateJd(Integer productCateId,String jdCategoryIds){
    	productCateId = ConvertUtil.toInt(productCateId,0);
    	if(ConvertUtil.toInt(productCateId,0) == 0 || StringUtils.isEmpty(jdCategoryIds)){
    		log.error("插入失败：参数出现异常! 【productCateId:"+productCateId+"，jdCategoryIds:"+jdCategoryIds+"】");
    		return 0;
    	}
    	
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus   status = transactionManager.getTransaction(def);
    	try{
    		//先删除所有关联信息
    		productCateJdWriteDao.delByProductCateId(productCateId);
    		
    		List<ProductCateJd> productCateJds = new ArrayList<>();
    		String[] jdCategoryIdArr =  jdCategoryIds.split(",");
    		for (String value : jdCategoryIdArr) {
    			int jdCategoryId = ConvertUtil.toInt(value, 0);
				if(jdCategoryId !=0){
					ProductCateJd pcj = new ProductCateJd();
					pcj.setJdCategoryId(jdCategoryId);
					pcj.setProductCate(productCateId);
					productCateJds.add(pcj);
				}
			}
    		
    		if(CollectionUtils.isEmpty(productCateJds)){
    			throw new BusinessException("插入失败！集合为空！");
    		}
    		
    		productCateJdWriteDao.batchInsertProductCateJd(productCateJds);
			transactionManager.commit(status);
    	}catch(Exception e){
    		log.error("插入失败："+e.getMessage());
    		e.printStackTrace();
    		transactionManager.rollback(status);
    		return 0;
    	}
    	return 1;
    }
    
    /**
     * 分页查询平台分类和京东分类关系信息
     * @param queryMap
     * @param pager
     * @return
     */
    public List<ProductCateJd> pageProductCateJd(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productCateJdReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        Map<String, String> map = new HashMap<String, String>(queryMap);
        map.put("start", start.toString());
        map.put("size", size.toString());

        List<ProductCateJd> pcjList = productCateJdReadDao.queryList(map);
        return pcjList;
    }
    
    /**
     * 验证分类表中的京东分类是否与 JD分类表建立链接关系，如果没有则建立
     */
    public Integer VerifJDCate(){
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus   status = transactionManager.getTransaction(def);
    	try{
        	List<ProductCate> list = productCateReadDao.getUnCateJd();
        	if(list != null || list.size()>0){
        		List<ProductCateJd> productCateJds = new ArrayList<ProductCateJd>();
            	for (int i = 0; i < list.size(); i++) {
            		ProductCateJd productCateJd = new ProductCateJd();
            		productCateJd.setProductCate(list.get(i).getId());
            		productCateJd.setJdCategoryId(list.get(i).getJdCatId());
            		productCateJds.add(productCateJd);
            		if(i%200==0 || i==list.size()-1){
            			productCateJdWriteDao.batchInsertProductCateJd(productCateJds);
            			productCateJds.clear();
            		}
        		}
        	}
        	transactionManager.commit(status);
    	}catch(Exception e){
    		e.printStackTrace();
    		transactionManager.rollback(status);
    	}
    	
    	return  0;
    }
}
