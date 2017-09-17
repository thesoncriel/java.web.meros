function cmbAtt_Change(str){
	var obj = document.getElementById("cmb" + str);
	document.getElementById("hdn" + str).value = obj.selectedIndex;
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


function chkRow_Change(chkRow){
	var tblData = document.getElementsByTagName("table");
	var index = -1;
	var parent = chkRow.parentNode.parentNode.parentNode.parentNode;
	var num = 0;
	//alert("하하");
	for(var i = 0; i < tblData.length; i+=2){
		//alert(tblData[i].innerHTML);
		if(parent == tblData[i]){
			
			index = num;
			break;
		}
		num++;
	}
	//alert(index);
	chkRow.value = index;
}

function chkRowAll(chkAll){
	var chkRow = document.getElementsByName("chkRow");
	//alert(chkRow);
	for(var i = 0; i < chkRow.length; i++){
		if(chkAll){
			chkRow[i].checked = true;
			chkRow[i].value = i;
		}
		else{
			chkRow[i].checked = false;
			chkRow[i].value = null;
		}
	}
}


function chkList_init(val){
	var chkList = document.getElementsByName("chkList");
	var tblData = document.getElementsByTagName("table");
	
	var con = 1;
	for(var i = 0; i < chkList.length; i++){
		if((val & con) == con){
			chkList[i].checked = true;
		}
		else{
			chkList[i].checked = false;
		}
		
		if(i == 0){
			//alert(tblData[i*2].rows[0].childNodes[1].style.display);
			for(var j = 0; j < tblData.length; j+=2){
				tblData[j].rows[0].cells[1].style.display = (chkList[0].checked)? "inline" : "none";
			}
		}
		else{
			for(var j = 1; j < tblData.length; j+=2){
				
				tblData[j].rows[i-1].style.display = (chkList[i].checked)? "inline" : "none";
			}
		}
		con *= 2;
	}
}

function chkList_Click(chkMe){
	var chkList = document.getElementsByName("chkList");
	var tblData = document.getElementsByTagName("table");
	var index = 0;

	for(var i = 0; i < chkList.length; i++){
		if(chkMe == chkList[i]){
			index = i;
			break;
		}
	}
	//alert(tblData);
	for(var i = 1; i < tblData.length; i+=2){
		if(index == 0){
			tblData[i-1].rows[0].cells[1].style.display = (chkMe.checked)? "inline" : "none";
		}
		else{
			tblData[i].rows[index-1].style.display = (chkMe.checked)? "inline" : "none";
		}
	}

}

function a_Click(db, ao){
	var tblData = document.getElementsByTagName("table");
	var index = 0;
	var parent = ao.parentNode.parentNode.parentNode.parentNode;
	for(var i = 1; i < tblData.length; i+=2){
		if(parent == tblData[i]){
			break;
		}
		index++;
	}
	ao.href = "documentview.jsp?db=" + db + "&row=" + index;
	return true;
}