    //限制图片上传的大小
function limitJpg(fileId) {
    var max_size = 1024;// 1M
    var tmpFile = document.getElementById(fileId);
    if(tmpFile.files&&tmpFile.files[0]){
        var fileData = tmpFile.files[0];
        var size = fileData.size;
        if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(tmpFile.value)) {
            $.messager.alert('提示','图片类型必须是[.gif,jpeg,jpg,png]中的一种');
            tmpFile.value = "";
            return false;
        }
        if (size > max_size * 1024) {
            $.messager.alert('提示','图片大小不能超过1M');
            tmpFile.value = "";
            return false;
        }
    }else{
        $.messager.alert('提示','请上传图片。');
        return false;
    }
};
function widthHeighCheck(fileId,maxWidth,maxHeigh) {
    var input = document.getElementById(fileId);
    if(input.files){
        //读取图片数据
        var f = input.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            var data = e.target.result;
            //加载图片获取图片真实宽度和高度
            var image = new Image();
            image.onload=function(){
                var width = image.width;
                var height = image.height;
                if(width!=maxWidth||height!=maxHeigh){
                    $.messager.alert('提示','请上传'+maxWidth+'*'+maxHeigh+'的图片。');
                    input.value = "";
                    return false;
                }
            };
            image.src= data;
        };
        reader.readAsDataURL(f);
    }else{
        var image = new Image();
        image.onload =function(){
            var width = image.width;
            var height = image.height;
            var fileSize = image.fileSize;
            if(width!=maxWidth||height!=maxHeigh){
                $.messager.alert('提示','请上传300*300的图片。');
                input.value = "";
                return false;
            }
        }
        image.src = input.value;

    }
};

//下面用于多图片上传预览功能
function setImagePreviews(fileId,showdiv,count) {
    debugger;
    var docObj = document.getElementById(fileId);
    var dd = document.getElementById(showdiv);
    dd.innerHTML = "";
    var fileList = docObj.files;
    if(fileList.length>count){
       $.messager.alert('提示','请上传小于或等于'+count+'张图片');
        docObj.value = "";
       return;
    }
    for (var i = 0; i < fileList.length; i++) {
        dd.innerHTML += "<div style='float:left' > <img id='img" + i + "' /> </div>";
        var imgObjPreview = document.getElementById("img"+i);
        if (docObj.files && docObj.files[i]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '180px';
            imgObjPreview.style.height = '180px';
            imgObjPreview.style.marginLeft='15px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
        }
        else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            alert(imgSrc)
            var localImagId = document.getElementById("img" + i);
            //必须设置初始大小
            localImagId.style.width = "180px";
            localImagId.style.height = "180px";
            localImagId.style.marginLeft='15px';
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                $.messager.alert('提示','您上传的图片格式不正确，请重新选择!');
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }
    return true;
}

//下面用于多图片校验宽度和高度，以及上传预览功能
function imgCheck(fileId,showdiv,maxWidth,maxHeigh) {
    var input = document.getElementById(fileId);
    var dd = document.getElementById(showdiv);
    dd.innerHTML+="";
    if(input.files){
        //读取图片数据
        var f = input.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            var data = e.target.result;
            //加载图片获取图片真实宽度和高度
            var image = new Image();
            image.onload=function(){
                var width = image.width;
                var height = image.height;
                if(width!=maxWidth||height!=maxHeigh){
                    $.messager.alert('提示','请上传'+maxWidth+'*'+maxHeigh+'的图片。');
                    input.value = "";
                    return false;
                }else{
                    image= e.target.result;
                    dd.innerHTML += "<div class='img-con'> <img id='img" + f.name + "' /><span>✖</span></div>";
                    var imgObjPreview = document.getElementById("img"+f.name);
                    imgObjPreview.style.width = '180px';
                    imgObjPreview.style.height = '180px';
                    imgObjPreview.style.marginLeft='15px';
                    imgObjPreview.src = window.URL.createObjectURL(f);
                }
            };
            image.src= data;
        };
        reader.readAsDataURL(f);
    }else{
        var image = new Image();
        image.onload =function(){
            var width = image.width;
            var height = image.height;
            var fileSize = image.fileSize;
            if(width!=maxWidth||height!=maxHeigh){
                $.messager.alert('提示','请上传300*300的图片。');
                input.value = "";
                return false;
            }
        }
        image.src = input.value;

    }
};

//限制只能是png格式图片
function limitPNG(fileId) {
    var max_size = 1024;// 1M
    var tmpFile = document.getElementById(fileId);
    if(tmpFile.files&&tmpFile.files[0]){
        var fileData = tmpFile.files[0];
        var size = fileData.size;
        if (!/\.(png|PNG)$/.test(tmpFile.value)) {
            $.messager.alert('提示','图片类型必须是png');
            tmpFile.value = "";
            return false;
        }
        if (size > max_size * 1024) {
            $.messager.alert('提示','图片大小不能超过1M');
            tmpFile.value = "";
            return false;
        }
    }else{
        $.messager.alert('提示','请上传图片。');
        return false;
    }
};
