<%@ page contentType="text/html; ISO-8859-1" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Working Example</title>
    <link href="/css/styleSheet.css" rel="stylesheet" type="text/css"/>
</head>

<body>

    <div id="topPart">
        <form:form modelAttribute="hand">
           <div>
               <input type="submit" name="action"  value="Pick Card"/>
               <input type="submit" name="action"  value="Play Card"/>
           </div>
        </form:form>
    </div>

    <div id="boardArea">
        <img id="boardCross" src="img/other/board.png"/>
        <div id="gridContainer">
            <span style="color:${board[0][0].getColor()}">${board[0][0].getName()}</span>
            <span style="color:${board[0][1].getColor()}">${board[0][1].getName()}</span>
            <span style="color:${board[0][2].getColor()};  padding-left:40%; ">${board[0][2].getName()}</span>

            <span style="color:${board[1][0].getColor()}">${board[1][0].getName()}</span>
            <span style="color:${board[1][1].getColor()}">${board[1][1].getName()}</span>
            <span style="color:${board[1][2].getColor()};  padding-left:40%;">${board[1][2].getName()}</span>

            <span style="color:${board[2][0].getColor()}">${board[2][0].getName()}</span>
            <span style="color:${board[2][1].getColor()}">${board[2][1].getName()}</span>
            <span style="color:${board[2][2].getColor()};  padding-left:40%;">${board[2][2].getName()}</span>
        </div>
    </div>


    <div id ="playerArea">
        <span id="discardArea">
            <span id="dicardText">Discard text</span>
        </span>
        <div id="handArea">
            <c:forEach var="row" items="${hand.hand}" varStatus="handLoop">
                <img src="${row.cardImg}" onclick="selectCard(${row.getNumber()},'${row.getSuit()}','${row.cardImg}',${handLoop.index})" id="${handLoop.index}">
            </c:forEach>
        </div>
    </div>


<script type="text/javascript">
 function selectCard(number,suit,cardImg,handIndex) {
    if (document.getElementById(handIndex).classList.contains('selected')) {
        document.getElementById(handIndex).classList.remove('selected');

    } else {
        document.getElementById(handIndex).classList.add('selected');


        fetch ('/selected', {
            method : 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({number:number,suit:suit,handIndex:handIndex}),
        })
        .then(response =>{ return response.json();})
        .then(data =>  {
            let element = document.getElementById(handIndex)
            element.src = data.img;
            element.onclick = function () {selectCard(data.number,data.suit,data.img,handIndex);};

             let discardElement = document.getElementById('discardArea');
             let discardCard = document.createElement("img");
             discardCard.src = cardImg;
             discardCard.classList.add('discardedCard')
             discardCard.setAttribute("style", "float:left");
             discardElement.appendChild(discardCard)
        });
    }

 };
</script>



</body>

</html>