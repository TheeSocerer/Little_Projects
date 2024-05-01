let score = JSON.parse(localStorage.getItem('score')) || {
    Loss: 0,
    Wins: 0,
    Ties: 0,
}

updateScore();

function playGame(playerMove){
    let result = '';
    const computerChoice = pickComputerMove();

    if( playerMove === "Rock"){
        if( computerChoice === 'Rock'){
            result = 'Tie';
        }else if( computerChoice === 'Scissor'){
            result = 'You Win';   
        }else{
            result = 'You Lose';
        }
        
    }else if( playerMove === "Scissor"){
        if( computerChoice === 'Rock'){
            result = 'You Lose';
        }else if( computerChoice === 'Scissor'){
            result = 'Tie';   
        }else{
            result = 'You Win';
        }
    }else if( playerMove === "Paper"){
        if( computerChoice === 'Rock'){
            result = 'You Win';
        }else if( computerChoice === 'Scissor'){
            result = 'You Lose';   
        }else{
            result = 'Tie';
        }
    }

    if( result === "You Win"){
        score.Wins += 1;
    }else if( result === "You Lose"){
        score.Loss += 1;
    }else if( result === "Tie"){
        score.Ties += 1;
    }

    localStorage.setItem("score",JSON.stringify(score));
    document.querySelector('.js-result').innerHTML = result;
    document.querySelector('.js-moves').innerHTML =
    `You
<img src="images/${playerMove}-emoji.png" class = "move-icon">
<img src="images/${computerChoice}-emoji.png" class = "move-icon">
Computer`;

    updateScore();


}

function pickComputerMove(){
    const randomNumber = Math.random();
    let computerChoice = '';
    if(randomNumber >=0 && randomNumber<1/3){
        computerChoice = 'Rock';
    }else if (randomNumber >=1/3 && randomNumber<2/3){
        computerChoice = 'Paper';
    }else{
        computerChoice = 'Scissor';
    }
    return computerChoice;
}
function updateScore(){
    document.querySelector('.js-score').innerHTML = 
    `Wins: ${score.Wins} Losses: ${score.Loss} Ties: ${score.Ties}`;

}