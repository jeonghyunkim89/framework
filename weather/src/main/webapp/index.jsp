<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>날씨</title>

    <!-- bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="resources/weather.js"></script>
</head>
<body>
    <div class="container bg-dark text-light mt-5 p-5">
        <h2>중기예보 정보</h2>
        <hr/>
        <select id="location" class="w-25 d-inline form-select">
            <option value="11B10101">서울</option>
            <option value="11B20201">인천</option>
            <option value="11B20601">수원</option>
            <option value="11C10301">청주</option>
            <option value="11C20602">논산</option>
            <option value="11D20501">강릉</option>
            <option value="11D20601">동해</option>
        </select>
        <a id="btn1" class="btn btn-secondary" data-bs-toggle="collapse" href="#todayWeather">날씨 조회</a>
        현재 시간 : <span id="now"></span>
        <div class="collapse" id="todayWeather">
            <br/>
            <div class="card w-75">
                <div class="card-body">
                    <h5 class="card-title">3일 후 예상 기온</h5>
                    
                    <p id="after3" class="card-text"></p>

                    <h5  class="card-title">7일 후 예상 기온</h5>
                    
                    <p id="after7" class="card-text"></p>
                    
                    <h5  class="card-title">10일 후 예상 기온</h5>
                    
                    <p id="after10" class="card-text"></p>

                </div>
            </div>
        </div>
        <hr/>
        <h5>UI 참고</h5>
        <div class="card w-75">
            <div class="card-body">
                <h5 id="after3" class="card-title">예상 기온</h5>
                
                <p class="card-text">
                    <span class="badge text-bg-primary">최고기온 &#8451;</span>
                    <span class="badge text-bg-danger">최저기온 &#8451;</span>
                </p>
            </div>
        </div>
    </div>
</body>
</html>