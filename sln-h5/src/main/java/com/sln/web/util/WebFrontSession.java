package com.sln.web.util;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sln.core.ConstantsEJS;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.member.Member;

/**
 * 用户Session对象管理
 *                       
 * @Filename: WebFrontSession.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class WebFrontSession {
    protected static org.apache.log4j.Logger log                 = org.apache.log4j.LogManager
        .getLogger(WebFrontSession.class);

    private static String                    MEMBER_SESSION_NAME = "memberSession";

    /**
     * member放入session中
     * @param request
     * @param member
     * @throws Exception
     */
    public static void putMemberSession(HttpServletRequest request, Member member) {
        //        putMemberSession(request, member, null, null, null, null);
    	String sessionId = request.getParameter("sessionId");
    	HttpSession session= null;
    	if(sessionId !=null && !"".equals(sessionId)){
    		session = MySessionContext.getSession(sessionId);
    	}else{
    		session = request.getSession();
    	}
        //        if (member != null) {
        //            session.setAttribute(MEMBER_SESSION_NAME, member);
        //        }
        MemberSession memberSession = new MemberSession();
        if (member != null) {
            memberSession.setMember(member);
        }
        session.setAttribute(MEMBER_SESSION_NAME, memberSession);
        member.setSessionId(session.getId());
    }

    public static void removeMemberSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(MEMBER_SESSION_NAME);
        }
    }

    /**
     * 对象放入session中
     * @param request
     * @param member
     * @throws Exception
     */
    public static void putObjToSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * 从session中取出对象
     * @param request
     * @param member
     * @throws Exception
     */
    public static Object getObjFromSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    /**
     * 从session中移除对象
     * @param request
     * @param member
     * @throws Exception
     */
    public static void delObjFromSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        session.removeAttribute(key);
        ;
    }

    /**
     * 获取用户session
     * @param request
     * @return
     * @throws Exception
     */
    public static MemberSession getMemberSession(HttpServletRequest request) {
    	String sessionId = request.getParameter("sessionId");
    	HttpSession session;
    	if(sessionId != null && !"".equals(sessionId)){
    		session = MySessionContext.getSession(sessionId);
    	}else{
    		 session = request.getSession();
    	}
    	MemberSession memberSession = null;
    	if(session.getAttribute(MEMBER_SESSION_NAME) != null){
    		 memberSession = (MemberSession) session.getAttribute(MEMBER_SESSION_NAME) ;
    	}
        
        return memberSession;
    }

    /**
     * 在公共头将sessionId写入cookie
     * @param request
     * @param response
     */
    public static void addSessionIdToCookie(HttpServletRequest request,
                                            HttpServletResponse response) {
        // 从request中取回cookie，判断存sessionId的cookie对象是否存在
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (DomainUrlUtil.getSLN_COOKIE_NAME().equals(cookie.getName())
                    && cookie.getValue() != null) {
                    return;
                }
            }
        }
        // sessionId存入cookie
        Cookie cookie = new Cookie(DomainUrlUtil.getSLN_COOKIE_NAME(),
            request.getSession().getId());
        cookie.setMaxAge(30 * 24 * 60 * 60); // cookie缓存一个月
        cookie.setDomain(DomainUrlUtil.getSLN_COOKIE_DOMAIN());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取用户验证码
     * @param request
     * @return
     * @throws Exception
     */
    public static String getVerifyNumber(HttpServletRequest request) {
        String verify_number = (String) request.getSession()
            .getAttribute(ConstantsEJS.VERIFY_NUMBER_NAME);
        return verify_number;
    }

    public static Member getLoginedUser(HttpServletRequest request) {
        Member member = new Member();
        try {
            MemberSession memberSession = getMemberSession(request);
            if (memberSession == null) {
                return null;
                // throw new BusinessException("会员信息获取失败，请重试！");
            }
            member = memberSession.getMember();
            if (member == null) {
                return null;
                // throw new BusinessException("会员信息获取失败，请重试！");
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return member;
    }
    
    public static HttpSession geDefSession(HttpServletRequest request,boolean create){
    	String sessionId = request.getParameter("sessionId");
    	HttpSession session= null;
    	if(sessionId != null && !"".equals(sessionId)){
    		session = MySessionContext.getSession(sessionId);
    	}else{
    		 session = request.getSession(create);
    	}
    	return session ;
    }
    
}
