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
xhr.open("GET", "viewPendingForms", true);
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
        //email;
        const emailData = document.createElement("td");
        emailData.innerText = forms[i].email;
        newRow.appendChild(emailData);
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
        //availableAmount;
        const availableAmountData = document.createElement("td");
        availableAmountData.innerText = '$' + forms[i].availableAmount;
        newRow.appendChild(availableAmountData);
        //pendingAmount;
        const pendingAmountData = document.createElement("td");
        pendingAmountData.innerText = '$' + forms[i].pendingAmount;
        newRow.appendChild(pendingAmountData);
        //awardedAmount;
        const awardedAmountData = document.createElement("td");
        awardedAmountData.innerText = '$' + forms[i].awardedAmount;
        newRow.appendChild(awardedAmountData);
        
        tableBody.appendChild(newRow);
    }
}


//Create the response object 

let FormInfo = function(reimbursementId, choice, moreInfo){
	this.reimbursementId = reimbursementId;
	this.choice = choice;
	this.moreInfo = moreInfo;	
}

//Second AJAX Call to return the results

function postFormResponse(){
	//Get form information first
	event.preventDefault();
	
	let reimbursementId = document.getElementById("applicationId").value;

	let buttonData = document.getElementsByName("radios");
	let moreInfo = document.getElementById("requestedInfo").value;
	let choice = null;
	
	for(i = 0; i < buttonData.length; i++){
		if(buttonData[i].checked){
			choice = buttonData[i].value;
			
			break;
		}
	}
	
	let formResponse = new FormInfo(reimbursementId, choice, moreInfo);
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			getForms();
		}
	}
	
	xhr.open("POST", "viewPendingForms", true);
	
	xhr.send(JSON.stringify(formResponse));	
}

window.onload = function(){
    getForms();
    document.getElementById("submissionOfForm").addEventListener("click", postFormResponse);
}