var avatarList=[];
var avatarIndex=-1;

var flagList=[];
var flagIndex;

var sexList=["/resources/Images/Registration/Sex/female.png","/resources/Images/Registration/Sex/male.png"];
var sexIndex=0;

var raceList=[];
var raceIndex=-1;

var classList=[];
var classIndex=-1;


$(document).ready(function() { 

    document.getElementById('bottomLeftArrow').onclick = function () { changeSelection('sex', -1) };
    document.getElementById('bottomRightArrow').onclick = function(){changeSelection('sex',1)};
    document.getElementById('topLeftArrow').onclick = function(){changeSelection('race',-1)};
    document.getElementById('topRightArrow').onclick = function(){changeSelection('race',1)};
    document.getElementById('middleLeftArrow').onclick = function(){changeSelection('class',-1)};
    document.getElementById('middleRightArrow').onclick = function () { changeSelection('class', 1) };
    document.getElementById('leftAvatarArrow').onclick = function(){changeSelection('avatar',-1)};
    document.getElementById('rightAvatarArrow').onclick = function () { changeSelection('avatar', 1) };

    $.ajax({
        url: "/profile/gameRaces/",
        dataType: "json",
        type: "GET",
        success: function (data) {
            raceList = data;
        }
    });

    $.ajax({
        url: "/profile/gameClasses/",
        dataType: "json",
        type: "GET",
        success: function (data) {
            classList = data;
        }
    });

    $.ajax({
        url: "/profile/gameAvatars/",
        dataType: "json",
        type: "GET",
        success: function (data) {
            avatarList = data;
        }
    });
})

function changeSelection(id,direction){

switch(id){
case 'sex' :
		if((direction == -1 && sexIndex == 0) || (direction == 1 && sexIndex == sexList.length-1)){
			if(direction==1){
				sexIndex =0;
			}
			else{
				sexIndex = sexList.length-1;
			}
		}
		else{
			sexIndex+=direction;
		}
		document.getElementById(id).src = sexList[sexIndex];
		
		break;
case 'race':
		if((direction == -1 && raceIndex == 0) || (direction == 1 && raceIndex == raceList.length-1)){
			if(direction==1){
				raceIndex =0;
			}
			else{
				raceIndex = raceList.length-1;
			}
		}
		else{
			raceIndex+=direction;
		}
		document.getElementById(id).src = raceList[raceIndex].racePictureUrl;
        document.getElementById("description").value = raceList[raceIndex].raceDescription;
        document.getElementById("avatar").src = avatarList[getAvatarIndex()].picturePath;
		break;		
case 'class':
		if((direction == -1 && classIndex == 0) || (direction == 1 && classIndex == 4)){
			if(direction==1){
				classIndex =0;
			}
			else{
				classIndex = 4;
			}
		}
		else{
			classIndex+=direction;
		}
        var index = getIndex(classList,raceList[raceIndex].raceId,classIndex);

		document.getElementById(id).src = classList[index].classPictureUrl;
        document.getElementById("cloth").src = classList[index].classClothUrl;
        document.getElementById("arms").src = classList[index].classBlazonUrl;
        document.getElementById("description").value = classList[index].classDescription;


		break;
case 'avatar' :
		if((direction == -1 && avatarIndex == 0) || (direction == 1 && avatarIndex == 5)){
			if(direction==1){
				avatarIndex =0;
			}
			else{
				avatarIndex = 0;
			}
		}
		else{
			avatarIndex+=direction;
		}
		document.getElementById(id).src = avatarList[getAvatarIndex()].picturePath;
		break;
default:
	break;
}
}

function getIndex(inputList,id,index){
      for(var i=0;i<inputList.length; ++i)
      {
          if(inputList[i].raceId == id)
          {
              if(index != 0)
              {
                  index--;
              }
              else{
                return i;
              }
          }
      }
}

function getAvatarIndex()
{
    var first;
    var isFirst=true;
    var index = avatarIndex;
    for(var i=0;i<avatarList.length; ++i)
    {
        if(avatarList[i].race.raceId == raceList[raceIndex].raceId && avatarList[i].male == sexIndex)
        {
             if(isFirst)
             {
                 first = i;
                 isFirst=false;
             }
            if(index != 0)
            {
                index--;
            }
            else{
                return i;
            }
        }
    }
    return first;
}