/**
 * dom1.js
 */
document.getElementById('fruit').style.display = 'none';

document.querySelector('table.table tr:nth-of-type(5)')//
	.setAttribute('align', 'center'); // 가운데 정렬.

// 페이지 로딩하면서 회원3명 출력.
console.log(myMembers);
for (let mem of myMembers) {
	document.getElementById('memberList')//
		.appendChild(addRow(mem));
}

// 추가버튼 클릭이벤트 등록.
document.getElementById('addBtn').addEventListener('click', addMemberFnc);
document.getElementById('modBtn').addEventListener('click', modMemberFnc);
document.getElementById('delBtn').addEventListener('click', removeMemberFnc);

// 힌트.
document.querySelector('thead input[type="checkbox"]').addEventListener('change', allCheckFnc);
// this => 1.이벤트(이벤트대상), 2.함수(window), 3.객체(객체자신)
const obj = {
	name: '홍길동',
	age: 20,
	showInfo() {
		return '이름은 ' + this.name + ', 나이는 ' + this.age;
	}
}
obj.showInfo();

function sum(a, b) {
	return a + b;
}

let sum = (a, b) => a + b;

let result = sum(10, 20);


// 전체선택.
function allCheckFnc() {
	// tbody의 하위에 있는 모든 input[type="checkbox"]의 속성을 변경.
	console.log(this.checked); // 활용하면 편하게 작업이 가능.
	//let checkVal = this.checked;
	document.querySelectorAll('tbody#memberList tr')// NodeList [tr, tr, tr.....]
		.forEach(item => item.children[5].children[0].checked = this.checked);
}

// 선택삭제.
function removeMemberFnc() {
	let targetTr = document.querySelectorAll('#memberList tr');
	for (let tr of targetTr) {
		console.log(tr.children[5].children[0].checked);
		if (tr.children[5].children[0].checked) {
			tr.remove();
		}
	}
}

function modMemberFnc() {
	let targetTr = document.querySelectorAll('#memberList tr');
	// 입력화면의 회원아이디 값을 가져와서 mid변수저장.
	let mid = document.getElementById('mid').value;
	let mname = document.getElementById('mname').value;
	for (let tr of targetTr) {
		console.log(tr.children[0].innerHTML, mid);
		if (tr.children[0].innerHTML == mid) {
			tr.children[1].innerHTML = mname;
			tr.children[2].innerHTML = document.getElementById('mphone').value;
			tr.children[3].innerHTML = document.getElementById('mpoint').value;
		}
	}
} // end of modMemberFnc

function addMemberFnc() {
	const id = document.getElementById('mid').value;
	const name = document.getElementById('mname').value;
	const phone = document.getElementById('mphone').value;
	const point = document.getElementById('mpoint').value;

	if (!id || !name || !phone || !point) {
		alert('필수값을 입력.');
		return;
	}

	document.getElementById('memberList')//
		.appendChild(addRow({ id, name, phone, point }));

	document.getElementById('mid').value = "";
	document.getElementById('mname').value = "";
	document.getElementById('mphone').value = "";
	document.getElementById('mpoint').value = "";

}	// end of addMemberFnc()

function addRow(member = { id, name, phone, point }) {
	// tr > td * 4
	const tr = document.createElement('tr');
	tr.addEventListener('click', showDetailFnc);

	for (let prop in member) {
		const td = document.createElement('td');
		td.innerHTML = member[prop];
		tr.appendChild(td);
	}
	// 삭제버튼 생성.
	// <td><button>삭제</button></td>
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.addEventListener('click', removeTrElement);
	btn.innerText = '삭제';
	td.appendChild(btn);
	tr.appendChild(td);
	// 체크박스 생성.
	td = document.createElement('td');
	btn = document.createElement('input');
	btn.setAttribute('type', 'checkbox');
	td.appendChild(btn);
	tr.appendChild(td);

	return tr;
} // end of addRow()

// 이벤트핸들러.
function removeTrElement(e) {
	console.log('btn', e);
	e.stopPropagation(); // 상위요소로 이벤트전파 차단.
	console.log(this.parentElement.parentElement.remove());
}

function showDetailFnc(e) {
	console.log('tr', e);
	// tr의 자식요소의 값을 입력 input에 반환.
	console.dir(this.children[1].innerText);
	document.getElementById('mid').value = this.children[0].innerText;
	document.getElementById('mname').value = this.children[1].innerText;
	document.getElementById('mphone').value = this.children[2].innerText;
	document.getElementById('mpoint').value = this.children[3].innerText;

}
