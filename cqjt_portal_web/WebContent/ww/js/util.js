/*此工具类包括以下功能
 * 1.null转空值
 * 2.下拉框数据绑定
 * 3.图片格式验证
 * 4.文件格式验证
 * */

/*字符串null值转空值
 * */
function nullToEmpty(str){
		if(str==null)
			return "";
		else{
			return str;
		}
	}
/*下拉框数据绑定
 * url为ajax调用地址
 * selectID为下拉框ID
 * selectedVal初始被选中的value值
 * */
function getSelectList(url,selectID,selectedVal)
{
	$.ajax({
		type: "GET",
		url: url,
		dataType:"json",
		success: function(data){
			var option = '<option value=""></option>';
			$.each(data,function(i,item){
				if(item.value==selectedVal){
					option += '<option selected="selected" value=' + item.value + '>' + item.label + '</option>';
				}
				else{
				 option += '<option value=' + item.value + '>' + item.label + '</option>';
				}
			});
			$("#"+selectID).empty().append(option);
        },
		error:function(data){
		}
	});	
	
}
/*图片格式验证
 * id为上传控件的ID
 * 目前仅验证jpg|gif|png|bmp
 * */
function imgFormatValidate(id){
	
    var url = $("#"+id).val();
    var filename = url.substring(url.lastIndexOf(".") + 1).toLowerCase();
    if (!/\.{|gif|jpg|jpeg|bmp|png|}$/.test(filename))
{
    	asyncbox.alert("图片格式有误，请选择jpg|jpeg|gif|png|bmp格式的图片上传","信息窗口"); 
        return false;

}
    return true;
}
/*文件格式验证
 * id为上传控件的ID
 * 目前仅验证doc|docx|pdf
 * */
function fileFormatValidate(id){
    var url = $("#"+id).val();
    var filename = url.substring(url.lastIndexOf(".") + 1).toLowerCase();
    if (!/\.{|doc|docx|pdf|}$/.test(filename))
{
    	asyncbox.alert("文件格式有误，请选择doc|docx|pdf格式的文件上传","信息窗口"); 
        return false;

}return true;
}

/*视频格式验证
 * id为上传控件的ID
 * 目前仅验证mp4
 * */
function videoFormatValidate(id){
    var url = $("#"+id).val();
    var filename = url.substring(url.lastIndexOf(".") + 1).toLowerCase();
    if (!/\.{|mp4|}$/.test(filename))
{
    	alert("文件格式有误，请选择mp4格式的视频上传","信息窗口");
    	asyncbox.alert("文件格式有误，请选择mp4或wmv格式的视频上传","信息窗口"); 
        return false;

}return true;
}
/**
 * 去除左右空格
 * @param str 
 * @returns
 */
function trim(str){   
    return str.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   
} 