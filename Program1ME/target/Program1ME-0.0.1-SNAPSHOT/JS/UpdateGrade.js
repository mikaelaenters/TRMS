//AJAX Call Steps;

function getForms(){
//Step 1. Create XHR
let xhr = new XMLHttpRequest();
//Step 2. Define function to handle ready state change of response
//ReadyState - represents how far the response has gotten 
//0-Request Initialized
//1-Server Connection Established
//2-Recieved Request
//3-Processing Request
//4-Request Finished & Response Ready
//I am parsing my JSON object and saving it to forms. 
xhr.onreadystatechange = function() {
    
	if(xhr.readyState === 4 && xhr.status === 200) {
        let forms = JSON.parse(xhr.responseText);
        pending(forms);
    }
	
	
}
//Step 3. Open XHR
xhr.open("GET", "UpdateGrade", true);
//Step 4. Send XHR
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
    
    const paragraph = document.getElementById("userFunds");
    	let text = `Available Amount: ${forms[0].availableAmount}
    	Pending Amount: ${forms[0].pendingAmount}
    	Awarded Amount: ${forms[0].awardedAmount}`;
    	
    	paragraph.innerText = text;
}


//Create the response object 

let UpdatedGrade = function(reimbursementId, finalGrade){
	this.reimbursementId = reimbursementId;
	this.finalGrade = finalGrade;	
}

//Second AJAX Call to return the results

function postFinalGrade(){
	//Get form information first
	event.preventDefault();
	
	let reimbursementId = document.getElementById("applicationId").value;
	
	let finalGrade = document.getElementById("finalGradeUpdate").value;
	
	let finalUpdate = new UpdatedGrade(reimbursementId, finalGrade);
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			getForms();
		}
	}
	
	xhr.open("POST", "UpdateGrade", true);
	
	xhr.send(JSON.stringify(finalUpdate));	
}

window.onload = function(){
    getForms();
    document.getElementById("submissionOfGrade").addEventListener("click", postFinalGrade);
}