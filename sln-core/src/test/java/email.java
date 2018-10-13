import com.sln.core.email.MailSenderInfo;
import com.sln.core.email.SendMail;

import java.math.BigDecimal;
import java.util.Date;

public class email {
    public static void main(String[] agrs) throws Exception{
        BigDecimal countBackMoney =new BigDecimal("120.8");
        String subject="海核云谷门户电商批次退款订单";
        String a="1771452241@qq.com,315762466@qq.com,1617982559@qq.com";
        //String sendTo[] = {"1771452241@qq.com","315762466@qq.com","1617982559@qq.com"};//发送到那里
        String html= MailSenderInfo.productModel("pc20184119",2,countBackMoney,"已发送",new Date());
        SendMail sendMail = new SendMail();
        String sendTo[]=a.split(",");
        sendMail.send163Email(sendTo, subject,html,null);
    }
}
