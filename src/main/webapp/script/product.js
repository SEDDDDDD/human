/**
*
*/

function checkProduct() {
	if (document.frm.name.value.length === 0) {
		alert("상품명을 입력하세요.");
		frm.name.focus();
		return false;
	}
	if (document.frm.price.value.length === 0) {
		alert("가격을 입력하세요.");
		frm.price.focus();
		return false;
	}
	if (isNaN(document.frm.price.value)) {
		alert("숫자로 입력하세요.");
		frm.price.focus();
		return false;
	}
	return true;
}

// 이미지 자동 전환
	var index = 0; //이미지에 접근하는 인덱스
	window.onload = function(){
	slideShow();
	}

function slideShow() {
	var i;
	var x = document.getElementsByClassName("slide1"); //slide1에 대한 dom 참조
	for (i = 0; i < x.length; i++) {
		x[i].style.display = "none"; //처음에 전부 display를 none으로 한다.
	}
	index++;
	if (index > x.length) {
		index = 1; //인덱스가 초과되면 1로 변경
	}
	x[index - 1].style.display = "block"; //해당 인덱스는 block으로
	setTimeout(slideShow, 4000); //함수를 4초마다 호출
}

// 상세 페이지 하단 탭 전환
function openTap(evt, tapName) {
	// Declare all variables
	var i,
		tabcontent,
		tablinks;

	// Get all elements with class="tabcontent" and hide them
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Get all elements with class="tablinks" and remove the class "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i]
			.className
			.replace(" active", "");
	}

	// Show the current tab, and add an "active" class to the button that opened the
	// tab
	document
		.getElementById(tapName)
		.style
		.display = "block";
	evt.currentTarget.className += " active";
}















