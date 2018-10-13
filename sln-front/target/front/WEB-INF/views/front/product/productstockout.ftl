<#include "/front/commons/_headbig.ftl" />
<link rel="icon" type="image/x-icon" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/img/favicon.png" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/article.css">
<link rel="stylesheet" href='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/frontseller/css/errorStyle.css'>
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/css/userindex.css"/>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/common.js'></script>
<!-- 需要依赖JQuery库执行！ -->
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/areaSupport.js'></script>
<script>
	var domain = "${(domainUrlUtil.SLN_URL_RESOURCES)!}";
</script>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.SLN_STATIC_RESOURCES)!}/js/jquery.lazyload.js'></script>
<style>
form label.valid{  
    color:#F00;  
     }  
</style>

<script type="text/javascript">
   jQuery.validator.addMethod("isMobile", function(value, element) {
             var length = value.length;
             var mobile = /^(1)\d{10}$/;
             return this.optional(element) || (length == 11 && mobile.test(value));
         }, "请正确填写手机号码");
         
$().ready(function() {  
 $("#forminfo").validate({
  submitHandler:function(form){
            alert("恭喜你，缺货商品登记成功!");   
            form.submit();
        },
         errorClass:"valid"   
 }); 
}); 
</script>
<div id="loadingDiv" style="position: fixed; display: none; z-index: 2000; top: 0px; left: 0px; width: 100%; height: 100%; background-color: rgba(255, 255, 255, 0.5)"></div>
<div class='container'>
	<div class='business-layout'>
		<div class='joinin-concrete'>
			<div id='apply-company-info' class='apply-company-info' style="margin:0 auto;">
				<form action='${(domainUrlUtil.SLN_URL_RESOURCES)!}/product/create.html' id='forminfo' method='post'">
					<table border='0' cellpadding="0" cellspacing="0" class='all' style="margin: 0 auto;"
						id='table' width="100%">
						<thead>
							<tr>
								<th colspan='10' style='text-align: center;color: #ff8f00;border-bottom: solid 2px #ff8f00;height:5px;font-size:20px'>缺货商品登记</th>
							</tr>
							<hr color="yellow" width="30px" style="solid:2px"/>
						</thead>
						<tbody valign="middle">
							<tr>
								<th>商&nbsp;&nbsp;品&nbsp;&nbsp;编&nbsp;&nbsp;号&nbsp;&nbsp;：</th>
								<td>
								  <input name='productCode' type='text' id="productCode" class="form-control w200 required" value="" maxlength="10" data-msg-required="请填写商品编号">
							    </td>
							</tr>
							<tr>
								<th>商&nbsp;&nbsp;品&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;&nbsp;：</th>
								<td><input name='productName' type='text' id="productName" class='form-control w200 required' value=""  maxlength="10" data-msg-required="请填写商品名称"></td>
							</tr>
							<tr>
								<th>
									商&nbsp;&nbsp;品&nbsp;&nbsp;地&nbsp;&nbsp;址&nbsp;&nbsp;：</th>
								<td><input name='productAddress' type='text' id="productAddress" class='form-control w200 required' value="" data-msg-required="请填写商品地址"></td>
							</tr>
							<tr>
								<th>手&nbsp;&nbsp;机&nbsp;&nbsp;号&nbsp;&nbsp;码&nbsp;&nbsp;：</th>
								<td><input type="text" class="form-control w200" name="phoneNumber"
                                   value=""  required data-rule-isMobile="true"  data-msg-required="请填写手机号">
								</td>
							
							</tr>
							<tr>
								<th>员&nbsp;&nbsp;工&nbsp;&nbsp;编&nbsp;&nbsp;号&nbsp;&nbsp;：</th>
								<td><input name='staffId' type='text' id="staffId" class='form-control w200 required' value="" maxlength="10" data-msg-required="请填写员工编号"> &nbsp;</td>
							</tr>
							<tr>
								<th>员&nbsp;&nbsp;工&nbsp;&nbsp;姓&nbsp;&nbsp;名&nbsp;&nbsp;：</th>
								<td><input name='staffName' type='text' id="staffName" class='form-control w200 required' value="" maxlength="6" data-msg-required="请填写员工姓名"> &nbsp;</span></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="20">&nbsp;</td>
							</tr>
						</tfoot>
					</table>
					<tr colspan="2" style=" padding: 20px 200px;">
								<td class='p-item'>
									<input type='submit'  class='btn btn-warning bt_submit' id='buttonSubmit' name='button' value='确认'>
								</td>&nbsp;&nbsp;
								<td class='p-item'>
									<input type='reset'  class='btn btn-warn active' id='buttonReset' name='button' value='取消'>
								</td>
						</tr>		
				</form>
			</div>
		</div>
	</div>
</div>
<#include "/front/commons/_endbig.ftl" />