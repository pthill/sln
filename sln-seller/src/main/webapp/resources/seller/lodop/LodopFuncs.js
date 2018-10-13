﻿var CreatedOKLodop7766 = null;

function getLodop(oOBJECT, oEMBED) {
	/***************************************************************************
	 * 本函数根据浏览器类型决定采用哪个页面元素作为Lodop对象： IE系列、IE内核系列的浏览器采用oOBJECT，
	 * 其它浏览器(Firefox系列、Chrome系列、Opera系列、Safari系列等)采用oEMBED,
	 * 如果页面没有相关对象元素，则新建一个或使用上次那个,避免重复生成。 64位浏览器指向64位的安装程序install_lodop64.exe。
	 **************************************************************************/
	var strHtmInstall = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='install_lodop32.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
	var strHtmUpdate = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
	var strHtm64_Install = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='install_lodop64.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
	var strHtm64_Update = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
	var strHtmFireFox = "<br><br><font color='#FF00FF'>注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它</font>";
	var strHtmChrome = "<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
	var LODOP;
	try {
		// =====判断浏览器类型:===============
		var isIE = (navigator.userAgent.indexOf('MSIE') >= 0)
				|| (navigator.userAgent.indexOf('Trident') >= 0);
		var is64IE = isIE && (navigator.userAgent.indexOf('x64') >= 0);
		// =====如果页面有Lodop就直接使用，没有则新建:==========
		if (oOBJECT != undefined || oEMBED != undefined) {
			if (isIE)
				LODOP = oOBJECT;
			else
				LODOP = oEMBED;
		} else {
			if (CreatedOKLodop7766 == null) {
				LODOP = document.createElement("object");
				LODOP.setAttribute("width", 0);
				LODOP.setAttribute("height", 0);
				LODOP
						.setAttribute("style",
								"position:absolute;left:0px;top:-100px;width:0px;height:0px;");
				if (isIE)
					LODOP.setAttribute("classid",
							"clsid:2105C259-1E0C-4534-8141-A753534CB4CA");
				else
					LODOP.setAttribute("type", "application/x-print-lodop");
				document.getElementById("bodysln").appendChild(LODOP);
				CreatedOKLodop7766 = LODOP;
			} else
				LODOP = CreatedOKLodop7766;
		}
		;
		// =====判断Lodop插件是否安装过，没有安装或版本过低就提示下载安装:==========
		var errMsg = null;
		if (((LODOP == null) || (typeof (LODOP.VERSION) == "undefined"))
				&& LODOP.errMsg == undefined) {
			if (navigator.userAgent.indexOf('Chrome') >= 0) {
				document.getElementById("bodysln").innerHTML = strHtmChrome
						+ document.getElementById("bodysln").innerHTML;
				errMsg = strHtmChrome;
			}
			if (navigator.userAgent.indexOf('Firefox') >= 0) {
				document.getElementById("bodysln").innerHTML = strHtmFireFox
						+ document.getElementById("bodysln").innerHTML;
				errMsg = strHtmFireFox;
			}
			if (is64IE) {
				document.write(strHtm64_Install);
				errMsg = strHtm64_Install;
			} else if (isIE) {
				document.write(strHtmInstall);
				errMsg = strHtmInstall;
			} else {
				document.getElementById("bodysln").innerHTML = strHtmInstall
						+ document.getElementById("bodysln").innerHTML;
				errMsg = strHtmInstall;
			}
			LODOP.errMsg = errMsg;
			return LODOP;
		} else if (LODOP.VERSION < "6.1.9.8") {
			if (is64IE) {
				document.write(strHtm64_Update);
				errMsg = strHtm64_Update;
			} else if (isIE) {
				document.write(strHtmUpdate);
				errMsg = strHtmUpdate;
			} else {
				document.getElementById("bodysln").innerHTML = strHtmUpdate
						+ document.getElementById("bodysln").innerHTML;
				errMsg = strHtmUpdate;
			}
			LODOP.errMsg = errMsg;
			return LODOP;
		}
		;
		// =====如下空白位置适合调用统一功能(如注册码、语言选择等):====

		// ============================================================
		return LODOP;
	} catch (err) {
		if (is64IE)
			document.getElementById("bodysln").innerHTML = "Error:"
					+ strHtm64_Install
					+ document.getElementById("bodysln").innerHTML;
		else
			document.getElementById("bodysln").innerHTML = "Error:"
					+ strHtmInstall
					+ document.getElementById("bodysln").innerHTML;
		return LODOP;
	}
	;
}
