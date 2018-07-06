package com.nsb.ndisconf.server.disconf.web.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsb.ndisconf.server.disconf.web.service.sign.form.SigninForm;
import com.nsb.ndisconf.server.disconf.web.service.sign.service.SignMgr;
import com.nsb.ndisconf.server.disconf.web.service.user.bo.User;
import com.nsb.ndisconf.server.disconf.web.service.user.dto.Visitor;
import com.nsb.ndisconf.server.disconf.web.service.user.form.PasswordModifyForm;
import com.nsb.ndisconf.server.disconf.web.service.user.service.UserMgr;
import com.nsb.ndisconf.server.disconf.web.service.user.vo.VisitorVo;
import com.nsb.ndisconf.server.disconf.web.web.auth.constant.LoginConstant;
import com.nsb.ndisconf.server.disconf.web.web.auth.login.RedisLogin;
import com.nsb.ndisconf.server.disconf.web.web.auth.validator.AuthValidator;
import com.nsb.ndisconf.server.dsp.common.annotation.NoAuth;
import com.nsb.ndisconf.server.dsp.common.constant.ErrorCode;
import com.nsb.ndisconf.server.dsp.common.constant.WebConstants;
import com.nsb.ndisconf.server.dsp.common.controller.BaseController;
import com.nsb.ndisconf.server.dsp.common.vo.JsonObjectBase;
import com.nsb.ndisconf.server.ub.common.commons.ThreadContext;

/**
 * @author liaoqiqi
 * @version 2014-1-20
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/account")
public class UserController extends BaseController {

    @Autowired
    private UserMgr userMgr;

    @Autowired
    private AuthValidator authValidator;

    @Autowired
    private SignMgr signMgr;

    @Autowired
    private RedisLogin redisLogin;

    /**
     * GET 获取
     *
     * @param
     *
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase get() {

        VisitorVo visitorVo = userMgr.getCurVisitor();
        if (visitorVo != null) {
            return buildSuccess("visitor", visitorVo);
        } else {
            // 没有登录啊
            return buildGlobalError("syserror.inner", ErrorCode.GLOBAL_ERROR);
        }
    }

    /**
     * 登录
     *
     * @param signin
     * @param request
     *
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public JsonObjectBase signin(@Valid SigninForm signin, HttpServletRequest request) {

        // 验证
        authValidator.validateLogin(signin);
        // 数据库登录
        User user = signMgr.signin(signin.getName());
        // 过期时间
        int expireTime = LoginConstant.SESSION_EXPIRE_TIME;
        if (signin.getRemember().equals(1)) {
            expireTime = LoginConstant.SESSION_EXPIRE_TIME2;
        }
        // redis login
        redisLogin.login(request, user, expireTime);
        VisitorVo visitorVo = userMgr.getCurVisitor();
        return buildSuccess("visitor", visitorVo);
    }

    /**
     * 登出
     *
     * @param request
     *
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase signout(HttpServletRequest request) {
        redisLogin.logout(request);
        return buildSuccess("ok", "ok");
    }

    /**
     * 修改密码
     *
     * @param
     *
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @ResponseBody
    public JsonObjectBase password(@Valid PasswordModifyForm passwordModifyForm, HttpServletRequest request) {
        // 校验
        authValidator.validatePasswordModify(passwordModifyForm);
        // 修改
        Visitor visitor = ThreadContext.getSessionVisitor();
        userMgr.modifyPassword(visitor.getLoginUserId(), passwordModifyForm.getNew_password());
        // re login
        redisLogin.logout(request);
        return buildSuccess("修改成功，请重新登录");
    }
    
    /**
     * GET 本机操作是否同步到其他服务器
     *
     * @param
     *
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseBody
    public Integer sync(@RequestParam("flag") Integer flag) {
    	getSession().setAttribute(WebConstants.SYNC_FLAG, flag);
    	LOG.info("sync flag "+getSession().getAttribute(WebConstants.SYNC_FLAG));
    	return flag;
    }
    
    /**
     * GET 本机操作是否同步到其他服务器
     *
     * @param
     *
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/getsync", method = RequestMethod.GET)
    @ResponseBody
    public Integer getsync() {
    	Object o= getSession().getAttribute(WebConstants.SYNC_FLAG);
    	if(o==null){
    		return 0;
    	}else{
    		JsonObjectBase job = get();
    		Object oo =job.getMessage().get("visitor");
    		if(oo==null){
    			return 0;
    		}
    		return 1;
    	}
    }
    
}
