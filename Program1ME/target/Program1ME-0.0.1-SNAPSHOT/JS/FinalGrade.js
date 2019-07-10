
function getForms(){

let xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
    
	if(xhr.readyState === 4 && xhr.status === 200) {
        let forms = JSON.parse(xhr.responseText);
        pending(forms);
	}
}

xhr.open("GET", "approveFinalGrade", true);

xhr.send();
}

function pending(forms){
	const tableBody = document.getElementById("newForms");
	
	tableBody.innerHTML = "";
	
    for(i = 0; i < forms.length; i++){
        const tableBody = document.getElementById("newForms");
        const newRow = document.createElement("tr");
        //reimbusementId;
        const formIdData = document.createElement("td");
        formIdData.innerText = forms[i].reimbursementId;
        newRow.appendChild(formIdData);
        //fullname;
        const fullNameData = document.createElement("td");
        fullNameData.innerText = forms[i].fullName;
        newRow.appendChild(fullNameData);
        //eventType;
        const eventTypeData = document.createElement("td");
        eventTypeData.innerText = forms[i].eventType;
        newRow.appendChild(eventTypeData);
        //eventDate;
        const eventDateData = document.createElement("td");
        eventDateData.innerText = forms[i].eventDate;
        newRow.appendChild(eventDateData);
        //Cost
        const costData = document.createElement("td");
        costData.innerText = '$' + forms[i].cost;
        newRow.appendChild(costData);
        //description;
        const descriptionData = document.createElement("td");
        descriptionData.innerText = forms[i].description;
        newRow.appendChild(descriptionData);
        //passingGrade;
        const passingGradeData = document.createElement("td");
        passingGradeData.innerText = forms[i].passingGrade;
        newRow.appendChild(passingGradeData);
        //completedGrade;
        const currentGradeData = document.createElement("td");
        currentGradeData.innerText = forms[i].completedGrade;
        newRow.appendChild(currentGradeData);

        tableBody.appendChild(newRow);
    }
}

let FinalDecision = function(reimbursementId, choice){
	this.reimbursementId = reimbursementId;
	this.choice = choice;	
}

//Second AJAX Call to return the results

function postFinalDecision(){
	//Get form information first
	event.preventDefault();
	
	let reimbursementId = document.getElementById("applicationId").value;
	
	let buttonData = document.getElementsByName("radios");
	let choice = null;
	
	for(i = 0; i < buttonData.length; i++){
		if(buttonData[i].checked){
			choice = buttonData[i].value;
			break;
		}
	}
	
	let finalDecision = new FinalDecision(reimbursementId, choice);
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			getForms();
		}
	}
	
	xhr.open("POST", "approveFinalGrade", true);
	

	xhr.send(JSON.stringify(finalDecision));	
}

window.onload = function(){
    getForms();
    document.getElementById("submissionOfForm").addEventListener("click", postFinalDecision);
}