/**
 * board Form 유효성 검사
 */

/* const submitButton = document.getElementById("submitButton");
 const title = document.getElementById("title");
 const writer = document.getElementById("writer")
 
 submitButton.addEventListener("click", function(){
	 if(title.value== ''){
		 alert("제목은 필수 입니다.")
	 }
	 else if(writer.value== ''){
		 alert("작성자는 필수 입니다.")
	 }
	 else{
	 	console.log('submitButton click');
	 }
 });*/
 
 $("#submitButton").click(function(){
	 $("#contactForm").submit();
 });
 
 //<input type="file" name="boardFiles">
 
 let count=1;
 const max = 5;
 $("#fileAdd").click(function(){
	 if(count>=5){
		 alert("파일 첨부는 최대 "+max+"만큼만 가능합나디ㅏ.")
		 return;
	 }
	 let child = '<input type="file" name="boardFiles" style="margin-top:10px">';
	 $('#filelist').append(child);
	 count++;
 });
 
