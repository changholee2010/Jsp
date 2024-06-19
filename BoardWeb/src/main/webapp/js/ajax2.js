/**
 * ajax2.js
 * XMLHttpRequest 객체.
 */
const xhtp = new XMLHttpRequest();
xhtp.open('get', 'data/MOCK_DATA.json');
xhtp.send();
xhtp.onload = function() {
	let data = JSON.parse(xhtp.responseText); // json문자열 -> 자바스크립트 객체.
	//console.log(data);
	// DOM 활용해서 페이지 작성.
	data.forEach(function(emp) {
		console.log(emp);
		document.getElementById('list').appendChild(makeRow(emp));
	});
}

let fields = ['id', 'first_name', 'email', 'salary'];
function makeRow(obj = {}) {
	let tr = document.createElement('tr');
	fields.forEach(field => {
		let td = document.createElement('td');
		td.innerHTML = obj[field];
		tr.appendChild(td);
	})
	return tr;
} // end of makeRow().

const xhtm = new XMLHttpRequest();
xhtm.open('get', 'loginForm.do');
xhtm.send();
xhtm.onload = function() {
	console.log(xhtm);
	// document.getElementById('show').innerHTML = xhtm.responseText;
}

console.log('end');