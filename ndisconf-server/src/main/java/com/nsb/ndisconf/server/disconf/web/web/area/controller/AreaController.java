package com.nsb.ndisconf.server.disconf.web.web.area.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nsb.ndisconf.server.disconf.web.service.area.bo.Area;
import com.nsb.ndisconf.server.disconf.web.service.area.service.AreaMgr;
import com.nsb.ndisconf.server.disconf.web.service.sync.service.SyncMgr;
import com.nsb.ndisconf.server.disconf.web.utils.ThreadPools;
import com.nsb.ndisconf.server.dsp.common.annotation.NoAuth;
import com.nsb.ndisconf.server.dsp.common.constant.WebConstants;
import com.nsb.ndisconf.server.dsp.common.controller.BaseController;
import com.nsb.ndisconf.server.dsp.common.vo.JsonObjectBase;
import com.nsb.ndisconf.server.wonder.other.PropUtils;

@Controller
@RequestMapping(WebConstants.API_PREFIX + "/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaMgr areaMgr;

	@Autowired
	private SyncMgr syncMgr;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjectBase list() {

		List<Area> envListVos = areaMgr.list();
		return buildListSuccess(envListVos, envListVos.size());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonObjectBase add(Area area) {
		areaMgr.addArea(area);

		// HTTP联动操作
		if (getSysc()) {
			final Area areaT=area;
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.addAreaSync(areaT);
					LOG.info("not sync add area " + i);
					
				}
			});
		}

		return buildSuccess("创建成功");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonObjectBase delete(@RequestParam("id") Long id) {
		areaMgr.delArea(id);

		// HTTP联动操作
		if (getSysc()) {
			final Long idT =id;
			ThreadPools.execute(new Runnable() {
				@Override
				public void run() {
					int i = syncMgr.delAreaSync(idT);
					LOG.info("not sync del area " + i);
				}
			});
		}

		return buildSuccess("删除成功");
	}
	
	/**
     * GET 获取
     *
     * @param
     *
     * @return
     */
    @NoAuth
    @RequestMapping(value = "/local", method = RequestMethod.GET)
    @ResponseBody
    public Object local() {
    	Long areaId= PropUtils.getLocalAreaId();
    	return areaId;
    }

}
