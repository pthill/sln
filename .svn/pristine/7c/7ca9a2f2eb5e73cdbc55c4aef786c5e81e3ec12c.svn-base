package com.sln.model.member;

import com.sln.core.ConstantsEJS;
import com.sln.core.Md5;
import com.sln.core.SlnConfig;
import com.sln.core.WDUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.sms.AlidayuSms;
import com.sln.dao.shop.read.member.MemberSpecialIntegralReadDao;
import com.sln.dao.shop.read.member.MemberWelfareSendDetailReadDao;
import com.sln.dao.shop.read.member.MemberWelfareSendReadDao;
import com.sln.dao.shop.read.member.WelfareCompanyReadDao;
import com.sln.dao.shop.read.seller.SellerReadDao;
import com.sln.dao.shop.write.member.*;
import com.sln.dao.shop.write.system.CodeWriteDao;
import com.sln.entity.member.*;
import com.sln.entity.message.Message;
import com.sln.entity.seller.Seller;
import com.sln.entity.system.Code;
import com.sln.model.message.MessageModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MemberWelfareSendModel {
    @Resource
    private MemberWelfareSendReadDao        memberWelfareSendReadDao;
    @Resource
    private MemberWelfareSendWriteDao       memberWelfareSendWriteDao;
    @Resource
    private MemberWelfareSendDetailWriteDao memberWelfareSendDetailWriteDao;
    @Resource
    private MemberWelfareSendDetailReadDao memberWelfareSendDetailReadDao;
    @Resource
    private DataSourceTransactionManager    transactionManager;
    @Resource
    private MemberWriteDao                  memberWriteDao;
    @Resource
    private MemberGradeIntegralLogsWriteDao memberGradeIntegralLogsWriteDao;
    @Resource
    private MessageModel                    messageModel;
    @Resource
    private CodeWriteDao                    codeWriteDao;
    @Resource
    private WelfareCompanyWriteDao          welfareCompanyWriteDao;
    @Resource
    private WelfareCompanyReadDao           welfareCompanyReadDao;
    @Resource
    private SellerReadDao                   sellerReadDao;
    @Resource
    private MemberSpecialIntegralWriteDao   memberSpecialIntegralWriteDao;
    @Resource
    private MemberSpecialIntegralReadDao    memberSpecialIntegralReadDao;

    private static Logger log = LogManager
            .getLogger(MemberProductBackModel.class);

    public MemberWelfareSend getById(Integer id){
        MemberWelfareSend memberWelfareSend= memberWelfareSendReadDao.get(id);
        List<MemberWelfareSendDetail> list=memberWelfareSendDetailReadDao.page(id,null,null);
        memberWelfareSend.setChildren(list);
        return memberWelfareSend;
    }

    public Integer countPage(Map<String,String>queryMap)throws Exception{
        return memberWelfareSendReadDao.pageCount(queryMap);
    }
    public List<MemberWelfareSend>page(Map<String,String>queryMap,Integer size,Integer start){
        return memberWelfareSendReadDao.page(queryMap,size,start);
    }

    public Boolean save(MultipartFile multipartFile,Integer userId,Integer id,Integer sellerId)throws Exception{
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        InputStream is;
        Integer count;
        Workbook wb;
        try {
            if(id==null||id==0){
            }else{
               count=del(id);
                if(count==0){
                    throw new BusinessException("更新失败");
                }
            }
            MemberWelfareSend memberWelfareSend=new MemberWelfareSend();
            CommonsMultipartFile cf= (CommonsMultipartFile)multipartFile;
            //新建一个文件
            //数据字典配置上传execl存放的文件
            Code code =codeWriteDao.getCode("EMAIL_CODE","2");
            String filename=new Date().getTime()+".xls";
            String  path=code.getCodeText().trim() + filename;
            File file=new File(path);
            File parent=file.getParentFile();
            if(!parent.exists()){
                parent.mkdirs();
            }
            file.createNewFile();
            cf.getFileItem().write(file);
            is = new FileInputStream(file);
            //根据文件名判断文件是2003版本还是2007版本
            if(WDUtil.isExcel2007(file.getName())){
                wb = new HSSFWorkbook(is);
            }else{
                wb = new XSSFWorkbook(is);
            }
            Sheet sheet=wb.getSheetAt(0);
            //获取execl的行数
            Integer totalRows=sheet.getPhysicalNumberOfRows();
            if(totalRows<=2){
                throw new BusinessException("execl上传有误，请重新上传");
            }
            Integer totalCells=0;
            //如果execl的第一行的长度
            if(sheet.getRow(0).getPhysicalNumberOfCells()!=6){
                //得到Excel的列数
                throw new BusinessException("execl上传有误，请重新上传");
            }
            //如果execl的第二行不为空
            if(sheet.getRow(1) != null){
                //得到Excel的列数
                totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
            }
            if(totalCells!=7){
                throw new BusinessException("execl上传有误，请重新上传");
            }
            memberWelfareSend.setPath(file.getPath());
            memberWelfareSend.setCountPerson(totalRows-2);
            memberWelfareSend.setCreateUser(userId);
            Row r0=sheet.getRow(0);//获取第一行的数据
            for(int c=0;c<r0.getPhysicalNumberOfCells();c++){
                Cell cell = r0.getCell(c);
                if (null != cell){
                    switch (c) {
                        case 0:
                            break;
                        case 1:
                            //公司信息
                            if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
                                throw new BusinessException("公司名称不能为空");
                            }
                            memberWelfareSend.setCompany(cell.getStringCellValue().trim());
                            break;
                        case 2:
                            break;
                        case 3:
                            //部门名称
                            if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
                                throw new BusinessException("部门名称不能为空");
                            }
                            memberWelfareSend.setDept(cell.getStringCellValue().trim());
                            break;
                        case 4:
                            break;
                        case 5:
                            if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
                                throw new BusinessException("费用名称");
                            }
                            memberWelfareSend.setCostName(cell.getStringCellValue().trim());
                            break;
                        default:
                            break;
                    }
                }
            }
             memberWelfareSend.setSellerId(sellerId);
             count=memberWelfareSendWriteDao.insert(memberWelfareSend);
            if(count==0){
                throw new BusinessException("保存福利积分信息时出现异常");
            }
            Map<String,String> queryMap=new HashMap<>();
            queryMap.put("q_company",memberWelfareSend.getCompany());
            queryMap.put("q_dept",memberWelfareSend.getDept());
            int sum=welfareCompanyReadDao.pageCount(queryMap);
            if(sum<1){
                WelfareCompany w=new WelfareCompany();
                w.setCompany(memberWelfareSend.getCompany());
                w.setDept(memberWelfareSend.getDept());
                welfareCompanyWriteDao.insert(w);
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9]))\\d{8}$";
            String sellerName="";
            if(sellerId!=null){
               Seller seller= sellerReadDao.get(sellerId);
               if(seller==null){
                   throw new BusinessException("商家不存在");
               }else{
                   sellerName=seller.getSellerName();
               }
            }
            //循环Excel行数,从第三行开始。
            for(int r=2;r<totalRows;r++){
                Row row = sheet.getRow(r);
                if (row == null) break;
                MemberWelfareSendDetail t = new MemberWelfareSendDetail();
                t.setSellerName(sellerName);
                t.setWelfareId(memberWelfareSend.getId());
                //循环Excel的列
                for(int c = 0; c <totalCells; c++){
                    Cell cell = row.getCell(c);
                    if (null != cell){
                        switch (c) {
                            case 0:
                                //员工号
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                if(cell.getStringCellValue().trim()==null||cell.getStringCellValue().trim().equals("")){
                                    throw new BusinessException("员工号不能为空");
                                }
                                t.setStaffNo(cell.getStringCellValue().trim());
                                break;
                            case 1:
                                //姓名
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                if(cell.getStringCellValue().trim()==null||cell.getStringCellValue().trim().equals("")){
                                    throw new BusinessException("员工姓名不能为空");
                                }
                                t.setName(cell.getStringCellValue().trim());
                                break;
                            case 2:
                                //电话号码
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                log.info("当前手机号"+cell.getStringCellValue().trim());
                                log.info("当前的手机号是"+cell.getStringCellValue().length());
                                try{
                                    if(cell.getStringCellValue()==null||cell.getStringCellValue().equals("")){
                                        throw new BusinessException("手机号不能为空");
                                    }else{
                                        if(cell.getStringCellValue().trim().length() != 11){
                                            throw new BusinessException("手机号应为11位数");
                                        }else{
                                            Pattern p = Pattern.compile(regex);
                                            Matcher matcher = p.matcher(cell.getStringCellValue().trim());
                                            boolean isMatch = matcher.matches();
                                            if(isMatch){
                                                t.setTel(cell.getStringCellValue().trim());
                                                break;
                                            } else {
                                                log.info("您的手机号" + cell.getStringCellValue() + "是错误格式！！！");
                                                throw new BusinessException("你的手机号码错误格式");
                                            }
                                        }
                                    }
                                }catch (Exception e){
                                    throw new BusinessException(e.getMessage());
                                }
                            case 3:
                                //发放额度
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                if(cell.getStringCellValue()==null||cell.getStringCellValue().equals("")){
                                    throw new BusinessException("请输入金额");
                                }
                                Integer money;
                                try {
                                    money=Integer.parseInt(cell.getStringCellValue().trim());
                                }catch (NumberFormatException n){
                                    n.printStackTrace();
                                    throw new BusinessException("发放金额请输入整数");
                                }
                                t.setMoney(money);
                                break;
                            case 4:
                                //出生日期
                                SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                                if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
                                    throw new BusinessException("请填写出生日期");
                                }else if(cell.getCellType()==cell.CELL_TYPE_STRING){
                                    if(cell.getStringCellValue()==null||cell.getStringCellValue().equals("")){
                                        throw new BusinessException("请填写出生日期");
                                    }else{
                                        t.setBirthday(cell.getStringCellValue());
                                    }
                                }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                                    if(DateUtil.isCellDateFormatted(cell)){
                                        Date theDate=cell.getDateCellValue();
                                        t.setBirthday(s.format(theDate));
                                    }else {
                                        throw new BusinessException("出生日期填写错误");
                                    }
                                }
                                break;
                            case 5:
                                //积分生效日期
                                log.info("输出积分生效日期=="+cell.getCellType());
                                if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
                                    t.setStartTime(sdf.format(new Date()));
                                }else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                    if (cell.getStringCellValue() == null || cell.getStringCellValue().equals("")) {
                                        t.setStartTime(sdf.format(new Date()));
                                    } else {
                                        t.setStartTime(sdf.format(cell.getStringCellValue()));
                                    }
                                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    log.info("输出积分生效日期="+cell.getDateCellValue());
                                    if (cell.getDateCellValue() == null || cell.getDateCellValue().equals("")) {
                                        t.setStartTime(sdf.format(new Date()));
                                    } else {
                                        if (DateUtil.isCellDateFormatted(cell)) {
                                            Date theDate = cell.getDateCellValue();
                                            t.setStartTime(sdf.format(theDate));
                                        } else {
                                            throw new BusinessException("积分生效日期错误");
                                        }
                                    }
                                }
                                break;
                            case 6:
                                //积分失效日期
                                Date date = new Date(System.currentTimeMillis());
                                calendar.setTime(date);
                                calendar.add(Calendar.YEAR, 20);
                                date = calendar.getTime();
                                if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
                                      t.setEndTime(sdf.format(date));
                                }else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                    if (cell.getStringCellValue() == null || cell
                                            .getStringCellValue().equals("")) {
                                       t.setEndTime(sdf.format(date));
                                    } else {
                                        t.setEndTime(sdf.format(cell.getStringCellValue()));
                                    }
                                } else {
                                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        if (cell.getDateCellValue() == null || cell.getDateCellValue().equals("")) {
                                            t.setEndTime(sdf.format(cell.getStringCellValue()));
                                        } else {
                                            if (DateUtil.isCellDateFormatted(cell)) {
                                                Date theDate = cell.getDateCellValue();
                                                t.setEndTime(sdf.format(theDate));
                                            }
                                        }
                                    }
                                }
                                break;
                            default:
                                break;
                        }

                    }else{
                        if(c==0){
                            throw new BusinessException("员工号不能为空");
                        }
                        if(c==1){
                            throw new BusinessException("员工姓名不能为空");
                        }
                        if(c==2){
                            throw new BusinessException("手机号不能为空");
                        }
                        if(c==3){
                            throw new BusinessException("发放额度不能为空");
                        }
                        if(c==4){
                            throw new BusinessException("出生日期不能为空");
                        }
                        if(c==5){
                            t.setStartTime(sdf.format(new Date()));
                        }
                        Date date = new Date(System.currentTimeMillis());
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, 20);
                        date = calendar.getTime();
                        if(c==6){
                            t.setEndTime(sdf.format(date));
                        }
                    }

                }
                count=memberWelfareSendDetailWriteDao.insert(t);
                if(count==0){
                    throw new BusinessException("保存福利积分详细信息时出现异常");
                }
            }
            is.close();
            transactionManager.commit(status);
            return true;
        }catch (BusinessException be){
            transactionManager.rollback(status);
            throw be;
        }catch (Exception e){
            transactionManager.rollback(status);
            throw e;
        }

    }

    public Boolean sendWelfareSend(Integer id,String ip)throws Exception{
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            int count;
            Boolean state;
            MemberWelfareSend memberWelfareSend=memberWelfareSendReadDao.get(id);
            if(memberWelfareSend==null){
                throw new BusinessException("获取福利积分对象失败!");
            }
            memberWelfareSend.setSendTime(new Date());
            memberWelfareSend.setSendStatus("1");
            count =memberWelfareSendWriteDao.update(memberWelfareSend);
            if(count==0){
                throw new BusinessException("福利积分状态失败!");
            }
            Map<String,Object>map=new HashMap<>();
            Integer memberId;
            int password=0;
            List<MemberWelfareSendDetail> list=memberWelfareSendDetailReadDao.page(id,null,null);
            if(list!=null&&list.size()>0){
                for(MemberWelfareSendDetail m:list){
                    List<Member> members=memberWriteDao.getByName(m.getTel());
                    if(members!=null&&members.size()>0){
                        Member member=members.get(0);
                        if(m.getSellerName()==null||m.getSellerName().equals("")){
                            //通用积分
                            MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                            memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
                            if(m.getMoney()>=0){
                                member.setIntegral(m.getMoney());
                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_14);
                                memberGradeIntegralLogs.setValue(m.getMoney());
                            }else{
                                Integer integral=member.getIntegral()+m.getMoney();
                                if(integral<=0){
                                    //如果为负数则变为0；
                                    member.setIntegral(-member.getIntegral());
                                    memberGradeIntegralLogs.setValue(member.getIntegral());
                                }else {
                                    member.setIntegral(m.getMoney());
                                    memberGradeIntegralLogs.setValue(m.getMoney());
                                }
                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_15);
                            }
                            count=memberWriteDao.updateIntegral(member);
                            if (count == 0) {
                                throw new BusinessException("会员通用积分信息更新失败，请重试！");
                            }
                            memberGradeIntegralLogs.setMemberId(member.getId());
                            memberGradeIntegralLogs.setMemberName(member.getName());
                            memberGradeIntegralLogs.setOptDes(memberWelfareSend.getCostName());
                            memberGradeIntegralLogs.setCreateTime(new Date());
                            count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                            if (count == 0) {
                                throw new BusinessException("会员积分日志记录失败，请重试！");
                            }
                        }else{
                            //会员专项积分对应的商家记录表
                            if(m.getMoney()>=0){
                                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_3);
                                MemberSpecialIntegral specialIntegral=new MemberSpecialIntegral();
                                specialIntegral.setMemberId(member.getId());
                                specialIntegral.setSellerId(memberWelfareSend.getSellerId());
                                specialIntegral.setStartTime(sdf.parse(m.getStartTime()));
                                specialIntegral.setEndTime(sdf.parse(m.getEndTime()));
                                specialIntegral.setValue(m.getMoney());
                                specialIntegral.setInitValue(m.getMoney());
                                count=memberSpecialIntegralWriteDao.insert(specialIntegral);
                                if(count==0){
                                    throw new BusinessException("保存专项积分对应商家关系表失败");
                                }
                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_14);
                                memberGradeIntegralLogs.setMemberId(member.getId());
                                memberGradeIntegralLogs.setMemberName(member.getName());
                                memberGradeIntegralLogs.setValue(m.getMoney());
                                memberGradeIntegralLogs.setOptDes(m.getSellerName());
                                memberGradeIntegralLogs.setCreateTime(new Date());
                                count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                                if (count == 0) {
                                    throw new BusinessException("会员积分值日志记录失败，请重试！");
                                }
                            }else{
                                map.put("q_sellerId",memberWelfareSend.getSellerId());
                                map.put("q_memberId",member.getId());
                                Integer surplus=m.getMoney();
                                List<MemberSpecialIntegral> specialIntegrals=memberSpecialIntegralReadDao.page(map,null,null);
                                if(specialIntegrals==null||specialIntegrals.size()==0){
                                }else {
                                    for( int i=0;i<specialIntegrals.size();i++){
                                        MemberSpecialIntegral memberSpecialIntegral=specialIntegrals.get(i);
                                        if(memberSpecialIntegral.getValue()>0){
                                            if(memberSpecialIntegral.getValue()+surplus>=0){
                                                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                                                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_3);
                                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_15);
                                                memberGradeIntegralLogs.setMemberId(member.getId());
                                                memberGradeIntegralLogs.setMemberName(member.getName());
                                                memberGradeIntegralLogs.setValue(surplus);
                                                memberGradeIntegralLogs.setOptDes(m.getSellerName());
                                                memberGradeIntegralLogs.setCreateTime(new Date());
                                                memberGradeIntegralLogs.setMsiId(memberSpecialIntegral.getId());
                                                count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                                                if (count == 0) {
                                                    throw new BusinessException("会员积分值日志记录失败，请重试！");
                                                }
                                                memberSpecialIntegral.setValue(memberSpecialIntegral.getValue()+surplus);
                                                memberSpecialIntegralWriteDao.update(memberSpecialIntegral);
                                                if(count==0){
                                                    throw new BusinessException("修改专项积分失败");
                                                }
                                                break;
                                            }else{
                                                surplus = surplus + memberSpecialIntegral.getValue();
                                                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                                                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_3);
                                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_15);
                                                memberGradeIntegralLogs.setMemberId(member.getId());
                                                memberGradeIntegralLogs.setMemberName(member.getName());
                                                memberGradeIntegralLogs.setValue(-memberSpecialIntegral.getValue());
                                                memberGradeIntegralLogs.setOptDes(m.getSellerName());
                                                memberGradeIntegralLogs.setCreateTime(new Date());
                                                memberGradeIntegralLogs.setMsiId(memberSpecialIntegral.getId());
                                                count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                                                if (count == 0) {
                                                    throw new BusinessException("会员积分值日志记录失败，请重试！");
                                                }
                                                memberSpecialIntegral.setValue(0);
                                                memberSpecialIntegralWriteDao.update(memberSpecialIntegral);
                                                if (count == 0) {
                                                    throw new BusinessException("修改专项积分失败");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        memberId=member.getId();
                        //设置短信
                        state=AlidayuSms.sendOLDWelfareSend(m.getTel(),memberWelfareSend.getCostName(),String.valueOf(m.getMoney()),SlnConfig.ALIDAYU_SMS_MEMBER_OLDWELFARESEND);
                        if(!state){
                            throw new BusinessException("短信发送失败，请重试！");
                        }
                    }else{
                        //如果该手机未注册则用该手机号注册一个
                        password=(int)((Math.random()*9+1)*100000);
                        System.out.println(password);
                        Member member=new Member();
                        member.setName(m.getTel());
                        member.setPassword(Md5.getMd5String(String.valueOf(password)));
                        member.setGrade(Member.GRADE_1);
                        member.setGradeValue(0);
                        member.setLoginNumber(0);
                        member.setPwdErrCount(0);
                        member.setLastLoginIp(ip);
                        member.setSource(ConstantsEJS.SOURCE_1_PC);
                        member.setBalance(new BigDecimal(0.00));
                        member.setBalancePwd(Md5.getMd5String(String.valueOf(password)));
                        member.setIsEmailVerify(ConstantsEJS.YES_NO_0);
                        member.setIsSmsVerify(ConstantsEJS.YES_NO_0);
                        member.setSmsVerifyCode("");
                        member.setEmailVerifyCode("");
                        member.setCanReceiveSms(1);
                        member.setCanReceiveEmail(1);
                        member.setStatus(Member.STATUS_1);
                        member.setEmail("");
                        if(m.getSellerName()==null||m.getSellerName().equals("")){
                            //通用积分
                            if(m.getMoney()>=0){
                                member.setIntegral(m.getMoney());
                                count = memberWriteDao.save(member);
                                if (count == 0) {
                                    throw new BusinessException("信息保存失败，请重试！");
                                }
                                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2);
                                memberGradeIntegralLogs.setValue(m.getMoney());
                                memberGradeIntegralLogs.setMemberId(member.getId());
                                memberGradeIntegralLogs.setMemberId(member.getId());
                                memberGradeIntegralLogs.setMemberName(member.getName());
                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_14);
                                memberGradeIntegralLogs.setOptDes(memberWelfareSend.getCostName());
                                memberGradeIntegralLogs.setCreateTime(new Date());
                                count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                                if (count == 0) {
                                    throw new BusinessException("会员经验值日志记录失败，请重试！");
                                }
                            }else{
                                member.setIntegral(0);
                                count = memberWriteDao.save(member);
                                if (count == 0) {
                                    throw new BusinessException("信息保存失败，请重试！");
                                }
                            }

                        }else{
                            //专项积分
                            if(m.getMoney()>=0){
                                member.setIntegral(0);
                                count = memberWriteDao.save(member);
                                if (count == 0) {
                                    throw new BusinessException("信息保存失败，请重试！");
                                }
                                //会员专项积分对应的商家记录表
                                MemberSpecialIntegral specialIntegral=new MemberSpecialIntegral();
                                specialIntegral.setSellerId(memberWelfareSend.getSellerId());
                                specialIntegral.setMemberId(member.getId());
                                specialIntegral.setValue(m.getMoney());
                                specialIntegral.setInitValue(m.getMoney());
                                specialIntegral.setStartTime(sdf.parse(m.getStartTime()));
                                specialIntegral.setEndTime(sdf.parse(m.getEndTime()));
                                count=memberSpecialIntegralWriteDao.insert(specialIntegral);
                                if(count==0){
                                    throw new BusinessException("保存专项积分对应商家关系表失败");
                                }
                                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                                memberGradeIntegralLogs.setType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_3);
                                memberGradeIntegralLogs.setValue(m.getMoney());
                                memberGradeIntegralLogs.setMemberId(member.getId());
                                memberGradeIntegralLogs.setMemberName(member.getName());
                                memberGradeIntegralLogs.setOptType(MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_OPT_T_14);
                                memberGradeIntegralLogs.setOptDes(m.getSellerName());
                                memberGradeIntegralLogs.setCreateTime(new Date());
                                count = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                                if (count == 0) {
                                    throw new BusinessException("会员经验值日志记录失败，请重试！");
                                }
                            }else{
                                member.setIntegral(0);
                                count = memberWriteDao.save(member);
                                if (count == 0) {
                                    throw new BusinessException("信息保存失败，请重试！");
                                }
                            }
                        }
                        memberId=member.getId();
                        //设置短信
                        state=AlidayuSms.sendNEWWelfareSend(m.getTel(),memberWelfareSend.getCostName(),String.valueOf(m.getMoney()),m.getTel(),String.valueOf(password),String.valueOf(password),
                                SlnConfig.ALIDAYU_SMS_MEMBER_NEWWELFARESEND);
                        if(!state){
                            throw new BusinessException("短信发送失败，请重试！");
                        }
                    }
                    //设置系统消息
                    map.put("staffNo",m.getStaffNo());
                    map.put("name",m.getName());
                    map.put("costName",memberWelfareSend.getCostName());
                    map.put("money",m.getMoney());
                    state=messageModel.sendMessageToMember(map,memberId,Message.FLJFFF);
                    if(!state){
                        throw new BusinessException("系统消息发送失败，请重试！");
                    }
                }
            }
            transactionManager.commit(status);
            return true;
        }catch (BusinessException be){
            be.printStackTrace();
            log.error("积分发放失败"+be.getMessage());
            transactionManager.rollback(status);
            throw be;
        }catch (Exception e){
            e.printStackTrace();
            log.error("积分发送失败信息"+e.getMessage());
            transactionManager.rollback(status);
            throw e;
        }
    }

    public Integer del(Integer id)throws Exception{
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try{
            int count=memberWelfareSendWriteDao.del(id);
            if(count==0){
                throw new BusinessException("删除出现异常");
            }
            count=memberWelfareSendDetailWriteDao.del(id);
            if(count==0){
                throw new BusinessException("删除出现异常");
            }
            transactionManager.commit(status);
            return count;
        }catch (BusinessException be){
            be.printStackTrace();
            log.error("积分发放失败"+be.getMessage());
            transactionManager.rollback(status);
            throw be;
        }catch (Exception e){
            e.printStackTrace();
            log.error("积分发送失败信息"+e.getMessage());
            transactionManager.rollback(status);
            throw e;
        }
    }
}
