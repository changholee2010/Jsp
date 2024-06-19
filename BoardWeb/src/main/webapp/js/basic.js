/**
 * basic.js
 */
// DOM 연습.
document.querySelector('ul#fruit>li').innerHTML = '사과';
// LI 생성.
let li = document.createElement('li'); // <li></li>
li.innerHTML = '<b>오렌지</b>'; // <li><b>오렌지</b></li>

document.querySelector('ul#fruit').appendChild(li);
document.querySelector('ul#fruit>li').remove();
document.querySelector('ul#fruit>li').style.backgroundColor = 'yellow';

console.log(document.querySelectorAll('#fruit>li'));
let lists = document.querySelectorAll('#fruit>li');
for (let list of lists) {
	console.log(list)
	//버튼생성.
	let btn = document.createElement('button');
	btn.innerText = '삭제';
	btn.setAttribute('class', 'btn btn-primary');
	btn.addEventListener('click', function () {
		btn.parentElement.remove();
	}); // 이벤트유형, 실행코드
	list.appendChild(btn);
}