/**
 * ajax3.js
 */
// 목록가져오기.
const xthp = new XMLHttpRequest();
xthp.open('get', 'membersAjax.do');
xthp.send();
xthp.onload = function() {
	console.log(xthp);
	let data = JSON.parse(xthp.responseText);
	data.forEach(user => {
		document.getElementById('list').appendChild(makeRow(user))
	})
}
// json 문자열의 필드이름을 활용.
const fields = ['userId', 'userName', 'userPw', 'responsibility'];
function makeRow(obj = {}) {
	let tr = document.createElement('tr');
	tr.setAttribute('id', obj.userId); // <tr id='user01'  ....>
	tr.addEventListener('dblclick', function(e) {
		document.getElementById('myModal').style.display = 'block';
		// 선택된 사용자의 이름, 비번을 모달에 출력.
		console.log(e, this);
		document.getElementById('modify_name').value = //
			this.children[1].innerHTML;
		document.getElementById('modify_pass').value = //
			this.children[2].innerHTML;
		document.getElementById('modify_id').value = //
			this.children[0].innerHTML;
	})
	fields.forEach(field => {
		let td = document.createElement('td');
		td.innerHTML = obj[field];
		tr.appendChild(td);
	});
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.setAttribute('data-delete-id', obj.userId);
	btn.addEventListener('click', removeMemberFnc);
	btn.innerHTML = '삭제';
	td.appendChild(btn);
	tr.appendChild(td);
	return tr;
} // end of makeRow().

function removeMemberFnc(e) {
	console.log(e);
	let did = this.dataset.deleteId; // data-delId
	did = e.target.parentElement.parentElement.children[0].innerText;
	let tr = document.getElementById(did);
	const delAjax = new XMLHttpRequest();
	delAjax.open('get', 'delAjax.do?id=' + did);
	delAjax.send();
	delAjax.onload = function() {
		let result = JSON.parse(delAjax.responseText);
		if (result.retCode == 'OK') {
			alert('정상삭제');
			tr.remove();
		}
	}
}

// 수정이벤트.
document.getElementById('modBtn').addEventListener('click', function() {
	let id = document.getElementById('modify_id').value;
	let name = document.getElementById('modify_name').value;
	let pass = document.getElementById('modify_pass').value;

	// ajax 생성.
	// 정상적으로 정보가 업데이트되면 화면 수정.
	// 수정이 안됐으면 화면수정X.

	let targetTr = document.getElementById(id); // 
	targetTr.children[1].innerHTML = name;
	targetTr.children[2].innerHTML = pass;

	// 모달창 닫기.
	document.getElementById('myModal').style.display = 'none';

})

// 등록이벤트.
document.getElementById('addBtn').addEventListener('click', function() {
	const formData = new FormData(); // form-data처리.
	const fileField = document.querySelector('#myPic');

	formData.append("id", document.getElementById('uid').value);
	formData.append("pw", document.getElementById('upw').value);
	formData.append("name", document.getElementById('uname').value);
	formData.append("myImage", fileField.files[0]);

	upload(formData);
})

// fetch 파일 업로드.
async function upload(formData) {
	try {
		const response = await fetch("addMember.do", {
			method: "PUT",
			body: formData,
		});
		const result = await response.json();
		if (result.retCode == 'OK') {
			alert('성공');
			let newMem = result.retVal;
			document.getElementById('list').appendChild(makeRow(newMem));
		} else {
			alert('등록처리중 예외발생.');
		}
	} catch (error) {
		console.error("실패:", error);
	}
} // end of upload(formData).

function addMemberFunc() {

	let id = document.getElementById('uid').value;
	let pw = document.getElementById('upw').value;
	let nm = document.getElementById('uname').value;
	let auth = document.getElementById('auth').value;

	const addAjax = new XMLHttpRequest();
	let url = 'addAjax.do?id=' + id + '&pw=' + pw + '&nm=' + nm + '&auth=' + auth;
	addAjax.open('get', url);
	addAjax.send();
	addAjax.onload = function() {
		let result = JSON.parse(addAjax.responseText);
		if (result.retCode == 'OK') {
			let newMem = { userId: id, userName: nm, userPw: pw, responsibility: auth }
			alert(result.retMsg);
			document.getElementById('list').appendChild(makeRow(newMem));

		} else {
			alert(result.retMsg);
		}

	}
} // end of addMemberFunc().

// id 체크 이벤트
document.getElementById('uid').addEventListener('change', function() {
	let checkId = this.value;

	const checkAjax = new XMLHttpRequest();
	checkAjax.open('get', 'checkIdAjax.do?id=' + checkId);
	checkAjax.send();
	checkAjax.onload = function() {
		let result = JSON.parse(checkAjax.responseText);
		if (result.retCode == 'Exist') {
			alert('이미 존재하는 아이디입니다.');
			document.querySelector('#addBtn').disabled = true;

		} else {
			alert('등록가능한 아이디입니다.');
			document.querySelector('#addBtn').disabled = false;

		}
	}

})