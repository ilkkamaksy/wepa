function upVoteMessage(elem) {
    var xhttp;  
    if (elem.dataset.id == "") {
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("upvote-count-" + elem.dataset.id).textContent = parseInt(elem.dataset.count) + 1 + " upvotes";
            elem.parentNode.removeChild(elem);
        }
    };
    xhttp.open("GET", elem.href, true);
    xhttp.send();
}

function onVoteLinkClick() {
    document.querySelectorAll('.message-action').forEach(elem => {
        elem.addEventListener('click', event => {
            event.preventDefault();
            upVoteMessage(elem);
        })
    })
}

onVoteLinkClick();