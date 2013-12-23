<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>
        Test - map
    </title>
</head>
<script type="text/javascript" src="/resources/js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Consts.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Controllers/Observable.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/CanvasWrap.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Text.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Controllers/Events.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Controllers/Clickable.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Controllers/Button.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Resources.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Ellemnts/Point.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Ellemnts/Area.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Ellemnts/ClickableImage.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Ellemnts/AreaImage.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Ellemnts/AreaText.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Ellemnts/Card.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Ellemnts/CardFighter.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/Ellemnts/CardSupport.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Utils/CanvasManager.js"></script>
<script type="text/javascript" src="/resources/js/GameState/PlayerField.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Map.js"></script>
<script type="text/javascript" src="/resources/js/GameState/Game.js"></script>
<body onload="game.init('canvas_loading')">
<div style="float:left;">
    <canvas id="canvas_loading" style="position:absolute; left:50; top:50; border: solid 1px red; zindex:1" width="1000"
            height="1000">
    </canvas>
</div>
</body>
