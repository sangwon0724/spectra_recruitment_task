/**
 * JSON 형식의 GET/POST 요청을 보낼 수 있는 fetch 유틸 함수
 *
 * @param url 요청할 URL
 * @param method - 요청 방식
 * @param data 전송할 데이터 (GET일 경우 쿼리스트링으로 처리)
 * @param fnSuccess 성공했을 때의 처리
 * @param fnFail 실패했을 때의 처리
 */
async function fetchJson(url, method = 'GET', data = {}, fnSuccess, fnFail) {
    const options = {
        method,
        headers: {
            'Content-Type': 'application/json',
        },
    };

    if (method === 'POST' && data) {
        options.body = JSON.stringify(data);
    }

    if (method === 'GET' && data) {
        const queryParams = new URLSearchParams(data).toString();
        url += '?' + queryParams;
    }

    const response = await fetch(url, options);
    let res = await response.json();

    if (response.ok) {
        if(fnSuccess !== null && typeof fnSuccess === "function"){
            fnSuccess(res)
        }
    } else {
        if(fnFail !== null && typeof fnFail === "function"){
            fnFail(res)
        } else {
            alert(res.msg);
            return;
        }
    }
}
