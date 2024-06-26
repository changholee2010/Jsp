/**
 * public.js
 */
let url = 'https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=qCwQYxNXeK%2FaB1Ngf9oNZDttjmjQ6ku1OdR6%2Fd0Jj5EIdqOxMXolplih%2BYjTqB4uxCuK636co3tf9T5%2Fr9OLvw%3D%3D';

// 센터정보생성.
document.getElementById('centerDB').addEventListener('click', createCenterInfo);

function createCenterInfo() {
	// 1.open API 호출.
	// 2.컨트롤 호출 DB입력.
	fetch(url)
		.then(result => result.json())
		.then(result => {
			let centers = result.data; // [{},{},{}] => [{"id":"hong"}]
			return fetch('centerInfo.do', {
				method: 'post', // 전달되는 값이 body영역에 저장.
				headers: { 'Content-Type': "application/json" }, //키=값&키=값
				body: JSON.stringify(centers) // 객체 -> json문자열.
			})
		})
		.then(result => result.json()) // {"txnCnt": 3}
		.then(result => {
			console.log(result);
			if (result.txnCnt > 0) {
				alert(result.txnCnt + '건 처리 성공');
			} else {
				alert('실패');
			}
		})
		.catch(err => console.log(err));
}


let centerList = []; // 검색된 센터의 전체정보를 담아놓는 용도.
let sidoList = []; // 시도목록을 담아놓는 용도.
const target = document.querySelector('#centerList'); // 하위목록.
const selectSido = document.querySelector('#searchList'); // select태그.

fetch(url) // promise객체로 반환.
	.then(result => result.json()) // [{"id":1, "center.."},{},{}] -> [{},{}]
	.then(result => {
		console.log(result)
		centerList = result.data; // 전역변수 centerList에 저장.
		result.data.forEach((center, idx) => {
			target.appendChild(makeRow(center));
		});
		// 시도리스트 만들기.
		result.data.forEach(center => {
			if (sidoList.indexOf(center.sido) == -1) {
				sidoList.push(center.sido);
			}
		})
		sidoList.forEach(sido => {
			let opt = document.createElement('option');
			opt.value = sido;
			opt.innerHTML = sido;
			selectSido.appendChild(opt);
		})

	})
	.catch(err => console.log(err));

// 2) 주소검색 기능.
//document.querySelector('#findBtn').addEventListener('click', searchByAddress);
document.querySelector('#search').addEventListener('change', searchByAddress);
selectSido.addEventListener('change', searchBysido);

// 시도로 검색해서 목록.
function searchBysido() {
	target.innerHTML = '';
	centerList.filter(center => center.sido == this.value) //
		.forEach(center => target.appendChild(makeRow(center)));
}

// 주소검색해서 목록.
function searchByAddress() {
	// 목록지우고 다시 그리기.
	target.innerHTML = '';
	let searchWord = document.querySelector('#search').value;
	if (!searchWord) {
		alert('검색조건을 입력하세요.');
		return;
	}
	// 검색결과출력.
	// centerList.filter(center => center.address.indexOf(searchWord) != -1) //
	// 	.forEach(center => target.appendChild(makeRow(center)));

	// 검색키워드 굵게 표시하기.
	centerList.reduce((acc, center) => {
		if (center.address.indexOf(searchWord) != -1) {
			let tr = makeRow(center);
			// 검색조건결과를 <b>태그로 감싸기.
			let tr_trans = center.address.replace(searchWord, '<b>' + searchWord + '</b>');
			tr.querySelector('td:nth-of-type(4)').innerHTML = tr_trans;
			acc.appendChild(tr);
		}
		return acc;
	}, target);
	// 검색조건 초기화.
	document.querySelector('#search').value = '';
}

// 1) 목록을 출력하기.
const fields = ['id', 'centerName', 'phoneNumber', 'address'];

function makeRow(center = {}) {
	let tr = document.createElement('tr');
	tr.addEventListener('click', function() {
		//location.href = "map.do?x=" + center.lat + "&y=" + center.lng;
		window.open("map.do?x=" + center.lat + "&y=" + center.lng + "&cn=" + center.centerName.replace("코로나19 ", ""));
	});
	fields.forEach(field => {
		let td = document.createElement('td');
		td.innerHTML = center[field];
		tr.appendChild(td);
	});
	return tr;
} // end of makeRow().
// end of program.