package com.aura.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

import com.aura.basic.BasicActionSupportImpl;
import com.aura.model.Content;
import com.aura.model.Dimension;
import com.aura.model.Stream;
import com.aura.service.ContentService;
import com.aura.service.DimensionService;
import com.aura.util.JsonHelper;

@Controller("streamAction")
public class StreamAction extends BasicActionSupportImpl {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name="dimensionService")
	private DimensionService dimensionService;
	
	@Resource(name="contentService")
	private ContentService contentService;
	
	/**
	 * Storm
	 * 1. 国家分布
	 * 2. 国家稿件 
	 */
	public void getCountryList() {
		int time = Integer.parseInt(this.getRequest().getParameter("time"));
		
		Dimension dimension = new Dimension();
		dimension.setStartSecond(time);
		dimension.setEndSecond(time);
		
		Content content = new Content();
		content.setStartSecond(time);
		content.setEndSecond(time);
		List<Dimension> dimensionList = dimensionService.getStreamCountryList(dimension);
		List<Content> contentList = contentService.getStreamCountryContentList(content);
		
		Stream storm = new Stream();
		storm.setCountry(dimensionList);
		storm.setContent(contentList);
		JsonHelper.printBasicJsonObject(getResponse(), storm);
	}
	
	/**
	 * Spark Streaming
	 * 1. 地区分布
	 * 2. 地区稿件
	 */
	public void getProvinceList() {
		int time = Integer.parseInt(this.getRequest().getParameter("time"));

		Dimension dimension = new Dimension();
		dimension.setStartSecond(time);
		dimension.setEndSecond(time);
		
		Content content = new Content();
		content.setStartSecond(time);
		content.setEndSecond(time);
		List<Dimension> dimensionList = dimensionService.getStreamProvinceList(dimension);
		List<Content> contentList = contentService.getStreamProvinceContentList(content);
		
		Stream streaming = new Stream();
		streaming.setArea(dimensionList);
		streaming.setContent(contentList);
		JsonHelper.printBasicJsonObject(getResponse(), streaming);
	}

}
