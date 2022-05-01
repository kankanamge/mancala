<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <link rel="stylesheet" href="Semantic-UI/semantic.min.css">
        <link rel="stylesheet" href="Semantic-UI/components/accordion.min.css">
        <script src="Semantic-UI/jquery3.2.0.js"></script>
        <script src="Semantic-UI/semantic.min.js"></script>
        <script src="Semantic-UI/components/accordion.min.js"></script>

        <script language='javascript'>
            $(document).ready(function () {
                $('.ui.accordion').accordion();
            });
        </script>

    </head>
    <body>
        <div class="ui equal width left aligned padded grid" style="margin: 0 auto">
            <div class="row">
                <div class="black column">
                    <h1 class="ui center aligned header orange"> Give your Brain a workout</h1>
                </div>
            </div>        
            <div class="row" style="background-color: #1b5c54;"> 
                <div class="column" style="background-size: 100% 100%;">
                    <a class="image" href="#">
                        <img src="images/background.png">
                    </a>
                </div>            
                <div class="black column two wide">
                    <div class="ui card" style="margin: 0 auto">
                        <button onclick="window.location.href='/game'" id="btn1" class="ui button massive green center aligned ">
                            Start Game
                        </button> 
                    </div>
                </div>                   
            </div> 
            <div class="row">
                <div class="black column"></div>
            </div>  
        </div>
    </body>
</html>