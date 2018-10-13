package com.sln.web.util.freemarker;

import java.util.List;

import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.core.jd.util.JDConfig;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class ProductImagePathModel implements TemplateMethodModel {

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		//参数1：商品来源；参数2：产品编码；
		if(arguments!=null && arguments.size() > 0){
			//商品来源，2才是京东的商品
			Object sourceObj  = arguments.get(0);
			if(sourceObj!=null && Integer.valueOf(String.valueOf(sourceObj)) == 2){
				return JDConfig.IMAGE_PATH_160;
			}
			//产品编码是否为海核淘的商品
			Object productCode = arguments.get(1);
			if(productCode != null && String.valueOf(productCode).startsWith("HHT")){
				return DomainUrlUtil.HHT_IMG;
			}
		}
		return DomainUrlUtil.SLN_IMAGE_RESOURCES;
	}


}
