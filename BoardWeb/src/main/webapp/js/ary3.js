/**
 * ary3.js
 */
let sum = [10, 20, 30, 40, 50].reduce(function (acc, elem, idx, ary) {
	console.log(acc, elem); //, idx, ary);
	if (acc > elem) {
		return acc;
	}
	return elem;
}, 0);
console.log('최대값: ', sum);

['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'].reduce(function (acc, elem) {
	let li = document.createElement('li');
	li.innerHTML = elem;
	acc.appendChild(li);

	return acc;
}, document.getElementById('fruit'));