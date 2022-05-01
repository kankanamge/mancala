<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <link rel="stylesheet" href="Semantic-UI/semantic.min.css">
        <link rel="stylesheet" href="Semantic-UI/components/transition.min.css">
        <script src="Semantic-UI/jquery3.2.0.js"></script>
        <script src="Semantic-UI/semantic.min.js"></script>
        <script src="Semantic-UI/components/transition.min.js"></script>

        <script language='javascript'>
            $(document).ready(function () {
                $('.stone')
                        .transition('tada')
                ;
            });
        </script>
    </head>
    <body>
        <div class="ui equal width center aligned padded grid">
            <div class="row" style="background-color: #1b5c54;color: #FFFFFF;">

                <div class="black column two wide">
                    <div class="ui card" style="margin: 0 auto">
                        <a class="image" href="#">
                            <img src="images/rachel.png">
                        </a>
                        <div class="content">
                            <a class="header" href="#">Rachel</a>
                            <div class="meta">
                                <a>Player1</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="column" style="background-size: 100% 100%;">
                    <div>
                        <div>
                            <br/>
                            <table style="border: double; border-color: maroon; background-color: black; margin: 0 auto">
                                <c:forEach begin="0" end="11" var="i" step="1" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.index <=5}">
                                            <c:set var="x" value="${5-i}"></c:set>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="x" value="${i}"></c:set>

                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${(mancalaBoard.pits[x].pitNumber == 6 && mancalaBoard.pits[x].pitOwner.playerNumber == 1) || (mancalaBoard.pits[x].pitNumber == 1 && mancalaBoard.pits[x].pitOwner.playerNumber == 2)}">
                                        <tr >
                                        <c:if test="${mancalaBoard.pits[x].pitOwner.playerNumber == 1 && mancalaBoard.pits[x].pitNumber==6}">
                                            <td style="border: double #000000; background-color: black; padding: 0px" rowspan="2">
                                            </td>
                                            <td style="background-color: brown; padding: 5px; width: 15%" rowspan="2">
                                                <div class="massive green olive ui label">
                                                    <i class="diamond icon"></i> ${mancalaBoard.mancalaStores[0].numberOfStones}
                                                </div>
                                            </td>
                                        </c:if>
                                    </c:if>

                                    <td style="border-left: solid; border-color: #9e1317;
                                    <c:if test="${mancalaBoard.pits[x].pitOwner.playerNumber == 1}">
                                            border-bottom: dashed khaki;
                                    </c:if>
                                    <c:if test="${mancalaBoard.pits[x].pitOwner.playerNumber == 1 && turn ==1}">
                                            cursor: pointer;
                                    </c:if>
                                    <c:if test="${mancalaBoard.pits[x].pitOwner.playerNumber == 2 && turn ==2}">
                                            cursor: pointer;
                                    </c:if>
                                        padding: 5px; text-align: center; width: 80px; height: 80px"
                                        onclick="function play() {

                                            window.location = '/play?player=${mancalaBoard.pits[x].pitOwner.playerNumber}&pitnum=${mancalaBoard.pits[x].pitNumber}&numstones=${mancalaBoard.pits[x].numberOfStones}';

                                        }
                                        <c:if test="${mancalaBoard.pits[x].pitOwner.playerNumber == 1 && turn ==1}">
                                                play();
                                        </c:if>

                                        <c:if test="${mancalaBoard.pits[x].pitOwner.playerNumber == 2 && turn ==2}">
                                                play();
                                        </c:if>

                                                ">
                                        <c:forEach begin="1" end="${mancalaBoard.pits[x].numberOfStones}" var="i" step="1"
                                                varStatus="loop">
                                            <img class="stone" style="width: 30%" src="images/7.jpg" alt="heh what happened">
                                            <c:if test="${i%3==0}">
                                                <br/>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    
                                    <c:if test="${(mancalaBoard.pits[x].pitNumber == 1 && mancalaBoard.pits[x].pitOwner.playerNumber == 1) || (mancalaBoard.pits[x].pitNumber == 6 && mancalaBoard.pits[x].pitOwner.playerNumber == 2)}">
                                        <c:if test="${mancalaBoard.pits[x].pitOwner.playerNumber == 1}">
                                            <td style="background-color: brown; padding: 5px; width: 15%"
                                                rowspan="2">
                                                <div class="massive green olive ui label">
                                                    <i class="diamond icon"></i> ${mancalaBoard.mancalaStores[1].numberOfStones}
                                                </div>
                                            </td>
                                            <td style="border: double #000000; background-color: black; padding: 0px"
                                                rowspan="2">
                                            </td>
                                        </c:if>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>

                            <br/>
                            <div class="ui buttons">
                                <button onclick="window.location.href='/'" class="ui button">Exit</button>
                                <div class="or"></div>
                                <button onclick="window.location.href='/game'" class="ui positive button">Restart</button>
                            </div>
                            <br/>
                            <br/>
                        </div>
                    </div>
                </div>

                <div class="black column two wide">
                    <div class="ui card" style="margin: 0 auto">
                        <a class="image">
                            <img src="images/molly.png">
                        </a>
                        <div class="content">
                            <a class="header" href="#">Molly</a>
                            <div class="meta">
                                <a>Player2</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="black column">
                    <c:if test="${turn == 1}">
                        <i class="big icons">
                            <i class="orange big loading spinner icon"></i>
                            <i class="teal up arrow icon"></i>
                        </i>
                    </c:if>
                </div>
                <div class="column black">
                    <div class="ui label violet big">
                        State
                        <div class="detail"><c:out value="${state}"></c:out></div>
                    </div>

                    <c:if test="${turn == 1 || turn == 2}">
                        <div class="ui label orange big">
                            Turn
                            <div class="detail"><c:out value="Player ${turn}"></c:out>
                            </div>
                        </div>
                    </c:if>
                    <br/>
                </div>
                <div class="black column">
                    <c:if test="${turn == 2}">
                        <i class="big icons">
                            <i class="orange big loading spinner icon"></i>
                            <i class="teal up arrow icon"></i>
                        </i>
                    </c:if>
                </div>
            </div>
        </div>

    </body>
</html>