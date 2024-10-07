function enterkey() {
		if (window.event.keyCode == 13) {
			search();
		}
	}

function search() {
   let keyword = document.getElementById("keyword").value;
   let searchType = document.getElementById("searchType").value;
   let c_number = document.getElementById("c_number").value;
   
  	 $.ajax({
       url: "/professor/students/todate/search", 
       type: "GET", 
       data: {
      		      c_number : c_number,
			      keyword : keyword,
			      searchType : searchType
      		 }, 
       contentType: "application/json",
       success: function(response) { 
       		console.log(response);
         	$('#infoTable').empty(); 
          if(response.length == 0){
  				$("#searchNo").append(
  				`
  				   <h3>검색 결과가 없습니다.</h3>`
  				   )
            }else {
            values = response.attStuList;
            
	       	$("#infoTable").append(
	           `<tr>
	                  <th>학번</th>
	                  <th>이름</th>
	                  <th>출석</th>
	                  <th>지각</th>
	                  <th>조퇴</th>
	                  <th>결석</th>
	             </tr>`
	         );
	         $.each(values, function(index, value){
	            	$("#infoTable").append(
	                    `
	         			<tr id ="attList">
	                      <td name="id_${value.id}" value="${value.id}">${value.id}</td>
	                      <td name="name_${value.id}" value="${value.name}">${value.name}</td>
	                      <td name="attendance_${value.id}" value="${value.attendance}">${value.attendance}</td>
	                      <td name="tardy_${value.id}" value="${value.tardy}">${value.tardy}</td>
	                      <td name="early_${value.id}" value="${value.early}">${value.early}</td>
	                      <td name="absent_${value.id}" value="${value.absent}">${value.absent}</td>
	                     </tr>`
	  				);
	 		 });
	 		 }
       },
       error: function(xhr, status, error) { 
               alert(`수강생 검색에 실패하였습니다. 다시 시도해주세요.`);
       }
   });
   }