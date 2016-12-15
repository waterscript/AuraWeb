package com.aura.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.aura.basic.BasicActionSupportImpl;
import com.aura.model.Dimension;
import com.aura.service.DimensionService;
import com.aura.util.JsonHelper;

@Controller("offlineAction")
public class OfflineAction extends BasicActionSupportImpl {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name="dimensionService")
	private DimensionService dimensionService;
	
	/**
	 * Hive/MapReduce 流量统计
	 */
	public void getOfflineList() {
		Dimension dimension = new Dimension();
		List<Dimension> list = dimensionService.getOfflineList(dimension);
		JsonHelper.printBasicJsonList(getResponse(), list);
	}
}
