function sendRequest() {
    $.ajax({
        type: 'POST',
        url: '/file/uploadAndDownload',
        data: getFormData(),
        cache: false,
        processData: false,
        contentType: false,
        success: function (data) {
            console.info(data);
            ajaxFileDownload(data.file, data.filename);
        },
        error: function (data) {
            alert(data.exception);
        }
    });
}

function getFormData() {
    let formData = new FormData();
    formData.append('username', 'rex');
    formData.append('file', $('#file')[0].files[0]);
    return formData;
}

function ajaxFileDownload(data, fileName) {
    try {
        // atbo(): 解碼 base-64 字串
        let bstr = atob(data);
        let n = bstr.length;
        let u8arr = new Uint8Array(n);
        while (n--) {
            u8arr[n] = bstr.charCodeAt(n);
        }
        // TODO 檔案格式改了這裡要改
        let blob = new Blob([u8arr], {
            type: '\'text/csv'
        });
        if (window.navigator && window.navigator.msSaveOrOpenBlob) { // for IE
            window.navigator.msSaveOrOpenBlob(blob, fileName);
        }
        // for Non-IE (chrome, firefox etc.)
        else {
            let downloadLink = document.createElement('a');
            document.body.appendChild(downloadLink);
            downloadLink.style = 'display: none';
            let url = window.URL.createObjectURL(blob);
            downloadLink.href = url;
            downloadLink.download = fileName;
            downloadLink.click();
            downloadLink.remove();
            window.URL.revokeObjectURL(url);
        }
    } catch (e) {
        alert(e);
    }
}