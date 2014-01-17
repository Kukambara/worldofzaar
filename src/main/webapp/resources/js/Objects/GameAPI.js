/**
 * Created by Kseon on 17.01.14.
 */
function GameAPI(){
    //--------------------------------------------------------------
    //--------------------Get Actions-------------------------------

    this.Move = function (myCardId,enemyCardId,enemyId){
        return Move(myCardId,enemyCardId,enemyId);
    }

    this.EndMove = function (){
        return EndMove();
    }

    this.PutCard = function (cardId,position){
        return PutCard(cardId,position);
    }

    this.Skip = function (){
        return Skip();
    }

    this.ThrowOff = function (cardId){
        return ThrowOff(cardId);
    }

    //----------------------------------------------------------
    //--------------------Get API-------------------------------

    this.GetGame = function (){
        return GetGame();
    }

    this.IsGameReady = function (){
        return IsGameReady();
    }

    this.UserIsReady = function (){
        return UserIsReady();
    }

    //----------------------------------------------------------
    //--------------------Send Query----------------------------

    function Move(myCardId,enemyCardId,enemyId){
        $.ajax({
            url: "/api/game/move?myCardId="+myCardId+"&enemyCardId="+enemyCardId+"&enemyId="+enemyId,
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                return data;
            }
        });
    }

    function EndMove(){
        $.ajax({
            url: "/api/game/move/endMove",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                return data;
            }
        });
    }

    function PutCard(cardId,position){
        $.ajax({
            url: "/api/game/putCard?cardId="+cardId+"&position="+position,
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                return data;
            }
        });
    }

    function Skip(){
        $.ajax({
            url: "/api/game/skip",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                return data;
            }
        });
    }

    function ThrowOff(cardId){
        $.ajax({
            url: "/api/game/throwOff?cardId="+cardId,
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                return data;
            }
        });
    }

    function GetGame(){
        $.ajax({
            url: "/api/game/getGame",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                return data;
            }
        });
    }

    function IsGameReady(){
        $.ajax({
            url: "/api/game/isGameReady",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                return data;
            }
        });
    }

    function UserIsReady(){
        $.ajax({
            url: "/api/game/userIsReady",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                return data;
            }
        });
    }

}