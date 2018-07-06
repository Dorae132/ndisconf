package com.nsb.ndisconf.server.disconf.web.web.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsb.ndisconf.server.disconf.web.service.app.bo.App;
import com.nsb.ndisconf.server.disconf.web.service.app.form.AppNewForm;
import com.nsb.ndisconf.server.disconf.web.service.app.service.AppMgr;
import com.nsb.ndisconf.server.disconf.web.service.app.vo.AppListVo;
import com.nsb.ndisconf.server.disconf.web.service.config.service.ConfigMgr;
import com.nsb.ndisconf.server.disconf.web.service.sync.service.SyncMgr;
import com.nsb.ndisconf.server.disconf.web.utils.ThreadPools;
import com.nsb.ndisconf.server.disconf.web.web.app.validator.AppValidator;
import com.nsb.ndisconf.server.dsp.common.constant.WebConstants;
import com.nsb.ndisconf.server.dsp.common.controller.BaseController;
import com.nsb.ndisconf.server.dsp.common.vo.JsonObjectBase;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/app")
public class AppController extends BaseController {

	@Autowired
	private AppMgr appMgr;

	@Autowired
	private ConfigMgr configMgr;

	@Autowired
	private AppValidator appValidator;

	@Autowired
	private SyncMgr syncMgr;

	/**
	 * list
	 *
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjectBase list() {

		List<AppListVo> appListVos = appMgr.getAuthAppVoList();

		return buildListSuccess(appListVos, appListVos.size());
	}

	/**
	 * list
	 *
	 * @return
	 */
	@RequestMapping(value = "/listapp", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjectBase listapp() {

		List<App> appListVos = appMgr.getAppList();

		return buildListSuccess(appListVos, appListVos.size());
	}

	/**
	 * create
	 *
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonObjectBase add(@Valid AppNewForm appNewForm) {

		LOG.info(appNewForm.toString());

		appValidator.validateCreate(appNewForm);

		appMgr.create(appNewForm);

		// HTTP联动操作
		if (getSysc()) {
			final AppNewForm appNewFormT=appNewForm;
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.addAppSync(appNewFormT);
					LOG.info("not sync add app " + i);
				}
			});
		}

		return buildSuccess("创建成功");
	}

	/**
	 * delete
	 *
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjectBase delete(@RequestParam("id") Long appid) {

		// 删除应用
		appMgr.delete(appid);

		// 删除应用相关配置
		configMgr.deleteAppConfig(appid);

		// HTTP联动操作
		if (getSysc()) {
			final Long appidT=appid;
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.delAppSync(appidT);
					LOG.info("not sync del app " + i);
				}
			});
		}
		
		

		return buildSuccess("删除成功");
	}

}
