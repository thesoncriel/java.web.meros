
function cmbAtt_Change(str){
	var obj = document.getElementById("cmb" + str);
	document.getElementById("hdn" + str).value = obj.selectedIndex;
}

function btnDoc_Click(mode){
	document.getElementById("hdnMd").Value = mode;
	return true;
}

function a_Click(db, ao){
	ao.href = "documentview.jsp?db=" + db + "&row=" + (ao.parentNode.parentNode.rowIndex - 1);
	return true;
}

function chkRow_Change(chkRow){
	chkRow.value = chkRow.parentNode.parentNode.rowIndex - 1;
}

function chkRowAll(chkAll){
	var chkRow = document.getElementsByName("chkRow");
	for(var i = 0; i < chkRow.length; i++){
		if(chkAll.checked){
			chkRow[i].checked = true;
			chkRow[i].value = chkRow[i].parentNode.parentNode.rowIndex - 1;
		}
		else{
			chkRow[i].checked = false;
			chkRow[i].value = null;
		}
	}
}

function btnNew_Click(db){
	location.href = "documentview.jsp?db=" + db;
}

function btnMod_Click(){
	var btnMod = document.getElementById("btnMod");
	location.href = "documentview.jsp?db=0&md=mod";
}
function btnIns_Click(){
	var md = document.getElementById("hdnMd");
	md.value = "ins";
	var frm = document.getElementById("frm");
	frm.action = "insert.jsp";
	setDate();
	setHorseType();
	frm.submit();
}

function btnDel_Click(){
	var frm = document.getElementById("frm");
	
	if(confirm("한번 지워진 데이터는 복구할 수 없습니다.\n계속 하시겠습니까?")){
		frm.action = "delete.jsp";
		frm.submit();
	}
}
function btnUpd_Click(){
	var md = document.getElementById("hdnMd");
	md.value = "upd";
	var frm = document.getElementById("frm");
	frm.action = "update.jsp";
	setDate();
	setHorseType();
	frm.submit();
}

function btnDeln_Click(){
	var frm = document.getElementById("frm");
	
	if(confirm("정말 탈퇴 하시겠습니까??\n레알?? ㅠ_ㅠ")){
		frm.action = "delete.jsp";
		frm.submit();
	}
}
function btnUpdn_Click(){
	var frm = document.getElementById("frm");
	frm.action = "update.jsp";
	setDate();
	setHorseType();
	alert("회원정보 변경에 성공 하였습니다 :)");
	frm.submit();
}

function btnBack_Click(){
	var frm = document.getElementById("frm");
	frm.action = "search.jsp";
	frm.submit();
}

function docInit(){
	try{
		var hdnAdmin = document.getElementById("hdnAdmin");
		var cmbAdmin = document.getElementById("cmbAdmin");
		for(var i = 0; i < cmbAdmin.length; i++){
			if(cmbAdmin.options[i].value == hdnAdmin.value){
				cmbAdmin.options[i].selected = "selected";
				break;
			}
		}
	}catch(e){}
	
	try{
		var hdnGender = document.getElementById("hdnGender");
		var cmbGender = document.getElementById("cmbGender");
		for(var i = 0; i < cmbGender.length; i++){
			if(cmbGender.options[i].value == hdnGender.value){
				cmbGender.options[i].selected = "selected";
				break;
			}
		} 
	}catch(e){}
	
	try{
		var hdnHId = document.getElementById("hdnHId");
		var cmbHId = document.getElementById("cmbHId");
		for(var i = 0; i < cmbHId.length; i++){
			if(cmbHId.options[i].value == hdnHId.value){
				cmbHId.options[i].selected = "selected";
				break;
			}
		} 
	}catch(e){}
	
	try{
		var imgPic = document.getElementById("imgPic");
		//alert(imgPic.src);
		var loc = "http://" + location.host + "/WebMeroz/";
		//alert(loc == imgPic.src);
		//loc.replace(location., replaceValue)
		if(imgPic.src == loc){
			imgPic.src = "img/temp.png";
		}
	}catch(e){}
	
	try{
		var tblInput = document.getElementById("tblInput");
		var btnIns = document.getElementById("btnIns");
		var btnUpd = document.getElementById("btnUpd");
		var btnDel = document.getElementById("btnDel");
		//alert(tblInput.rows[0].cells[1].childNodes[0].value == "");
		if(tblInput.rows[0].cells[1].childNodes[0].value == ""){
			btnUpd.style.display = "none";
			btnDel.style.display = "none";
		}
		else{
			btnIns.style.display = "none";
		}
	}catch(e){}
}

function bindDate(){
	try{
		var hd = document.getElementById("hdnDate");
		var str = hd.value;
		str = str.split("-");
		
		var y = document.getElementById("cmbYear");
		var m = document.getElementById("cmbMonth");
		var d = document.getElementById("cmbDay");
		
		for(var i = 0; i <= 30; i++){
			var opt = document.createElement("option");
			opt.value = i + 1990;
			opt.text = i + 1990;
			if(opt.value == str[0]) opt.selected = "selected";
			try{
				y.add(opt);
			}catch(ex){
				y.add(opt, null);
			}
		}
		for(var i = 1; i <= 12; i++){
			var opt = document.createElement("option");
			opt.value = i;
			opt.text = i;
			if(opt.value == str[1]) opt.selected = "selected";
			try{
				m.add(opt);
			}catch(ex){
				m.add(opt, null);
			}
		}
		for(var i = 1; i <= 31; i++){
			var opt = document.createElement("option");
			opt.value = i;
			opt.text = i;
			if(opt.value == str[2]) opt.selected = "selected";
			try{
				d.add(opt);
			}catch(ex){
				d.add(opt, null);
			}
		}
	}catch(e){
		alert(e);
	}
	
	try{
		var tblInput = document.getElementById("tblInput");
		var btnIns = document.getElementById("btnIns");
		var btnUpd = document.getElementById("btnUpd");
		var btnDel = document.getElementById("btnDel");
		//alert(tblInput.rows[0].cells[1].childNodes[0].value == "");
		if(tblInput.rows[0].cells[1].childNodes[0].value == ""){
			btnUpd.style.display = "none";
			btnDel.style.display = "none";
		}
		else{
			btnIns.style.display = "none";
		}
	}catch(e){}
}

function bindHorseType(){
	try{
		var ht = document.getElementById("hdnType");
		var t = document.getElementById("cmbType");
		//alert(ht.value + " : " + t);
		for(var i = 0; i < t.options.length; i++){
			//alert(t.options[i].text == ht.value);
			if(t.options[i].text == ht.value){
				
				t.options[i].selected = "selected";
				break;
			}
		}
	}
	catch(ex){
		alert(ex);
	}
}

function setDate(){
	try{
		var hd = document.getElementById("hdnDate");
		
		var y = document.getElementById("cmbYear");
		var m = document.getElementById("cmbMonth");
		var d = document.getElementById("cmbDay");
		
		var year = y.options[y.selectedIndex].value;
		var month = m.options[m.selectedIndex].value;
		var day = d.options[d.selectedIndex].value;
		
		month = (month < 10)? "0" + month : month;
		day = (day < 10)? "0" + day : day;
		
		hd.value = year + "-" + month + "-" + day;
	}
	catch(ex){}
}

function setHorseType(){
	try{
		var ht = document.getElementById("hdnType");
		var t = document.getElementById("cmbType");
		//alert(t);
		ht.value = t; 
	}
	catch(ex){}
}

var keycount = 0;
function koko(e){
	keycount++;
	//alert(e.keycode);
}