/**
 * public.js
 */
let url = 'https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=qCwQYxNXeK%2FaB1Ngf9oNZDttjmjQ6ku1OdR6%2Fd0Jj5EIdqOxMXolplih%2BYjTqB4uxCuK636co3tf9T5%2Fr9OLvw%3D%3D';

let centerList = []; // 검색된 센터의 전체정보를 담아놓는 용도.
let sidoList = []; // 시도목록을 담아놓는 용도.
const target = document.querySelector('#centerList'); // 하위목록.
const selectSido = document.querySelector('#searchList'); // select태그.

fetch(url)
	.then(result => result.json()) // [{"id":1, "center.."},{},{}] -> [{},{}]
	.then(result => {
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

	});

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
	fields.forEach(field => {
		let td = document.createElement('td');
		td.innerHTML = center[field];
		tr.appendChild(td);
	});
	return tr;
} // end of makeRow().
// end of program.