/**
 * 未知，存放共用函数
 */
$(".topReturn").on("click",function(){
	history.go(-1);
	//window.location.reload();
});
function getFileUrl(sourceId) {
	var url;

	if (window.navigator.userAgent.indexOf("Chrome") >= 1 || window.navigator.userAgent.indexOf("Safari") >= 1){
		url = window.webkitURL.createObjectURL(document.getElementById(sourceId).files.item(0));
	}else{
		url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
	}

	
	return url;
}

/**
 * 判断选择的图片是否符合系统要求的方法
 * 
 * @param fileInput
 *            上传文件对象
 * @param size
 *            上传文件限制的大小
 * @param filePath
 *            上传到服务器的路径
 * @param imgId
 *            如果是图片，更换图片的id
 * @param data
 *            和上传文件同时提交的数据,格式{name : 'name1',age : 'age1'}
 */
function selectPictrue(fileInput, size, data) {
	var filePath = fileInput.val();// 获取选择文件路径
	var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();// 获取文件后缀名
	if (!checkFileImg(fileExt)) {// 判断选择的文件是否为图片文件
		//alert("您上传的文件不是图片,请重新选择！");
		return 'PIC';
	}
	if (size != 0) {
		if (fileInput[0].files && fileInput[0].files[0]) { // 谷歌浏览器判断文件大小
			var fileSize = toDecimal(fileInput[0].files[0].size / 1024 / 1024);
			if (fileSize > size) {
				//alert('你选择的文件大小为' + fileSize + 'M，请选择小于' + size + 'M的图片！');
				return 'SIZE';
			}
		} else {// IE浏览器判断文件大小
			fileInput[0].select();
			var url = document.selection.createRange().text;
			try {
				var fso = new ActiveXObject("Scripting.FileSystemObject");
			} catch (e) {
				//alert('如果你用的是ie 请将安全级别调低！');
				return 'IE';
			}
			var fileSize = toDecimal(fso.GetFile(url).size / 1024 / 1024);
			if (fileSize > size) {
				//alert('你选择的文件大小为' + fileSize + 'M，请选择小于' + size + 'M的图片！');
				return 'SIZE';
			}
		}
	}
}


/**
 * 判断上传的文件是否为图片
 * 
 * @param imgName
 *            文件名
 * @returns 是否为图片
 */
function checkFileImg(imgName) {
	if (!imgName.match(/.jpg|.png|.jpeg/i)) {
		return false;
	}
	return true;
}
/**
 * 保留两位小数 功能：将浮点数四舍五入，取小数点后2位
 * 
 * @param x
 *            需要保留两位小数的数
 * @returns 保留两位小数后的数
 */
function toDecimal(x) {
	var f = parseFloat(x);
	if (isNaN(f)) {
		return;
	}
	f = Math.round(x * 100) / 100;
	return f;
}


