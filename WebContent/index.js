function btnJoin_OnClick(){
	var hdnMd = document.getElementById("hdnMd");
	var frm = document.getElementById("frmLogin");
	//alert(hdnValue.value);
	hdnMd.value = "join";
	frm.submit();
	return true;
}
function btnSubmit_OnClick(){
	var hdnMd = document.getElementById("hdnMd");
	hdnMd.value = "login";
	return true;
}