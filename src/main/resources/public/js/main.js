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

function upVoteMessage(elem) {
    var xhttp;  
    if (elem.dataset.id == "") {
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("comment-nav-" + elem.dataset.id).innerHTML = this.responseText;
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

function submitComment(elem) {
    var xhttp;  
    xhttp = new XMLHttpRequest();
    xhttp.open("POST", elem.action, true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            
            let contentContainer = document.createElement("div")
            contentContainer.innerHTML = this.responseText;

            let target; 
            if (elem.dataset.type == "comment") {
                target = document.getElementById("comment-children-" + elem.dataset.id);
            } else {
                target = document.getElementById("comments-parent-" + elem.dataset.id);
            }
            
            target.prepend(contentContainer);

            let form = document.getElementById("comment-form-" + elem.dataset.id);
            form.classList.remove('active');
        }
    };

    var params = "messageContent=" + elem.getElementsByTagName("textarea")[0].value 
                    + "&page=" + elem.getElementsByClassName("paged")[0].value
                    + "&slug=" + elem.getElementsByClassName("user-slug")[0].value;
    xhttp.send(params);
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

function onLoadMoreCommentsLinkClick() {
    document.body.addEventListener('click', event => {
        if (event.target.matches('a.message-action--load-more')) {
            event.preventDefault();
            loadMoreComments(event.target);
        } else {
            return;
        }   
    });
}

function loadMoreComments(elem) {
    var xhttp;  
    if (elem.dataset.id == "") {
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let contentContainer = document.createElement("div")
            contentContainer.classList.add("paged-comments-container")
            contentContainer.innerHTML = this.responseText;
            let target = document.getElementById("comments-parent-" + elem.dataset.id);
            target.prepend(contentContainer);

            elem.dataset.count = parseInt(elem.dataset.count) + 2;
            elem.dataset.paged = parseInt(elem.dataset.paged) + 1;
            if (parseInt(elem.dataset.count) >= parseInt(elem.dataset.max)) {
                elem.classList.add("hidden")
            }
        }
    };
    xhttp.open("GET", elem.href + "/page/" + elem.dataset.paged, true);
    xhttp.send();
}


onLoadMoreCommentsLinkClick();
onVoteLinkClick();
onSkillsFormSubmit();
onCommentFormSubmit();
onReplyButtonClick();