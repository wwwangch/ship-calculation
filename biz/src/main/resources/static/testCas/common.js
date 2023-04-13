//后台服务地址
let backendUrlPrefix='http://127.0.0.1:7901/demo/';
//cas服务地址
let casServerPrefix='http://192.168.1.11:8080/cas/';
//后台校验st的url
let backendValidUrl='cas/valid';

/**通过TGT获取ST*/
function getST(location) {
    $.ajax({
        url: location,
        data: {
            service: backendUrlPrefix + backendValidUrl
        },
        type: "POST",
        dataType: "json",
        contentType:'application/x-www-form-urlencoded',
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        crossDomain: true,
        error: function (res, error) {
            console.log(res);
            if (res.status == 200) {
                //成功获取到ST
                //调用后台服务校验ST
                let st = res.responseText;
                validST(st);
            } else {
                alert("获取ST出错，TGT可能已过期或不可用了，请重新登录");
                window.location.href = "ticket.html";
            }
        }
    });
}

/**通过后台校验ST*/
function validST(st) {
    $.ajax({
        url: backendUrlPrefix + backendValidUrl + "?ticket=" + st,
        type: "GET",
        dataType: "json",
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        crossDomain: true,
        success: function (data) {
            alert("单点登录成功，前端做其他操作吧");
        },
        error: function (res, error) {
            console.log(res);
            if (res.status == 200) {
                //校验成功
                alert(res.responseText);

            } else {
                alert(res.responseText);
            }
        }
    });
}

/**尝试单点登录*/
function toSSO(corssStorage) {
    corssStorage.getItem(['location'], (result, data) => {
        console.log('client-1 getItem result: ', result);
        if (result['location'] != null && result['location'] != 'null') {
            //使用location获取ST
            getST(result['location']);
        } else {
            alert("未登陆，将跳转至登陆页面");
            window.location.href = "ticket.html";
        }
    })


}

// function getCorss() {
//     if (crossStorage == null) {
//         crossStorage = new CorssClient('http://127.0.0.1:8080/testCas/corss-middle.html');
//     }
//     return crossStorage;
// }