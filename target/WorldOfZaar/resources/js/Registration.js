var avatarData = [];
var avatarList = [];
var avatarIndex = 0;

var sexList = ["/resources/Images/Registration/Sex/female.png", "/resources/Images/Registration/Sex/male.png"];
var sexIndex = 0;

var raceData = [];
var raceList = [];
var raceIndex = 0;

var classData = [];
var classList = [];
var classIndex = -1;


$(document).ready(function () {

    $.ajax({
        url: "/profile/gameRaces/",
        dataType: "json",
        type: "GET",
        async: false,
        success: function (data) {
            raceData = data;
        }
    });

    $.ajax({
        url: "/profile/gameClasses/",
        dataType: "json",
        type: "GET",
        async: false,
        success: function (data) {
            classData = data;
        }
    });

    $.ajax({
        url: "/profile/gameAvatars/",
        dataType: "json",
        type: "GET",
        async: false,
        success: function (data) {
            avatarData = data;
        }
    });


    document.getElementById('bottomLeftArrow').onclick = function () {
        changeSex(-1)
    };
    document.getElementById('bottomRightArrow').onclick = function () {
        changeSex(1)
    };
    document.getElementById('topLeftArrow').onclick = function () {
        changeRace(-1)
    };
    document.getElementById('topRightArrow').onclick = function () {
        changeRace(1)
    };
    document.getElementById('middleLeftArrow').onclick = function () {
        changeClass(-1)
    };
    document.getElementById('middleRightArrow').onclick = function () {
        changeClass(1)
    };
    document.getElementById('leftAvatarArrow').onclick = function () {
        changeAvatar(-1)
    };
    document.getElementById('rightAvatarArrow').onclick = function () {
        changeAvatar(1)
    };
    document.getElementById('startButton').onclick = function () {
        createUser();
    };

    setClassList();
    setAvatarList();

    setRace();
    setSex();
    setAvatar();
})

function changeRace(direction) {
    if ((direction == -1 && raceIndex == 0) || (direction == 1 && raceIndex == raceData.length - 1)) {
        if (direction == 1) {
            raceIndex = 0;
        }
        else {
            raceIndex = raceData.length - 1;
        }
    }
    else {
        raceIndex += direction;
    }

    onChangeRace();
    setClassList();
    setRace();
    setAvatarList();
    setAvatar();

    setClassList();
}

function setRace() {
    document.getElementById("race").src = raceData[raceIndex].racePictureUrl;
    document.getElementById("description").value = raceData[raceIndex].raceDescription;
}

function changeClass(direction) {
    if ((direction == -1 && classIndex == 0) || (direction == 1 && classIndex == classList.length - 1)) {
        if (direction == 1) {
            classIndex = 0;
        }
        else {
            classIndex = classList.length - 1;
        }
    }
    else {
        classIndex += direction;
    }
    setClass();
}

function setClassList() {
    var tmp = 0;
    classList = [];

    for (var i = 0; i < classData.length; ++i) {
        if (classData[i].raceId == raceData[raceIndex].raceId) {
            classList[tmp] = classData[i];
            tmp++;
        }
    }
}

function setClass() {
    document.getElementById("class").src = classList[classIndex].classPictureUrl;
    document.getElementById("cloth").src = classList[classIndex].classClothUrl;
    document.getElementById("arms").src = classList[classIndex].classBlazonUrl;
    document.getElementById("description").value = classList[classIndex].classDescription;
}

function changeAvatar(direction) {
    if ((direction == -1 && avatarIndex == 0) || (direction == 1 && avatarIndex == avatarList.length - 1)) {
        if (direction == 1) {
            avatarIndex = 0;
        }
        else {
            avatarIndex = avatarList.length - 1;
        }
    }
    else {
        avatarIndex += direction;
    }
    setAvatar();
}

function setAvatarList() {
    var tmp = 0;
    avatarList = [];

    for (var i = 0; i < avatarData.length; ++i) {
        if (avatarData[i].race.raceId == raceData[raceIndex].raceId && avatarData[i].male != sexIndex) {
            avatarList[tmp] = avatarData[i];
            tmp++;
        }
    }
}

function setAvatar() {
    document.getElementById("avatar").src = avatarList[avatarIndex].picturePath;
}

function changeSex(direction) {
    if ((direction == -1 && sexIndex == 0) || (direction == 1 && sexIndex == sexList.length - 1)) {
        if (direction == 1) {
            sexIndex = 0;
        }
        else {
            sexIndex = sexList.length - 1;
        }
    }
    else {
        sexIndex += direction;
    }

    setAvatarList();
    setSex();
    setAvatar();
}

function setSex() {
    document.getElementById("sex").src = sexList[sexIndex];
}

function onChangeRace() {
    document.getElementById("class").src = "";
    document.getElementById("cloth").src = "";
    document.getElementById("arms").src = "";
    document.getElementById("avatar").src = "";

    classIndex = 0;
    avatarIndex = 0;
}

function createUser() {
    if (raceData[raceIndex].raceId == classList[classIndex].raceId) {
        var sex = sexIndex;
        var userName = document.getElementById("userName").value;
        var blazonId = classList[classIndex].blazonId;
        var pictureId = avatarList.racePictureId;

        alert("success");
    }

    alert("select Class");
}