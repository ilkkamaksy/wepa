function upVoteMessage(elem) {
    var xhttp;  
    if (elem.dataset.id == "") {
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("main-message-feed").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", elem.href, true);
    xhttp.send();
}

function upVoteSkill(elem) {
    var xhttp;  
    if (elem.dataset.id == "") {
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("skills-list").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", elem.href, true);
    xhttp.send();
}

function onVoteLinkClick() {
    document.body.addEventListener('click', event => {
        if (event.target.matches('a.message-action')) {
            event.preventDefault();
            upVoteMessage(event.target);
        } else if (event.target.matches('a.skill-action')) {
            event.preventDefault();
            upVoteSkill(event.target);
        } else {
            return;
        }   
    });
}

function submitSkill(elem) {
    var xhttp;  
    xhttp = new XMLHttpRequest();
    xhttp.open("POST", elem.action, true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("skills-list").innerHTML = this.responseText;
        }
    };
    var params = "skillName=" + document.getElementById("skillName").value;
    xhttp.send(params);
}

function onSkillsFormSubmit() {
    const form = document.getElementById("skills-form")

    if (!form) {
        return;
    }

    form.addEventListener('submit', event => {
        event.preventDefault();
        submitSkill(event.target);
    });
}

function submitComment(elem) {
    var xhttp;  
    xhttp = new XMLHttpRequest();
    xhttp.open("POST", elem.action, true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("main-message-feed").innerHTML = this.responseText;
        }
    };

    var params = "messageContent=" + elem.getElementsByTagName("textarea")[0].value 
                    + "&page=" + elem.getElementsByClassName("paged")[0].value
                    + "&slug=" + elem.getElementsByClassName("user-slug")[0].value;
    xhttp.send(params);
}

function onCommentFormSubmit() {
    document.body.addEventListener('submit', event => {
        if (event.target.matches('form.comments--comment-form')) {
            event.preventDefault();
            submitComment(event.target);
        } else {
            return;
        }   
    });
}

function onReplyButtonClick() {
    document.body.addEventListener('click', event => {
        if (event.target.matches('a.action-reply')) {
            event.preventDefault();
            const targetId = event.target.dataset.form;
            const form = document.getElementById("comment-form-" + targetId);
            form.classList.add('active');
        } else {
            return;
        }   
    });
}

onVoteLinkClick();
onSkillsFormSubmit();
onCommentFormSubmit();
onReplyButtonClick();