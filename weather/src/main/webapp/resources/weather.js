    $(function(){
    	// 현재 시간을 표시하는 함수
        function updateTime() {
            const now = new Date();
            const year = now.getFullYear();
            const month = String(now.getMonth() + 1).padStart(2, '0');
            const day = String(now.getDate()).padStart(2, '0');
            const hours = String(now.getHours());
            const minutes = String(now.getMinutes()).padStart(2, '0');
            const seconds = String(now.getSeconds()).padStart(2, '0');
            const AMPM = hours > 12 ? "오후":"오전";
            const currentTime = year+". "+month+". "+day+" "+AMPM+" "+hours+":"+minutes+":"+seconds;
            
            $("#now").text(currentTime);
        }
        
        // 페이지 로딩 시 바로 시간을 업데이트하고, 이후 1초마다 갱신
        updateTime();
        setInterval(updateTime, 1000);
    	
    	$("#btn1").click(function(){
    		$.ajax({
    			url: 'weather.do',
    			data: { location: $("#location").val()},
    			success: function(result){
    				
    				console.log(result);
    				const data = result.response.body.items.item;
    				
    				let after3 = "<span class='badge text-bg-primary'>"+ data[0].taMin3 +"&#8451;</span>"
                    			+ "<span class='badge text-bg-danger'>"+ data[0].taMax3+"&#8451;</span>"
                    			
                    $("#after3").html(after3);
                    
    				let after7 = "<span class='badge text-bg-primary'>"+ data[0].taMin7 +"&#8451;</span>"
        			+ "<span class='badge text-bg-danger'>"+ data[0].taMax7 +"&#8451;</span>"
		
        			$("#after7").html(after7);
        			
    				let after10 = "<span class='badge text-bg-primary'>"+ data[0].taMin10 +"&#8451;</span>"
        			+ "<span class='badge text-bg-danger'>"+ data[0].taMax10 +"&#8451;</span>"
        		 	
        			$("#after10").html(after10);
    			},
				error: function(err) {
					console.log(err);
				}
    		});
    	});
    });
