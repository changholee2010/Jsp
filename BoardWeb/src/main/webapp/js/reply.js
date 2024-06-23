/**
 * reply.js
 */

// 댓글목록 출력하기.
let page = 1;
svc.replyList({ bno, page }, replyListFnc);

// 댓글등록 이벤트.
document.getElementById('addReply').addEventListener('click', addReplyFnc);

// 페이징 a 태그를 클릭하면 목록보여주기.
document.querySelectorAll('div.pagination a').forEach(item => {
	item.addEventListener('click', function(e) {
		e.preventDefault(); // 페이지이동하는 기본기능 차단.
		page = item.innerHTML;
		svc.replyList({ bno, page }, replyListFnc);
	})
});

// 댓글건수를 활용해서 페이징계산하고 목록출력.

function makePagingFnc() {
	svc.replyTotalCnt(bno, createPagingList);
}
let pagination = document.querySelector('div.pagination');
function createPagingList() {
	let totalCnt = this.responseText;
	console.log(totalCnt); // 632건/5 => 127page.
	let startPage, endPage;
	let prev, next;
	let realEnd = Math.ceil(totalCnt / 5); // 127페이지.

	endPage = Math.ceil(page / 10) * 10; // 130page.
	startPage = endPage - 9; // 121페이지.
	endPage = endPage > realEnd ? realEnd : endPage;

	prev = startPage > 1;
	next = endPage < realEnd;

	// startPage, endPage, prev, next => a태그생성.
	pagination.innerHTML = '';
	if (prev) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', startPage - 1);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = "&laquo;";
		pagination.appendChild(aTag);
	}

	for (let p = startPage; p <= endPage; p++) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', p);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = p;
		if (page == p) {
			aTag.className = 'active';
		}
		pagination.appendChild(aTag);
	}

	if (next) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', endPage + 1);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = "&raquo;";
		pagination.appendChild(aTag);
	}

	document.querySelectorAll('div.pagination a').forEach(item => {
		item.addEventListener('click', function(e) {
			e.preventDefault(); // 페이지이동하는 기본기능 차단.
			page = item.dataset.page;
			svc.replyList({ bno, page }, replyListFnc);
		})
	});

} // end of createPagingList().

// 등록실행함수. bno, 
function addReplyFnc() {
	if (!replyer) {
		alert('로그인하세요!!');
		return;
	}

	let reply = document.getElementById('reply').value;
	if (!reply) {
		alert('댓글을 등록하세요!!');
		return;
	}

	svc.addReply({ replyer, reply, bno }, addReplyCallback);
} // end of addReplyFnc()

function addReplyCallback() {
	// 화면에 댓글정보목록에 추가.
	let result = JSON.parse(this.responseText);
	if (result.retCode == 'OK') {
		alert('등록성공!!');
		page = 1;
		svc.replyList({ bno, page }, replyListFnc);
		document.getElementById('reply').value = '';
	} else {
		alert('실패=> ' + result.retVal);
	}
} // end of addReplyCallback().

// replyList의 매개값으로 사용될 함수.
function replyListFnc() {
	// 5개댓글을 지워주고 새롭게 목록출력.
	document.querySelectorAll('div.content>ul>li').forEach((item, idx) => {
		if (idx > 2) {
			item.remove();
		}
	});
	let data = JSON.parse(this.responseText);
	console.log(data);
	data.forEach(reply => {
		let li = cloneRow(reply);
		document.querySelector('div.content>ul').appendChild(li);
	})

	makePagingFnc();
} // end of replyListFnc().

Date.prototype.format = function() {
	let yyyy = this.getFullYear();
	let mm = this.getMonth() + 1;
	let dd = this.getDate();

	let hh = this.getHours();
	let mi = this.getMinutes();
	let ss = this.getSeconds();

	return yyyy + '-' + ('0' + mm).slice(-2) + '-' + ('0' + dd).slice(-2) + ' '//
		+ ('0' + hh).slice(-2) + ':' + ('0' + mi).slice(-2) + ":" + ('0' + ss).slice(-2);
}

// 댓글정보 -> li 생성.
function cloneRow(reply = {}) {
	let format_date = new Date(reply.replyDate).format();

	let li = document.querySelector('div.content>ul>li:nth-of-type(3)')//
		.cloneNode(true);

	li.style.display = 'block';
	li.setAttribute('id', reply.replyNo);
	li.querySelector('span:nth-of-type(1)').innerHTML = reply.replyNo;
	li.querySelector('span:nth-of-type(2)').innerHTML = reply.reply;
	li.querySelector('span:nth-of-type(3)').innerHTML = reply.replyer;
	li.querySelector('span:nth-of-type(4)').innerHTML = format_date;
	return li;
} // end of cloneRow(reply = {}).

// 한건삭제.
function deleteRow(e) {
	let li = e.target.parentElement.parentElement;
	let rno = li.getAttribute('id');

	svc.removeReply(rno, deleteReplyFnc);
	// removeReply메소드의 매개값으로 전달될 함수. 화면에서 한건 지우기.
	function deleteReplyFnc() {
		let result = JSON.parse(this.responseText);
		if (result.retCode == 'OK') {
			alert(result.retMsg);
			svc.replyList({ bno, page }, replyListFnc);
		} else {
			alert('Error=>' + result.retMsg);
		}
	}
} // end of deleteRow(e).
