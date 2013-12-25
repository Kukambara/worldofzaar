function PlayerState(){
}

function PlayerData(){
    this.id;
    this.stats = [] ;
    this.state;
    this.avatar;
    this.playerField;

    this.Init = function (id, energy, health, armor, mysteryPower, negative, avatar, playerField){
        this.id = id;
        this.avatar = avatar;
        this.playerField = playerField;

        this.SetStatByName("hp", health);
        this.SetStatByName("ep", energy);
        this.SetStatByName("ap", armor);
        this.SetStatByName("mp", mysteryPower);
        this.SetStatByName("nv", negative);
    }

    this.SetStatByName = function(/*string*/ statName, value){
        this.stats[statName] = value;
        this.playerField.SetStatByName(statName, this.stats[statName]);
    }

}


function Player() {
    this.playerData;

	Player.prototype.init = function (/*PlayerData*/ pd) {
        this.playerData = pd;
	}

	Player.prototype.DrawAvatar = function(){

	}

	Player.prototype.DrawCards = function(){

	}

	Player.prototype.DrawAll = function () {
        this.DrawAvatar();
        this.DrawCards();
	}

	//PLayer.prototype.
};