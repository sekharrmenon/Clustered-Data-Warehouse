<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<style>
.upload_form_cont {
    background: -moz-linear-gradient(#ffffff, #f2f2f2);
    background: -ms-linear-gradient(#ffffff, #f2f2f2);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #ffffff), color-stop(100%, #f2f2f2));
    background: -webkit-linear-gradient(#ffffff, #f2f2f2);
    background: -o-linear-gradient(#ffffff, #f2f2f2);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#f2f2f2');
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#f2f2f2')";
    background: linear-gradient(#ffffff, #f2f2f2);

    color:#000;
    overflow:hidden;
}
#upload_form {
    float:left;
    padding:20px;
    width:700px;
}
#preview {
    background-color:#fff;
    display:block;
    float:right;
    width:200px;
}
#upload_form > div {
    margin-bottom:10px;
}
#speed,#remaining {
    float:left;
    width:100px;
}
#b_transfered {
    float:right;
    text-align:right;
}
.clear_both {
    clear:both;
}
input {
    border-radius:10px;
    -moz-border-radius:10px;
    -ms-border-radius:10px;
    -o-border-radius:10px;
    -webkit-border-radius:10px;

    border:1px solid #ccc;
    font-size:14pt;
    padding:5px 10px;
}
input[type=button] {
    background: -moz-linear-gradient(#ffffff, #dfdfdf);
    background: -ms-linear-gradient(#ffffff, #dfdfdf);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #ffffff), color-stop(100%, #dfdfdf));
    background: -webkit-linear-gradient(#ffffff, #dfdfdf);
    background: -o-linear-gradient(#ffffff, #dfdfdf);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#dfdfdf');
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#dfdfdf')";
    background: linear-gradient(#ffffff, #dfdfdf);
}
#image_file {
    width:400px;
}
#progress_info {
    font-size:10pt;
}
#fileinfo,#error,#error2,#abort,#warnsize {
    color:#aaa;
    display:none;
    font-size:10pt;
    font-style:italic;
    margin-top:10px;
}
#progress {
    border:1px solid #ccc;
    display:none;
    float:left;
    height:14px;

    border-radius:10px;
    -moz-border-radius:10px;
    -ms-border-radius:10px;
    -o-border-radius:10px;
    -webkit-border-radius:10px;

    background: -moz-linear-gradient(#66cc00, #4b9500);
    background: -ms-linear-gradient(#66cc00, #4b9500);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #66cc00), color-stop(100%, #4b9500));
    background: -webkit-linear-gradient(#66cc00, #4b9500);
    background: -o-linear-gradient(#66cc00, #4b9500);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#66cc00', endColorstr='#4b9500');
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#66cc00', endColorstr='#4b9500')";
    background: linear-gradient(#66cc00, #4b9500);
}
#progress_percent {
    float:right;
}
#upload_response {
    margin-top: 10px;
    padding: 20px;
    overflow: hidden;
    border: 1px solid #ccc;

    border-radius:10px;
    -moz-border-radius:10px;
    -ms-border-radius:10px;
    -o-border-radius:10px;
    -webkit-border-radius:10px;

    box-shadow: 0 0 5px #ccc;
    background: -moz-linear-gradient(#bbb, #eee);
    background: -ms-linear-gradient(#bbb, #eee);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #bbb), color-stop(100%, #eee));
    background: -webkit-linear-gradient(#bbb, #eee);
    background: -o-linear-gradient(#bbb, #eee);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#bbb', endColorstr='#eee');
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#bbb', endColorstr='#eee')";
    background: linear-gradient(#bbb, #eee);
}

</style>
<link rel="stylesheet" href="resources/css/upload.css">
<script src="resources/js/upload.js"></script>
<title>File Uploading Form</title>
</head>

<body>
        <header>
            <h2>Clustered Data Warehouse</h2>
        </header>
        <div class="container">
            <div class="contr"><h2>You can select the file  and click Upload button</h2></div>

            <div class="upload_form_cont">
                <form id="upload_form" enctype="multipart/form-data" method="post" action="fileupload">
                    <div>
                        <div><label for="image_file">Please select a csv file</label></div>
                        <div><input type="file" name="targetFile" id="image_file"  accept=".csv" onchange="fileSelected();" /></div>
                    </div>
                    <div>
                        <input type="submit" value="Upload File"  />
                    </div>
                    <div id="fileinfo">
                        <div id="filename"></div>
                        <div id="filesize"></div>
                        <div id="filetype"></div>
                        <div id="filedim"></div>
                    </div>

                    <div id="progress_info">
                        <div id="progress"></div>
                        <div id="progress_percent">&nbsp;</div>
                        <div class="clear_both"></div>
                        <div>
                            <div id="speed">&nbsp;</div>
                            <div id="remaining">&nbsp;</div>
                            <div id="b_transfered">&nbsp;</div>
                            <div class="clear_both"></div>
                        </div>
                        <div id="upload_response"><c:out value='${message}' /></div>
                    </div>
                </form>

                <img id="preview" />
            </div>
        </div>
    </body>

</html>