/**
 * Created by wey580231 on 2017/6/29.
 */



function resizeD3ViewSize() {
    var height = $("#content").height() - $("#menuBar").height();
    $("#div_center").css("height", height);
}

$(document).ready(function () {
    resizeD3ViewSize();

    $("#activeSelector").attr("disabled", true);

    $("#activeSelector").change(function () {
        var val = $(this).children('option:selected').val();
        if (val.length > 0) {
            LoadView(val);
        }
        else {
            CloseView('1');
        }
    });

    document.getElementById("welcome_page").style.display = "block";
    document.getElementById('ComView1').focus();
});

function loginAps() {
    document.getElementById("CheckData").Initialize("");
    document.getElementById("CheckData").LoadFile(Server + ViewPath + "ButtonCheckData.view");
    var successLogin = -1;
    if (firstLog == 1) {
        document.getElementById("Login").Initialize("");
        document.getElementById("Login").LoadFile(Server + ViewPath + "ButtonLogin.view");
        successLogin = document.getElementById("Login").RunScript("Login");
        if (successLogin == 1) {
            firstLog = 2;
            $("#activeSelector").attr("disabled", false);
        }
    }

    document.getElementById("AutoScheduling").Initialize("");
    document.getElementById("AutoScheduling").LoadFile(Server + ViewPath + "ButtonAutoScheduling.view");
    document.getElementById("ResumeScheduling").Initialize("");
    document.getElementById("ResumeScheduling").LoadFile(Server + ViewPath + "ButtonResumeScheduling.view");
    document.getElementById("Logout").Initialize("");
    document.getElementById("Logout").LoadFile(Server + ViewPath + "ButtonLogout.view");
    document.getElementById("RegenerateExcel").Initialize("");
    document.getElementById("RegenerateExcel").LoadFile(Server + ViewPath + "ButtonGenerateExcel.view");
    document.getElementById("RegenerateView").Initialize("");
    document.getElementById("RegenerateView").LoadFile(Server + ViewPath + "ButtonGenerateView.view");
    document.getElementById("TableObjective").Initialize("");
    document.getElementById("TableObjective").LoadFile(Server + ViewPath + "TableObjective.view");

    window.setTimeout(function () {
        document.getElementById('ComView1').focus();
    }, 0);
    window.setTimeout(function () {
        document.getElementById('ComView2').focus();
    }, 0);
    window.setTimeout(function () {
        document.getElementById('ComView3').focus();
    }, 0);
}


$(window).resize(function () {
    resizeD3ViewSize();
});

var ViewPath;
var Server = ServerIP + ":" + ServerPort;
var firstLog = 1;
var currentview = "null";
var comview1 = "null";
var comview2 = "null";
var comview3 = "null";
var nbComView;
var comviewselected = 0;
var isZoomInSelected = false;
var isZoomOutSelected = false;
var widthWelcomePage = 0;
var heightWelcomePage = 0;
var widthScreen = document.body.clientWidth;
var heightScreen = document.documentElement.clientHeight;
var idComviewSelected = 0;
var ystart = 0;
var xstart = 0;
var table_view = new Array(
    "TableProcess.view",
    "TableTypeResourceProduct.view",
    "TableOrder.view",
    "TableResource.view",
    "TableJob.view",
    "TableSite.view",
    "TableOrderTaskMenu.view",
    "TableResourceTaskMenu.view",
    "TableJobTaskMenu.view",
    "TableSiteTask.view",
    "GanttOrderTaskMenu.view",
    "GanttResourceTaskMenu.view",
    "GanttJobTaskMenu.view",
    "GanttSiteTaskMenu.view",
    "GanttJobOverviewMenu.view",
    "HistogramResource.view",
    "HistogramOrderResourceMenu.view",
    "SimulationJobMenu.view",
    "SimulationResource.view",
    "TableSnapShot.view",
    "TableOrderBottleneckedMenu.view");

if (typeUser == 0) {
    ViewPath = "/View/" + idUser + "/";
}
else if (typeUser == 1) {
    ViewPath = "/View/" + idUser + "/Client/";
}
else if (typeUser == 2) {
    ViewPath = "/View/" + idUser + "/Provider/";
}
var img = document.getElementById("welcome_image");
var temp = new Image();
temp.src = img.src;
temp.onload = function () {
    widthWelcomePage = parseInt(this.width);
    heightWelcomePage = parseInt(this.height);
    heightSet = heightScreen - 60 - 20 - 20;
    widthSet = widthScreen - 300;

    if (heightSet * widthWelcomePage / heightWelcomePage > widthSet) {
        document.getElementById("welcome_page").style.width = widthSet + "px";
        document.getElementById("welcome_page").style.height = widthSet * heightWelcomePage / widthWelcomePage + "px";
        document.getElementById("welcome_page").style.paddingTop = (heightScreen - 60 - 20 - 25 - widthSet * heightWelcomePage / widthWelcomePage) / 2 + "px";
    }
    else {
        document.getElementById("welcome_page").style.width = heightSet * widthWelcomePage / heightWelcomePage + "px";
        document.getElementById("welcome_page").style.height = heightSet + "px";
    }
}

document.onselectstart = new Function("return false"); //to not select items


if (getUrlVars()["view"] != undefined)
    nbComView = 1;
else
    nbComView = 0;


function BestFit(scale, fit) {
    document.getElementById('zoomin').style.backgroundColor = 'white';
    document.getElementById('zoomout').style.backgroundColor = 'white';
    if (isZoomInSelected == true) {
        ZoomIn();
    }
    else if (isZoomOutSelected == true) {
        ZoomOut();
    }
    if (comviewselected == 1) {
        document.getElementById("ComView1").SetNaturalScale(scale);
        document.getElementById("ComView1").SetBestFit(fit);
    }
    else if (comviewselected == 2) {
        document.getElementById("ComView2").SetNaturalScale(scale);
        document.getElementById("ComView2").SetBestFit(fit);
    }
    else if (comviewselected == 3) {
        document.getElementById("ComView3").SetNaturalScale(scale);
        document.getElementById("ComView3").SetBestFit(fit);
    }
    else if (comviewselected == 4) {
        document.getElementById("TableObjective").SetNaturalScale(scale);
        document.getElementById("TableObjective").SetBestFit(fit);
    }
    else if (comviewselected == 5) {
        document.getElementById("TableUser").SetNaturalScale(scale);
        document.getElementById("TableUser").SetBestFit(fit);
    }
}

function NaturalScale(scale, fit) {
    document.getElementById('zoomin').style.backgroundColor = 'white';
    document.getElementById('zoomout').style.backgroundColor = 'white';
    if (isZoomInSelected == true) {
        ZoomIn();
    }
    else if (isZoomOutSelected == true) {
        ZoomOut();
    }
    if (comviewselected == 1) {
        document.getElementById("ComView1").SetNaturalScale(scale);
        document.getElementById("ComView1").SetBestFit(fit);
    }
    else if (comviewselected == 2) {
        document.getElementById("ComView2").SetNaturalScale(scale);
        document.getElementById("ComView2").SetBestFit(fit);
    }
    else if (comviewselected == 3) {
        document.getElementById("ComView3").SetNaturalScale(scale);
        document.getElementById("ComView3").SetBestFit(fit);
    }
    else if (comviewselected == 4) {
        document.getElementById("TableObjective").SetNaturalScale(scale);
        document.getElementById("TableObjective").SetBestFit(fit);
    }
    else if (comviewselected == 5) {
        document.getElementById("TableUser").SetNaturalScale(scale);
        document.getElementById("TableUser").SetBestFit(fit);
    }
}

function ZoomIn() {
    isZoomInSelected = !isZoomInSelected;
    if (isZoomInSelected == true) {
        document.getElementById('zoomin').style.backgroundColor = '#ADEAEA';
        document.getElementById('zoomout').style.backgroundColor = 'white';
        isZoomOutSelected = false;
    }
    else if (isZoomInSelected == false) {
        document.getElementById('zoomin').style.backgroundColor = 'white';
    }
    ZoomInComView(document.getElementById("ComView1"));
    ZoomInComView(document.getElementById("ComView2"));
    ZoomInComView(document.getElementById("ComView3"));
    ZoomInComView(document.getElementById("TableObjective"));
    ZoomInComView(document.getElementById("TableUser"));
}

function ZoomInComView(comview) {
    if (isZoomInSelected == true) {
        comview.SetZoomCursor(1);
    }
    else {
        comview.SetZoomCursor(0);
    }
}

function ZoomOut() {
    isZoomOutSelected = !isZoomOutSelected;
    if (isZoomOutSelected == true) {
        document.getElementById('zoomout').style.backgroundColor = '#ADEAEA';
        document.getElementById('zoomin').style.backgroundColor = 'white';
        isZoomInSelected = false;
    }
    else if (isZoomOutSelected == false) {
        document.getElementById('zoomout').style.backgroundColor = 'white';
    }
    ZoomOutComView(document.getElementById("ComView1"));
    ZoomOutComView(document.getElementById("ComView2"));
    ZoomOutComView(document.getElementById("ComView3"));
    ZoomOutComView(document.getElementById("TableObjective"));
    ZoomOutComView(document.getElementById("TableUser"));
}

function ZoomOutComView(comview) {
    if (isZoomOutSelected == true) {
        comview.SetZoomCursor(-1);
    }
    else {
        comview.SetZoomCursor(0);
    }
}

function RedrawView() {
    document.getElementById('zoomin').style.backgroundColor = 'white';
    document.getElementById('zoomout').style.backgroundColor = 'white';
    isZoomInSelected = false;
    isZoomOutSelected = false;
    document.getElementById("TableObjective").LoadFile(Server + ViewPath + "TableObjective.view");
    document.getElementById("TableUser").LoadFile(Server + ViewPath + "TableUser.view");
    if (nbComView == 1) {
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
    }
    else if (nbComView == 2) {
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
        document.getElementById("ComView2").LoadFile(Server + ViewPath + comview2);
    }
    else if (nbComView == 3) {
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
        document.getElementById("ComView2").LoadFile(Server + ViewPath + comview2);
        document.getElementById("ComView3").LoadFile(Server + ViewPath + comview3);
    }
}

function LoadView(file) {
    nbComView = 1;
    comview1 = file;
    comview2 = "null";
    comview3 = "null";
    document.getElementById("ComView2").style.display = "none";
    document.getElementById("ComView3").style.display = "none";
    document.getElementById("ComView1").style.display = "none";
    document.getElementById("welcome_page").style.display = "none";
    document.getElementById("CloseWindow1").style.display = "block";
    document.getElementById("CloseWindow2").style.display = "none";
    document.getElementById("CloseWindow3").style.display = "none";
    var path = Server + ViewPath + comview1;
    document.getElementById('ComView1').LoadFile(path);
    document.getElementById("ComView1").style.height = "100%";
    document.getElementById("ComView1").style.width = "100%";
    document.getElementById("ComView1").style.display = "block";
    window.setTimeout(function () {
        document.getElementById('ComView1').focus();
    }, 0);
    $('#mm1').menu('show', {left: widthScreen / 2 + "px", top: heightScreen + 20 + "px"});
    comviewselected = 1;
    if (isZoomInSelected == true) {
        window.setTimeout(function () {
            document.getElementById('ComView1').SetZoomCursor(1);
        }, 0);
    }
    else if (isZoomOutSelected == true) {
        window.setTimeout(function () {
            document.getElementById('ComView1').SetZoomCursor(-1);
        }, 0);
    }

}

function LoadSimulation(file) {
    LoadView(file);
    setTimeout(function () {
        document.getElementById("ComView1").RunScript("SimulationJob");
    }, 1000);
    setTimeout(function () {
        document.getElementById("ComView1").RunScript("SimulationResource");
    }, 1000);
    window.setTimeout(function () {
        document.getElementById('ComView1').focus();
    }, 0);
    $('#mm1').menu('show', {left: widthScreen / 2 + "px", top: heightScreen + 20 + "px"});
    comviewselected = 1;
    if (isZoomInSelected == true) {
        window.setTimeout(function () {
            document.getElementById('ComView1').SetZoomCursor(1);
        }, 0);
    }
    else if (isZoomOutSelected == true) {
        window.setTimeout(function () {
            document.getElementById('ComView1').SetZoomCursor(-1);
        }, 0);
    }
}

function positionRecord1() {
    if (nbComView == 2)
        idComviewSelected = 1;
    else
        idComviewSelected = 2;
    ystart = event.clientY;
    xstart = event.clientX;
}

function positionRecord2() {
    idComviewSelected = 3;
    ystart = event.clientY;
    xstart = event.clientX;
}

function AddView() {
    document.getElementById("CloseWindow1").style.top = "5px";
    document.getElementById("CloseWindow1").style.right = "2px";
    document.getElementById("CloseWindow2").style.top = "3px";
    document.getElementById("CloseWindow2").style.right = "2px";
    document.getElementById("CloseWindow3").style.top = "3px";
    document.getElementById("CloseWindow3").style.right = "2px";
    document.getElementById("heightResizeBar1").style.marginLeft = "0px";
    document.getElementById("heightResizeBar2").style.marginLeft = "0px";
    document.getElementById("welcome_page").style.display = "none";
    if (nbComView == 0) {
        comview1 = currentview;
        nbComView = 1;
        document.getElementById("ComView1").style.height = "100%";
        document.getElementById("ComView1").style.width = "99%";
        document.getElementById("ComView1").style.display = "block";
        document.getElementById("CloseWindow1").style.display = "block";
        document.getElementById("CloseWindow2").style.display = "none";
        document.getElementById("CloseWindow3").style.display = "none";
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
        if (currentview == "SimulationJobMenu.view" || currentview == "SimulationResource.view") {
            setTimeout(function () {
                document.getElementById("ComView1").RunScript("SimulationJob");
            }, 1000);
            setTimeout(function () {
                document.getElementById("ComView1").RunScript("SimulationResource");
            }, 1000);
        }
        window.setTimeout(function () {
            document.getElementById('ComView1').focus();
        }, 0);
        $('#mm1').menu('show', {left: widthScreen / 2 + "px", top: heightScreen + 20 + "px"});
        comviewselected = 1;
        if (isZoomInSelected == true) {
            window.setTimeout(function () {
                document.getElementById('ComView1').SetZoomCursor(1);
            }, 0);
        }
        else if (isZoomOutSelected == true) {
            window.setTimeout(function () {
                document.getElementById('ComView1').SetZoomCursor(-1);
            }, 0);
        }
    }
    else if (nbComView == 1) {
        comview2 = currentview;
        document.getElementById("CloseWindow1").style.display = "block";
        document.getElementById("CloseWindow2").style.display = "block";
        document.getElementById("CloseWindow3").style.display = "none";
        document.getElementById("heightResizeBar1").style.display = "block";
        document.getElementById("heightResizeBar1").style.width = "100%";
        document.getElementById("heightResizeBar1").style.height = "5px";
        document.getElementById("heightResizeBar1").style.cursor = "N-Resize";
        document.getElementById("ComView1").style.width = "99%";
        document.getElementById("ComView1").style.height = "50%";
        document.getElementById("ComView2").style.width = "99%";
        document.getElementById("ComView2").style.height = "49%";
        document.getElementById("ComView1").style.display = "block";
        document.getElementById("ComView2").style.display = "block";

        document.getElementById("ComView2").LoadFile(Server + ViewPath + comview2);
        document.getElementById("CloseWindow2").style.top = document.getElementById('ComView1').offsetHeight + 11 + "px";
        if (currentview == "SimulationJobMenu.view" || currentview == "SimulationResource.view") {
            setTimeout(function () {
                document.getElementById("ComView2").RunScript("SimulationJob");
            }, 1000);
            setTimeout(function () {
                document.getElementById("ComView2").RunScript("SimulationResource");
            }, 1000);
        }
        window.setTimeout(function () {
            document.getElementById('ComView1').focus();
        }, 0);
        window.setTimeout(function () {
            document.getElementById('ComView2').focus();
        }, 0);
        window.setTimeout(function () {
            document.getElementById('footer').focus();
        }, 0);
        nbComView = 2;
        comviewselected = 2;
        if (isZoomInSelected == true) {
            window.setTimeout(function () {
                document.getElementById('ComView2').SetZoomCursor(1);
            }, 0);
        }
        else if (isZoomOutSelected == true) {
            window.setTimeout(function () {
                document.getElementById('ComView2').SetZoomCursor(-1);
            }, 0);
        }
    } else {
        if (comview3 == "null") {
            comview3 = currentview;
            nbComView = 3;
        }
        else {
            comview1 = comview2;
            comview2 = comview3;
            comview3 = currentview;
        }
        document.getElementById("CloseWindow1").style.display = "block";
        document.getElementById("CloseWindow2").style.display = "block";
        document.getElementById("CloseWindow3").style.display = "block";
        document.getElementById("heightResizeBar1").style.display = "block";
        document.getElementById("heightResizeBar1").style.width = "100%";
        document.getElementById("heightResizeBar1").style.height = "5px";
        document.getElementById("heightResizeBar1").style.cursor = "N-Resize";
        document.getElementById("heightResizeBar2").style.display = "block";
        document.getElementById("heightResizeBar2").style.width = "100%";
        document.getElementById("heightResizeBar2").style.height = "5px";
        document.getElementById("heightResizeBar2").style.cursor = "N-Resize";
        document.getElementById("ComView1").style.width = "99%";
        document.getElementById("ComView1").style.height = "33%";
        document.getElementById("ComView2").style.width = "99%";
        document.getElementById("ComView2").style.height = "33%";
        document.getElementById("ComView3").style.width = "99%";
        document.getElementById("ComView3").style.height = "32%";
        document.getElementById("ComView1").style.display = "block";
        document.getElementById("ComView2").style.display = "block";
        document.getElementById("ComView3").style.display = "block";
        document.getElementById("CloseWindow2").style.top = document.getElementById('ComView1').offsetHeight + 11 + "px";
        document.getElementById("CloseWindow3").style.top = document.getElementById('ComView1').offsetHeight * 2 + 16 + "px";
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
        document.getElementById("ComView2").LoadFile(Server + ViewPath + comview2);
        document.getElementById("ComView3").LoadFile(Server + ViewPath + comview3);
        if (currentview == "SimulationJobMenu.view" || currentview == "SimulationResource.view") {
            setTimeout(function () {
                document.getElementById("ComView3").RunScript("SimulationJob");
            }, 1000);
            setTimeout(function () {
                document.getElementById("ComView3").RunScript("SimulationResource");
            }, 1000);
        }
        comviewselected = 3;
        window.setTimeout(function () {
            document.getElementById('ComView1').focus();
        }, 0);
        window.setTimeout(function () {
            document.getElementById('ComView2').focus();
        }, 0);
        window.setTimeout(function () {
            document.getElementById('ComView3').focus();
        }, 0);
        window.setTimeout(function () {
            document.getElementById('footer').focus();
        }, 0);
        if (isZoomInSelected == true) {
            window.setTimeout(function () {
                document.getElementById('ComView1').SetZoomCursor(1);
            }, 0);
            window.setTimeout(function () {
                document.getElementById('ComView2').SetZoomCursor(1);
            }, 0);
            window.setTimeout(function () {
                document.getElementById('ComView3').SetZoomCursor(1);
            }, 0);
        }
        else if (isZoomOutSelected == true) {
            window.setTimeout(function () {
                document.getElementById('ComView1').SetZoomCursor(-1);
            }, 0);
            window.setTimeout(function () {
                document.getElementById('ComView2').SetZoomCursor(-1);
            }, 0);
            window.setTimeout(function () {
                document.getElementById('ComView3').SetZoomCursor(-1);
            }, 0);
        }
    }
}

function CloseView(id) {
    if (nbComView == 1) {
        document.getElementById("ComView1").style.display = "none";
        document.getElementById("ComView2").style.display = "none";
        document.getElementById("ComView3").style.display = "none";
        document.getElementById("CloseWindow1").style.display = "none";
        document.getElementById("CloseWindow2").style.display = "none";
        document.getElementById("CloseWindow3").style.display = "none";
        document.getElementById("heightResizeBar1").style.display = "none";
        document.getElementById("heightResizeBar2").style.display = "none";
        document.getElementById("welcome_page").style.display = "block";
        nbComView = 0;
        comviewselected = 0;
    }
    else if (nbComView == 2) {
        document.getElementById("ComView1").style.display = "none";
        document.getElementById("ComView2").style.display = "none";
        document.getElementById("ComView3").style.display = "none";
        document.getElementById("welcome_page").style.display = "none";
        document.getElementById("ComView1").style.height = "100%";
        document.getElementById("ComView1").style.width = "99%";
        document.getElementById("ComView1").style.display = "block";
        document.getElementById("CloseWindow1").style.display = "block";
        document.getElementById("CloseWindow2").style.display = "none";
        document.getElementById("CloseWindow3").style.display = "none";
        document.getElementById("heightResizeBar1").style.display = "none";
        if (id == 1) {
            comview1 = comview2;
            comview2 = "null";
            document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
            comviewselected = 1;
        }
        else if (id == 2) {
            document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
            comviewselected = 2;
        }
        nbComView = 1;
    }
    else if (nbComView == 3) {
        if (id == 1) {
            comview1 = comview2;
            comview2 = comview3;
            comviewselected = 1;
        }
        else if (id == 2) {
            comview2 = comview3;
            comviewselected = 2;

        }
        else if (id == 3) {
            comviewselected = 3;
        }
        comview3 = "null";
        document.getElementById("ComView3").style.display = "none";
        document.getElementById("ComView1").style.width = "99%";
        document.getElementById("ComView1").style.height = "50%";
        document.getElementById("ComView2").style.width = "99%";
        document.getElementById("ComView2").style.height = "50%";
        document.getElementById("ComView2").style.display = "block";
        document.getElementById("ComView1").style.display = "block";
        document.getElementById("CloseWindow1").style.display = "block";
        document.getElementById("CloseWindow2").style.display = "block";
        document.getElementById("CloseWindow3").style.display = "none";
        document.getElementById("CloseWindow2").style.top = document.getElementById('ComView1').offsetHeight + 8 + "px";
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
        document.getElementById("ComView2").LoadFile(Server + ViewPath + comview2);
        window.setTimeout(function () {
            document.getElementById('ComView2').focus();
        }, 0);
        window.setTimeout(function () {
            document.getElementById('ComView1').focus();
        }, 0);
        nbComView = 2;
    }
}
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}

if ($(".get_view_ok")[0]) {
    var file = getUrlVars()["view"];
    var currentAccordion = getUrlVars()["currentAccordion"];
    if (currentAccordion != undefined) {
        document.getElementById(currentAccordion).selected = true;
    }

    if (file == undefined) {
        document.getElementById("welcome_page").style.display = "block";
    }
    else {
        var i = 0;
        var bool = 0;
        while (i != table_view.length) {
            if (table_view[i] == file)
                bool = 1;
            i += 1;
        }
        if (bool == 0) {
            alert('该对象不存在');
            document.getElementById("welcome_page").style.display = "block";
        }
        else {
            comview1 = file;
            var path = Server + ViewPath + file;
            document.getElementById("ComView1").style.display = "block";
            document.getElementById("CloseWindow1").style.display = "block";
            document.getElementById("ComView1").LoadFile(path);
            window.setTimeout(function () {
                document.getElementById('ComView1').focus();
            }, 0);
            window.setTimeout(function () {
                document.getElementById('ComView2').focus();
            }, 0);
            window.setTimeout(function () {
                document.getElementById('ComView3').focus();
            }, 0);
        }
    }
}

function FuncAutoScheduling() {
    document.getElementById("AutoScheduling").RunScript("AutoScheduling");
    document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1)
}
function FuncResumeScheduling() {
    document.getElementById("ResumeScheduling").RunScript("ResumeScheduling");
    document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1)
}
function FuncRegenerateExcel() {
    document.getElementById("RegenerateExcel").RunScript("GenerateExel");
    document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1)
}
function FuncRegenerateView() {
    document.getElementById("RegenerateView").RunScript("GenerateView");
    document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1)
}
function change_language() {
    document.location.href = "../en/index.html";
}
function FuncLogout() {
    var successLogout = -1;
    successLogout = document.getElementById("Logout").RunScript("Logout");
    if (successLogout == 1) {
        CloseWindow();
    }

    if (nbComView == 1) {
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
    }
    else if (nbComView == 2) {
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
        document.getElementById("ComView2").LoadFile(Server + ViewPath + comview2);
    }
    else if (nbComView == 3) {
        document.getElementById("ComView1").LoadFile(Server + ViewPath + comview1);
        document.getElementById("ComView2").LoadFile(Server + ViewPath + comview2);
        document.getElementById("ComView3").LoadFile(Server + ViewPath + comview3);
    }
}
function OpenNewWindow() {
    window.open("index.html");
}
function CloseWindow() {
    window.opener = null;
    window.open('', '_self');
    window.close();
}
