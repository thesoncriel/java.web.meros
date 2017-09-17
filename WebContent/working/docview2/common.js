
function cmbAtt_Change(str){
	var obj = document.getElementById("cmb" + str);
	document.getElementById("hdn" + str).value = obj.selectedIndex;
}

function btnDoc_Click(mode){
	document.getElementById("hdnMd").Value = mode;
	return true;
}

function a_Click(db, ao){
	location.href = "documentview.jsp?db=" + db + "&row=" + (ao.parentNode.parentNode.rowIndex - 1);
}

function chkRow_Change(chkRow){
	chkRow.value = chkRow.parentNode.parentNode.rowIndex - 1;
}

function btnNew_Click(db){
	location.href = "documentview.jsp?db=" + db;
}

function btnDel_Click(){
	var frm = document.getElementById("frm");
	
	if(confirm("한번 지워진 데이터는 복구할 수 없습니다.\n계속 하시겠습니까?")){
		frm.action = "delete.jsp";
		frm.submit();
	}
}

function btnIns_Click(){
	var md = document.getElementById("hdnMd");
	md.Value = "ins";
	var frm = document.getElementById("frm");
	frm.action = "insert.jsp";
	setDate()
	frm.submit();
}

function btnUpd_Click(){
	var md = document.getElementById("hdnMd");
	md.Value = "upd";
	var frm = document.getElementById("frm");
	frm.action = "insert.jsp";
	setDate()
	frm.submit();
}

function cmbBind(){
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
			imgPic.src = "img/temp.png";		}
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
		
		hd.value = y.options[y.selectedIndex].value + "-" + m.options[m.selectedIndex].value + "-" + d.options[d.selectedIndex].value; 
	}
	catch(ex){}
}

function setHorseType(){
	try{
		var ht = document.getElementById("hdnType");
		var t = document.getElementById("cmbType");
		alert(t);
		ht.value = t; 
	}
	catch(ex){}
}